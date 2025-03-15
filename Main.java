import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
 * Universidad del Valle de Guatemala
 * Algoritmos y Estructuras de Datos
 * Ing. Douglas Barrios
 * @author: Marcelo Detlefsen, Jose Rivera, Fabián Prado
 * Creación: 01/03/2025
 * última modificación: 15/03/2025
 * File Name: Main.java
 * Descripción: Clase principal que utiliza el Lexer para:
 * 1. Tokenizar la expresión LISP.
 * 2. Verificar si la expresión está balanceada.
 * 3. Crear un AST a partir de los tokens.
 */

public class Main 
{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder codeBuilder = new StringBuilder();

        System.out.println("\nIngrese la expresión LISP (puede usar varias líneas).");
        System.out.println("Al terminar de ingresarla, presione Enter en una línea vacía para finalizar:\n");

        /**
         * ciclo para ejecutar las expresiones LISP (puede usar varias líneas).
         */
        while (true) {
            String line = scanner.nextLine().trim();
            if (line.isEmpty()) {
                break;
            }
            codeBuilder.append(line).append(" ");
        }

        String fullInput = codeBuilder.toString().trim();
        
        // Verificar si hay contenido en la entrada que no se pudo parsear como expresiones válidas
        Lexer lexerForFullInput = new Lexer();
        if (!lexerForFullInput.isBalanced(fullInput)) {
            System.out.println("\nAdvertencia: La entrada completa contiene paréntesis desbalanceados.");
            System.out.println("Se intentarán procesar las expresiones válidas que se puedan identificar.");
        }
        
        // Divide la entrada en expresiones individuales basadas en paréntesis de nivel superior
        List<String> expressions = splitExpressions(fullInput);
        
        // Verificar si se encontraron expresiones válidas
        if (expressions.isEmpty() && !fullInput.isEmpty()) {
            System.out.println("\nError: No se pudieron identificar expresiones LISP válidas en la entrada.");
            scanner.close();
            return;
        }
        
        // Identificar contenido que no se pudo parsear como expresión válida
        String remainingContent = findInvalidContent(fullInput, expressions);
        if (!remainingContent.trim().isEmpty()) {
            System.out.println("\nLa siguiente entrada no es una expresión LISP válida: " + remainingContent.trim());
        }
        
        // Procesa cada expresión individualmente
        for (String expression : expressions) {
            System.out.println("\nProcesando expresión: " + expression);
            
            // Validar que la expresión contenga paréntesis
            if (!expression.contains("(") || !expression.contains(")")) {
                System.out.println("Error: La expresión '" + expression + "' no es una expresión válida en LISP (falta de paréntesis).");
                continue;
            }

            Lexer lexer = new Lexer();
            
            // Verificar balance de paréntesis para esta expresión específica
            boolean balanced = lexer.isBalanced(expression);
            if (!balanced) {
                System.out.println("Error: La expresión '" + expression + "' no es válida. Tiene paréntesis desbalanceados.");
                continue;
            }
            
            // Tokenizar y parsear si la expresión es válida
            List<Token> tokens = lexer.tokenize(expression);
            
            // Validar que la expresión tenga tokens
            if (tokens.isEmpty()) {
                System.out.println("Error: La expresión '" + expression + "' no es válida. No se encontraron tokens.");
                continue;
            }
            
            // Mostrar la lista de tokens
            System.out.print("Lista de elementos (tokens): ");
            for (int i = 0; i < tokens.size(); i++) {
                System.out.print("[" + tokens.get(i).getValue() + "]");
                if (i < tokens.size() - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println();
            
            // Validar que la expresión sea una expresión LISP válida
            if (!lexer.isValidExpression(tokens)) {
                System.out.println("Error: La expresión '" + expression + "' no es una expresión LISP válida. Debe contener al menos un operador.");
                continue;
            }
            
            // Crear el AST si hay tokens
            try {
                Parser parser = new Parser(tokens);
                List<ASTNode> astList = parser.parse();
                if (astList.isEmpty()) {
                    System.out.println("Error: No se pudo generar un AST para la expresión '" + expression + "'.");
                } else {
                    for (ASTNode ast : astList) {
                        System.out.println("Árbol de sintaxis abstracta (AST): " + ast.toString());
                    }
                }
            } catch (RuntimeException e) {
                System.out.println("Error al parsear la expresión '" + expression + "': " + e.getMessage());
            }
        }
        
        scanner.close();
    }
    
    /**
     * Encuentra el contenido que no pudo ser procesado como expresiones LISP válidas
     * @param fullInput La entrada completa
     * @param validExpressions Lista de expresiones válidas ya identificadas
     * @return El contenido que no forma parte de ninguna expresión válida
     */
    private static String findInvalidContent(String fullInput, List<String> validExpressions) {
        String workingInput = fullInput;
        for (String expr : validExpressions) {
            workingInput = workingInput.replace(expr, "");
        }
        return workingInput;
    }
    
    /**
     * Divide la entrada en expresiones LISP de nivel superior individuales.
     * Cada expresión comienza con '(' y termina con su ')' correspondiente.
     */
    private static List<String> splitExpressions(String input) {
        List<String> result = new ArrayList<>();
        int start = -1;
        int depth = 0;
        
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            
            if (c == '(') {
                if (depth == 0) {
                    start = i;
                }
                depth++;
            } else if (c == ')') {
                depth--;
                if (depth == 0 && start != -1) {
                    result.add(input.substring(start, i + 1));
                    start = -1;
                }
            }
        }
        
        return result;
    }
}

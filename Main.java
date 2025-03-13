import java.util.List;
import java.util.Scanner;

/*
 * Universidad del Valle de Guatemala
 * Algoritmos y Estructuras de Datos
 * Ing. Douglas Barrios
 * @author: Marcelo Detlefsen, Jose Rivera, Fabián Prado
 * Creación: 01/03/2025
 * última modificación: 11/03/2025
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

        String code = codeBuilder.toString().trim();

        // Validar que la expresión contenga paréntesis balanceados
        if (!code.contains("(") || !code.contains(")")) {
            System.out.println("Error: La expresión ingresada no es una expresión válida en LISP (falta de paréntesis).");
            scanner.close();
            return;
        }

        Lexer lexer = new Lexer();

        boolean balanced = lexer.isBalanced(code);
        if (!balanced) {
            System.out.println("La expresión: " + code + " es incorrecta ya que tiene paréntesis desbalanceados.");
            scanner.close();
            return;
        }

        List<Token> tokens = lexer.tokenize(code);

        // Mostrar la lista de tokens en la expresión LISP
        if (tokens.isEmpty()) {
            System.out.println("No se encontraron tokens en la expresión.");
        } else {
            System.out.print("Lista de elementos (tokens): ");
            for (int i = 0; i < tokens.size(); i++) {
                System.out.print("[" + tokens.get(i).getValue() + "]");
                if (i < tokens.size() - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println();
        }

        // Parse the tokens into an AST
        /**
         * crear el arbol AST
         */
        Parser parser = new Parser(tokens);
        ASTNode ast = parser.parse();
        System.out.println("Árbol de sintaxis abstracta (AST): " + ast.toString());

        scanner.close();
    }
}
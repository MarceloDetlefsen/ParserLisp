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

        // Asi se leen multiples lineas de texto
        while (true) {
            String line = scanner.nextLine().trim();
            if (line.isEmpty()) {
                break;
            }
            codeBuilder.append(line).append(" ");
        }

        //Se almacena la expresión en un string
        String code = codeBuilder.toString().trim();

        // Verificación de que la entrada tenga paréntesis
        if (!code.contains("(") || !code.contains(")")) {
            System.out.println("Error: La expresión ingresada no es una expresión válida en LISP (falta de paréntesis).");
            scanner.close();
            return;
        }

        Lexer lexer = new Lexer();

        // Se verifica si la instancia de la expresion sea correcta 
        boolean balanced = lexer.isBalanced(code);
        if (!balanced) {
            System.out.println("La expresión: " + code + " es incorrecta ya que tiene paréntisis desbalanceados.");
            scanner.close();
            return;
        }
        
        // Al estar correcta, tokenizamos la expresion de LISP
        List<Token> tokens = lexer.tokenize(code);

        // Asi se imprime la lista, pero se modificará
        String placeHolder = code.replace("(", "[").replace(")", "]");  
        System.out.println(placeHolder);
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
        scanner.close();

        //Aqui se utilizará el Parser para los tokens y se creará el AST
    }
}

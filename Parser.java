import java.util.List;

/*
 * Universidad del Valle de Guatemala
 * Algoritmos y Estructuras de Datos
 * Ing. Douglas Barrios
 * @author: Marcelo Detlefsen, Jose Rivera, Fabián Prado
 * Creación: 01/03/2025
 * última modificación: 01/03/2025
 * File Name: Parser.java
 * Descripción: Clase que se encarga de analizar la expresión LISP.
 * 
 * Implementación siguiendo las especificaciones proporcionadas en la estructura UML y los archivos .java anteriores.
 * Código generado con la asistencia de DeepSeek.
 */


public class Parser 
{
    private List<Token> tokens;
    private int currentTokenIndex;

    //Constructor
    public Parser(List<Token> tokens) {
        this.tokens = tokens;
        this.currentTokenIndex = 0;
    }

    //Método que analiza la expresión LISP
    public ASTNode parse() {
        return parseExpression();
    }


    /**
     * Método que analiza una expresión LISP.
     * convierte las secuencias de tokens en un arbol de sintaxis abstracta
     * analiza si la expresion en un QUOTE o una operación algebraica 
     * @return node
     */
    private ASTNode parseExpression() {
        consume("(");

        if (peek().getValue().equals("QUOTE")) { //QUOTE en LISP se usa para citar una expresión y evitar que se evalúe.

            consumeAny(); 
            ASTNode quoteNode = new ASTNode("QUOTE");
            StringBuilder sb = new StringBuilder();

            while (!peek().getValue().equals(")")) {
                sb.append(consumeAny().getValue()).append(" ");
            }

            quoteNode.addChild(new ASTNode(sb.toString().trim()));

            consume(")");
            return quoteNode; // Representa una expresión textual
        }
        else { // si no es quote: 

            Token firstToken = consumeAny();
            ASTNode node = new ASTNode(firstToken.getValue());

            while (!peek().getValue().equals(")")) {
                if (peek().getValue().equals("(")) {
                    node.addChild(parseExpression());
                } 
                else {
                    String rawValue = consumeAny().getValue();
                    node.addChild(parseAtom(rawValue));
                }
            }

            consume(")");
            return node; // devuelve el nodo principal
        }
    }

    /**
     * parseAtom(String) intenta convertir el valor en un número (int/double).
     * Si no es número, se queda como string (un símbolo).
     */
    private ASTNode parseAtom(String rawValue) {
        try {
            int intValue = Integer.parseInt(rawValue);
            return new ASTNode(String.valueOf(intValue));
        } catch (NumberFormatException e1) {
            try {
                double doubleValue = Double.parseDouble(rawValue);
                return new ASTNode(String.valueOf(doubleValue));
            } catch (NumberFormatException e2) {
                return new ASTNode(rawValue);
            }
        }
    }

    /**
     * verifica si el token actual coincide con el valor esperado
     * si no coincide, lanza una excepción
     * si coincide avanza al siguiente token y lo devuelve
     * @param expectedValue
     * @return token
     */
    private Token consume(String expectedValue) {
        Token token = tokens.get(currentTokenIndex);
        if (!token.getValue().equals(expectedValue)) {
            throw new RuntimeException("Expected " + expectedValue + " but found " + token.getValue());
        }
        currentTokenIndex++;
        return token;
    }

    /**
     * devuelve el token actual y avanza al siguiente en la lista
     * @return tokens
     */
    private Token consumeAny() {
        return tokens.get(currentTokenIndex++);
    }

    /**
     * devuelve el token actual pero no avanza en la lista
     * @return
     */
    private Token peek() {
        return tokens.get(currentTokenIndex);
    }
}
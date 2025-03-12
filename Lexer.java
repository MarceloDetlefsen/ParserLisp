import java.util.*;
import java.util.regex.*;

/*
 * Universidad del Valle de Guatemala
 * Algoritmos y Estructuras de Datos
 * Ing. Douglas Barrios
 * @author: Marcelo Detlefsen, Jose Rivera, Fabián Prado
 * Creación: 01/03/2025
 * última modificación: 01/03/2025
 * File Name: Lexer.java
 * Descripción: Clase que se encarga de analizar la expresión LISP.
 * 
 * Implementación basada en el diseño del intérprete proporcionado en el diagrama UML.
 * Código original generado con la asistencia de ChatGPT.
 */

public class Lexer 
{
    private List<Token> tokens;

    //Constructor
    public Lexer() {
        this.tokens = new ArrayList<>();
    }

    // Este método permite comparar patrones de texto
    public List<Token> tokenize(String code) {
        tokens.clear();
        Matcher matcher = Pattern.compile("\\(|\\)|[a-zA-Z]+|[0-9]+|[-+*/]").matcher(code);
        while (matcher.find()) {
            tokens.add(new Token(matcher.group()));
        }
        return tokens;
    }

    public List<Token> fixErrors(List<Token> tokens) {
        //Este método se implementará más adelante, para el propósito de esta entrega no es necesario.
        return tokens;
    }

    public List<Token> detectIntent(List<Token> tokens) {
        //Este método se implementará más adelante, para el propósito de esta entrega no es necesario.
        return tokens;
    }

    // Este método permite comprobar que la expresión esté equilibrada
    public boolean isBalanced(String code) {
        int balance = 0;
        for (char c : code.toCharArray()) {
            if (c == '(') balance++;
            else if (c == ')') balance--;
            if (balance < 0) return false;
        }
        return balance == 0; // si al final el balance es cero, los paréntesis están equilibrados
    }

    //Comprobar que la expresión sea válida
    public boolean isValidExpression(List<Token> tokens) {
        boolean hasParentheses = false;
        boolean hasOperators = false;
        String operators = "+-*/";

        for (Token token : tokens) {
            String value = token.getValue();
            if (value.equals("(") || value.equals(")")) {
                hasParentheses = true;
            } else if (operators.contains(value)) {
                hasOperators = true;
            }
        }
        return hasParentheses && hasOperators; //retorna verdadero si hay parentesis y operadores
    }
}

import java.util.ArrayList;
import java.util.List;

/*
 * Universidad del Valle de Guatemala
 * Algoritmos y Estructuras de Datos
 * Ing. Douglas Barrios
 * @author: Marcelo Detlefsen, Jose Rivera, Fabián Prado
 * Creación: 12/03/2025
 * última modificación: 12/03/2025
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

    public Parser(List<Token> tokens) {
        this.tokens = tokens;
        this.currentTokenIndex = 0;
    }

    public List<Token> fixErrors(List<Token> tokens) {
        //Este método se implementará más adelante, para el propósito de esta entrega no es necesario.
        return tokens;
    }

    public List<Token> detectIntent(List<Token> tokens) {
        //Este método se implementará más adelante, para el propósito de esta entrega no es necesario.
        return tokens;
    }

    //Implementación pendiente de ASTNode

     //Este método se implementará más adelante, para el propósito de esta entrega no es necesario.
    public ASTNode optimizeAST(ASTNode ast) {
        return null;
    }

     //Este método se implementará más adelante, para el propósito de esta entrega no es necesario.
    public ASTNode extendSyntax(ASTNode ast) {
        return null;
    }

     /**
      *     Metodo recursivo que Parsear una expresion en Lisp a Python
      *     @return El objeto parseado
     */
    public Object parse() {
        // Revisar que se hayan procesado todos los tokens
        if (currentTokenIndex >= tokens.size()) {
            return null;
        }
        
        Token token = tokens.get(currentTokenIndex);
        currentTokenIndex++;
        String value = token.getValue();
        
        switch (value) {
            case "(":
                List<Object> expression = new ArrayList<>();
                while (currentTokenIndex < tokens.size() && !tokens.get(currentTokenIndex).getValue().equals(")")) {
                    expression.add(parse());
                }
                return expression;

            case "QUOTE":
                Object quotedTokens = parse();
                return quotedTokens;
            
            default:
                // Si el token es un atom, revisar si el token es un numero, si no, retornar su valor como tal
                try {
                    int intValue = Integer.parseInt(value);
                    return intValue;
                } catch (NumberFormatException e) {
                    try {
                        double doubleValue = Double.parseDouble(value);
                        return doubleValue;
                    } catch (NumberFormatException ex) {
                        return value;
                    }
                }
        }
    }
}
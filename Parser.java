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

    //Implementación pendiente de Token
}
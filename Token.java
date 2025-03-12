/*
 * Universidad del Valle de Guatemala
 * Algoritmos y Estructuras de Datos
 * Ing. Douglas Barrios
 * @author: Marcelo Detlefsen, Jose Rivera, Fabián Prado
 * Creación: 01/03/2025
 * última modificación: 01/03/2025
 * File Name: Token.java
 * Descripción: Clase que se encarga de almacenar los tokens de la expresión LISP.
 */

//Clase Token 
public class Token 
{
    private String value; 

    public Token(String value) {
        this.value = value; 
    }

    public String getValue() {
        return value; //Devuelve el valor del token
    }

    @Override
    public String toString() {
        return value;
    }
}

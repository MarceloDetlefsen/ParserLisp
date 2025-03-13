import java.util.ArrayList;
import java.util.List;

/*
 * Universidad del Valle de Guatemala
 * Algoritmos y Estructuras de Datos
 * Ing. Douglas Barrios
 * @author: Marcelo Detlefsen, Jose Rivera, Fabián Prado
 * Creación: 11/03/2025
 * última modificación: 11/03/2025
 * File Name: ASTNode.java
 * Descripción: Clase que se encarga de almacenar los nodos del árbol de sintaxis abstracta.
 */

public class ASTNode 
{
    private String value;
    private List<ASTNode> children;

    public ASTNode(String value) {
        this.value = value;
        this.children = new ArrayList<>();
    }

    public void addChild(ASTNode child) {
        this.children.add(child);
    }

    public String getValue() {
        return value;
    }

    public List<ASTNode> getChildren() {
        return children;
    }

    @Override
    public String toString() {
        if (children.isEmpty()) {
            return value;
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("[").append(value);
            for (ASTNode child : children) {
                sb.append(" ").append(child.toString());
            }
            sb.append("]");
            return sb.toString();
        }
    }
}
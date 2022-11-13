package ooga.view.entity_types;

import javafx.scene.Node;
import ooga.model.entities.Entity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * This is a list of JavaFX nodes that will be visualized by the View.
 */
public class NodeContainer implements Iterable<Node> {
    private List<Node> entities;

    public NodeContainer(){
        entities = new ArrayList<>();
    }

    @Override
    public Iterator<Node> iterator() {
        return (Iterator<Node>) entities;
    }

    public void addNode(Node node){
        entities.add(node);
    }
}

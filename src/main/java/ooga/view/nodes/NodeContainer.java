package ooga.view.nodes;

import javafx.scene.Node;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * This is a list of JavaFX nodes that will be visualized by the View.
 */
public class NodeContainer implements Iterable<Node> {
    private List<ScrollingNode> entities;

    public NodeContainer(){
        entities = new ArrayList<>();
    }

    @Override
    public Iterator<Node> iterator() {
        return (Iterator<Node>) entities;
    }

    public void addNode(ScrollingNode node){
        entities.add(node);
    }

    public int size(){
        return entities.size();
    }
}

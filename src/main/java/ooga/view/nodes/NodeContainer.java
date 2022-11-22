package ooga.view.nodes;

import javafx.scene.Node;
import ooga.Main;

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

    public void updateCameraPosition(double cameraX, double cameraY){
        for (ScrollingNode a : entities) {
            a.updateCameraX(cameraX);
            a.updateCameraY(cameraY);
        }
    }


    public ScrollingNode getMainCharacter(){
        return null;
    }

}

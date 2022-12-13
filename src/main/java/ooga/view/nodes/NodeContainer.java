package ooga.view.nodes;

import javafx.scene.Node;
import ooga.Main;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * This is a list of JavaFX nodes that will be visualized by the View.
 */
public class NodeContainer implements Iterable<ScrollingNode> {
    private List<ScrollingNode> entities;
    private ScrollingNode mainCharacterNode;

    public NodeContainer(){
        entities = new ArrayList<>();
        mainCharacterNode = null;
    }

    @Override
    public Iterator<ScrollingNode> iterator() {
        return entities.iterator();
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

    public void remove(ScrollingNode node){
        entities.remove(node);
        if(node.equals(mainCharacterNode)){
            mainCharacterNode = null;
        }
    }

    public void setMainCharacter(ScrollingNode node){
        mainCharacterNode = node;
    }

    public ScrollingNode getMainCharacter(){
        return mainCharacterNode;
    }

}

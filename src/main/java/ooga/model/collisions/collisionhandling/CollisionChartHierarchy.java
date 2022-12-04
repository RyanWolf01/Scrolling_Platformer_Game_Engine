package ooga.model.collisions.collisionhandling;

import java.util.ArrayList;
import java.util.Collection;

public class CollisionChartHierarchy {

  private class TreeNode {
    private Collection<TreeNode> myChildren;
    private TreeNode myParent;

    private TreeNode(TreeNode parent) {
      myParent = parent;
      myChildren = new ArrayList<>();
    }

    private void addChild(TreeNode tr) {
      myChildren.add(tr);
    }
  }
}

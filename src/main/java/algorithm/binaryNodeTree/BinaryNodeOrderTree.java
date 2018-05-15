package algorithm.binaryNodeTree;

import java.util.List;

public class BinaryNodeOrderTree {

    public BinaryNode createOrderTree(List<Integer> valueList) {
        BinaryNode root = new BinaryNode();
        for (Integer value : valueList) {
            root = addTreeNode(root, value);
        }
        return root;
    }

    private BinaryNode addTreeNode(BinaryNode treeNode, Integer value) {
        if (value != null) {
            if (treeNode == null) {
                treeNode = new BinaryNode();
                treeNode.setValue(value);
            } else {
                BinaryNode childNode = new BinaryNode();
                childNode.setValue(value);
                Integer rootValue = treeNode.getValue();
                if (value.compareTo(rootValue) <= 0) {
                    //值小于根节点则插入到左子树
                    treeNode.setLeftNode(childNode);
                }else {
                    treeNode.setRightNode(childNode);
                }
            }
        }
        return addTreeNode(treeNode,value);
    }


}

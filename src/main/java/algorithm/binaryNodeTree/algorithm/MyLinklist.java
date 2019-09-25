package algorithm.binaryNodeTree.algorithm;

import java.util.LinkedList;
import java.util.List;

public class MyLinklist {

    public static void main(String[] args) {
        Node first = new Node("");
        initNodeList(first);

        Node target = getMidNodeBasic(first);
        System.out.println(target.getValue());

        initNodeList(first);
        target = getMidNodeDouble(first);
        System.out.println(target.getValue());
    }

    /**
     * 偶数个数字的 返回中间较大的数字
     */
    private static Node getMidNodeDouble(Node first) {
        Node fastNode = first;
        Node slowNode = first;
        while (true) {
            if (fastNode.getNextNode() != null) {
                fastNode = fastNode.getNextNode();
                if (fastNode.getNextNode() == null) {
                    break;
                }
                fastNode = fastNode.getNextNode();
                slowNode = slowNode.getNextNode();
            } else {
                break;
            }
        }
        return slowNode;
    }


    private static Node getMidNodeBasic(Node first) {
        int i = 0;
        // 找到first 的中间node
        Node target = first;
        while (true) {
            target = target.getNextNode();
            if (target != null) {
                i++;
            } else {
                break;
            }
        }

        if (i % 2 == 0) {
            i = i / 2;
        } else {
            i = ((i - 1) / 2);
        }

        target = first;
        int j = 0;
        while (true) {
            if (target.getNextNode() != null) {
                if (j == i) {
                    break;
                }
                j++;
                target = target.getNextNode();
            } else {
                break;
            }
        }
        return target;
    }

    private static void initNodeList(Node first) {
        first.setPreNode(null);

//        first.setNextNode(new Node("the sec").setNextNode(new Node("the third").setNextNode(new Node("the forth").setNextNode(new Node("the fifth")))));
        first.setNextNode(new Node("the sec").setNextNode(new Node("the third")));

        first.setValue("the first");
    }


}

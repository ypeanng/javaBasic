package algorithm.binaryNodeTree.algorithm;

import lombok.Data;

@Data
public class Node<T> {

    public Node(T value){
        this.value = value;
    }
    private Node<T> preNode;
    private Node<T> nextNode;

    Node setNextNode(Node<T> node){
        this.nextNode = node;
        return this;
    }

    private T value;
}

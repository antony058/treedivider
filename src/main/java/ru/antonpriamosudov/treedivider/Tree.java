package ru.antonpriamosudov.treedivider;

public class Tree<T> {
    private Node<T> rootNode;

    public Tree() {

    }

    public Tree(Node<T> rootNode) {
        this.rootNode = rootNode;
    }

    public Node<T> getRootNode() {
        return rootNode;
    }

    public void setRootNode(Node<T> rootNode) {
        this.rootNode = rootNode;
    }
}

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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + rootNode.hashCode();

        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (this.getClass() != obj.getClass())
            return false;

        Tree<T> tree = (Tree<T>) obj;
        if (rootNode != ((Tree<T>) obj).rootNode)
            return false;

        return true;
    }
}

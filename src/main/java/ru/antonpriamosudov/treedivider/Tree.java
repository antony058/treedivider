package ru.antonpriamosudov.treedivider;

import ru.antonpriamosudov.treedivider.exception.NotValidTreeSize;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    public Set<Tree<Object>> divide(final int maxSize) {
        final Set<Tree<Object>> treeSet = new HashSet<Tree<Object>>();

        countSubTreeSize((Node<Object>) rootNode, 0, maxSize);
        subTree((Node<Object>) rootNode, 0, maxSize, new Node<Object>(), treeSet);

        return treeSet;
    }

    private void subTree(Node<Object> root, int currentSize, final int maxSize, Node<Object> newNode, Set<Tree<Object>> treeSet) {
        if (currentSize + root.getSubTreeSize() > maxSize) {
            currentSize += root.getWeight();
            newNode.setWeight(root.getWeight());

            Node<Object> upLevelNode = new Node<Object>(newNode);
            int upLevelCurrentSize = currentSize;

            for (Node<Object> node: root.getChildNodesList()) {
                if (currentSize + node.getSubTreeSize() <= maxSize) {
                    currentSize += node.getSubTreeSize();

                    newNode.addChildNode(node);
                } else {
                    treeSet.add(new Tree<Object>(newNode));

                    Node<Object> copyNode = new Node<Object>(upLevelNode);
                    copyNode.addChildNode(new Node<Object>());
                    subTree(node, upLevelCurrentSize, maxSize, copyNode.getChildNodesList().get(0), treeSet);
                }
            }
        } else {
            newNode.copySubTree(root);
            treeSet.add(new Tree<Object>(newNode));
        }
    }

    private int countSubTreeSize(Node<Object> root, int currentSize, final int maxSize) {
        currentSize += root.getWeight();
        if (currentSize > maxSize)
            throw new NotValidTreeSize();

        root.setSubTreeSize(root.getWeight());

        if (root.getChildNodesAmount() != 0) {
            List<Node<Object>> nodes = root.getChildNodesList();

            for (int i=0;i<nodes.size();i++) {
                root.setSubTreeSize(countSubTreeSize(nodes.get(i), currentSize, maxSize) + root.getSubTreeSize());
            }
        }

        return root.getSubTreeSize();
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

        if (!rootNode.equals(tree.getRootNode())) {
            return false;
        }

        return true;
    }
}

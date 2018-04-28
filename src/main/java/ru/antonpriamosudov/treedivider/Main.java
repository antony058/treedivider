package ru.antonpriamosudov.treedivider;

import java.util.Set;

public class Main {

    public static void main(String[] args) {
        Tree<Object> tree = new Tree<Object>(createTree());
        Set<Tree<Object>> treeSet = tree.divide(126);
        System.out.println(treeSet);
    }

    public static Node<Object> createTree() {
        Node<Object> root = new Node<Object>(10, null);

        root.addChildNode(new Node<Object>(1, null));
        root.addChildNode(new Node<Object>(4, null));
        root.addChildNode(new Node<Object>(11, null));
        root.addChildNode(new Node<Object>(99, null));

        Node<Object> node = root.getChildNodesList().get(1);
        node.addChildNode(new Node<Object>(100, null));
        node.addChildNode(new Node<Object>(33, null));
        node.addChildNode(new Node<Object>(112, null));

        return root;
    }

    public static Node<Object> createTree2() {
        Node<Object> root = new Node<Object>(5, null);
        root.addChildNode(new Node<Object>(3, null));
        root.addChildNode(new Node<Object>(4, null));
        root.addChildNode(new Node<Object>(8, null));

        root.getChildNodesList().get(1).addChildNode(new Node<Object>(1, null));
        root.getChildNodesList().get(1).addChildNode(new Node<Object>(2, null));

        return root;
    }
}

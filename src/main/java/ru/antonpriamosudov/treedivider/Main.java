package ru.antonpriamosudov.treedivider;

import ru.antonpriamosudov.treedivider.utils.TreeDivider;

import java.util.ArrayList;
import java.util.List;

public class Main {

    private static List<Integer> list = new ArrayList<Integer>();

    public static void main(String[] args) {
        TreeDivider.divide(new Tree<Integer>(createTree()), 126);
        treeSize(createTree(), 0);
        System.out.println(list);
    }

    public static Node<Integer> createTree() {
        Node<Integer> root = new Node<Integer>(10);
        root.addChildNodes(1, 4, 11, 99);

        Node<Integer> node = root.getChildNodesList().get(1);
        node.addChildNodes(100, 33, 112);

        return root;
    }

    public static void treeSize(Node<Integer> root, int size) {
        size += root.getData();
        if (root.getChildNodesAmount() == 0) {
            list.add(size);
        }

        for (Node<Integer> node: root.getChildNodesList()) {
            treeSize(node, size);
        }
    }

    public static Node<Integer> moveToParentNode(Node<Integer> node) {
        while (node.getParentNode() != null) {
            node = node.getParentNode();
        }

        return node;
    }
}

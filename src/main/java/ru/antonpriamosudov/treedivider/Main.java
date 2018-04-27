package ru.antonpriamosudov.treedivider;

import ru.antonpriamosudov.treedivider.utils.TreeDivider;

import java.util.ArrayList;
import java.util.List;

public class Main {

    private static List<Integer> list = new ArrayList<Integer>();

    public static void main(String[] args) {
        TreeDivider.divide(new Tree<Integer>(createTree()), 126);
    }

    public static Node<Integer> createTree() {
        Node<Integer> root = new Node<Integer>(10);
        root.addChildNodes(1, 4, 11, 99);

        Node<Integer> node = root.getChildNodesList().get(1);
        node.addChildNodes(100, 33, 112);

        return root;
    }

    public static Node<Integer> createTree2() {
        Node<Integer> root = new Node<Integer>(5);
        root.addChildNodes(3, 4, 8);

        root.getChildNodesList().get(1).addChildNodes(1, 2);

        return root;
    }
}

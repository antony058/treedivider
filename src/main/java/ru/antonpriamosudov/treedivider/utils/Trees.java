package ru.antonpriamosudov.treedivider.utils;

import ru.antonpriamosudov.treedivider.Node;

public class Trees {

    public static int countTreeSize(Node<Integer> root) {
        if (root.getChildNodesAmount() == 0)
            return root.getData();

        int size = root.getData();
        for (Node<Integer> node: root.getChildNodesList())
            size += countTreeSize(node);

        return size;
    }
}

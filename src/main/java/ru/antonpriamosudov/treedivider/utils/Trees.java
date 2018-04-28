package ru.antonpriamosudov.treedivider.utils;

import ru.antonpriamosudov.treedivider.Node;

public class Trees {

    public static int countTreeSize(Node<Object> root) {
        if (root.getChildNodesAmount() == 0)
            return root.getWeight();

        int size = root.getWeight();
        for (Node<Object> node: root.getChildNodesList())
            size += countTreeSize(node);

        return size;
    }
}

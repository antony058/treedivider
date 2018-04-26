package ru.antonpriamosudov.treedivider.utils;

import ru.antonpriamosudov.treedivider.Node;
import ru.antonpriamosudov.treedivider.Tree;
import ru.antonpriamosudov.treedivider.exception.NotValidTreeSize;

import java.util.ArrayList;
import java.util.List;

public class TreeDivider {
    private static List<Tree<Integer>> treeList = new ArrayList<Tree<Integer>>();

    public static void divide(Tree<Integer> tree, final int maxSize) {
        countSubTreeSize(tree.getRootNode(), 0, maxSize);
        Node<Integer> root = tree.getRootNode();
        subTree(root, 0, maxSize, new Node<Integer>());
        System.out.println(treeList);
    }

    private static void subTree(Node<Integer> root, int currentSize, final int maxSize, Node<Integer> newNode) {
        if (currentSize + root.getSubTreeSize() > maxSize) {
            newNode.setData(root.getData());

            Node<Integer> upLevelNode = new Node<Integer>(newNode);
            int upLevelCurrentSize = currentSize;

            for (Node<Integer> node: root.getChildNodesList()) {
                if (currentSize + node.getSubTreeSize() <= maxSize) {
                    currentSize += node.getSubTreeSize();

                    newNode.addChildNode(node);
                } else {
                    treeList.add(new Tree<Integer>(newNode));

                    upLevelNode.addChildNode(new Node<Integer>());
                    subTree(node, upLevelCurrentSize, maxSize, upLevelNode.getChildNodesList().get(0));
                }
            }
        } else {
            treeList.add(new Tree<Integer>(root));
        }
    }

    /*private static void subTree(Node<Integer> root, int currentSize, final int maxSize, Node<Integer> newNode) {
        currentSize += root.getData();
        if (currentSize > maxSize) {
            throw new NotValidTreeSize();
        }

        newNode.setData(root.getData());

        if (root.getChildNodesAmount() != 0) {
            List<Node<Integer>> nodes = root.getChildNodesList();
            for (int i=0;i<nodes.size();i++) {
                Node<Integer> node = new Node<Integer>(newNode);
                node.addChildNode(new Node<Integer>());
                subTree(nodes.get(i), currentSize, maxSize, node.getChildNodesList().get(0));
            }
        } else {
            newNode = moveNodeToRoot(newNode);
            treeList.add(new Tree<Integer>(newNode));
        }
    }*/

    private static int countSubTreeSize(Node<Integer> root, int currentSize, final int maxSize) {
        currentSize += root.getData();
        if (currentSize > maxSize)
            throw new NotValidTreeSize();

        root.setSubTreeSize(root.getData());

        if (root.getChildNodesAmount() != 0) {
            List<Node<Integer>> nodes = root.getChildNodesList();

            for (int i=0;i<nodes.size();i++) {
                root.setSubTreeSize(countSubTreeSize(nodes.get(i), currentSize, maxSize) + root.getSubTreeSize());
            }
        }

        return root.getSubTreeSize();
    }


    private static Node<Integer> moveNodeToRoot(Node<Integer> node) {
        while (node.getParentNode() != null)
            node = node.getParentNode();

        return node;
    }
}

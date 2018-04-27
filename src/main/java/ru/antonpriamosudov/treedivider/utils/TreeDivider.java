package ru.antonpriamosudov.treedivider.utils;

import ru.antonpriamosudov.treedivider.Node;
import ru.antonpriamosudov.treedivider.Tree;
import ru.antonpriamosudov.treedivider.exception.NotValidTreeSize;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TreeDivider {
    private static Set<Tree<Integer>> treeSet = new HashSet<Tree<Integer>>();

    public static Set<Tree<Integer>> divide(Tree<Integer> tree, final int maxSize) {
        treeSet = new HashSet<Tree<Integer>>();

        countSubTreeSize(tree.getRootNode(), 0, maxSize);
        subTree(tree.getRootNode(), 0, maxSize, new Node<Integer>());
        System.out.println(treeSet);

        return treeSet;
    }

    private static void subTree(Node<Integer> root, int currentSize, final int maxSize, Node<Integer> newNode) {
        if (currentSize + root.getSubTreeSize() > maxSize) {
            currentSize += root.getData();
            newNode.setData(root.getData());

            Node<Integer> upLevelNode = new Node<Integer>(newNode);
            int upLevelCurrentSize = currentSize;

            for (Node<Integer> node: root.getChildNodesList()) {
                if (currentSize + node.getSubTreeSize() <= maxSize) {
                    currentSize += node.getSubTreeSize();

                    newNode.addChildNode(node);
                } else {
                    treeSet.add(new Tree<Integer>(newNode));

                    Node<Integer> copyNode = new Node<Integer>(upLevelNode);
                    copyNode.addChildNode(new Node<Integer>());
                    subTree(node, upLevelCurrentSize, maxSize, copyNode.getChildNodesList().get(0));
                }
            }
        } else {
            newNode.copySubTree(root);
            treeSet.add(new Tree<Integer>(newNode));
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
}

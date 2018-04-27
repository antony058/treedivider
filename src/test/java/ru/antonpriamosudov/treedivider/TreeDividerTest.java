package ru.antonpriamosudov.treedivider;

import org.junit.Test;
import ru.antonpriamosudov.treedivider.exception.NotValidTreeSize;
import ru.antonpriamosudov.treedivider.utils.TreeDivider;

public class TreeDividerTest {

    @Test(expected = NotValidTreeSize.class)
    public void treeDivideNotValidTreeSizeException() {
        Node<Integer> root = createTree();

        TreeDivider.divide(new Tree<Integer>(root), 14);
    }

    @Test
    public void treeDivideSuccessTestSingleTreeAsResult() {
        Node<Integer> root = createTree();

        TreeDivider.divide(new Tree<Integer>(root), 100);
    }

    @Test
    public void treeDivideSuccessTestMultipleTreesAsResult() {
        Node<Integer> root = createTree();

        TreeDivider.divide(new Tree<Integer>(root), 15);
    }

    private Node<Integer> createTree() {
        Node<Integer> root = new Node<Integer>(5);
        root.addChildNodes(1, 2, 3);

        root.getChildNodesList().get(1).addChildNodes(7, 8);
        // Вес самое тяжелой ветки - 15 (5 -> 2 -> 8)

        return root;
    }
}

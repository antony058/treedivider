package ru.antonpriamosudov.treedivider;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.antonpriamosudov.treedivider.exception.NotValidTreeSize;
import ru.antonpriamosudov.treedivider.utils.TreeDivider;
import ru.antonpriamosudov.treedivider.utils.Trees;

import java.util.Iterator;
import java.util.Set;

public class TreeDividerTest {
    private Node<Integer> root;
    private Node<Integer> root2;

    @Before
    public void createTree() {
        root = new Node<Integer>(5);
        root.addChildNodes(1, 2, 3);

        root.getChildNodesList().get(1).addChildNodes(7, 8);
        // Вес самое тяжелой ветки - 15 (5 -> 2 -> 8)
    }

    @Before
    public void createTree2() {
        root2 = new Node<Integer>(5);
        root2.addChildNodes(1, 2, 3);

        root2.getChildNodesList().get(1).addChildNodes(7, 8);
        // Вес самое тяжелой ветки - 15 (5 -> 2 -> 8)
    }

    @Test(expected = NotValidTreeSize.class)
    public void treeDivideNotValidTreeSizeException() {
        TreeDivider.divide(new Tree<Integer>(root), 14);
    }

    @Test
    public void treeDivideSuccessTestSingleTreeAsResult() {
        Set<Tree<Integer>> trees = TreeDivider.divide(new Tree<Integer>(root), 100);
        Assert.assertEquals(trees.size(), 1);
    }

    @Test
    public void treeDivideSuccessTestMultipleTreesAsResult() {
        Set<Tree<Integer>> trees = TreeDivider.divide(new Tree<Integer>(root), 15);
        Assert.assertTrue(trees.size() > 1);
    }

    @Test
    public void treeDivideSuccessCheckAllSubTreesForSize() {
        final int maxSize = 15;
        Set<Tree<Integer>> trees = TreeDivider.divide(new Tree<Integer>(root), maxSize);
        for (Tree<Integer> tree: trees) {
            Assert.assertTrue(Trees.countTreeSize(tree.getRootNode()) <= maxSize);
        }
    }

    @Test
    public void treeDivideSuccessCompareResultTreesSets() {
        final int maxSize = 15;
        Set<Tree<Integer>> trees1 = TreeDivider.divide(new Tree<Integer>(root), maxSize);
        Set<Tree<Integer>> trees2 = TreeDivider.divide(new Tree<Integer>(root2), maxSize);

        Assert.assertEquals(trees1.size(), trees2.size());

        Iterator<Tree<Integer>> iterator1 = trees1.iterator();
        Iterator<Tree<Integer>> iterator2 = trees2.iterator();

        while (iterator1.hasNext() && iterator2.hasNext()) {
            Assert.assertEquals(iterator1.next(), iterator2.next());
        }
    }
}

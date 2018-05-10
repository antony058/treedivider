package ru.antonpriamosudov.treedivider;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.antonpriamosudov.treedivider.exception.NotValidTreeSize;
import ru.antonpriamosudov.treedivider.utils.Trees;

import java.util.Iterator;
import java.util.Set;

public class TreeDividerTest {
    private Node<Object> root;
    private Node<Object> root2;

    @Before
    public void createTree() {
        root = new Node<Object>(5, null);
        root.addChildNode(new Node<Object>(1, null));
        root.addChildNode(new Node<Object>(2, null));
        root.addChildNode(new Node<Object>(3, null));

        root.getChildNodesList().get(1).addChildNode(new Node<Object>(7, null));
        root.getChildNodesList().get(1).addChildNode(new Node<Object>(8, null));
        // Вес самое тяжелой ветки - 15 (5 -> 2 -> 8)
    }

    @Before
    public void createTree2() {
        root2 = new Node<Object>(5, null);
        root2.addChildNode(new Node<Object>(1, null));
        root2.addChildNode(new Node<Object>(2, null));
        root2.addChildNode(new Node<Object>(3, null));

        Node<Object> node = root2.getChildNodesList().get(1);

        node.addChildNode(new Node<Object>(7, null));
        node.addChildNode(new Node<Object>(8, null));
        node.addChildNode(new Node<Object>(5, null));
        // Вес самое тяжелой ветки - 15 (5 -> 2 -> 8)
    }

    @Test(expected = NotValidTreeSize.class)
    public void treeDivideNotValidTreeSizeException() {
        Tree<Object> tree = new Tree<Object>(root);
        Set<Tree<Object>> treeSet = tree.divide(14);
    }

    @Test
    public void treeDivideSuccessTestSingleTreeAsResult() {
        Tree<Object> tree = new Tree<Object>(root);
        Set<Tree<Object>> treeSet = tree.divide(100);

        Assert.assertEquals(treeSet.size(), 1);
    }

    @Test
    public void treeDivideSuccessTestMultipleTreesAsResult() {
        Tree<Object> tree = new Tree<Object>(root);
        Set<Tree<Object>> treeSet = tree.divide(15);

        Assert.assertEquals(3, treeSet.size());
    }

    @Test
    public void treeDivideSuccessCheckAllSubTreesForSize() {
        final int maxSize = 15;
        Tree<Object> tree = new Tree<Object>(root);
        Set<Tree<Object>> treeSet = tree.divide(15);

        for (Tree<Object> subTree: treeSet) {
            Assert.assertTrue(Trees.countTreeSize(subTree.getRootNode()) <= maxSize);
        }
    }

    @Test
    public void treeDivideSuccessCompareResultTreesSets() {
        final int maxSize = 15;

        Tree<Object> tree1 = new Tree<Object>(root);
        Set<Tree<Object>> trees1 = tree1.divide(maxSize);

        Tree<Object> tree2 = new Tree<Object>(root2);
        Set<Tree<Object>> trees2 = tree2.divide(maxSize);

        Assert.assertEquals(trees1.size(), trees2.size());

        Iterator<Tree<Object>> iterator1 = trees1.iterator();
        Iterator<Tree<Object>> iterator2 = trees2.iterator();

        while (iterator1.hasNext() && iterator2.hasNext()) {
            Assert.assertEquals(iterator1.next(), iterator2.next());
        }
    }

    @Test
    public void treeDivideFewSameDataTest() {
        Tree<Object> tree = new Tree<Object>(root2);
        Set<Tree<Object>> treeSet = tree.divide(15);

        Assert.assertEquals(treeSet.size(), 4);
    }
}

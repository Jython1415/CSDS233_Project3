import java.lang.reflect.Method;

import org.junit.Assert;
import org.junit.Test;

/**
 * Tester class for BinarySearchTree
 * @author Joshua Shew
 */
public class BinarySearchTreeTester {
    /**
     * Unit tests for insert method
     */
    @Test
    public void testInsert() {
        String insertError = "The value was not inserted correctly";

        // empty tree
        BinarySearchTree<Integer, Integer> tree1 = new BinarySearchTree<Integer, Integer>();
        insert(tree1, 1);
        Assert.assertEquals(insertError, "1", preOrderString(tree1));

        // tree with only the root (left and right side)
        tree1 = newTree();
        insert(tree1, 1);
        insert(tree1, 0);
        Assert.assertEquals(insertError, "1 (0, )", preOrderString(tree1));
        tree1 = newTree();
        insert(tree1, 1);
        insert(tree1, 2);
        Assert.assertEquals(insertError, "1 (, 2)", preOrderString(tree1));

        // new value goes on far left
        tree1 = newTree();
        Assert.assertEquals(insertError, "", preOrderString(tree1));
        insert(tree1, 5, 2, 3, 7, 6, 8);
        Assert.assertEquals(insertError, "5 (2 (, 3), 7 (6, 8))", preOrderString(tree1));
        insert(tree1, 0);
        Assert.assertEquals(insertError, "5 (2 (0, 3), 7 (6, 8))", preOrderString(tree1));

        // new value goes on far right
        insert(tree1, 9);
        Assert.assertEquals(insertError, "5 (2 (0, 3), 7 (6, 8 (, 9)))", preOrderString(tree1));

        // new value goes in the middle
        insert(tree1, 4);
        Assert.assertEquals(insertError, "5 (2 (0, 3 (, 4)), 7 (6, 8 (, 9)))", preOrderString(tree1));

        // new value is a repeated value
        tree1 = newTree();
        insert(tree1, 2, 1, 3);
        Assert.assertEquals(insertError, "2 (1, 3)", preOrderString(tree1));
        insert(tree1, 3);
        Assert.assertEquals(insertError, "2 (1, 3 (, 3))", preOrderString(tree1));
        insert(tree1, 1);
        Assert.assertEquals(insertError, "2 (1 (, 1), 3 (, 3))", preOrderString(tree1));
        insert(tree1, 1);
        Assert.assertEquals(insertError, "2 (1 (, 1 (, 1)), 3 (, 3))", preOrderString(tree1));
    }

    /**
     * Unit tests for search method
     */
    @Test
    public void testSearch() {

    }

    /**
     * Unit tests for delete method
     */
    @Test
    public void testDelete() {

    }

    /**
     * Unit tests for inorderRec method
     */
    @Test
    public void testInorderRec() {

    }

    /**
     * Unit tests for kthSmallest method
     */
    @Test
    public void kthSmallest() {

    }

    public String preOrderString(BinarySearchTree<?, ?> tree) {
        Class<?> treeClass = tree.getClass();
        try {
            Method method = treeClass.getDeclaredMethod("preOrderToString");
            method.setAccessible(true);
            return (String)method.invoke(tree, (Object[])null);
        }
        catch (Exception e) {
            throw new NullPointerException("Could not call the method via reflection");
        }
    }

    public BinarySearchTree<Integer, Integer> insert(BinarySearchTree<Integer, Integer> tree, int... values) {
        for (int i : values)
            tree.insert(i, i);

        return tree;
    }

    public BinarySearchTree<Integer, Integer> newTree() {
        return new BinarySearchTree<Integer, Integer>();
    }
}

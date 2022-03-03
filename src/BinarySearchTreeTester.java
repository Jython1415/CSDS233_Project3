import java.lang.reflect.Method;
import java.util.NoSuchElementException;
import org.junit.Assert;
import org.junit.Test;

/**
 * Tester class for BinarySearchTree
 * @author Joshua Shew
 */
public class BinarySearchTreeTester {
    private BinarySearchTreeTester() {
        // does nothing
    }

    /**
     * Unit tests for insert method
     */
    @Test
    public void testInsert() {
        String insertError = "The value was not inserted correctly";

        // empty tree
        BinarySearchTree<Integer, Integer> tree1 = newTree();
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
        String exceptionExpected = "The method should have thrown an exception but it did not";
        String wrongException = "The method threw the wrong exception: ";
        String badException = "The method should not have thrown an exception: ";
        String wrongValue = "The method returned the wrong value";

        // empty tree
        BinarySearchTree<Integer, Integer> tree1 = newTree();
        try {
            tree1.search(1);
            Assert.fail(exceptionExpected);
        }
        catch (NoSuchElementException e) {

        }
        catch (Exception e) {
            Assert.fail(wrongException + e.toString());
        }

        // root node (match and not a match)
        insert(tree1, 2);
        try {
            Assert.assertEquals(wrongValue, (Integer)2, tree1.search(2));
        }
        catch (Exception e) {
            Assert.fail(badException + e.toString());
        }
        try {
            tree1.search(1);
            Assert.fail(exceptionExpected);
        }
        catch (NoSuchElementException e) {

        }
        catch (Exception e) {
            Assert.fail(wrongException + e.toString());
        }

        // search for the smallest element (match and not a match)
        tree1 = newTree();
        insert(tree1, 5, 2, 7, 1, 3, 6, 8);
        try {
            Assert.assertEquals(wrongValue, (Integer)1, tree1.search(1));
        }
        catch (Exception e) {
            Assert.fail(badException + e.toString());
        }
        try {
            tree1.search(0);
            Assert.fail(exceptionExpected);
        }
        catch (NoSuchElementException e) {

        }
        catch (Exception e) {
            Assert.fail(wrongException + e.toString());
        }

        // search for the largest element (match and not a match)
        try {
            Assert.assertEquals(wrongValue, (Integer)8, tree1.search(8));
        }
        catch (Exception e) {
            Assert.fail(badException + e.toString());
        }
        try {
            tree1.search(9);
            Assert.fail(exceptionExpected);
        }
        catch (NoSuchElementException e) {

        }
        catch (Exception e) {
            Assert.fail(wrongException + e.toString());
        }

        // search for a middle element (match and not a match)
        try {
            Assert.assertEquals(wrongValue, (Integer)6, tree1.search(6));
        }
        catch (Exception e) {
            Assert.fail(badException + e.toString());
        }
        try {
            tree1.search(4);
            Assert.fail(exceptionExpected);
        }
        catch (NoSuchElementException e) {

        }
        catch (Exception e) {
            Assert.fail(wrongException + e.toString());
        }

        // search for a duplicate element
        insert(tree1, 4, 4);
        try {
            Assert.assertEquals(wrongValue, (Integer)4, tree1.search(4));
        }
        catch (Exception e) {
            Assert.fail(badException + e.toString());
        }
    }

    /**
     * Unit tests for delete method
     */
    @Test
    public void testDelete() {
        String badDeletion = "The value should not have been removed";
        String wrongDeletion = "The value was not removed correctly";

        // empty tree
        BinarySearchTree<Integer, Integer> tree1 = newTree();
        tree1.delete((Integer)1); // does nothing and that is correct

        // root only (match and no match)
        insert(tree1, 5);
        tree1.delete((Integer)1);
        Assert.assertEquals(badDeletion, "5", preOrderString(tree1));
        tree1.delete((Integer)5);
        Assert.assertEquals(wrongDeletion, "", preOrderString(tree1));

        // large tree but no match
        

        // smallest value (yes children, no children)

        // largest value (yes children, no children)

        // middle value (left child, right child, both children, both children have children too)

        // remove root in large tree

        // remove a duplicate value
    }

    /**
     * Unit tests for inorderRec method
     */
    @Test
    public void testInorderRec() {
        // empty tree

        // root only

        // large tree
    }

    /**
     * Unit tests for kthSmallest method
     */
    @Test
    public void kthSmallest() throws NoSuchElementException {
        // empty tree

        // negative input

        // root only (no exception and exception)
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

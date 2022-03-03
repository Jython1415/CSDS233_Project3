import java.lang.reflect.Method;
import java.rmi.UnexpectedException;

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

    public String preOrderString(BinarySearchTree tree) {
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
}

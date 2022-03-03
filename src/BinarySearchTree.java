import java.lang.Comparable;
import java.util.List;

/**
 * This class represents a tree of sorted information. Each bit of stored data has a "key" associated with it that determines how it should be sorted.
 * All methods are efficient: O(N)
 * @author Joshua Shew
 */
public class BinarySearchTree<T extends Comparable<? super T>, V> {

    /**
     * This method inserts the value into the tree based on the associated key
     * @param key the key determines how the value should be sorted within the tree
     * @param value the value to be stored in the tree
     */
    public void insert(T key, V value) {

    }

    /**
     * This method finds a value in the tree based on its associated key
     * @param key the key to search for in the tree
     * @return the value that is associated with the key
     */
    public V search(T key) {
        return null;
    }

    /**
     * This method removes the value associated with the input key
     * @param key the key associated with the value that should be removed
     */
    public void delete(T key) {
        
    }

    /**
     * This method returns list with all the values in the tree in ascending order (based on their "key")
     * @return a list with all the values in the tree in ascending order
     */
    public List<V> inorderRec() {
        return null;
    }

    /**
     * This method returns the value associated with the kth smallest key
     * @param k which value to return
     * @return the value associated with the kth smallest key
     */
    public V kthSmallest(int k) {
        return null;
    }

    private String preOrderToString() {

    }

    private String preOrderToStringTree(StringBuilder string, BinaryNode currentNode) {
        
    }

    private class BinaryNode {
        private T key;
        private V value;
        private BinaryNode left;
        private BinaryNode right;
        private BinaryNode parent;

        public BinaryNode(T key, V value) {
            this.key = key;
            this.value = value;

            this.left = null;
            this.right = null;
            this.parent = null;
        }

        public BinaryNode(T key, V value, BinaryNode left, BinaryNode right, BinaryNode parent) {
            this.key = key;
            this.value = value;

            this.left = left;
            this.right = right;
            this.parent = parent;
        }

        public T getKey() {
            return this.key;
        }

        public void setKey(T key) {
            this.key = key;
        }

        public V value() {
            return this.value;
        }

        public void setValue(V value) {
            this.value = value;
        }

        public BinaryNode getLeft() {
            return this.left;
        }

        public void setLeft(BinaryNode left) {
            this.left = left;
        }

        public BinaryNode getRight() {
            return this.right;
        }

        public void setRight(BinaryNode right) {
            this.right = right;
        }

        public BinaryNode getParent() {
            return this.parent;
        }
        
        public void setParent(BinaryNode parent) {
            this.parent = parent;
        }
    }
}

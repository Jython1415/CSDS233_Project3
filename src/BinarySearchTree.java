import java.lang.Comparable;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * This class represents a tree of sorted information. Each bit of stored data has a "key" associated with it that determines how it should be sorted.
 * All methods are efficient: O(N)
 * @author Joshua Shew
 */
public class BinarySearchTree<T extends Comparable<? super T>, V> {

    private BinaryNode root;

    private BinaryNode getRoot() {
        return this.root;
    }

    private void setRoot(BinaryNode root) {
        this.root = root;
    }

    /**
     * This method inserts the value into the tree based on the associated key
     * @param key the key determines how the value should be sorted within the tree
     * @param value the value to be stored in the tree
     */
    public void insert(T key, V value) {
        BinaryNode parent = findPreNode(key);
        if (parent == null) {
            setRoot(new BinaryNode(key, value));
        }
        else if (key.compareTo(parent.getKey()) < 0) {
            parent.setLeft(new BinaryNode(key, value, parent));
        }
        else {
            parent.setRight(new BinaryNode(key, value, parent));
        }
    }

    /**
     * This method finds a value in the tree based on its associated key
     * If there are duplicate keys, the value that is associated with the key that is found first will be returned (no guaranteed pattern)
     * @param key the key to search for in the tree
     * @throws NoSuchElementException if a node with the key cannot be found
     * @return the value that is associated with the key
     */
    public V search(T key) throws NoSuchElementException {
        return searchTree(key, getRoot()).getValue();
    }

    private BinaryNode searchTree(T key, BinaryNode currentNode) throws NoSuchElementException {
        if (currentNode == null) {
            throw new NoSuchElementException();
        }
        else if (key.compareTo(currentNode.getKey()) == 0) {
            return currentNode;
        }
        else {
            return searchTree(key, (key.compareTo(currentNode.getKey()) < 0) ?
                                   currentNode.getLeft() : currentNode.getRight());
        }
    }

    /**
     * This method removes the value associated with the input key
     * If the key is not found, then no value is removed
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
        StringBuilder string = new StringBuilder();


        preOrderToStringTree(string, getRoot());

        return string.toString();
    }

    private void preOrderToStringTree(StringBuilder string, BinaryNode currentNode) {
        if (currentNode != null) {
            string.append(currentNode.getValue());
            if (currentNode.getLeft() != null || currentNode.getRight() != null) {
                string.append(" (");
                preOrderToStringTree(string, currentNode.getLeft());
                string.append(", ");
                preOrderToStringTree(string, currentNode.getRight());
                string.append(")");
            }
        }
    }

    private BinaryNode findPreNode(T key) {
        return findPreNode(key, getRoot());
    }

    private BinaryNode findPreNode(T key, BinaryNode currentNode) {
        if (currentNode == null) {
            return null;
        }
        else if (currentNode.getKey().compareTo(key) == 0) {
            return (currentNode.getRight() != null && key.compareTo(currentNode.getRight().getKey()) == 0) ? 
                   findPreNode(key, currentNode.getRight()) : currentNode;
        }
        else if (key.compareTo(currentNode.getKey()) < 0) {
            return (currentNode.getLeft() == null) ? currentNode : findPreNode(key, currentNode.getLeft());
        }
        else {
            return (currentNode.getRight() == null) ? currentNode : findPreNode(key, currentNode.getRight());
        }
    }

    private BinaryNode findMin(BinaryNode currentNode) {
        if (currentNode == null) {
            return null;
        }
        else if (currentNode.getLeft() == null) {
            return currentNode;
        }
        else {
            return findMin(currentNode.getLeft());
        }
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

        public BinaryNode(T key, V value, BinaryNode parent) {
            this.key = key;
            this.value = value;

            this.left = null;
            this.right = null;
            this.parent = parent;
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

        public V getValue() {
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

import java.lang.Comparable;
import java.util.List;
import java.util.LinkedList;
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
        delete(key, getRoot());
    }

    private void delete(T key, BinaryNode currentNode) {
        BinaryNode node = findNode(key, currentNode);
        if (node != null) {
            System.out.println(node.getValue().toString());
            if (node.getLeft() != null && node.getRight() != null) { // two children
                node.reassignData(findMin(node.getRight()));
                delete(node.getKey(), node.getRight());
            }
            else if (node.getLeft() != null) { // replace with left child
                BinaryNode parentNode = node.getParent();
                node = node.getLeft();
                node.setParent(parentNode);
                if (parentNode == null) {
                    setRoot(node);
                }
                else {
                    if (key.compareTo(node.getParent().getKey()) < 0) {
                        node.getParent().setLeft(node);
                    }
                    else {
                        node.getParent().setRight(node);
                    }
                }
            }
            else if (node.getRight() != null) { // replace with right child
                BinaryNode parentNode = node.getParent();
                node = node.getRight();
                node.setParent(parentNode);
                if (parentNode == null) {
                    setRoot(node);
                }
                else {
                    if (key.compareTo(node.getParent().getKey()) < 0) {
                        node.getParent().setLeft(node);
                    }
                    else {
                        node.getParent().setRight(node);
                    }
                }
            }
            else {
                if (node.getParent() == null) {
                    setRoot(null);
                }
                else if (key.compareTo(node.getParent().getKey()) < 0) {
                    node.getParent().setLeft(null);
                }
                else {
                    node.getParent().setRight(null);
                }
            }
        }
    }

    /**
     * This method returns list with all the values in the tree in ascending order (based on their "key")
     * @return a list with all the values in the tree in ascending order
     */
    public List<V> inorderRec() {
        List<V> list = new LinkedList<V>();

        inorderRec(list, getRoot());

        return list;
    }

    private void inorderRec(List<V> list, BinaryNode currentNode) {
        if (currentNode != null) {
            inorderRec(list, currentNode.getLeft());
            list.add(currentNode.getValue());
            inorderRec(list, currentNode.getRight());
        }
    }

    /**
     * This method returns the value associated with the kth smallest key
     * @param k which value to return
     * @throws NoSuchElementException if k is larger than the number of values stored in the tree
     * @return the value associated with the kth smallest key
     */
    public V kthSmallest(int k) throws NoSuchElementException {
        List<V> list = inorderRec();

        if (k > list.size() || k <= 0) {
            throw new NoSuchElementException();
        }
        else {
            return list.get(k - 1);
        }
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

    private BinaryNode findNode(T key, BinaryNode currentNode) {
        if (currentNode == null) {
            return null;
        }
        if (key.compareTo(currentNode.getKey()) == 0) {
            return currentNode;
        }
        else {
            return findNode(key,
                            (key.compareTo(currentNode.getKey()) < 0) ? currentNode.getLeft() : currentNode.getRight());
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

        public BinaryNode reassignData(BinaryNode node) {
            this.setValue(node.getValue());
            this.setKey(node.getKey());

            return this;
        }
    }

    public static void main(String[] args) {
        System.out.println("** Demonstration **");
        System.out.println(""); // new line

        System.out.println("Create a new empty BinarySearchTree\nBinarySearchTree<Integer, Integer> tree1 = new BinarySearchTree<Integer, Integer>();");
        System.out.println(""); // new line

        BinarySearchTree<Integer, Integer> tree1 = BinarySearchTreeTester.newTree();

        System.out.println("Insert: 2, 1, 4, 5, 9, 3, 6, 7, 10, 12, 11");
        System.out.println("Uses: tree1.insert(x, x); // so key and value are the same");

        BinarySearchTreeTester.insert(tree1, 2, 1, 4, 5, 9, 3, 6, 7, 10, 12, 11);

        System.out.println(""); // new line
        System.out.println("Use tree1.inorderRec().toString() to display the tree");
        System.out.println(tree1.inorderRec().toString());
        System.out.println(""); // new line
        System.out.println("Here is a preorder print to show the structure of the tree");
        System.out.println(tree1.preOrderToString());

        System.out.println(""); // new line
        System.out.println("Delete 4 and 9 using: tree1.delete(x);");
        
        tree1.delete(4);
        tree1.delete(9);

        System.out.println(""); // new line
        System.out.println(tree1.inorderRec().toString());
        System.out.println(tree1.preOrderToString());

        System.out.println(""); // new line
        System.out.println("Search 12 and 4 using: tree1.search(x);");
        System.out.println(tree1.search(12).toString());
        System.out.println(""); // new line
        System.out.println("Searching 4 will raise an exception\nI will catch and print that exception");
        try {
            tree1.search(4);
        }
        catch (Exception e) {
            System.out.println(e.toString());
        }

        System.out.println(""); // new line
        System.out.println("Find the 3rd smallest element by using: tree1.kthSmallest(x);");
        System.out.println(tree1.kthSmallest(3).toString());

        System.out.println(""); // new line
        System.out.println("The tree uses generics, so other comparable types can be used as keys, and any type can be the value stored in the tree");
        System.out.println("For this example I will use strings as the key, and doubles as the values");
        System.out.println(""); // new line
        System.out.println("BinarySearchTree<String, Double> tree2 = new BinarySearchTree<String, Double>();");
        System.out.println(""); // new line
        System.out.println("I will insert: (\"b\", 7.5), (\"a\", 2.0), (\"z\", 1.1), and (\"h\", 13.4)");

        BinarySearchTree<String, Double> tree2 = new BinarySearchTree<String, Double>();
        tree2.insert("b", 7.5);
        tree2.insert("a", 2.0);
        tree2.insert("z", 1.1);
        tree2.insert("h", 13.4);
        
        System.out.println(""); // new line
        System.out.println("Here is the tree printed out in 2 different ways");
        System.out.println(tree2.inorderRec().toString());
        System.out.println(tree2.preOrderToString());

        System.out.println(""); // new line
        System.out.println("Demonstration pt.8 is shown through the above tests as well");

        System.out.println(""); // new line
        System.out.println("endOfDemonstration();");
    }
}

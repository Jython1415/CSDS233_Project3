import java.lang.Comparable;
import java.util.List;

/**
 * This class represents a tree of sorted information. Each bit of stored data has a "key" associated with it that deteremines how it should be sorted.
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
}

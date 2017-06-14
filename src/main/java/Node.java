/**
 * Created by Xotra on 8.6.2017..
 */
public class Node<T extends Comparable<T>> {
    int height;
    T value;
    Node left;
    Node right;

    public Node(T value) {
        this.value = value;
        height = 0;
        left = null;
        right = null;
    }
    public Node(Node<T> node) {
        this.right = node.right;
        this.left = node.left;
        this.value = node.value;
        height = 0;
    }
}
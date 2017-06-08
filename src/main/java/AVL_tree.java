import static sun.swing.MenuItemLayoutHelper.max;

/**
 * Created by Xotra on 8.6.2017..
 */
public class AVL_tree<T extends Comparable<T>>{
    Node root;

    public AVL_tree(T object) {
        root = new Node(object);
    }

    public Node insert(Node current, Node node) {

        if(current == null) {
            current = node;
            return current;
        } else if (current.value.compareTo(node.value) < 0) {
            current.right = insert(current.right, node);
        } else if (current.value.compareTo(node.value) > 0) {
            current.left = insert(current.left, node);
        }
        if(current.left == null && current.right == null) {}
        else if(current.left == null && current.right.height > 0) {
            balance(current);
        }
        else if (current.left != null && current.left.height > 0 && current.right == null) {
            balance(current);
        }
        else if (current.left != null && current.right != null && Math.abs(current.left.height - current.right.height) > 1) {
            balance(current);
        }
        return current;
    }

    public void calcutaHeight (Node node) {
        int height = 0;
        if (node.left != null) {
            height = node.left.height;
        }
        if (node.right != null) {
            height = max(height, node.right.height);
        }
        node.height = height + 1;
    }

    public void balance(Node node) {
        if (node.left == null) {
            if (node.right.left != null) {
                rotateRight(node.right);
            }
            rotateLeft(node);
        }
        if (node.right == null) {
            if (node.left.right != null) {
                rotateLeft(node.left);
            }
            rotateRight(node);
        }
    }

    public void inOrder (Node node) {
        if(node.left != null) {
            inOrder(node.left);
        }
        System.out.println(node.value);
        if(node.right != null) {
            inOrder(node.right);
        }
        return;
    }

    public void rotateLeft(Node node) {
        Node temp = new Node(node);
        node = node.right;
        temp.right = node.left;
        node.left = temp;
    }

    public void rotateRight(Node node) {
        Node temp = new Node(node);
        node = node.left;
        temp.left = node.right;
        node.right = temp;
    }

    public static void main(String [] args) {
        AVL_tree<Integer> tree = new AVL_tree<>(5);
        tree.insert(tree.root, new Node(2));
        tree.insert(tree.root, new Node(8));
        tree.insert(tree.root, new Node(-50));
        tree.insert(tree.root, new Node(100));
        tree.insert(tree.root, new Node(11));
        tree.insert(tree.root, new Node(1));
        tree.insert(tree.root, new Node(66));
        tree.insert(tree.root, new Node(0));
        tree.insert(tree.root, new Node(134217728));
        tree.inOrder(tree.root);
    }

}
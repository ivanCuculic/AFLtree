import static sun.swing.MenuItemLayoutHelper.max;

/**
 * Created by Xotra on 8.6.2017..
 */
public class AVL_tree<T extends Comparable<T>>{
    Node root;

    public AVL_tree(T object) {
        root = new Node(object);
    }

    public Node insert(Node node) {
        root = insert(root, node);
        return root;
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
        calculateHeight(current);
        if(current.left == null && current.right == null) {}
        else if(current.left == null && current.right.height > 0) {
            current = balance(current);
        }
        else if (current.left != null && current.left.height > 0 && current.right == null) {
            current = balance(current);
        }
        else if (current.left != null && current.right != null && Math.abs(current.left.height - current.right.height) > 1) {
            current = balance(current);
        }
        return current;
    }

    public void calculateHeight (Node node) {
        System.out.println("Kalkuliram: " + node.value);
        int height = 0;
        if (node.left != null) {
            height = node.left.height + 1;
        }
        if (node.right != null) {
            height = max(height, node.right.height + 1);
        }
        node.height = height;
    }

    public Node balance(Node node) {
//        Node parrent = node;
        System.out.println("Balansiram " + node.value);
        if (node.left == null) {
            if (node.right.left != null) {
                node.right = rotateRight(node.right);
            }
            node = rotateLeft(node);
        }
        else if (node.right == null) {
            if (node.left.right != null) {
                node.left = rotateLeft(node.left);
            }
            node = rotateRight(node);
        }
        else if (node.left.height - node.right.height > 1) {
            node = rotateRight(node);
        }
        else {
            node = rotateLeft(node);
        }
        return node;
    }

    public void inOrder (Node node) {
        if(node.left != null) {
            inOrder(node.left);
        }
        System.out.println(node.value + " (" + node.height + ")");
        if(node.right != null) {
            inOrder(node.right);
        }
        return;
    }

    public Node rotateLeft(Node node) {
        System.out.println("Rotiram lijevo: " + node.value);
        boolean changeRoot = (node == root);
        Node temp = new Node(node);
        node = node.right;
        temp.right = node.left;
        node.left = temp;
        if (changeRoot) {
            root = node;
        }
        calculateHeight(node);
        calculateHeight(temp);
        return node;
    }

    public Node rotateRight(Node node) {
        System.out.println("Rotiram desno: " + node.value);
        boolean changeRoot = (node == root);
        Node temp = new Node(node);
        temp.left = node.left.right;
        if(node.left.right != null) {
            System.out.println("node.left.right = " + node.left.right.value);
        }
        node = node.left;
        node.right = temp;
        if (changeRoot) {
            root = node;
        }
        calculateHeight(temp);
        return node;
    }

    public static void main(String [] args) {
        AVL_tree<Integer> tree = new AVL_tree<>(100);
        tree.insert(new Node(90));
        tree.insert(new Node(28));
        tree.insert(new Node(112));
        tree.insert(new Node(-5));
        tree.insert(new Node(3));
        tree.insert(new Node(0));
        System.out.println("Zavrsni inOrder::  ");
        tree.inOrder(tree.root);
        System.out.println("Root je " + tree.root.value);
    }

}
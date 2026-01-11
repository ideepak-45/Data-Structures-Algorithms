package main.java.traversals;

import main.java.representation.TreeNode;

public class DepthFirstSearch {
    public static void dfsInorder(TreeNode node) {
        if (node == null) {
            return;
        }
        System.out.print(node.val + " "); // Process the current node
        dfsInorder(node.left);              // Traverse left subtree
        dfsInorder(node.right);             // Traverse right subtree
    }

    public static void dfsPreorder(TreeNode node) {
        if (node == null) {
            return;
        }
        dfsPreorder(node.left);              // Traverse left subtree
        System.out.print(node.val + " "); // Process the current node
        dfsPreorder(node.right);             // Traverse right subtree
    }

    public static void dfsPostorder(TreeNode node) {
        if (node == null) {
            return;
        }
        dfsPostorder(node.left);              // Traverse left subtree
        dfsPostorder(node.right);             // Traverse right subtree
        System.out.print(node.val + " "); // Process the current node
    }

    public static void main(String[] args) {
        // Example usage:
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        dfsInorder(root);
    }
}

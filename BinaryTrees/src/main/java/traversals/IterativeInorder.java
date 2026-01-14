package main.java.traversals;

import main.java.representation.TreeNode;
import java.util.*;

public class IterativeInorder {
    public static void inorderTraversal(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode current = root;

        while (current != null || !stack.isEmpty()) {
            // Reach the leftmost node of the current node
            while (current != null) {
                stack.push(current);
                current = current.left;
            }
            // Current must be null at this point
            current = stack.pop();
            System.out.print(current.val + " "); // Process the current node
            // Visit the right subtree
            current = current.right;
        }
    }

    public static void main(String[] args) {
        // Example usage:
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        inorderTraversal(root);
    }
}

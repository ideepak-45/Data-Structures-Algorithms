package main.java.traversals;

import main.java.representation.TreeNode;
import java.util.*;

public class IterativePostorderOneStackAndLastVisited {
    public static void postorderTraversal(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode current = root;
        TreeNode lastVisited = null;

        while (current != null || !stack.isEmpty()) {
            // Reach the leftmost node of the current node
            while (current != null) {
                stack.push(current);
                current = current.left;
            }

            TreeNode peekNode = stack.peek();
            // If right child exists and traversing node from left child, move to right child
            if (peekNode.right != null && lastVisited != peekNode.right) {
                current = peekNode.right;
            } else {
                System.out.print(peekNode.val + " "); // Process the current node
                lastVisited = stack.pop();
            }
        }
    }

    public static void main(String[] args) {
        // Example usage:
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        postorderTraversal(root);
    }
}

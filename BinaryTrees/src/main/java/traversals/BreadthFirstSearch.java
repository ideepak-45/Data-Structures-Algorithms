package main.java.traversals;

import java.util.*;
import main.java.representation.TreeNode;

public class BreadthFirstSearch {
    public static void bfsLevelOrderTraversal(TreeNode root) {
        if (root == null) {
            return;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode current = queue.poll();
            System.out.print(current.val + " "); // Process the current node

            if (current.left != null) {
                queue.add(current.left); // Enqueue left child
            }
            if (current.right != null) {
                queue.add(current.right); // Enqueue right child
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

        bfsLevelOrderTraversal(root);
    }
}

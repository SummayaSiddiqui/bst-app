package com.keyin.demo;

import com.keyin.demo.binarySearchTree.BinarySearchTree;

public class App {
    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();

        // Insert values
        int[] numbers = {5, 2, 7, 1};
        for (int num : numbers) {
            bst.insert(num);
        }

        // Test traversal
        System.out.println("In-order traversal: " + bst.inorderTraversal());

        // Test JSON serialization
        System.out.println("Tree structure (JSON):\n" + bst.toJson());

        // Test balancing (bonus)
        bst.balance();
        System.out.println("Balanced tree (JSON):\n" + bst.toJson());
    }
}
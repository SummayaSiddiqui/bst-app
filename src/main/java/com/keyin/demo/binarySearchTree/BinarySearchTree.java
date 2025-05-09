package com.keyin.demo.binarySearchTree;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.LinkedHashMap;
import com.fasterxml.jackson.databind.ObjectMapper;

public class BinarySearchTree {
    private TreeNode root;

    public static class TreeNode {
        int value;
        TreeNode left, right;

        public TreeNode(int value) {
            this.value = value;
            left = right = null;
        }
    }

    public TreeNode getRoot() {
        return root;
    }

    public void insert(int value) {
        root = insertRec(root, value);
    }

    private TreeNode insertRec(TreeNode root, int value) {
        if (root == null) {
            root = new TreeNode(value);
            return root;
        }

        if (value < root.value) {
            root.left = insertRec(root.left, value);
        } else {
            root.right = insertRec(root.right, value);
        }

        return root;
    }

    public List<Integer> inorderTraversal() {
        List<Integer> result = new ArrayList<>();
        inorderTraversalRec(root, result);
        return result;
    }

    private void inorderTraversalRec(TreeNode root, List<Integer> result) {
        if (root != null) {
            inorderTraversalRec(root.left, result);
            result.add(root.value);
            inorderTraversalRec(root.right, result);
        }
    }

    public String toJson() {
        if (root == null) return "{}";

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(this.toJsonRec(root));
            return json.replace("\"", "&quot;");
        } catch (Exception e) {
            e.printStackTrace();
            return "{}";
        }
    }

    public Map<String, Object> toJsonObject() {
        return toJsonRec(root);
    }

    public Map<String, Object> toJsonWithRoot() {
        Map<String, Object> rootMap = new LinkedHashMap<>();
        rootMap.put("root", toJsonRec(this.root));
        return rootMap;
    }

    private Map<String, Object> toJsonRec(TreeNode node) {
        if (node == null) return null;

        Map<String, Object> map = new LinkedHashMap<>();
        map.put("value", node.value);
        map.put("left", toJsonRec(node.left));
        map.put("right", toJsonRec(node.right));
        return map;
    }

    // Balancing of the tree
    public void balance() {
        List<Integer> sorted = inorderTraversal();
        root = buildBalancedTree(sorted, 0, sorted.size() - 1);
    }

    private TreeNode buildBalancedTree(List<Integer> values, int start, int end) {
        if (start > end) return null;
        int mid = (start + end) / 2;
        TreeNode node = new TreeNode(values.get(mid));
        node.left = buildBalancedTree(values, start, mid - 1);
        node.right = buildBalancedTree(values, mid + 1, end);
        return node;
    }
}


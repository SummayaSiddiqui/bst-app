package com.keyin.demo.binarySearchTree;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

public class BinarySearchTreeTest {
    private BinarySearchTree bst;

    @BeforeEach
    public void setUp() {
        bst = new BinarySearchTree();
    }

    @Test
    public void testInsertAndInorderTraversal() {
        bst.insert(5);
        bst.insert(3);
        bst.insert(7);
        bst.insert(1);

        List<Integer> expected = List.of(1, 3, 5, 7);
        assertEquals(expected, bst.inorderTraversal());
    }

    @Test
    public void testJsonObjectStructure() {
        bst.insert(5);
        bst.insert(3);
        bst.insert(7);

        Map<String, Object> jsonMap = bst.toJsonObject();
        assertNotNull(jsonMap);
        assertEquals(5, jsonMap.get("value"));

        Map<String, Object> left = (Map<String, Object>) jsonMap.get("left");
        assertEquals(3, left.get("value"));

        Map<String, Object> right = (Map<String, Object>) jsonMap.get("right");
        assertEquals(7, right.get("value"));
    }

    @Test
    public void testToJsonWithRootKey() {
        bst.insert(10);
        Map<String, Object> rootJson = bst.toJsonWithRoot();
        assertTrue(rootJson.containsKey("root"));
        assertEquals(10, ((Map<String, Object>) rootJson.get("root")).get("value"));
    }

    @Test
    public void testBalance() {
        bst.insert(1);
        bst.insert(2);
        bst.insert(3);
        bst.insert(4);
        bst.insert(5);

        bst.balance();

        List<Integer> expected = List.of(1, 2, 3, 4, 5);
        assertEquals(expected, bst.inorderTraversal());

        assertEquals(3, bst.getRoot().value);
    }

    @Test
    public void testEmptyTreeJson() {
        BinarySearchTree emptyTree = new BinarySearchTree();
        assertEquals("{}", emptyTree.toJson());
    }
}
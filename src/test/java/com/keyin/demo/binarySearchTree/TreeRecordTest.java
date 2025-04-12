package com.keyin.demo.binarySearchTree;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TreeRecordTest {

    @Test
    public void testTreeRecordFields() {
        TreeRecord record = new TreeRecord();
        record.setId(1L);
        record.setInputNumbers("3,1,5");
        record.setTreeStructure("{...}");

        assertEquals(1L, record.getId());
        assertEquals("3,1,5", record.getInputNumbers());
        assertEquals("{...}", record.getTreeStructure());
    }
}

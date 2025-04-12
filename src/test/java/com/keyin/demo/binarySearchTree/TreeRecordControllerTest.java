package com.keyin.demo.binarySearchTree;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class TreeRecordControllerTest {

    @Mock
    private TreeRecordRepository treeRecordRepository;

    @InjectMocks
    private TreeRecordController controller;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testProcessNumbersJson_unbalanced() {
        Map<String, List<Integer>> request = new HashMap<>();
        request.put("numbers", Arrays.asList(3, 1, 5));

        TreeRecord mockRecord = new TreeRecord();
        mockRecord.setInputNumbers("[3, 1, 5]");
        mockRecord.setTreeStructure("{&quot;value&quot;:3,...}");
        mockRecord.setBalanced(false);

        when(treeRecordRepository.save(any(TreeRecord.class))).thenReturn(mockRecord);

        Map<String, Object> result = controller.processNumbersJson(request);

        assertEquals(3, result.get("root") instanceof Map ? ((Map<?, ?>) result.get("root")).get("value") : null);
        verify(treeRecordRepository, times(1)).save(any(TreeRecord.class));
    }

    @Test
    public void testProcessBalancedNumbersJson_balanced() {
        Map<String, List<Integer>> request = new HashMap<>();
        request.put("numbers", Arrays.asList(3, 1, 5));

        TreeRecord mockRecord = new TreeRecord();
        mockRecord.setInputNumbers("[3, 1, 5]");
        mockRecord.setTreeStructure("{&quot;value&quot;:3,...}");
        mockRecord.setBalanced(true);

        when(treeRecordRepository.save(any(TreeRecord.class))).thenReturn(mockRecord);

        Map<String, Object> result = controller.processBalancedNumbersJson(request);

        assertEquals(3, result.get("root") instanceof Map ? ((Map<?, ?>) result.get("root")).get("value") : null);
        verify(treeRecordRepository, times(1)).save(any(TreeRecord.class));
    }

    @Test
    public void testGetPreviousTrees_returnsHistory() {
        TreeRecord record = new TreeRecord();
        record.setId(1L);
        record.setInputNumbers("[3, 1, 5]");
        record.setTreeStructure("{&quot;value&quot;:3,...}");
        record.setBalanced(true);

        List<TreeRecord> mockList = Collections.singletonList(record);

        when(treeRecordRepository.findAll()).thenReturn(mockList);

        List<TreeRecord> result = controller.getPreviousTrees();

        assertEquals(1, result.size());
        assertEquals("[3, 1, 5]", result.get(0).getInputNumbers());
        verify(treeRecordRepository, times(2)).findAll();
    }
}

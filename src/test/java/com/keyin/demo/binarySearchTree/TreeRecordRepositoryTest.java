package com.keyin.demo.binarySearchTree;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class TreeRecordRepositoryTest {

    @Autowired
    private TreeRecordRepository treeRecordRepository;

    @Test
    public void testSaveAndFindAll() {
        // Given
        TreeRecord record = new TreeRecord();
        record.setInputNumbers("5,3,7,2,4,6,8");
        record.setTreeStructure("        5\n     /     \\\n   3        7\n  / \\      / \\\n 2   4    6   8");
        record.setBalanced(true);

        // When
        treeRecordRepository.save(record);
        List<TreeRecord> allRecords = treeRecordRepository.findAll();

        // Then
        assertThat(allRecords).hasSize(1);
        TreeRecord saved = allRecords.get(0);
        assertThat(saved.getInputNumbers()).isEqualTo("5,3,7,2,4,6,8");
        assertThat(saved.getTreeStructure()).contains("5");
        assertThat(saved.isBalanced()).isTrue();
    }
}

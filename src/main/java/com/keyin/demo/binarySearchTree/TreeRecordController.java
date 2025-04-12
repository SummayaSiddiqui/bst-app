package com.keyin.demo.binarySearchTree;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/tree")
public class TreeRecordController {

    @Autowired
    private TreeRecordRepository treeRecordRepository;

    @PostMapping("/process-numbers")
    @ResponseBody
    public Map<String, Object> processNumbersJson(@RequestBody Map<String, List<Integer>> request) {
        List<Integer> numbers = request.get("numbers");
        BinarySearchTree binarySearchTreeJson = new BinarySearchTree();

        for (Integer num : numbers) {
            binarySearchTreeJson.insert(num);
        }

        TreeRecord treeRecord = new TreeRecord();
        treeRecord.setInputNumbers(numbers.toString());
        treeRecord.setTreeStructure(binarySearchTreeJson.toJson());
        treeRecord.setBalanced(false);
        treeRecordRepository.save(treeRecord);

        return binarySearchTreeJson.toJsonWithRoot();
    }

    @PostMapping("/process-numbers/balanced")
    @ResponseBody
    public Map<String, Object> processBalancedNumbersJson(@RequestBody Map<String, List<Integer>> request) {
        List<Integer> numbers = request.get("numbers");
        BinarySearchTree binarySearchTreeJson = new BinarySearchTree();

        for (Integer num : numbers) {
            binarySearchTreeJson.insert(num);
        }

        binarySearchTreeJson.balance();

        TreeRecord treeRecord = new TreeRecord();
        treeRecord.setInputNumbers(numbers.toString());
        treeRecord.setTreeStructure(binarySearchTreeJson.toJson());
        treeRecord.setBalanced(true);
        treeRecordRepository.save(treeRecord);

        return binarySearchTreeJson.toJsonWithRoot();
    }

    @GetMapping("/history")
    public List<TreeRecord> getPreviousTrees() {
        treeRecordRepository.findAll();
        return treeRecordRepository.findAll();
    }
}

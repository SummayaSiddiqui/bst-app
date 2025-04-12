package com.keyin.demo.binarySearchTree;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        treeRecordRepository.save(treeRecord);

        return binarySearchTreeJson.toJsonWithRoot();
    }
}

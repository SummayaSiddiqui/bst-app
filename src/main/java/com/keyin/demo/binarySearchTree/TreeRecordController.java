package com.keyin.demo.binarySearchTree;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/tree")
public class TreeRecordController {

    @Autowired
    private TreeRecordRepository treeRecordRepository;
}

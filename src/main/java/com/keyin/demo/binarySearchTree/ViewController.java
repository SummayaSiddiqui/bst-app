package com.keyin.demo.binarySearchTree;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    @GetMapping("/enter-numbers")
    public String showInputPage() {
        return "forward:/index.html";
    }

    @GetMapping("/previous-trees")
    public String showPreviousTrees() {
        return "forward:/previous-trees.html";
    }
}

package com.keyin.demo.binarySearchTree;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(ViewController.class)
public class ViewControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testShowInputPage() throws Exception {
        mockMvc.perform(get("/enter-numbers"))
                .andExpect(view().name("forward:/index.html"));
    }

    @Test
    public void testShowPreviousTrees() throws Exception {
        mockMvc.perform(get("/previous-trees"))
                .andExpect(view().name("forward:/previous-trees.html"));
    }

    @Test
    public void testShowBalancedTree() throws Exception {
        mockMvc.perform(get("/balanced-tree"))
                .andExpect(view().name("forward:/balanced-tree.html"));
    }
}
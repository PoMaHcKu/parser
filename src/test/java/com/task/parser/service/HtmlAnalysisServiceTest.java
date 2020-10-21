package com.task.parser.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class HtmlAnalysisServiceTest {

    @Autowired
    HtmlAnalysisService analysisService;

    @Test
    void analysisTest() {
        String first = "hello";
        assertThrows(IllegalArgumentException.class, () -> analysisService.analysisLink(first));
        String second = "https://hello";
        assertTrue(analysisService.analysisLink(second).isEmpty());
        String third = "https://vk.com";
        assertFalse(analysisService.analysisLink(third).isEmpty());
    }
}
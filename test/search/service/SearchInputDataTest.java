package search.service;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SearchInputDataTest {

    @Test
    void getChoice() {
        String testChoice = "author";
        String testKeyword = "Hua";

        SearchInputData searchData = new SearchInputData(testChoice, testKeyword);

        String choice = searchData.getChoice();

        assertEquals(testChoice, choice, "The getChoice method should return the correct choice");
    }

    @Test
    void getKeyword() {
        String testChoice = "author";
        String testKeyword = "Hua";

        SearchInputData searchData = new SearchInputData(testChoice, testKeyword);

        String keyword = searchData.getKeyword();

        assertEquals(testKeyword, keyword, "The getKeyword method should return the correct keyword");
    }
}
package search.service.interface_adapter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import search.service.SearchInputBoundary;
import search.service.SearchInputData;

import static org.junit.jupiter.api.Assertions.*;

class SearchControllerTest {

    private SearchController controller;
    private FakeSearchInteractor fakeInteractor;

    private class FakeSearchInteractor implements SearchInputBoundary {

        SearchInputData lastInputData;

        public void execute(SearchInputData inputData) {
            // Capture the inputData for assertion
            this.lastInputData = inputData;
        }
        public SearchInputData getLastInputData() {
            return lastInputData;

        }
    }

    @BeforeEach
    public void setUp() {
        fakeInteractor = new FakeSearchInteractor();
        controller = new SearchController(fakeInteractor);
    }

    @Test
    void execute() {
        String choice = "title";
        String keyword = "Effective Java";

        controller.execute(choice, keyword);

        assertNotNull(fakeInteractor.getLastInputData(), "Input data should not be null");

        assertEquals(choice, fakeInteractor.getLastInputData().getChoice(), "Choice should match");

        assertEquals(keyword, fakeInteractor.getLastInputData().getKeyword(), "Keyword should match");
    }
}
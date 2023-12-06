package search.service.interface_adapter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import search.entity.Book;
import search.entity.CommonBook;
import search.service.SearchOutputData;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SearchPresenterTest {

    class MockSearchViewModel extends SearchViewModel{
        boolean propertyChangedFired = false;

        public void firePropertyChanged(){
            propertyChangedFired = true;
        }

        public SearchState getState(){
            return super.getState();
        }

        public void setState(SearchState state){
            super.setState(state);
        }
    }

    private SearchPresenter searchPresenter;
    private MockSearchViewModel searchViewModel;

    @BeforeEach
    public void setUp(){
        searchViewModel = new MockSearchViewModel();
        searchPresenter = new SearchPresenter(searchViewModel);
    }


    @Test
    void prepareSuccessView() {
        Book book1 = new CommonBook("The Great Gatsby", "F. Scott Fitzgerald", "Fiction",
                "1234567890");

        Book book2 = new CommonBook("Clean Code", "Robert Cecil Martin", "Programming",
                "9780136083238");

        List<Book> books = List.of(book1, book2);

        SearchOutputData searchOutputData = new SearchOutputData(books, false);

        searchPresenter.prepareSuccessView(searchOutputData);

        assertTrue(searchViewModel.propertyChangedFired);
        assertEquals(books, searchViewModel.getState().getBooks());
    }

    @Test
    void prepareNotFoundView() {
        String message = "No books found";

        searchPresenter.prepareNotFoundView(message);

        assertTrue(searchViewModel.propertyChangedFired);
        assertEquals(message, searchViewModel.getState().getNotFound());

    }

    @Test
    void prepareFailView() {
        String error = "Search failed";

        searchPresenter.prepareFailView(error);

        assertTrue(searchViewModel.propertyChangedFired);
        assertEquals(error, searchViewModel.getState().getKeywordError());
    }
}
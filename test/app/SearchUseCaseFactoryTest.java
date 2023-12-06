package app;

import interface_adapter.ViewManagerModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import search.data_access.SearchDataAccessObject;
import search.service.interface_adapter.SearchController;
import search.service.interface_adapter.SearchViewModel;
import user_manage.data_access.FileReviewDataAccessObject;
import user_manage.service.collection_management.add_book.AddBookDataAccessInterface;
import user_manage.service.collection_management.add_book.interface_adapter.AddBookViewModel;
import user_manage.service.collection_management.show_all_lists.interface_adapter.ShowAllListsViewModel;
import user_manage.service.history.add_history.Interface_adapter.AddingHistoryViewModel;
import user_manage.service.reading_review.show_all_reviews.interface_adapter.ShowAllReviewsViewModel;
import user_manage.service.reading_review.write_reviews.interface_adapter.WriteReviewsViewModel;
import view.UserCenter.UserCenterViewModel;

import static org.junit.jupiter.api.Assertions.*;

class SearchUseCaseFactoryTest {

    private SearchViewModel searchViewModel;
    private SearchDataAccessObject searchDataAccessObject;
    private ViewManagerModel viewManagerModel;
    private ShowAllReviewsViewModel showAllReviewsViewModel;
    private WriteReviewsViewModel writeReviewsViewModel;
    private FileReviewDataAccessObject reviewDataAccessObject;
    private AddBookViewModel addBookViewModel;
    private AddBookDataAccessInterface addBookDataAccessObject;
    private ShowAllListsViewModel showAllListsViewModel;
    private UserCenterViewModel userCenterViewModel;
    private AddingHistoryViewModel addingHistoryViewModel;


    @BeforeEach
    public void setUp() {

    }

    @Test
    void createSearchController() {
    }

    @Test
    void create() {
    }
}
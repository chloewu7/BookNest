package app;

import interface_adapter.ViewManagerModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import search.data_access.SearchDataAccessObject;
import search.service.interface_adapter.SearchController;
import search.service.interface_adapter.SearchViewModel;
import user_manage.data_access.FileCollectionDataAccessObject;
import user_manage.data_access.FileHistoryDataAccessObject;
import user_manage.data_access.FileReviewDataAccessObject;
import user_manage.data_access.FileUserDataAccessObject;
import user_manage.entity.CommonCollectionListFactory;
import user_manage.entity.CommonHisotryFactory;
import user_manage.entity.CommonReviewFactory;
import user_manage.entity.CommonUserFactory;
import user_manage.service.collection_management.add_book.AddBookDataAccessInterface;
import user_manage.service.collection_management.add_book.interface_adapter.AddBookViewModel;
import user_manage.service.collection_management.show_all_lists.interface_adapter.ShowAllListsViewModel;
import user_manage.service.history.add_history.Interface_adapter.AddingHistoryViewModel;
import user_manage.service.history.read_history.ReadingHistoryDataAccessInterface;
import user_manage.service.history.read_history.interface_adpter.ReadingHistoryViewModel;
import user_manage.service.reading_review.show_all_reviews.interface_adapter.ShowAllReviewsViewModel;
import user_manage.service.reading_review.write_reviews.interface_adapter.WriteReviewsViewModel;
import view.SearchView;
import view.ShowAllReviewsView;
import view.UserCenter.UserCenterViewModel;
import view.WriteReviewsView;

import java.util.List;

import javax.swing.*;
import java.io.File;
import java.io.IOException;

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
    private ReadingHistoryViewModel readingHistoryViewModel;
    private ReadingHistoryDataAccessInterface readingHistoryDataAccessObject;


    @BeforeEach
    public void setUp() throws IOException {
        searchViewModel = new SearchViewModel();
        viewManagerModel = new ViewManagerModel();
        showAllReviewsViewModel = new ShowAllReviewsViewModel();
        writeReviewsViewModel = new WriteReviewsViewModel();
        addBookViewModel = new AddBookViewModel();
        showAllListsViewModel = new ShowAllListsViewModel();
        userCenterViewModel = new UserCenterViewModel();
        addBookViewModel = new AddBookViewModel();
        addingHistoryViewModel = new AddingHistoryViewModel();
        readingHistoryViewModel = new ReadingHistoryViewModel();

        searchDataAccessObject = new SearchDataAccessObject();

        FileUserDataAccessObject userDataAccessObject = new FileUserDataAccessObject("./users.csv",
                new CommonUserFactory());
        reviewDataAccessObject = new FileReviewDataAccessObject("./reviews.csv",
                new CommonReviewFactory());
        addBookDataAccessObject = new FileCollectionDataAccessObject(new File("./collection.csv"),
                new CommonCollectionListFactory());
        readingHistoryDataAccessObject = new FileHistoryDataAccessObject("./history.csv",new
                CommonHisotryFactory());

    }

    @Test
    void createSearchControllerTest() {
        SearchController searchController = SearchUseCaseFactory.createSearchController(searchViewModel,
                searchDataAccessObject);

        assertNotNull(searchController, "SearchController should be instantiated.");

    }

    @Test
    void createViewListTest() {
        List<JPanel> views = SearchUseCaseFactory.create(
                viewManagerModel, searchViewModel, searchDataAccessObject, showAllReviewsViewModel,
                writeReviewsViewModel, reviewDataAccessObject, addBookViewModel, addBookDataAccessObject,
                showAllListsViewModel, userCenterViewModel, readingHistoryViewModel, readingHistoryDataAccessObject

        );

        assertNotNull(views, "The list of views should not be null.");
        assertFalse(views.isEmpty(), "The list of views should not be empty.");

        assertTrue(views.get(0) instanceof SearchView, "The first view should be an instance of " +
                "SearchView.");
        assertTrue(views.get(1) instanceof ShowAllReviewsView, "The second view should be an instance of " +
                "ShowAllReviewsView.");
        assertTrue(views.get(2) instanceof WriteReviewsView, "The third view should be an instance of " +
                "WriteReviewsView.");

    }


    @Test
    void create() {
    }
}
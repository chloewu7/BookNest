package app;

import interface_adapter.ViewManagerModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import search.data_access.SearchDataAccessObject;
import search.service.interface_adapter.SearchViewModel;
import user_manage.data_access.FileCollectionDataAccessObject;
import user_manage.data_access.FileHistoryDataAccessObject;
import user_manage.data_access.FileReviewDataAccessObject;
import user_manage.entity.CommonCollectionListFactory;
import user_manage.entity.CommonHisotryFactory;
import user_manage.entity.CommonReviewFactory;
import user_manage.service.collection_management.create_list.interface_adapter.CreateListViewModel;
import user_manage.service.collection_management.show_all_lists.interface_adapter.ShowAllListsViewModel;
import user_manage.service.collection_management.show_books_in_list.interface_adapter.ShowBooksInListViewModel;
import user_manage.service.history.read_history.interface_adpter.ReadingHistoryViewModel;
import user_manage.service.reading_review.show_my_reviews.interface_adapter.ShowMyReviewsViewModel;
import view.UserCenter.UserCenterView;
import view.UserCenter.UserCenterViewModel;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserCenterFactoryTest {
    private ViewManagerModel viewManagerModel;
    private UserCenterViewModel userCenterViewModel;
    private ShowMyReviewsViewModel showMyReviewsViewModel;
    private FileReviewDataAccessObject reviewDataAccessObject;
    private SearchViewModel searchViewModel;
    private SearchDataAccessObject searchDataAccessObject;
    private ShowAllListsViewModel showAllListsViewModel;
    private FileCollectionDataAccessObject collectionDataAccessObject;
    private CreateListViewModel createListViewModel;
    private ShowBooksInListViewModel showBooksInListViewModel;
    private ReadingHistoryViewModel readingHistoryViewModel;
    private FileHistoryDataAccessObject historyDataAccessObject;

    @BeforeEach
    void setUp() throws IOException {
        viewManagerModel = new ViewManagerModel();
        userCenterViewModel = new UserCenterViewModel();
        showMyReviewsViewModel = new ShowMyReviewsViewModel();
        searchViewModel = new SearchViewModel();
        showAllListsViewModel = new ShowAllListsViewModel();
        showBooksInListViewModel = new ShowBooksInListViewModel();
        readingHistoryViewModel = new ReadingHistoryViewModel();
        createListViewModel = new CreateListViewModel();

        reviewDataAccessObject = new FileReviewDataAccessObject("./reviews.csv",
                new CommonReviewFactory());
        collectionDataAccessObject = new FileCollectionDataAccessObject(new File("./collection.csv"),
                new CommonCollectionListFactory());
        historyDataAccessObject = new FileHistoryDataAccessObject("./history.csv",new
                CommonHisotryFactory());
        searchDataAccessObject = new SearchDataAccessObject();
    }

    @Test
    void createFactoryComponentsTest() {
        List<JPanel> components = UserCenterFactory.create(
                viewManagerModel, userCenterViewModel,showMyReviewsViewModel, reviewDataAccessObject,
                searchViewModel, searchDataAccessObject, showAllListsViewModel, collectionDataAccessObject,
                createListViewModel, showBooksInListViewModel, readingHistoryViewModel, historyDataAccessObject

        );

        assertNotNull(components, "The list of components should not be null.");

        assertTrue(components.get(0) instanceof UserCenterView, "The first component should be UserCenterView.");
    }



    @Test
    void create() {
    }
}
package view.UserCenter;

import interface_adapter.ViewManagerModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import search.data_access.SearchDataAccessObject;
import search.entity.NewResponseFactory;
import search.entity.ResponseFactory;
import search.service.SearchDataAccessInterface;
import search.service.SearchInputBoundary;
import search.service.SearchInteractor;
import search.service.SearchOutputBoundary;
import search.service.interface_adapter.SearchController;
import search.service.interface_adapter.SearchPresenter;
import search.service.interface_adapter.SearchViewModel;
import user_manage.data_access.FileCollectionDataAccessObject;
import user_manage.data_access.FileHistoryDataAccessObject;
import user_manage.data_access.FileReviewDataAccessObject;
import user_manage.entity.*;
import user_manage.service.collection_management.show_all_lists.ShowAllListsDataAccessInterface;
import user_manage.service.collection_management.show_all_lists.ShowAllListsInputBoundary;
import user_manage.service.collection_management.show_all_lists.ShowAllListsInteractor;
import user_manage.service.collection_management.show_all_lists.ShowAllListsOutputBoundary;
import user_manage.service.collection_management.show_all_lists.interface_adapter.ShowAllListsController;
import user_manage.service.collection_management.show_all_lists.interface_adapter.ShowAllListsPresenter;
import user_manage.service.collection_management.show_all_lists.interface_adapter.ShowAllListsViewModel;
import user_manage.service.history.read_history.ReadingHistoryDataAccessInterface;
import user_manage.service.history.read_history.ReadingHistoryInteractor;
import user_manage.service.history.read_history.ReadingHistoryOutputBoundary;
import user_manage.service.history.read_history.interface_adpter.ReadingHistoryController;
import user_manage.service.history.read_history.interface_adpter.ReadingHistoryPresenter;
import user_manage.service.history.read_history.interface_adpter.ReadingHistoryViewModel;
import user_manage.service.reading_review.show_my_reviews.ShowMyReviewsDataAccessInterface;
import user_manage.service.reading_review.show_my_reviews.ShowMyReviewsInputBoundary;
import user_manage.service.reading_review.show_my_reviews.ShowMyReviewsInteractor;
import user_manage.service.reading_review.show_my_reviews.ShowMyReviewsOutputBoundary;
import user_manage.service.reading_review.show_my_reviews.interface_adapter.ShowMyReviewsController;
import user_manage.service.reading_review.show_my_reviews.interface_adapter.ShowMyReviewsPresenter;
import user_manage.service.reading_review.show_my_reviews.interface_adapter.ShowMyReviewsViewModel;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class UserCenterViewTest {

    private UserCenterView userCenterView;
    private ViewManagerModel viewManagerModel;
    private UserCenterViewModel userCenterViewModel;
    private ShowMyReviewsViewModel showMyReviewsViewModel;
    private ShowMyReviewsController showMyReviewsController;
    private SearchController searchController;
    private SearchViewModel searchViewModel;
    private ShowAllListsController showAllListsController;
    private ShowAllListsViewModel showAllListsViewModel;

    private ReadingHistoryController readingHistoryController;
    private ReadingHistoryViewModel readingHistoryViewModel;

    @BeforeEach
    void setUp() throws IOException {

        viewManagerModel = new ViewManagerModel();
        userCenterViewModel = new UserCenterViewModel();
        showMyReviewsViewModel = new ShowMyReviewsViewModel();
        searchViewModel = new SearchViewModel();
        showAllListsViewModel = new ShowAllListsViewModel();
        readingHistoryViewModel = new ReadingHistoryViewModel();

        String mockCsvString = "./reviews.csv";
        ReviewFactory mockReviewFactory = new CommonReviewFactory();
        ShowMyReviewsDataAccessInterface showMyReviewsDataAccessObject = new FileReviewDataAccessObject(
                mockCsvString, mockReviewFactory);
        ShowMyReviewsOutputBoundary showMyReviewsPresenter = new ShowMyReviewsPresenter(viewManagerModel,
                showMyReviewsViewModel);
        ShowMyReviewsInputBoundary showMyReviewsInteractor = new ShowMyReviewsInteractor(showMyReviewsDataAccessObject,
                showMyReviewsPresenter);
        showMyReviewsController = new ShowMyReviewsController(showMyReviewsInteractor);

        SearchDataAccessInterface searchDataAccessObject = new SearchDataAccessObject();
        SearchOutputBoundary searchPresenter = new SearchPresenter(searchViewModel);
        ResponseFactory responseFactory = new NewResponseFactory();
        SearchInputBoundary searchInteractor = new SearchInteractor(searchDataAccessObject, searchPresenter,
                responseFactory);
        searchController = new SearchController(searchInteractor);

        File mockCSVCollection = new File("./collection.csv");
        CollectionListFactory collectionListFactory = new CommonCollectionListFactory();
        ShowAllListsDataAccessInterface showAllListsDataAccessObject = new FileCollectionDataAccessObject(
                mockCSVCollection,collectionListFactory);
        ShowAllListsOutputBoundary showAllListsPresenter = new ShowAllListsPresenter(showAllListsViewModel,
                viewManagerModel);
        ShowAllListsInputBoundary showAllListsInteractor = new ShowAllListsInteractor(showAllListsDataAccessObject,
                showAllListsPresenter);
        showAllListsController = new ShowAllListsController(showAllListsInteractor);

        readingHistoryViewModel = new ReadingHistoryViewModel();
        String mockCSVHistory = "./history.csv";
        HistoryFactory histroyFactory = new CommonHisotryFactory();
        ReadingHistoryDataAccessInterface readingHistoryDataAccessObject = new FileHistoryDataAccessObject(
                mockCSVHistory, histroyFactory);
        ReadingHistoryOutputBoundary mockHistoryPresenter = new ReadingHistoryPresenter(readingHistoryViewModel,
                viewManagerModel);
        ReadingHistoryInteractor mockReadingHistoryInteractor = new ReadingHistoryInteractor(mockHistoryPresenter,
                readingHistoryDataAccessObject);
        readingHistoryController = new ReadingHistoryController(mockReadingHistoryInteractor);

        userCenterView = new UserCenterView(viewManagerModel, userCenterViewModel, showMyReviewsController,
                showMyReviewsViewModel, searchController, searchViewModel, showAllListsController,
                showAllListsViewModel, readingHistoryController, readingHistoryViewModel);

    }

    @Test
    void collectionButtonActionPerformed() {
        userCenterView.collectionButton.doClick();

        assertEquals("all collection lists", viewManagerModel.getActiveView(),
                "Clicking collection button should set active view to 'all collection lists'.");
    }

    @Test
    void searchButtonActionPerformed() {
        userCenterView.searchButton.doClick();

        assertEquals("Search", viewManagerModel.getActiveView(),
                "Clicking search button should set active view to 'Search'.");
    }

    @Test
    void reviewButtonActionPerformed() {
        userCenterView.reviewButton.doClick();

        assertEquals("my reviews", viewManagerModel.getActiveView(),
                "Clicking review button should set active view to 'my reviews'.");
    }

    @Test
    void historyButtonActionPerformed() {
        userCenterView.historyButton.doClick();

        assertEquals("History", viewManagerModel.getActiveView(),
                "Clicking history button should set active view to 'History'.");
    }

    @Test
    void propertyChangeUpdatesTitle() {
        // Assuming the UserCenterState is correctly set up
        UserCenterState newState = new UserCenterState();
        newState.setUsername("TestUser");
        PropertyChangeEvent evt = new PropertyChangeEvent(userCenterViewModel, "username", null, newState);

        userCenterView.propertyChange(evt);

        Component[] components = userCenterView.title.getComponents();
        assertTrue(components[0] instanceof JLabel, "First component should be a JLabel.");
        JLabel titleLabel = (JLabel) components[0];
        assertEquals("User Center for TestUser", titleLabel.getText(),
                "Property change should update the title with the new username.");
    }

    @Test
    void actionPerformed() {
    }

    @Test
    void propertyChange() {
    }
}
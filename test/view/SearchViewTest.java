package view;

import interface_adapter.ViewManagerModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.shadow.com.univocity.parsers.csv.CsvFormat;
import search.data_access.SearchDataAccessObject;
import search.entity.Book;
import search.entity.CommonBook;
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
import user_manage.service.collection_management.add_book.AddBookDataAccessInterface;
import user_manage.service.collection_management.add_book.AddBookInteractor;
import user_manage.service.collection_management.add_book.AddBookOutputBoundary;
import user_manage.service.collection_management.add_book.interface_adapter.AddBookController;
import user_manage.service.collection_management.add_book.interface_adapter.AddBookPresenter;
import user_manage.service.collection_management.add_book.interface_adapter.AddBookViewModel;
import user_manage.service.collection_management.show_all_lists.interface_adapter.ShowAllListsViewModel;
import user_manage.service.history.read_history.ReadingHistoryDataAccessInterface;
import user_manage.service.history.read_history.ReadingHistoryInteractor;
import user_manage.service.history.read_history.ReadingHistoryOutputBoundary;
import user_manage.service.history.read_history.interface_adpter.ReadingHistoryController;
import user_manage.service.history.read_history.interface_adpter.ReadingHistoryPresenter;
import user_manage.service.history.read_history.interface_adpter.ReadingHistoryViewModel;
import user_manage.service.reading_review.show_all_reviews.ShowAllReviewsDataAccessInterface;
import user_manage.service.reading_review.show_all_reviews.ShowAllReviewsInputBoundary;
import user_manage.service.reading_review.show_all_reviews.ShowAllReviewsInteractor;
import user_manage.service.reading_review.show_all_reviews.ShowAllReviewsOutputBoundary;
import user_manage.service.reading_review.show_all_reviews.interface_adapter.ShowAllReviewsController;
import user_manage.service.reading_review.show_all_reviews.interface_adapter.ShowAllReviewsPresenter;
import user_manage.service.reading_review.show_all_reviews.interface_adapter.ShowAllReviewsViewModel;
import view.UserCenter.UserCenterViewModel;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SearchViewTest {

    private SearchView searchView;
    private SearchController mockSearchController;
    private SearchViewModel mockSearchViewModel;
    private ViewManagerModel mockViewManagerModel;
    private ShowAllReviewsController mockShowAllReviewsController;
    private ShowAllReviewsViewModel mockShowAllReviewsViewModel;
    private AddBookController mockAddBookController;
    private AddBookViewModel mockAddBookViewModel;
    private ShowAllListsViewModel mockShowAllListsViewModel;
    private UserCenterViewModel mockUserCenterViewModel;
    private ReadingHistoryViewModel mockReadingHistoryViewModel;
    private ReadingHistoryController mockReadingHistoryController;

    @BeforeEach
    void setUp() throws IOException {

        mockSearchViewModel = new SearchViewModel();
        mockViewManagerModel = new ViewManagerModel();
        SearchDataAccessInterface mockSearchDataAccessObject = new SearchDataAccessObject();
        SearchOutputBoundary mockSearchPresenter = new SearchPresenter(mockSearchViewModel);
        ResponseFactory mockResponseFactory = new NewResponseFactory();
        SearchInputBoundary mockSearchInteractor = new SearchInteractor(mockSearchDataAccessObject,
                mockSearchPresenter, mockResponseFactory);
        mockSearchController = new SearchController(mockSearchInteractor);

        mockShowAllReviewsViewModel = new ShowAllReviewsViewModel();
        String mockCsvString = "./reviews.csv";
        ReviewFactory mockReviewFactory = new CommonReviewFactory();
            ShowAllReviewsDataAccessInterface mockShowAllReviewDataAccessObject = new FileReviewDataAccessObject(
                mockCsvString, mockReviewFactory
        );
        ShowAllReviewsOutputBoundary mockShowAllReviewsPresenter = new ShowAllReviewsPresenter(mockViewManagerModel,
                mockShowAllReviewsViewModel);
        ShowAllReviewsInputBoundary mockShowAllReviewsInteractor = new ShowAllReviewsInteractor(
                mockShowAllReviewDataAccessObject, mockShowAllReviewsPresenter);
        mockShowAllReviewsController = new ShowAllReviewsController(mockShowAllReviewsInteractor);

        mockAddBookViewModel = new AddBookViewModel();
        File mockCSVCollection = new File("./collection.csv");
        CollectionListFactory mockCollectionListFactory = new CommonCollectionListFactory();
        AddBookDataAccessInterface mockAddBookDataAccessObject = new
                FileCollectionDataAccessObject(mockCSVCollection,mockCollectionListFactory);
        AddBookOutputBoundary mockAddBookPresenter = new AddBookPresenter(mockAddBookViewModel);
        AddBookInteractor mockAddBookInteractor = new AddBookInteractor(mockAddBookDataAccessObject,
                mockAddBookPresenter);
        mockAddBookController = new AddBookController(mockAddBookInteractor);

        mockShowAllListsViewModel = new ShowAllListsViewModel();
        mockUserCenterViewModel = new UserCenterViewModel();

        mockReadingHistoryViewModel = new ReadingHistoryViewModel();
        File mockCSVHistory = new File("./history.csv");
        HistoryFactory mockHistroyFactory = new CommonHisotryFactory();
        ReadingHistoryDataAccessInterface mockReadingHistoryDataAccessObject = new FileHistoryDataAccessObject(
                mockCSVHistory, mockHistroyFactory);
        ReadingHistoryOutputBoundary mockHistoryPresenter = new ReadingHistoryPresenter(mockReadingHistoryViewModel,
                mockViewManagerModel);
        ReadingHistoryInteractor mockReadingHistoryInteractor = new ReadingHistoryInteractor(mockHistoryPresenter,
                mockReadingHistoryDataAccessObject);
        mockReadingHistoryController = new ReadingHistoryController(mockReadingHistoryInteractor);

        searchView = new SearchView(
                mockSearchController,
                mockSearchViewModel,
                mockViewManagerModel,
                mockShowAllReviewsController,
                mockShowAllReviewsViewModel,
                mockAddBookController,
                mockAddBookViewModel,
                mockShowAllListsViewModel,
                mockUserCenterViewModel,
                mockReadingHistoryViewModel,
                mockReadingHistoryController
        );
    }

    @Test
    void actionPerformed() {
        String expectedSearchType = "Title";
        String expectedKeyword = "The Great Gatsby";
        searchView.searchTypeComboBox.setSelectedItem(expectedSearchType);
        searchView.searchTextField.setText(expectedKeyword);

        searchView.searchButton.doClick();

        boolean resultDisplayed = false;
        for (Component comp : searchView.resultsPanel.getComponents()) {
            if (comp instanceof JPanel) {
                JPanel panel = (JPanel) comp;
                for (Component innerComp : panel.getComponents()) {
                    if (innerComp instanceof JLabel) {
                        JLabel label = (JLabel) innerComp;
                        if (label.getText().contains(expectedKeyword)) {
                            resultDisplayed = true;
                            break;
                        }
                    }
                }
            }
        }

        assertTrue(resultDisplayed, "The search results should be displayed in the results panel.");


    }

    @Test
    void propertyChange() {
    }
}
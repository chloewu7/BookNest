package app;

import interface_adapter.ViewManagerModel;
import search.data_access.SearchDataAccessObject;
import search.entity.NewResponseFactory;
import search.entity.ResponseFactory;
import search.service.SearchDataAccessInterface;
import search.service.SearchInteractor;
import search.service.interface_adapter.SearchPresenter;
import search.service.interface_adapter.SearchState;
import user_manage.data_access.FileCollectionDataAccessObject;
import user_manage.data_access.FileHistoryDataAccessObject;
import user_manage.data_access.FileReviewDataAccessObject;
import user_manage.service.collection_management.create_list.CreateListDataAccessInterface;
import user_manage.service.collection_management.create_list.CreateListInteractor;
import user_manage.service.collection_management.create_list.CreateListOutputBoundary;
import user_manage.service.collection_management.create_list.interface_adapter.CreateListController;
import user_manage.service.collection_management.create_list.interface_adapter.CreateListPresenter;
import user_manage.service.collection_management.create_list.interface_adapter.CreateListViewModel;
import user_manage.service.collection_management.show_all_lists.ShowAllListsDataAccessInterface;
import user_manage.service.collection_management.show_all_lists.ShowAllListsInteractor;
import user_manage.service.collection_management.show_all_lists.ShowAllListsOutputBoundary;
import user_manage.service.collection_management.show_all_lists.interface_adapter.ShowAllListsController;
import user_manage.service.collection_management.show_all_lists.interface_adapter.ShowAllListsPresenter;
import user_manage.service.collection_management.show_all_lists.interface_adapter.ShowAllListsViewModel;
import user_manage.service.collection_management.show_books_in_list.ShowBooksInListDataAccessInterface;
import user_manage.service.collection_management.show_books_in_list.ShowBooksInListInteractor;
import user_manage.service.collection_management.show_books_in_list.ShowBooksInListOutputBoundary;
import user_manage.service.collection_management.show_books_in_list.interface_adapter.ShowBooksInListController;
import user_manage.service.collection_management.show_books_in_list.interface_adapter.ShowBooksInListPresenter;
import user_manage.service.collection_management.show_books_in_list.interface_adapter.ShowBooksInListViewModel;

import user_manage.service.history.read_history.ReadingHistoryDataAccessInterface;
import user_manage.service.history.read_history.ReadingHistoryInteractor;
import user_manage.service.history.read_history.ReadingHistoryOutputBoundary;
import user_manage.service.history.read_history.interface_adpter.ReadingHistoryController;
import user_manage.service.history.read_history.interface_adpter.ReadingHistoryPresenter;
import user_manage.service.history.read_history.interface_adpter.ReadingHistoryViewModel;
import user_manage.service.reading_review.show_my_reviews.ShowMyReviewsDataAccessInterface;
import user_manage.service.reading_review.show_my_reviews.ShowMyReviewsInteractor;
import user_manage.service.reading_review.show_my_reviews.ShowMyReviewsOutputBoundary;
import user_manage.service.reading_review.show_my_reviews.interface_adapter.ShowMyReviewsController;
import user_manage.service.reading_review.show_my_reviews.interface_adapter.ShowMyReviewsPresenter;
import user_manage.service.reading_review.show_my_reviews.interface_adapter.ShowMyReviewsViewModel;
import search.service.interface_adapter.SearchController;
import search.service.interface_adapter.SearchViewModel;
import search.service.SearchDataAccessInterface;
import search.service.SearchOutputBoundary;
import view.*;
import view.UserCenter.UserCenterView;
import view.UserCenter.UserCenterViewModel;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserCenterFactory {
    private UserCenterFactory(){}

    public static List<JPanel> create(ViewManagerModel viewManagerModel, UserCenterViewModel userCenterViewModel,
                                      ShowMyReviewsViewModel showMyReviewsViewModel, FileReviewDataAccessObject reviewDataAccessObject,
                                      SearchViewModel searchViewModel, SearchDataAccessObject searchDataAccessObject,
                                      ShowAllListsViewModel showAllListsViewModel, FileCollectionDataAccessObject collectionDataAccessObject,
                                      CreateListViewModel createListViewModel, ShowBooksInListViewModel showBooksInListViewModel, ReadingHistoryViewModel
                                      readingHistoryViewModel, FileHistoryDataAccessObject HistoryDAO) {
        try {
            ShowMyReviewsController showMyReviewsController = createShowMyReviewUseCase(viewManagerModel, showMyReviewsViewModel, reviewDataAccessObject);
            SearchController searchController = createSearchUseCase(viewManagerModel,searchViewModel, searchDataAccessObject);
            ShowAllListsController showAllListsController = createShowAllListsUseCase(viewManagerModel, showAllListsViewModel, collectionDataAccessObject);
            ShowBooksInListController showBooksInListController = createShowBooksInListUseCase(viewManagerModel, showBooksInListViewModel, collectionDataAccessObject);
            CreateListController createListController = createCreateListUseCase(viewManagerModel,createListViewModel, collectionDataAccessObject);
            ReadingHistoryController readingHistoryController =createHistoryController(viewManagerModel, readingHistoryViewModel, HistoryDAO);
            //AddingHistoryController addingHistoryController=createAddHistoryController(viewManagerModel,addingHistoryViewModel,HistoryDAO);

            List<JPanel> userManageViewList = new ArrayList<>();
            UserCenterView userCenterView =  new UserCenterView(viewManagerModel, userCenterViewModel, showMyReviewsController, showMyReviewsViewModel,
                    searchController, searchViewModel, showAllListsController, showAllListsViewModel, readingHistoryController,  readingHistoryViewModel);
            userManageViewList.add(userCenterView);
            ShowMyReviewsView showMyReviewsView = new ShowMyReviewsView(viewManagerModel, showMyReviewsController, showMyReviewsViewModel);
            userManageViewList.add(showMyReviewsView);
            ShowAllListsView showAllListsView = new ShowAllListsView(showAllListsController, showAllListsViewModel, viewManagerModel,
                    createListController, createListViewModel, userCenterViewModel, showBooksInListViewModel, showBooksInListController);
            userManageViewList.add(showAllListsView);

            ReadingHistoryView readingHistoryView = new ReadingHistoryView(readingHistoryViewModel, readingHistoryController ,viewManagerModel);
            userManageViewList.add(readingHistoryView);

            ShowBooksInListView showBooksInListView = new ShowBooksInListView(showBooksInListController, showBooksInListViewModel, viewManagerModel, userCenterViewModel);
            userManageViewList.add(showBooksInListView);

            return userManageViewList;
        } catch (IOException e){
            JOptionPane.showMessageDialog(null, "Could not open review data file.");
        }
        return null;
    }

    private static ShowMyReviewsController createShowMyReviewUseCase(ViewManagerModel viewManagerModel,
                                                                   ShowMyReviewsViewModel showMyReviewsViewModel,
                                                                   ShowMyReviewsDataAccessInterface reviewsDataAccessObject) throws IOException {
        ShowMyReviewsOutputBoundary showMyReviewsPresenter = new ShowMyReviewsPresenter(viewManagerModel, showMyReviewsViewModel);

        ShowMyReviewsInteractor showMyReviewsInteractor = new ShowMyReviewsInteractor(
                reviewsDataAccessObject, showMyReviewsPresenter);

        return new ShowMyReviewsController(showMyReviewsInteractor);
    }

    private static SearchController createSearchUseCase(ViewManagerModel viewManagerModel, SearchViewModel searchViewModel,
                                                        SearchDataAccessInterface searchDataAccessObject) {
        SearchOutputBoundary searchPresenter = new SearchPresenter(searchViewModel);
        ResponseFactory responseFactory = new NewResponseFactory();
        SearchInteractor searchInteractor = new SearchInteractor(searchDataAccessObject, searchPresenter, responseFactory);

        SearchController searchController = new SearchController(searchInteractor);

        return searchController;

    }

    private static ShowAllListsController createShowAllListsUseCase(ViewManagerModel viewManagerModel,
                                                                    ShowAllListsViewModel showAllListsViewModel,
                                                                    ShowAllListsDataAccessInterface showAllListsDataAccessObject){
        ShowAllListsOutputBoundary showAllListsOutputPresenter = new ShowAllListsPresenter(showAllListsViewModel, viewManagerModel);

        ShowAllListsInteractor showAllListsInteractor = new ShowAllListsInteractor(
                showAllListsDataAccessObject, showAllListsOutputPresenter);

        return new ShowAllListsController(showAllListsInteractor);
    }

    private static CreateListController createCreateListUseCase(ViewManagerModel viewManagerModel,
                                                                CreateListViewModel createListViewModel,
                                                                CreateListDataAccessInterface createListDataAccessObject) throws IOException {
        CreateListOutputBoundary createListPresenter = new CreateListPresenter(createListViewModel, viewManagerModel);

        CreateListInteractor createListInteractor = new CreateListInteractor(
                createListDataAccessObject, createListPresenter);

        return new CreateListController(createListInteractor);
    }

    private static ShowBooksInListController createShowBooksInListUseCase(ViewManagerModel viewManagerModel,
                                                                          ShowBooksInListViewModel showBooksInListViewModel,
                                                                          ShowBooksInListDataAccessInterface showBooksInListDataAccessObject){
        ShowBooksInListOutputBoundary showBooksInListOutputPresenter = new ShowBooksInListPresenter(showBooksInListViewModel, viewManagerModel);

        ShowBooksInListInteractor showBooksInListInteractor = new ShowBooksInListInteractor(
                showBooksInListDataAccessObject, showBooksInListOutputPresenter);

        return new ShowBooksInListController(showBooksInListInteractor);
    }

    private  static ReadingHistoryController createHistoryController(ViewManagerModel viewManagerModel,
                                                                    ReadingHistoryViewModel readingHistoryViewModel,
                                                                    ReadingHistoryDataAccessInterface readingHistoryDataAcessObject) throws  IOException{
        ReadingHistoryOutputBoundary readingHistoryPresenter = new ReadingHistoryPresenter(readingHistoryViewModel, viewManagerModel);
        ReadingHistoryInteractor interactor = new ReadingHistoryInteractor(readingHistoryPresenter, readingHistoryDataAcessObject);

        return new ReadingHistoryController(interactor);
    }

    /*private static AddingHistoryController createAddHistoryController(ViewManagerModel viewManagerModel, AddingHistoryViewModel addingHistoryViewModel,
                                                                      AddingHistoryDataAccessInterface addingHistoryDataAccessObj) throws IOException{
        AddingHistoryOutputBoundary addingHistoryPresenter = new AddingHistoryPresenter(viewManagerModel, addingHistoryViewModel);
        AddingHistoryInteractor interactor = new AddingHistoryInteractor(addingHistoryPresenter,addingHistoryDataAccessObj);
        return new AddingHistoryController(interactor);
    }

     */
}

package app;

import interface_adapter.ViewManagerModel;
import user_manage.data_access.FileCollectionDataAccessObject;
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
import view.ShowAllListsView;
import view.UserCenter.UserCenterViewModel;

import javax.swing.*;
import java.io.IOException;

public class ShowAllListsUseCaseFactory {
    private ShowAllListsUseCaseFactory(){}

    public static ShowAllListsView create(ViewManagerModel viewManagerModel,
                                          ShowAllListsViewModel showAllListsViewModel,
                                          CreateListViewModel createListViewModel,
                                          UserCenterViewModel userCenterViewModel,
                                          ShowBooksInListViewModel showBooksInListViewModel,
                                          FileCollectionDataAccessObject collectionDataAccessObject) {
        try {
            ShowAllListsController showAllListsController = createShowAllListsUseCase(viewManagerModel, showAllListsViewModel,
                    collectionDataAccessObject);
            CreateListController createListController = createCreateListUseCase(viewManagerModel,createListViewModel,
                    collectionDataAccessObject);
            ShowBooksInListController showBooksInListController = createShowBooksInListUseCase(viewManagerModel, showBooksInListViewModel,
                    collectionDataAccessObject);
            return new ShowAllListsView(showAllListsController, showAllListsViewModel, viewManagerModel,
                    createListController, createListViewModel, userCenterViewModel, showBooksInListViewModel, showBooksInListController);
        } catch (IOException e){
            JOptionPane.showMessageDialog(null, "Could not open review data file.");
        }
        return null;
    }

    private static ShowAllListsController createShowAllListsUseCase(ViewManagerModel viewManagerModel,
                                                                    ShowAllListsViewModel showAllListsViewModel,
                                                                    ShowAllListsDataAccessInterface showAllListsDataAccessObject) throws IOException {
        ShowAllListsOutputBoundary showAllListsPresenter = new ShowAllListsPresenter(showAllListsViewModel, viewManagerModel);

        ShowAllListsInteractor showAllListsInteractor = new ShowAllListsInteractor(
                showAllListsDataAccessObject, showAllListsPresenter);

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
}

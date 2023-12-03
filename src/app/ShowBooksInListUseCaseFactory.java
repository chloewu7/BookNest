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
import view.ShowBooksInListView;
import view.UserCenter.UserCenterViewModel;

import javax.swing.*;
import java.io.IOException;

public class ShowBooksInListUseCaseFactory {
    private ShowBooksInListUseCaseFactory(){}

    public static ShowBooksInListView create(ViewManagerModel viewManagerModel,
                                             ShowBooksInListViewModel showBooksInListViewModel,
                                             UserCenterViewModel userCenterViewModel,
                                             FileCollectionDataAccessObject collectionDataAccessObject) {
        try {
            ShowBooksInListController showBooksInListController = createShowBooksInListUseCase(viewManagerModel,
                    showBooksInListViewModel, collectionDataAccessObject);
            return new ShowBooksInListView(showBooksInListController, showBooksInListViewModel, viewManagerModel,
                    userCenterViewModel);
        } catch (IOException e){
            JOptionPane.showMessageDialog(null, "Could not open review data file.");
        }
        return null;
    }

    private static ShowBooksInListController createShowBooksInListUseCase(ViewManagerModel viewManagerModel,
                                                                          ShowBooksInListViewModel showBooksInListViewModel,
                                                                          ShowBooksInListDataAccessInterface showBooksInListDataAccessObject) throws IOException {
        ShowBooksInListOutputBoundary showBooksInListPresenter = new ShowBooksInListPresenter(showBooksInListViewModel, viewManagerModel);

        ShowBooksInListInteractor showBooksInListInteractor = new ShowBooksInListInteractor(
                showBooksInListDataAccessObject, showBooksInListPresenter);

        return new ShowBooksInListController(showBooksInListInteractor);
    }
}

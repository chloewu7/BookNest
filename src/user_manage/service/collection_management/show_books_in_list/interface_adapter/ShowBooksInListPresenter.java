package user_manage.service.collection_management.show_books_in_list.interface_adapter;

import interface_adapter.ViewManagerModel;
import user_manage.service.collection_management.show_all_lists.interface_adapter.ShowAllListsState;
import user_manage.service.collection_management.show_books_in_list.ShowBooksInListOutputBoundary;
import user_manage.service.collection_management.show_books_in_list.ShowBooksInListOutputData;

public class ShowBooksInListPresenter implements ShowBooksInListOutputBoundary {

    private final ShowBooksInListViewModel showBooksInListViewModel;
    private ViewManagerModel viewManagerModel;

    public ShowBooksInListPresenter(ShowBooksInListViewModel showBooksInListViewModel, ViewManagerModel viewManagerModel) {
        this.showBooksInListViewModel = showBooksInListViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(ShowBooksInListOutputData books) {
        ShowBooksInListState showBooksInListState = showBooksInListViewModel.getState();
        showBooksInListState.setBooks(books.getBooks());
        showBooksInListState.setListName(books.getListName());
        this.showBooksInListViewModel.setState(showBooksInListState);
        showBooksInListViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(showBooksInListViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}

package user_manage.service.collection_management.show_books_in_list.interface_adapter;

import user_manage.service.collection_management.show_books_in_list.ShowBooksInListOutputBoundary;
import user_manage.service.collection_management.show_books_in_list.ShowBooksInListOutputData;

public class ShowBooksInListPresenter implements ShowBooksInListOutputBoundary {

    private final ShowBooksInListViewModel showBooksInListViewModel;

    public ShowBooksInListPresenter(ShowBooksInListViewModel showBooksInListViewModel) {
        this.showBooksInListViewModel = showBooksInListViewModel;
    }

    @Override
    public void prepareSuccessView(ShowBooksInListOutputData books) {

    }
}

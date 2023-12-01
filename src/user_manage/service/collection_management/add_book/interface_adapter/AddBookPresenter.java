package user_manage.service.collection_management.add_book.interface_adapter;

import user_manage.service.collection_management.add_book.AddBookOutputBoundary;

public class AddBookPresenter implements AddBookOutputBoundary {
    private final AddBookViewModel addBookViewModel;

    public AddBookPresenter(AddBookViewModel addBookViewModel) {
        this.addBookViewModel = addBookViewModel;
    }

    @Override
    public void prepareSuccessView(String response) {

    }

    @Override
    public void prepareFailView(String error) {

    }
}

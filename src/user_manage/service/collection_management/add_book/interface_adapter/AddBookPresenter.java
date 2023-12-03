package user_manage.service.collection_management.add_book.interface_adapter;

import user_manage.service.collection_management.add_book.AddBookOutputBoundary;

public class AddBookPresenter implements AddBookOutputBoundary {
    private final AddBookViewModel addBookViewModel;


    public AddBookPresenter(AddBookViewModel addBookViewModel) {
        this.addBookViewModel = addBookViewModel;
    }

    @Override
    public void prepareSuccessView(String response) {
        AddBookState addBookState = addBookViewModel.getState();
        addBookState.setAddBookSuccess(response);
        addBookViewModel.firePropertyChanged();
    }

    public void prepareFailView(String error) {
        AddBookState addBookState = addBookViewModel.getState();
        addBookState.setAddBookError(error);
        addBookViewModel.firePropertyChanged();
    }
}

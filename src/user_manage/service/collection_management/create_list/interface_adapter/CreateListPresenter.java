package user_manage.service.collection_management.create_list.interface_adapter;

import interface_adapter.ViewManagerModel;
import user_manage.service.collection_management.add_book.interface_adapter.AddBookState;
import user_manage.service.collection_management.create_list.CreateListOutputBoundary;
import user_manage.service.collection_management.create_list.CreateListOutputData;
import user_manage.service.reading_review.show_all_reviews.interface_adapter.ShowAllReviewsState;

public class CreateListPresenter implements CreateListOutputBoundary {
    private final CreateListViewModel createListViewModel;
    private ViewManagerModel viewManagerModel;
    public CreateListPresenter(CreateListViewModel createListViewModel, ViewManagerModel viewManagerModel) {
        this.createListViewModel = createListViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(CreateListOutputData createListOutputData) {
        CreateListState createListState = createListViewModel.getState();
        createListState.setListsName(createListOutputData.getListNames());

        this.createListViewModel.setState(createListState);

        createListViewModel.firePropertyChanged();
        viewManagerModel.setActiveView(createListViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        CreateListState createListState = createListViewModel.getState();
        createListState.setCreateListError(error);
        createListViewModel.firePropertyChanged();
    }
}

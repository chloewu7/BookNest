package user_manage.service.collection_management.create_list.interface_adapter;

import user_manage.service.collection_management.create_list.CreateListOutputBoundary;
import user_manage.service.collection_management.create_list.CreateListOutputData;

public class CreateListPresenter implements CreateListOutputBoundary {
    private final CreateListViewModel createListViewModel;

    public CreateListPresenter(CreateListViewModel createListViewModel) {
        this.createListViewModel = createListViewModel;
    }

    @Override
    public void prepareSuccessView(CreateListOutputData list) {

    }

    @Override
    public void prepareFailView(String error) {

    }
}

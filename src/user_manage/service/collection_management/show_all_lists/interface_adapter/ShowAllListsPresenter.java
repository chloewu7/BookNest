package user_manage.service.collection_management.show_all_lists.interface_adapter;

import user_manage.service.collection_management.show_all_lists.ShowAllListsOutputBoundary;
import user_manage.service.collection_management.show_all_lists.ShowAllListsOutputData;

public class ShowAllListsPresenter implements ShowAllListsOutputBoundary {
    private final ShowAllListsViewModel showAllListsViewModel;


    public ShowAllListsPresenter(ShowAllListsViewModel showAllListsViewModel) {
        this.showAllListsViewModel = showAllListsViewModel;
    }

    @Override
    public void prepareSuccessView(ShowAllListsOutputData allListsName) {

    }
}

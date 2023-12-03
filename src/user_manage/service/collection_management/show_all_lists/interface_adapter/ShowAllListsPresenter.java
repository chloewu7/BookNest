package user_manage.service.collection_management.show_all_lists.interface_adapter;

import interface_adapter.ViewManagerModel;
import user_manage.service.collection_management.show_all_lists.ShowAllListsOutputBoundary;
import user_manage.service.collection_management.show_all_lists.ShowAllListsOutputData;

public class ShowAllListsPresenter implements ShowAllListsOutputBoundary {
    private final ShowAllListsViewModel showAllListsViewModel;
    private ViewManagerModel viewManagerModel;

    public ShowAllListsPresenter(ShowAllListsViewModel showAllListsViewModel, ViewManagerModel viewManagerModel) {
        this.showAllListsViewModel = showAllListsViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(ShowAllListsOutputData allListsName) {
        ShowAllListsState showAllListsState = showAllListsViewModel.getState();
        showAllListsState.setListsName(allListsName.getAllLists());
        this.showAllListsViewModel.setState(showAllListsState);
        showAllListsViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(showAllListsViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}

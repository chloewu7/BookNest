package user_manage.service.history.add_history.Interface_adapter;

import interface_adapter.ViewManagerModel;
import user_manage.service.history.add_history.AddingHistoryOutputBoundary;
import user_manage.service.history.add_history.AddingHistoryOutputData;
import view.ViewManager;

public class AddingHistoryPresenter implements AddingHistoryOutputBoundary {

    private final AddingHistoryViewModel addingHistoryViewModel;
    private final ViewManagerModel viewManagerModel;

    public AddingHistoryPresenter(ViewManagerModel viewManagerModel, AddingHistoryViewModel addingHistoryViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.addingHistoryViewModel = addingHistoryViewModel;
    }

    @Override
    public void prepareSuccessView(AddingHistoryOutputData response) {
        // Update the view model with success information
        AddingHistoryState state = addingHistoryViewModel.getState();
        state.setSuccessMessage(response.getMessage());
        addingHistoryViewModel.setState(state);
        addingHistoryViewModel.firePropertyChanged();

        // Optionally, switch to a different view
        // viewManagerModel.setActiveView("SomeOtherView");
        // viewManagerModel.firePropertyChanged();
    }


    @Override
    public void prepareFailView(String error) {
        // Update the view model with error information
        AddingHistoryState state = addingHistoryViewModel.getState();
        state.setErrorMessage(error);
        addingHistoryViewModel.setState(state);
        addingHistoryViewModel.firePropertyChanged();
    }
}


package user_manage.service.history.read_history.interface_adpter;

import interface_adapter.ViewManagerModel;
import user_manage.service.history.read_history.ReadingHistoryOutputBoundary;
import user_manage.service.history.read_history.ReadingHistoryOutputData;
import view.ViewManager;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class ReadingHistoryPresenter implements ReadingHistoryOutputBoundary {
    private final ReadingHistoryViewModel viewModel;

    private final ViewManagerModel viewManagerModel;

    public ReadingHistoryPresenter(ReadingHistoryViewModel viewModel, ViewManagerModel viewManagerModel) {
        this.viewModel = viewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void presentHistory(ReadingHistoryOutputData outputData) {
        List<String> history = outputData.getHistory();

        ReadingHistoryState state = viewModel.getState();
        state.setHistory(history);
        this.viewModel.setState(state);
        //viewModel.firePropertyChanged();


        // Optionally, update the view using the view manager
        //viewManagerModel.updateView("History");
        //viewManagerModel.firePropertyChanged();
    }
}

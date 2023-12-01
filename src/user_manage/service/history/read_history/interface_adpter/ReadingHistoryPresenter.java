package user_manage.service.history.read_history.interface_adpter;

import interface_adapter.ViewManagerModel;
import user_manage.service.history.read_history.ReadingHistoryOutputBoundary;
import user_manage.service.history.read_history.ReadingHistoryOutputData;
import view.ViewManager;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
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
        Map<LocalDateTime, String> history = outputData.getHistory();

        // Sort the history by timestamp and format it for display
        LinkedHashMap<LocalDateTime, String> sortedHistory = history.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));

        // Update the ViewModel with the formatted history
        viewModel.setHistory(sortedHistory);
        viewModel.notifyUpdate();

        // Optionally, update the view using the view manager
        viewManagerModel.updateView("ReadingHistoryView");
    }

    @Override
    public void handleFailure(String errorMessage) {
        viewModel.setErrorMessage(errorMessage);
        viewModel.notifyUpdate();

        // Optionally, update the view using the view manager
        viewManagerModel.updateView("ErrorView");
    }
}

package user_manage.service.history.read_history;

import user_manage.entity.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReadingHistoryInteractor implements ReadingHistoryInputBoundary {
    private final ReadingHistoryOutputBoundary outputBoundary;
    private final ReadingHistoryDataAccessInterface historyDataAccessObject;

    public ReadingHistoryInteractor(ReadingHistoryOutputBoundary outputBoundary, ReadingHistoryDataAccessInterface historyDataAccessObject) {
        this.outputBoundary = outputBoundary;
        this.historyDataAccessObject = historyDataAccessObject;
    }

    @Override
    public void execute(ReadingHistoryInputData readingHistoryInputData) {
        String user = readingHistoryInputData.getUserName();

        List<String> history = historyDataAccessObject.getHistoryByUserId(user);

        // Pass the history data to the output boundary
        ReadingHistoryOutputData outputData = new ReadingHistoryOutputData(history);
        outputBoundary.presentHistory(outputData);
    }
}
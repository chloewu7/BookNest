package user_manage.service.history.read_history;

import user_manage.entity.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReadingHistoryInteractor implements ReadingHistoryInputBoundary {
    private final ReadingHistoryOutputBoundary outputBoundary;
    private final ReadingHistoryDataAccessInterface userDataAccessObject;

    public ReadingHistoryInteractor(ReadingHistoryOutputBoundary outputBoundary, ReadingHistoryDataAccessInterface userDataAccessObject) {
        this.outputBoundary = outputBoundary;
        this.userDataAccessObject = userDataAccessObject;
    }

    @Override
    public void execute(ReadingHistoryInputData readingHistoryInputData) {
        try {
            // Assuming the user's ID is retrieved from the context/session
            //String userId = getCurrentUserId();
            String username = readingHistoryInputData.getUserName();
            if (!userDataAccessObject.user_exist(username)) {
                outputBoundary.handleFailure("User not found.");
            }

            // Create history record and add it to the user

            userDataAccessObject.addHistoryToUser(username, readingHistoryInputData.getBookName());

            List<String> history = userDataAccessObject.getHistoryByUserId(username);

            // Pass the history data to the output boundary
            ReadingHistoryOutputData outputData = new ReadingHistoryOutputData(history);
            outputBoundary.presentHistory(outputData);
        }catch (Exception e) {
            // Handle any exceptions and pass error messages to the output boundary
            outputBoundary.handleFailure(e.getMessage());
        }
    }

    private String getCurrentUserId() {
        // Implement logic to retrieve the current user's ID
        // This could be from a session, context, or authentication token
        return "user123";
    }
}
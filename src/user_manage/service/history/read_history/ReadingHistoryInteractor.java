package user_manage.service.history.read_history;

import java.time.LocalDateTime;
import java.util.HashMap;

public class ReadingHistoryInteractor implements ReadingHistoryInputBoundary {
    private final ReadingHistoryOutputBoundary outputBoundary;
    private final ReadingHistoryDataAccessInterface userDataAccessObject;

    public ReadingHistoryInteractor(ReadingHistoryOutputBoundary outputBoundary, ReadingHistoryDataAccessInterface userDataAccessObject) {
        this.outputBoundary = outputBoundary;
        this.userDataAccessObject = userDataAccessObject;
    }

    @Override
    public void execute() {
        try {
            // Assuming the user's ID is retrieved from the context/session
            String userId = getCurrentUserId();
            HashMap<LocalDateTime, String> history = userDataAccessObject.getUserHistory(userId);

            // Pass the history data to the output boundary
            ReadingHistoryOutputData outputData = new ReadingHistoryOutputData(history);
            outputBoundary.presentHistory(outputData);
        } catch (Exception e) {
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
package user_manage.service.history.read_history;

import user_manage.service.login.LoginOutputData;

import java.util.HashMap;
import java.util.List;
import java.time.LocalDateTime;

public interface ReadingHistoryOutputBoundary {

    void presentHistory(ReadingHistoryOutputData outputData);

    /**
     * Method to handle the scenario when reading history cannot be fetched.
     * @param errorMessage A message describing the error.
     */
}
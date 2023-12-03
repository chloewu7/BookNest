package user_manage.service.history.add_history.Interface_adapter;

import user_manage.service.history.add_history.AddingHistoryInputBoundary;
import user_manage.service.history.add_history.AddingHistoryInputData;

import java.time.LocalDateTime;

public class AddingHistoryController {
    private final AddingHistoryInputBoundary addingHistoryUseCaseInteractor;

    public AddingHistoryController(AddingHistoryInputBoundary addingHistoryUseCaseInteractor) {
        this.addingHistoryUseCaseInteractor = addingHistoryUseCaseInteractor;
    }

    public void execute(String userId, String bookname) {
        AddingHistoryInputData addingHistoryInputData = new AddingHistoryInputData(
                userId, bookname);

        addingHistoryUseCaseInteractor.execute(addingHistoryInputData);
    }
}


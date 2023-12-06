package user_manage.service.history.read_history.interface_adpter;

//import user_manage.service.history.add_history.AddingHistoryInputData;
import user_manage.service.history.read_history.ReadingHistoryInputBoundary;
import user_manage.service.history.read_history.ReadingHistoryInputData;

public class ReadingHistoryController{
    private final ReadingHistoryInputBoundary interactor;

    public ReadingHistoryController(ReadingHistoryInputBoundary interactor) {
        this.interactor = interactor;
    }

    /**
     * Triggers the execution of the ReadingHistory use case.
     * This method can be called by the UI layer in response to a user action, such as clicking a 'View History' button.
     */


    public void execute(String userId, String bookname) {
        ReadingHistoryInputData readingHistoryInputData = new ReadingHistoryInputData(
                userId, bookname);

        interactor.execute(readingHistoryInputData);
    }
}


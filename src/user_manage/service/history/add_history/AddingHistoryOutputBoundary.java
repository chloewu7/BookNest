package user_manage.service.history.add_history;

public interface AddingHistoryOutputBoundary {

    void prepareSuccessView(AddingHistoryOutputData user);

    void prepareFailView(String e);
}

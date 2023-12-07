package user_manage.service.history.add_history;

import user_manage.entity.History;
import user_manage.entity.HistoryFactory;
import user_manage.entity.User;

public class AddingHistoryInteractor implements AddingHistoryInputBoundary {

    private final AddingHistoryOutputBoundary addingHistoryPresenter;
    private final AddingHistoryDataAccessInterface historyDataAccessObject;
    //private final HistoryFactory historyFactory;

    // Constructor with dependency injection for the output boundary, data access object, and history factory
    public AddingHistoryInteractor(AddingHistoryOutputBoundary addingHistoryOutputBoundary, AddingHistoryDataAccessInterface historyDataAccessObject) {
        this.historyDataAccessObject = historyDataAccessObject;
        this.addingHistoryPresenter = addingHistoryOutputBoundary;
        //this.historyFactory = historyFactory;
    }

    @Override
    public void execute(AddingHistoryInputData addingHistoryInputData) {
        historyDataAccessObject.addHistoryToUser(addingHistoryInputData.getUserName(), addingHistoryInputData.getBookName());

        AddingHistoryOutputData addingHistoryOutputData = new AddingHistoryOutputData(true, "History added successfully for user " + addingHistoryInputData.getUserName());
        addingHistoryPresenter.prepareSuccessView(addingHistoryOutputData);
    }
}
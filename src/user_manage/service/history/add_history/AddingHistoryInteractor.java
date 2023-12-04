package user_manage.service.history.add_history;

import user_manage.entity.History;
import user_manage.entity.HistoryFactory;
import user_manage.entity.User;

public class AddingHistoryInteractor implements AddingHistoryInputBoundary {

    private final AddingHistoryOutputBoundary addingHistoryPresenter;
    private final AddingHistoryDataAccessInterface userDataAccessObject;
    private final HistoryFactory historyFactory;

    // Constructor with dependency injection for the output boundary, data access object, and history factory
    public AddingHistoryInteractor(AddingHistoryOutputBoundary addingHistoryOutputBoundary, AddingHistoryDataAccessInterface userDataAccessObject, HistoryFactory historyFactory) {
        this.userDataAccessObject = userDataAccessObject;
        this.addingHistoryPresenter = addingHistoryOutputBoundary;
        this.historyFactory = historyFactory;
    }

    @Override
    public void execute(AddingHistoryInputData addingHistoryInputData) {
        try {
            // Business logic for adding history
            User user = userDataAccessObject.getUserByName(addingHistoryInputData.getUserName());
            if (user == null) {
                addingHistoryPresenter.prepareFailView("User not found.");
            }

            // Create history record and add it to the user
            History history = historyFactory.create(addingHistoryInputData.getBookName());
            userDataAccessObject.addHistoryToUser(user, history);

            // If successful, prepare the success view
            AddingHistoryOutputData addingHistoryOutputData = new AddingHistoryOutputData(true, "History added successfully for user " + addingHistoryInputData.getUserName());
            addingHistoryPresenter.prepareSuccessView(addingHistoryOutputData);
        } catch (Exception e) {
            // Handle any exceptions or failure scenarios
            addingHistoryPresenter.prepareFailView(e.getMessage());
        }
    }
}
package user_manage.service.history.add_history;

import user_manage.entity.User;

public class AddingHistoryInteractor implements AddingHistoryInputBoundary {

    private final AddingHistoryOutputBoundary addingHistoryPresenter;
    private final AddingHistoryDataAccessInterface userDataAccessObject;

    // Constructor with dependency injection for the output boundary and data access object
    public AddingHistoryInteractor(AddingHistoryOutputBoundary addingHistoryOutputBoundary, AddingHistoryDataAccessInterface userDataAccessInterface) {
        this.userDataAccessObject = userDataAccessInterface;
        this.addingHistoryPresenter = addingHistoryOutputBoundary;
    }

    @Override
    public void execute(AddingHistoryInputData addingHistoryInputData) {
        try {
            // Business logic for adding history
            // For example, validate input data, interact with the database, etc.

            // Assuming we have a method to get the User object by ID
            User user = userDataAccessObject.getUserByName(addingHistoryInputData.getUserName());
            if (user == null) {
                addingHistoryPresenter.prepareFailView("User not found.");
            }

            // Add history record to the user
            String historyRecord = "Action: " + addingHistoryInputData.getActionType() + ", Book ID: " + addingHistoryInputData.getBookId();
            userDataAccessObject.addHistoryToUser(user, historyRecord);

            // If successful, prepare the success view
            AddingHistoryOutputData addingHistoryOutputData = new AddingHistoryOutputData(true, "History added successfully for user " + addingHistoryInputData.getUserName());
            addingHistoryPresenter.prepareSuccessView(addingHistoryOutputData);
        } catch (Exception e) {
            // Handle any exceptions or failure scenarios
            // Prepare the failed view with appropriate message or error details
            addingHistoryPresenter.prepareFailView(e.getMessage());
        }
    }
}
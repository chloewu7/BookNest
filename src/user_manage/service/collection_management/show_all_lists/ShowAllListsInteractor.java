package user_manage.service.collection_management.show_all_lists;

import user_manage.service.collection_management.add_book.AddBookOutputData;

public class ShowAllListsInteractor implements ShowAllListsInputBoundary{

    final ShowAllListsDataAccessInterface showAllListsDataAccessObject;

    final ShowAllListsOutputBoundary showAllListsPresenter;

    public ShowAllListsInteractor(ShowAllListsDataAccessInterface showAllListsDataAccessObject,
                                  ShowAllListsOutputBoundary showAllListsPresenter) {
        this.showAllListsDataAccessObject = showAllListsDataAccessObject;
        this.showAllListsPresenter = showAllListsPresenter;
    }

    @Override
    public void execute(ShowAllListsInputData showAllListsInputData) {
        String userName = showAllListsInputData.getUserName();
        ShowAllListsOutputData showAllListsOutputData =
                new ShowAllListsOutputData(showAllListsDataAccessObject.getListsName(userName));
        showAllListsPresenter.prepareSuccessView(showAllListsOutputData);
    }

    @Override
    public void executeWhenSearch(ShowAllListsInputData showAllListsInputData) {
        String userName = showAllListsInputData.getUserName();
        ShowAllListsOutputData showAllListsOutputData =
                new ShowAllListsOutputData(showAllListsDataAccessObject.getListsName(userName));
        showAllListsPresenter.prepareSuccessView(showAllListsOutputData);
    }
}

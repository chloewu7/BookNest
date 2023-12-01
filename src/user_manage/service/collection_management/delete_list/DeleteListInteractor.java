package user_manage.service.collection_management.delete_list;

import java.util.List;

public class DeleteListInteractor implements DeleteListInputBoundary{
    final DeleteListDataAccessInterface deleteListDataAccessObject;

    final DeleteListOutputBoundary deleteListPresenter;

    public DeleteListInteractor(DeleteListDataAccessInterface deleteListDataAccessObject,
                                DeleteListOutputBoundary deleteListPresenter) {
        this.deleteListDataAccessObject = deleteListDataAccessObject;
        this.deleteListPresenter = deleteListPresenter;
    }

    @Override
    public void execute(DeleteListInputData deleteListInputData) {
        String userName = deleteListInputData.getUserName();
        String listName = deleteListInputData.getListName();

        deleteListDataAccessObject.deleteCollectionList(userName, listName);
        List<String> existLists = deleteListDataAccessObject.getListsName(userName);
        DeleteListOutputData deleteListOutputData = new DeleteListOutputData(existLists);

        deleteListPresenter.prepareSuccessView(deleteListOutputData);
    }
}

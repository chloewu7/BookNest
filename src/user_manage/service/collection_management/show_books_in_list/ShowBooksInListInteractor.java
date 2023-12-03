package user_manage.service.collection_management.show_books_in_list;

import user_manage.entity.CollectionList;
import user_manage.service.collection_management.show_all_lists.ShowAllListsOutputData;

import java.util.Map;

public class ShowBooksInListInteractor implements ShowBooksInListInputBoundary{
    final ShowBooksInListDataAccessInterface showBooksInListDataAccessObject;

    final ShowBooksInListOutputBoundary showBooksInListPresenter;

    public ShowBooksInListInteractor(ShowBooksInListDataAccessInterface showBooksInListDataAccessObject,
                                     ShowBooksInListOutputBoundary showBooksInListPresenter) {
        this.showBooksInListDataAccessObject = showBooksInListDataAccessObject;
        this.showBooksInListPresenter = showBooksInListPresenter;
    }

    @Override
    public void execute(ShowBooksInListInputData showBooksInListInputData) {
        String userName = showBooksInListInputData.getUserName();
        String listName = showBooksInListInputData.getListName();
        Map<String, String> books= showBooksInListDataAccessObject.getBooksInlist(userName, listName);
        ShowBooksInListOutputData showBooksInListOutputData =
                new ShowBooksInListOutputData(listName, books);

        showBooksInListPresenter.prepareSuccessView(showBooksInListOutputData);
    }
}

package user_manage.service.collection_management.show_books_in_list.interface_adapter;

import user_manage.service.collection_management.show_books_in_list.ShowBooksInListInputBoundary;
import user_manage.service.collection_management.show_books_in_list.ShowBooksInListInputData;

public class ShowBooksInListController {
    final ShowBooksInListInputBoundary showBooksInListInteractor;

    public ShowBooksInListController(ShowBooksInListInputBoundary showBooksInListInteractor) {
        this.showBooksInListInteractor = showBooksInListInteractor;
    }

    public void execute(String userName, String listName){
        ShowBooksInListInputData showBooksInListInputData = new ShowBooksInListInputData(userName, listName);
        showBooksInListInteractor.execute(showBooksInListInputData);
    }
}

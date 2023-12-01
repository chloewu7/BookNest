package user_manage.service.collection_management.add_book.interface_adapter;


import search.entity.Book;
import user_manage.service.collection_management.add_book.AddBookInputBoundary;
import user_manage.service.collection_management.add_book.AddBookInputData;

public class AddBookController {
    final AddBookInputBoundary addBookInteractor;

    public AddBookController(AddBookInputBoundary addBookInteractor) {
        this.addBookInteractor = addBookInteractor;
    }

    public void execute(String userName, String listName, Book book) {
        AddBookInputData addBookInputData = new AddBookInputData(userName, listName, book);

        addBookInteractor.execute(addBookInputData);
    }
}

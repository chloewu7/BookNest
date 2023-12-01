package user_manage.service.collection_management.add_book;

import search.entity.Book;
import user_manage.entity.CollectionList;

import java.util.List;
import java.util.Map;

public class AddBookInteractor implements AddBookInputBoundary{
    final AddBookDataAccessInterface addBookDataAccessObject;

    final AddBookOutputBoundary addBookPresenter;

    public AddBookInteractor(AddBookDataAccessInterface addBookDataAccessObject,
                             AddBookOutputBoundary addBookPresenter) {
        this.addBookDataAccessObject = addBookDataAccessObject;
        this.addBookPresenter = addBookPresenter;
    }

    @Override
    public void execute(AddBookInputData addBookInputData) {
        String userName = addBookInputData.getUserName();
        String listName = addBookInputData.getListName();
        Book book = addBookInputData.getBook();
        String bookTitle = book.getTitle();
        String bookAuthor = book.getAuthor();

        Map<String, String> books = addBookDataAccessObject.getBooksInlist(userName, listName);

        if (books.containsKey(bookTitle) && books.get(bookTitle).equals(bookAuthor)) {
            addBookPresenter.prepareFailView("The book already in the list.");
        } else {
            addBookDataAccessObject.addToCollectionList(userName, listName, bookTitle, bookAuthor);
            addBookPresenter.prepareSuccessView("The book has been added successfully!");
        }
    }
}

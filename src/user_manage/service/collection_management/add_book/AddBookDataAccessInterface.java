package user_manage.service.collection_management.add_book;

import search.entity.Book;
import user_manage.entity.CollectionList;

import java.util.List;
import java.util.Map;

public interface AddBookDataAccessInterface {
    void addToCollectionList(String userName, String listName, String bookTitle, String bookAuthor);

//    CollectionList getCollectionListByName(String userName, String listName);

    Map<String, String> getBooksInlist(String userName, String listName);

}

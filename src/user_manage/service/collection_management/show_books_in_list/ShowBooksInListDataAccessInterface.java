package user_manage.service.collection_management.show_books_in_list;

import search.entity.Book;
import user_manage.entity.CollectionList;

import java.util.List;
import java.util.Map;

public interface ShowBooksInListDataAccessInterface {
//    CollectionList getCollectionListByName(String name);

    Map<String, String> getBooksInlist(String userName, String listName);
}

package user_manage.entity;

import search.entity.Book;

import java.util.List;
import java.util.Map;

public interface CollectionList {
    String getUserName();

    String getListName();

    Map<String, String> getBooks();

    List<String> getBooksName();

}

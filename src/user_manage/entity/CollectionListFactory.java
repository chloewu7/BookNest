package user_manage.entity;

import search.entity.Book;

import java.util.List;
import java.util.Map;

public interface CollectionListFactory {
    CollectionList createCollectionList(String listName, String userName, Map<String, String> books);

}

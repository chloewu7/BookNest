package user_manage.entity;

import search.entity.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CommonCollectionListFactory implements CollectionListFactory {
    public CollectionList createCollectionList(String userName, String listName, Map<String, String> books) {
        return new CommonCollectionList(userName, listName, books);
    }
}
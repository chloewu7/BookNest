package user_manage.service.collection_management.show_books_in_list.interface_adapter;

import user_manage.entity.CollectionList;
import user_manage.entity.CommonCollectionList;

import java.util.List;

public class ShowBooksInListState {
    private List<CollectionList> collectionLists;

    public List<CollectionList> getCollectionLists() {
        return collectionLists;
    }

    public void addCollectionList(CommonCollectionList like) {
    }
}

package user_manage.service.collection_management.add_book.interface_adapter;

import user_manage.entity.CollectionList;
import user_manage.entity.CommonCollectionList;

import java.util.List;

public class AddBookState {
    private List<CollectionList> collectionLists;

    public List<CollectionList> getCollectionLists() {
        return collectionLists;
    }

    public void addCollectionList(CommonCollectionList like) {
    }
}

package user_manage.service.collection_management.delete_list;

import java.util.List;

public interface DeleteListDataAccessInterface {
    List<String> getListsName(String userName);

    void deleteCollectionList(String userName, String listName);
}

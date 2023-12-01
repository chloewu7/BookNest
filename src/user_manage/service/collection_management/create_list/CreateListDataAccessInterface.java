package user_manage.service.collection_management.create_list;

import java.util.List;

public interface CreateListDataAccessInterface {
    List<String> getListsName(String userName);

    void createCollectionList(String userName, String listName);
}

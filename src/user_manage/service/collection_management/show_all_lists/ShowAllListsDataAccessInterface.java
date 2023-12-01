package user_manage.service.collection_management.show_all_lists;

import java.util.List;

public interface ShowAllListsDataAccessInterface {
    List<String> getListsName(String userName);
}

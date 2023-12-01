package user_manage.service.collection_management.show_all_lists;

import java.util.List;

public class ShowAllListsOutputData {
    private List<String> allListsName;

    public ShowAllListsOutputData(List<String> allListsName) {
        this.allListsName = allListsName;
    }

    public List<String> getAllLists(){
        return allListsName;
    }
}

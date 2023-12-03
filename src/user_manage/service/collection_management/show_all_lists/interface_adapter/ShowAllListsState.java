package user_manage.service.collection_management.show_all_lists.interface_adapter;

import user_manage.entity.CollectionList;
import user_manage.entity.CommonCollectionList;
import user_manage.service.collection_management.create_list.interface_adapter.CreateListState;

import java.util.ArrayList;
import java.util.List;

public class ShowAllListsState {
    private String userName = "";
    private List<String> listsName = new ArrayList<String>();

    public ShowAllListsState(ShowAllListsState copy){
        userName = copy.userName;
        listsName = copy.listsName;
    }
    public ShowAllListsState(){}

    public String getUserName() {
        return userName;
    }

    public List<String> getListsName() {
        return listsName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setListsName(List<String> listName) {
        this.listsName = listName;
    }
}

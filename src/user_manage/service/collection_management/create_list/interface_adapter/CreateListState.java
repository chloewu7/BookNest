package user_manage.service.collection_management.create_list.interface_adapter;

import user_manage.entity.CollectionList;
import user_manage.entity.CommonCollectionList;

import java.util.ArrayList;
import java.util.List;

public class CreateListState {
    private String userName = "";
    private List<String> listsName = new ArrayList<String>();
    private String createListError = null;
    public CreateListState(CreateListState copy){
        userName = copy.userName;
        listsName = copy.listsName;
        createListError = copy.createListError;
    }
    public CreateListState(){}

    public String getUserName() {
        return userName;
    }

    public List<String> getListsName() {
        return listsName;
    }

    public String getCreateListError() {
        return createListError;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setListsName(List<String> listName) {
        this.listsName = listName;
    }

    public void setCreateListError(String createListError) {
        this.createListError = createListError;
    }
}

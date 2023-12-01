package user_manage.service.collection_management.create_list;

public class CreateListInputData {
    final private String userName;
    final private String listName;

    public CreateListInputData(String userName, String listName) {
        this.userName = userName;
        this.listName = listName;
    }
    String getUserName() {return userName; }
    String getListName() {
        return listName;
    }
}

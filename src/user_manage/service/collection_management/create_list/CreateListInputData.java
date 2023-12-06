package user_manage.service.collection_management.create_list;

public class CreateListInputData {
    final private String userName;
    final private String listName;

    public CreateListInputData(String userName, String listName) {
        this.userName = userName;
        this.listName = listName;
    }
    public String getUserName() {return userName; }
    public String getListName() {
        return listName;
    }
}

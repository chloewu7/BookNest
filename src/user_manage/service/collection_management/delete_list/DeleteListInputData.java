package user_manage.service.collection_management.delete_list;

public class DeleteListInputData {
    final private String userName;
    final private String listName;

    public DeleteListInputData(String userName, String listName) {
        this.userName = userName;
        this.listName = listName;
    }
    String getUserName() {return userName; }
    String getListName() {
        return listName;
    }
}

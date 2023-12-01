package user_manage.service.collection_management.show_all_lists;

public class ShowAllListsInputData {
    final private String userName;

    public ShowAllListsInputData(String userName) {
        this.userName = userName;
    }
    String getUserName() {return userName; }
}

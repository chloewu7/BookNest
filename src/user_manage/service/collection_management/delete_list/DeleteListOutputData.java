package user_manage.service.collection_management.delete_list;

import java.util.List;

public class DeleteListOutputData {
    private final List<String> listNames;

    public DeleteListOutputData(List<String> listNames) {
        this.listNames = listNames;
    }

    public List<String> getListNames() {
        return listNames;
    }
}

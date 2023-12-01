package user_manage.service.collection_management.create_list;

import java.util.List;

public class CreateListOutputData {
    private final List<String> listNames;

    public CreateListOutputData(List<String> listNames) {
        this.listNames = listNames;
    }

    public List<String> getListNames() {
        return listNames;
    }
}

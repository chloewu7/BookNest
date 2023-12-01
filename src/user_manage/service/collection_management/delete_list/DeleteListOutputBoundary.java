package user_manage.service.collection_management.delete_list;

import user_manage.service.collection_management.create_list.CreateListOutputData;

public interface DeleteListOutputBoundary {
    void prepareSuccessView(DeleteListOutputData lists);
}

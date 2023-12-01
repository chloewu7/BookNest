package user_manage.service.collection_management.create_list;

public interface CreateListOutputBoundary {
    void prepareSuccessView(CreateListOutputData list);
    void prepareFailView(String error);
}

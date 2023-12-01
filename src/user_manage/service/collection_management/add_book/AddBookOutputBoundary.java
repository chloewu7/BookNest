package user_manage.service.collection_management.add_book;

public interface AddBookOutputBoundary {
    void prepareSuccessView(String response);

    void prepareFailView(String error);
}

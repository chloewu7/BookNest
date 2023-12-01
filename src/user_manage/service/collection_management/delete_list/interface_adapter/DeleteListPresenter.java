package user_manage.service.collection_management.delete_list.interface_adapter;

import user_manage.service.collection_management.delete_list.DeleteListOutputBoundary;
import user_manage.service.collection_management.delete_list.DeleteListOutputData;

public class DeleteListPresenter implements DeleteListOutputBoundary {
    private final DeleteListViewModel deleteListViewModel;

    public DeleteListPresenter(DeleteListViewModel deleteListViewModel) {
        this.deleteListViewModel = deleteListViewModel;
    }

    @Override
    public void prepareSuccessView(DeleteListOutputData lists) {

    }
}

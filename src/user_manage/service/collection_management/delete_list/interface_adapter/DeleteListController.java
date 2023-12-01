package user_manage.service.collection_management.delete_list.interface_adapter;


import user_manage.service.collection_management.delete_list.DeleteListInputBoundary;
import user_manage.service.collection_management.delete_list.DeleteListInputData;

public class DeleteListController {
    final DeleteListInputBoundary deleteListInteractor;

    public DeleteListController(DeleteListInputBoundary deleteListInteractor) {
        this.deleteListInteractor = deleteListInteractor;
    }

    public void execute(String userName, String listName){
        DeleteListInputData deleteListInputData = new DeleteListInputData(userName, listName);
        deleteListInteractor.execute(deleteListInputData);
    }
}

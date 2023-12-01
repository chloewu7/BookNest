package user_manage.service.collection_management.create_list.interface_adapter;

import user_manage.service.collection_management.create_list.CreateListInputBoundary;
import user_manage.service.collection_management.create_list.CreateListInputData;

public class CreateListController {
    final CreateListInputBoundary createListInteractor;

    public CreateListController(CreateListInputBoundary createListInteractor) {
        this.createListInteractor = createListInteractor;
    }

    public void execute(String userName, String listName){
        CreateListInputData createListInputData = new CreateListInputData(userName, listName);

        createListInteractor.execute(createListInputData);
    }
}

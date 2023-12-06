package user_manage.service.collection_management.show_all_lists.interface_adapter;

import user_manage.service.collection_management.show_all_lists.ShowAllListsInputBoundary;
import user_manage.service.collection_management.show_all_lists.ShowAllListsInputData;

public class ShowAllListsController {
    final ShowAllListsInputBoundary showAllListsInteractor;

    public ShowAllListsController(ShowAllListsInputBoundary showAllListsInteractor) {
        this.showAllListsInteractor = showAllListsInteractor;
    }

    public void execute(String userName) {
        ShowAllListsInputData showAllListsInputData = new ShowAllListsInputData(userName);

        showAllListsInteractor.execute(showAllListsInputData);
    }

    public void executeWhenSearch(String userName){
        ShowAllListsInputData showAllListsInputData = new ShowAllListsInputData(userName);

        showAllListsInteractor.executeWhenSearch(showAllListsInputData);
    }
}

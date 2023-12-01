package user_manage.service.collection_management.create_list;

import user_manage.service.collection_management.create_list.interface_adapter.CreateListPresenter;

import java.util.List;
import java.util.Map;

public class CreateListInteractor implements CreateListInputBoundary{
    final CreateListDataAccessInterface collectionDataAccessObject;

    final CreateListOutputBoundary createListPresenter;


    public CreateListInteractor(CreateListDataAccessInterface collectionDataAccessObject, CreateListOutputBoundary createListPresenter) {
        this.collectionDataAccessObject = collectionDataAccessObject;
        this.createListPresenter = createListPresenter;
    }

    @Override
    public void execute(CreateListInputData createListInputData) {
        String userName = createListInputData.getUserName();
        String listName = createListInputData.getListName();
        List<String> existLists = collectionDataAccessObject.getListsName(userName);
        if (existLists.contains((listName))){
            createListPresenter.prepareFailView("Collection list already exist!");
        }
        else {
            collectionDataAccessObject.createCollectionList(userName, listName);
            List<String>  ListsName = collectionDataAccessObject.getListsName(userName);

            CreateListOutputData createListOutputData = new CreateListOutputData(ListsName);
            createListPresenter.prepareSuccessView(createListOutputData);


        }
    }
}
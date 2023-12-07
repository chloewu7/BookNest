package user_manage.service.collection_management.create_list.interface_adapter;

import org.junit.jupiter.api.Test;
import interface_adapter.ViewManagerModel;
import user_manage.service.collection_management.create_list.CreateListOutputData;
import user_manage.service.collection_management.create_list.interface_adapter.CreateListPresenter;
import user_manage.service.collection_management.create_list.interface_adapter.CreateListState;
import user_manage.service.collection_management.create_list.interface_adapter.CreateListViewModel;
import user_manage.service.reading_review.show_all_reviews.interface_adapter.ShowAllReviewsState;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class CreateListPresenterTest {

    private static class FakeCreateListViewModel extends CreateListViewModel {
        @Override
        public void firePropertyChanged() {
        }

        @Override
        public void addPropertyChangeListener(PropertyChangeListener listener) {
        }
        public void setState(CreateListState newState) {
            assertEquals(newState.getUserName(), "");
            List<String> list = new ArrayList<String>();
            list.add("List1");
            list.add("List2");
            assertEquals(newState.getListsName(), list);
        }
    }

    private class FakeViewManagerModel extends ViewManagerModel {
        public void setActiveView(String activeView) {
        }
    }

    @Test
    void testPrepareSuccessView() {
        FakeCreateListViewModel fakeViewModel = new FakeCreateListViewModel();
        FakeViewManagerModel fakeViewManagerModel = new FakeViewManagerModel();
        CreateListPresenter createListPresenter = new CreateListPresenter(fakeViewModel, fakeViewManagerModel);

        List<String> listNames = new ArrayList<>();
        listNames.add("List1");
        listNames.add("List2");
        CreateListOutputData createListOutputData = new CreateListOutputData(listNames);

        createListPresenter.prepareSuccessView(createListOutputData);
    }

    @Test
    void testPrepareFailView() {
        FakeCreateListViewModel fakeViewModel = new FakeCreateListViewModel();
        CreateListPresenter createListPresenter = new CreateListPresenter(fakeViewModel, new FakeViewManagerModel());

        String errorMessage = "Failed to create list";
        createListPresenter.prepareFailView(errorMessage);
    }
}

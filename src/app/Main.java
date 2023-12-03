package app;

import interface_adapter.ViewManagerModel;
import search.data_access.SearchDataAccessObject;
import search.service.interface_adapter.SearchController;
import search.service.interface_adapter.SearchViewModel;
import user_manage.data_access.FileCollectionDataAccessObject;
import user_manage.data_access.FileHistoryDataAccessObject;
import user_manage.data_access.FileReviewDataAccessObject;
import user_manage.data_access.FileUserDataAccessObject;
import user_manage.entity.CommonCollectionListFactory;
import user_manage.entity.CommonHisotryFactory;
import user_manage.entity.CommonReviewFactory;
import user_manage.entity.CommonUserFactory;
import user_manage.service.collection_management.add_book.interface_adapter.AddBookViewModel;
import user_manage.service.collection_management.create_list.interface_adapter.CreateListViewModel;
import user_manage.service.collection_management.show_all_lists.interface_adapter.ShowAllListsViewModel;
import user_manage.service.collection_management.show_books_in_list.interface_adapter.ShowBooksInListViewModel;
import user_manage.service.history.add_history.Interface_adapter.AddingHistoryViewModel;
import user_manage.service.history.read_history.interface_adpter.ReadingHistoryViewModel;
import user_manage.service.login.interface_adapter.LoginViewModel;
import user_manage.service.reading_review.show_all_reviews.interface_adapter.ShowAllReviewsViewModel;
import user_manage.service.reading_review.show_my_reviews.interface_adapter.ShowMyReviewsViewModel;
import user_manage.service.reading_review.write_reviews.interface_adapter.WriteReviewsViewModel;
import user_manage.service.signup.interface_adapter.SignupViewModel;
import view.*;
import view.UserCenter.UserCenterView;
import view.UserCenter.UserCenterViewModel;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class Main {

    public static void main(String[] args) throws IOException {

        // The main application window.
        JFrame application = new JFrame("Library Software");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        application.setSize(1000, 600);

        //TODO: Layout might need to be changed in the future. line27-35 are tentative.
        CardLayout cardLayout = new CardLayout();

        // The various View objects. Only one view is visible at a time.
        JPanel views = new JPanel(cardLayout);
        application.add(views);

        // This keeps track of and manages which view is currently showing.
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);

        // create data access object
        SearchDataAccessObject searchDataAccessObject = new SearchDataAccessObject();
        FileCollectionDataAccessObject collectionDataAccessObject;

        FileUserDataAccessObject userDataAccessObject;
        FileReviewDataAccessObject reviewDataAccessObject;

        FileHistoryDataAccessObject readingHistoryDataAccessObject;
        try {
            userDataAccessObject = new FileUserDataAccessObject("./users.csv", new CommonUserFactory());
            reviewDataAccessObject = new FileReviewDataAccessObject("./reviews.csv",
                    new CommonReviewFactory());
            collectionDataAccessObject = new FileCollectionDataAccessObject(new File("./collection.csv"),
                    new CommonCollectionListFactory());
            readingHistoryDataAccessObject = new FileHistoryDataAccessObject(new File("./history.csv"),new
                    CommonHisotryFactory());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Create view model
        UserCenterViewModel userCenterViewModel = new UserCenterViewModel();

        ShowAllListsViewModel showAllListsViewModel = new ShowAllListsViewModel();
        CreateListViewModel createListViewModel = new CreateListViewModel();
        ShowBooksInListViewModel showBooksInListViewModel = new ShowBooksInListViewModel();
        AddBookViewModel addBookViewModel = new AddBookViewModel();

        ReadingHistoryViewModel readingHistoryViewModel = new ReadingHistoryViewModel();
        AddingHistoryViewModel addingHistoryViewModel = new AddingHistoryViewModel();

        SearchViewModel searchViewModel = new SearchViewModel();

        ShowAllReviewsViewModel showAllReviewsViewModel = new ShowAllReviewsViewModel();
        ShowMyReviewsViewModel showMyReviewsViewModel = new ShowMyReviewsViewModel();
        WriteReviewsViewModel writeReviewsViewModel = new WriteReviewsViewModel();


        // create Search View

        java.util.List<JPanel> searchViewList = new ArrayList<>();
        searchViewList = SearchUseCaseFactory.create(viewManagerModel, searchViewModel, searchDataAccessObject,
                showAllReviewsViewModel, writeReviewsViewModel, reviewDataAccessObject, addBookViewModel,
                collectionDataAccessObject, showAllListsViewModel, userCenterViewModel, addingHistoryViewModel);

        SearchView searchView = (SearchView) searchViewList.get(0);
        views.add(searchView, searchView.viewName);

        ShowAllReviewsView showAllReviewsView = (ShowAllReviewsView) searchViewList.get(1);
        views.add(showAllReviewsView, showAllReviewsView.viewName);

        WriteReviewsView writeReviewsView = (WriteReviewsView) searchViewList.get(2);
        views.add(writeReviewsView, writeReviewsView.viewName);

        // create Signup View
        SignupViewModel signupViewModel = new SignupViewModel();
        LoginViewModel loginViewModel = new LoginViewModel();

        SignupView signupView = SignupUseCaseFactory.create(viewManagerModel, signupViewModel, loginViewModel, userDataAccessObject);
        views.add(signupView, signupView.viewName);

        // create Login View
        LoginViewModel loginViewModel1 = new LoginViewModel();

        LoginView loginView = LoginUseCaseFactory.create(viewManagerModel, loginViewModel,searchViewModel,signupViewModel, userDataAccessObject, userDataAccessObject);
        views.add(loginView,loginView.viewName);

        // create ShowAllLists View

        // create WriteReview View
        //WriteReviewsViewModel writeReviewsViewModel = new WriteReviewsViewModel();

        //WriteReviewsView writeReviewsView = WriteReviewsUseCaseFactory.create(viewManagerModel, writeReviewsViewModel, reviewDataAccessObject);
        //views.add(writeReviewsView, writeReviewsView.viewName);

        // create ShowAllReview View

        // create ShowMyReview View
        //ShowMyReviewsViewModel showMyReviewsViewModel = new ShowMyReviewsViewModel();
        //ShowMyReviewsView showMyReviewsView = ShowMyReviewsUseCaseFactory.create(viewManagerModel, showMyReviewsViewModel, reviewDataAccessObject);
        //views.add(showMyReviewsView, showMyReviewsView.viewName);

        //uncomment to see searchView
        //viewManagerModel.setActiveView(searchView.viewName);

        //uncomment to see showAllReviewsView
        //viewManagerModel.setActiveView(showAllReviewsView.viewName);

        //uncomment to see showMyReviewsView
        //viewManagerModel.setActiveView(showMyReviewsView.viewName);

        //uncomment to see writeReviewsView
        //viewManagerModel.setActiveView(writeReviewsView.viewName);

        //uncomment to see loginView
        //viewManagerModel.setActiveView(loginView.viewName);

        // create UserCenter View

        java.util.List<JPanel> userManageViewList = new ArrayList<>();
        userManageViewList = UserCenterFactory.create(viewManagerModel, userCenterViewModel, showMyReviewsViewModel,
                reviewDataAccessObject, searchViewModel, searchDataAccessObject, showAllListsViewModel, collectionDataAccessObject,
                createListViewModel, showBooksInListViewModel,readingHistoryViewModel, readingHistoryDataAccessObject);

        UserCenterView userCenterView = (UserCenterView) userManageViewList.get(0);
        views.add(userCenterView, userCenterView.viewName);

        ShowMyReviewsView showMyReviewsView = (ShowMyReviewsView) userManageViewList.get(1);
        views.add(showMyReviewsView, showMyReviewsView.viewName);

        ShowAllListsView showAllListsView = (ShowAllListsView) userManageViewList.get(2);
        views.add(showAllListsView, showAllListsView.viewName);

        ReadingHistoryView readingHistoryView = (ReadingHistoryView) userManageViewList.get(3);
        views.add(readingHistoryView, readingHistoryView.viewName);
        //TODO: 把个自的View从userManageViewList拿出来 添加到views里
        viewManagerModel.setActiveView(loginView.viewName);

        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setVisible(true);
    }

}
package view;

import interface_adapter.ViewManagerModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import search.service.interface_adapter.SearchViewModel;
import user_manage.data_access.FileCollectionDataAccessObject;
import user_manage.data_access.FileReviewDataAccessObject;
import user_manage.data_access.FileUserDataAccessObject;
import user_manage.entity.CommonCollectionListFactory;
import user_manage.entity.CommonReviewFactory;
import user_manage.entity.CommonUserFactory;
import user_manage.service.collection_management.show_all_lists.ShowAllListsDataAccessInterface;
import user_manage.service.collection_management.show_all_lists.ShowAllListsInteractor;
import user_manage.service.collection_management.show_all_lists.ShowAllListsOutputBoundary;
import user_manage.service.collection_management.show_all_lists.interface_adapter.ShowAllListsController;
import user_manage.service.collection_management.show_all_lists.interface_adapter.ShowAllListsPresenter;
import user_manage.service.collection_management.show_all_lists.interface_adapter.ShowAllListsViewModel;
import user_manage.service.login.LoginDataAccessInterface;
import user_manage.service.login.LoginInteractor;
import user_manage.service.login.LoginOutputBoundary;
import user_manage.service.login.interface_adapter.LoginController;
import user_manage.service.login.interface_adapter.LoginPresenter;
import user_manage.service.login.interface_adapter.LoginViewModel;
import user_manage.service.reading_review.show_all_reviews.ShowAllReviewsDataAccessInterface;
import user_manage.service.signup.interface_adapter.SignupController;
import user_manage.service.signup.interface_adapter.SignupViewModel;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class LoginViewTest {

    private LoginView loginView;
    private ViewManagerModel viewManagerModel;
    private LoginViewModel loginViewModel;
    private SignupViewModel signupViewModel;

    private SearchViewModel searchViewModel;

    private LoginController loginController;

    private SignupController signupController;
    private ShowAllListsController showAllListsController;

    @BeforeEach
    void setUp() throws IOException {
        viewManagerModel = new ViewManagerModel();
        loginViewModel = new LoginViewModel();
        signupViewModel = new SignupViewModel();
        searchViewModel = new SearchViewModel();

        LoginDataAccessInterface loginDataAccessObject = new FileUserDataAccessObject("./users.csv",
                new CommonUserFactory());
        LoginOutputBoundary loginPresenter = new LoginPresenter(viewManagerModel, searchViewModel,loginViewModel);
        LoginInteractor loginInteractor = new LoginInteractor(loginDataAccessObject, loginPresenter);
        loginController = new LoginController(loginInteractor);

        ShowAllListsDataAccessInterface showAllListsDataAccessObject = new
                FileCollectionDataAccessObject(new File("./collection.csv"), new CommonCollectionListFactory());
        ShowAllListsViewModel showAllListsViewModel = new ShowAllListsViewModel();
        ShowAllListsOutputBoundary showAllListsPresenter = new ShowAllListsPresenter(showAllListsViewModel,
                viewManagerModel);
        ShowAllListsInteractor showAllListsInteractor = new ShowAllListsInteractor(showAllListsDataAccessObject,
                showAllListsPresenter);
        showAllListsController = new ShowAllListsController(showAllListsInteractor);

        loginView = new LoginView(loginViewModel, loginController, signupViewModel, signupController, searchViewModel,
                viewManagerModel, showAllListsController);
    }

    @Test
    void loginButtonActionPerformed() {
        // Set the username and password
        loginView.usernameInputField.setText("testUser");
        loginView.passwordInputField.setText("testPass");

        loginView.logIn.doClick();

    }

    @Test
    void signUpButtonActionPerformed() {
        loginView.signUp.doClick();

        assertEquals("sign up", viewManagerModel.getActiveView(),
                "Clicking sign up button should set active view to 'sign up'.");
    }

    @Test
    void actionPerformed() {
    }

    @Test
    void propertyChange() {
    }
}
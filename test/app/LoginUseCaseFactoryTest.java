package app;

import interface_adapter.ViewManagerModel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import search.service.interface_adapter.SearchViewModel;
import user_manage.data_access.FileCollectionDataAccessObject;
import user_manage.data_access.FileUserDataAccessObject;
import user_manage.entity.CommonCollectionListFactory;
import user_manage.entity.CommonUserFactory;
import user_manage.service.collection_management.show_all_lists.interface_adapter.ShowAllListsViewModel;
import user_manage.service.login.interface_adapter.LoginViewModel;
import user_manage.service.signup.interface_adapter.SignupViewModel;
import view.LoginView;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class LoginUseCaseFactoryTest {
    private ViewManagerModel viewManagerModel;
    private LoginViewModel loginViewModel;
    private SearchViewModel searchViewModel;
    private SignupViewModel signupViewModel;
    private ShowAllListsViewModel showAllListsViewModel;
    private FileUserDataAccessObject fileUserDataAccessObject;
    private FileCollectionDataAccessObject fileCollectionDataAccessObject;

    @BeforeEach
    void setUp() throws IOException {
        // Initialize all the necessary real objects
        viewManagerModel = new ViewManagerModel();
        loginViewModel = new LoginViewModel();
        searchViewModel = new SearchViewModel();
        signupViewModel = new SignupViewModel();
        showAllListsViewModel = new ShowAllListsViewModel();

        fileUserDataAccessObject = new FileUserDataAccessObject("./users.csv", new CommonUserFactory());
        fileCollectionDataAccessObject = new FileCollectionDataAccessObject(new File("./collection.csv"),
                new CommonCollectionListFactory());
    }

    @Test
    void testCreateLoginView() {
        // Call the factory method
        LoginView loginView = LoginUseCaseFactory.create(
                viewManagerModel,
                loginViewModel,
                searchViewModel,
                signupViewModel,
                showAllListsViewModel,
                fileUserDataAccessObject,
                fileUserDataAccessObject,
                fileCollectionDataAccessObject
        );

        assertNotNull(loginView, "LoginView should be created");
    }

    @Test
    void create() {
    }

    @AfterEach
    void tearDown() {

    }
}
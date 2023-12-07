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
import view.SignupView;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class SignupUseCaseFactoryTest {

    private ViewManagerModel viewManagerModel;
    private SignupViewModel signupViewModel;
    private FileUserDataAccessObject fileUserDataAccessObject;
    private LoginViewModel loginViewModel;

    @BeforeEach
    void setUp() throws IOException {
        // Initialize all the necessary real objects
        viewManagerModel = new ViewManagerModel();
        signupViewModel = new SignupViewModel();
        loginViewModel = new LoginViewModel();
        fileUserDataAccessObject = new FileUserDataAccessObject("./users.csv", new CommonUserFactory());
    }

    @Test
    void testCreateLoginView() {
        // Call the factory method
        SignupView signupView = SignupUseCaseFactory.create(
                viewManagerModel,
                signupViewModel,
                loginViewModel,
                fileUserDataAccessObject
        );

        assertNotNull(signupView, "SignupView should be created");
    }

    @Test
    void create() {
    }

    @AfterEach
    void tearDown() {

    }
}
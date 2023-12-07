package view;

import interface_adapter.ViewManagerModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import user_manage.data_access.FileUserDataAccessObject;
import user_manage.entity.CommonUserFactory;
import user_manage.entity.UserFactory;
import user_manage.service.login.interface_adapter.LoginViewModel;
import user_manage.service.signup.SignupDataAccessInterface;
import user_manage.service.signup.SignupInputBoundary;
import user_manage.service.signup.SignupInteractor;
import user_manage.service.signup.SignupOutputBoundary;
import user_manage.service.signup.interface_adapter.SignupController;
import user_manage.service.signup.interface_adapter.SignupPresenter;
import user_manage.service.signup.interface_adapter.SignupState;
import user_manage.service.signup.interface_adapter.SignupViewModel;

import java.beans.PropertyChangeEvent;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class SignupViewTest {
    private SignupView signupView;
    private ViewManagerModel viewManagerModel;
    private SignupViewModel signupViewModel;
    private SignupController signupController;

    @BeforeEach
    void setUp() throws IOException {
        viewManagerModel = new ViewManagerModel();
        signupViewModel = new SignupViewModel();

        SignupDataAccessInterface signupDataAccessObject = new
                FileUserDataAccessObject("./users.csv", new CommonUserFactory());
        LoginViewModel loginViewModel = new LoginViewModel();
        SignupOutputBoundary signupPresenter = new SignupPresenter(viewManagerModel, signupViewModel,
                loginViewModel);
        UserFactory userFactory = new CommonUserFactory();
        SignupInputBoundary signupInteractor = new SignupInteractor(signupDataAccessObject,signupPresenter,
                userFactory);
        signupController = new SignupController(signupInteractor);

        signupView = new SignupView(viewManagerModel, signupController,signupViewModel);

    }

    @Test
    void signupButtonActionPerformed() {
        // Set up test data
        signupView.usernameInputField.setText("testUser");
        signupView.passwordInputField.setText("testPass");
        signupView.repeatPasswordInputField.setText("testPass");

        signupView.signupButton.doClick();

    }

    @Test
    void returnButtonActionPerformed() {
        // Simulate clicking the return button
        signupView.returnButton.doClick();

        // Verify that the view is changed to "log in"
        assertEquals("log in", viewManagerModel.getActiveView(),
                "Clicking return button should set active view to 'log in'.");
    }

    @Test
    void propertyChangeUpdatesState() {
        SignupState newState = new SignupState();
        newState.setUsername("username");
        PropertyChangeEvent evt = new PropertyChangeEvent(signupViewModel, "username",
                null, newState);

        signupView.propertyChange(evt);

    }

    @Test
    void actionPerformed() {

    }

    @Test
    void propertyChange() {
    }
}
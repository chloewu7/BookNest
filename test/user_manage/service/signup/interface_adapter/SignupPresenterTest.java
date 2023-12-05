package user_manage.service.signup.interface_adapter;

import interface_adapter.ViewManagerModel;
import org.junit.jupiter.api.Test;
import user_manage.service.login.interface_adapter.LoginState;
import user_manage.service.login.interface_adapter.LoginViewModel;
import user_manage.service.signup.SignupOutputData;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

class SignupPresenterTest {
    public final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    //test signup success cases
    private class MockSuccessSignupViewModel extends SignupViewModel {
        public void setState(SignupState newState) {
            assertEquals("name", newState.getUsername());
            assertEquals("p", newState.getPassword());
            assertEquals("p", newState.getRepeatPassword());
        }
    }

    private class MockSuccessViewManagerModel extends ViewManagerModel{
        public void setActiveView(String activeView) {
            assertEquals(activeView, "log in");
        }
    }

    private class MockLoginViewModel extends LoginViewModel{
        public void setState(LoginState newState) {
            assertEquals("name", newState.getUsername());
        }

    }

    @Test
    void testPrepareSuccessView() {
        MockSuccessSignupViewModel mockSuccessSignupViewModel = new MockSuccessSignupViewModel();
        MockSuccessViewManagerModel mockSuccessViewManagerModel = new MockSuccessViewManagerModel();
        MockLoginViewModel mockLoginViewModel = new MockLoginViewModel();

        SignupPresenter signupPresenter = new SignupPresenter(mockSuccessViewManagerModel, mockSuccessSignupViewModel, mockLoginViewModel);
        SignupOutputData signupOutputData = new SignupOutputData("name", String.valueOf(LocalDateTime.now()), false);
        signupPresenter.prepareLoginView(signupOutputData);
    }

    //test signup fail cases

    private class MockFailViewManagerModel extends ViewManagerModel {
        public void setActiveView(String activeView) {
            assertEquals("sign up", activeView);
        }
    }

    private class MockfailViewModel extends SignupViewModel{
        public void setState(SignupState newState) {
            assertEquals("error", newState.getUsernameError());
        }
    }

    @Test
    void testPrepareNoReviewView() {
        MockfailViewModel mockNameErrorViewModel = new MockfailViewModel();
        MockFailViewManagerModel mockFailViewManagerModel = new MockFailViewManagerModel();
        LoginViewModel loginViewModel = new LoginViewModel();
        SignupPresenter nameErrorPresenter = new SignupPresenter(mockFailViewManagerModel, mockNameErrorViewModel, loginViewModel);
        nameErrorPresenter.prepareFailView("error");
    }
}
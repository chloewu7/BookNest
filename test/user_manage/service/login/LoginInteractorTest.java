package user_manage.service.login;

import org.junit.jupiter.api.Test;
import user_manage.entity.CommonUser;
import user_manage.entity.CommonUserFactory;
import user_manage.entity.User;
import user_manage.entity.UserFactory;
import user_manage.service.signup.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

class LoginInteractorTest {
    public final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private final User user = new CommonUser("name", "password", LocalDateTime.now());

    private class FailLoginAccessStub implements LoginDataAccessInterface {


        @Override
        public void saveNewUser(User user) {
        }

        @Override
        public User get(String name) {
            return user;
        }

        @Override
        public boolean existsByName(String identifier) {
            return false;
        }
    }

    private class SuccessLoginAccessStub implements LoginDataAccessInterface {

        @Override
        public void saveNewUser(User user) {
        }

        @Override
        public User get(String name) {
            return user;
        }

        @Override
        public boolean existsByName(String identifier) {
            return true;
        }
    }

    private class MockFailedPresenter implements LoginOutputBoundary {

        @Override
        public void prepareSuccessView(LoginOutputData user) {
            fail("show success screen when it suppose to tell the user they can not login");
        }

        @Override
        public void prepareFailView(String error) {

            assertEquals("not existing username: Account does not exist.", error);
        }
    }

    private class MockSuccessPresenter implements LoginOutputBoundary {

        @Override
        public void prepareSuccessView(LoginOutputData user) {
            assertEquals(user.getUsername(), "name");
        }

        @Override
        public void prepareFailView(String error) {
            fail("show fail screen when it suppose to log the user in to search page");
        }
    }

    @Test
    void executeWhenUserFailToLogin() {
        FailLoginAccessStub failLoginAccessStub = new FailLoginAccessStub();
        MockFailedPresenter mockFailedPresenter = new MockFailedPresenter();
        LoginInteractor loginInteractor = new LoginInteractor(failLoginAccessStub,
                mockFailedPresenter);
        LoginInputData loginInputData = new LoginInputData("not existing username", "password");
        loginInteractor.execute(loginInputData);
    }

    @Test
    void executeWhenUserCanLogin() {
        SuccessLoginAccessStub successLoginAccessStub = new SuccessLoginAccessStub();
        MockSuccessPresenter mockSuccessPresenter = new MockSuccessPresenter();
        LoginInteractor loginInteractor = new LoginInteractor(successLoginAccessStub, mockSuccessPresenter);
        LoginInputData loginInputdata = new LoginInputData("name", "password");
        loginInteractor.execute(loginInputdata);
    }
}
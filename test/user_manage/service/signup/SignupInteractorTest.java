package user_manage.service.signup;

import org.junit.jupiter.api.Test;
import user_manage.entity.CommonUserFactory;
import user_manage.entity.User;
import user_manage.entity.UserFactory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SignupInteractorTest {
    public final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private class UserExistDataAccessStub implements SignupDataAccessInterface {


        @Override
        public boolean existsByName(String username) {
            return true;
        }

        @Override
        public void saveNewUser(User newuser) {

        }
    }

    private class MockUserExistPresenter implements SignupOutputBoundary {

        @Override
        public void prepareLoginView(SignupOutputData response) {
            fail("show success screen when it suppose to tell the user they can sign up with the existing username");
        }

        @Override
        public void prepareFailView(String error) {
            assertEquals("User already exists.", error);
        }
    }

    private class MockURepeatedPasswordPresenter implements SignupOutputBoundary {

        @Override
        public void prepareLoginView(SignupOutputData response) {
            fail("show success screen when it suppose to tell the user they can sign up with the existing username");
        }

        @Override
        public void prepareFailView(String error) {
            assertEquals("Repeat password doesn't match.", error);
        }
    }
    private class MockUserFactory implements UserFactory{

        @Override
        public User create(String name, String password, LocalDateTime ltd) {
            assertEquals("username", name);
            assertEquals("password", password);
            return null;
        }
    }

    @Test
    void executeWhenUserExistsByName() {
        UserExistDataAccessStub UserExistDataAccessStub = new UserExistDataAccessStub();
        MockUserExistPresenter mockUserExistPresenter = new MockUserExistPresenter();
        UserFactory userFactory = new CommonUserFactory();
        SignupInteractor signupInteractor = new SignupInteractor(UserExistDataAccessStub,
                mockUserExistPresenter, userFactory);
        SignupInputData signupInputData = new SignupInputData("existing username", "p", "p");
        signupInteractor.execute(signupInputData);
        MockURepeatedPasswordPresenter mockURepeatedPasswordPresenter = new MockURepeatedPasswordPresenter();
        MockUserDataAccess mockUserDataAccess = new MockUserDataAccess();
        SignupInteractor signupInteractor2 = new SignupInteractor(mockUserDataAccess, mockURepeatedPasswordPresenter, userFactory);
        SignupInputData signupInputData2 = new SignupInputData("name", "p","rp");
        signupInteractor2.execute(signupInputData2);
    }

    private class MockUserDataAccess implements SignupDataAccessInterface{

        @Override
        public boolean existsByName(String username) {
            return false;
        }

        @Override
        public void saveNewUser(User newuser) {
            assertEquals("username", newuser.getName());
            assertEquals("password", newuser.getPassword());
            assertEquals(LocalDateTime.now().format(formatter), newuser.getCreationTime().format(formatter));
        }
    }

    private class MockSignupPresenter implements SignupOutputBoundary{

        @Override
        public void prepareLoginView(SignupOutputData response) {
            assertEquals("username", response.getUsername());
            assertEquals(String.valueOf(LocalDateTime.now().format(formatter)), response.getCreationTime().substring(0, 10));
        }

        @Override
        public void prepareFailView(String error) {
            fail("show fail screen when it suppose to successfully create new user");
        }
    }

    @Test
    void executeWhenUserCanBeCreated() {
        MockUserDataAccess mockUserDataAccess = new MockUserDataAccess();
        MockSignupPresenter mockSignupPresenter = new MockSignupPresenter();
        UserFactory userFactory = new CommonUserFactory();
        SignupInteractor signupInteractor = new SignupInteractor(mockUserDataAccess,
                mockSignupPresenter, userFactory);
        SignupInputData signupInputData2 = new SignupInputData("username", "password", "password");
        signupInteractor.execute(signupInputData2);

    }
}
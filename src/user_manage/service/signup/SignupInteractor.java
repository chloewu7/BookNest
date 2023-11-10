package user_manage.service.signup;

import user_manage.entity.User;
import user_manage.entity.UserFactory;

import java.time.LocalDateTime;

public class SignupInteractor implements SignupInputBoundary{

    final UserFactory userFactory;
    final SignupOutputBoundary signupPresenter;
    final SignupDataAccessInterface signupDataAccessObject;

    public SignupInteractor(SignupDataAccessInterface signupDataAccessObject,
                            SignupOutputBoundary signupOutputBoundary,
                            UserFactory userFactory) {
        this.signupDataAccessObject = signupDataAccessObject;
        this.signupPresenter = signupOutputBoundary;
        this.userFactory = userFactory;
    }

    @Override
    public void execute(SignupInputData signupInputData) {
        String username = signupInputData.getUsername();
        String password = signupInputData.getPassword();
        String repeatPassword = signupInputData.getRepeatedpassword();
        if (signupDataAccessObject.existsByName(username)){
            signupPresenter.prepareFailView("User already exists.");
        } else if (!password.equals(repeatPassword)){
            signupPresenter.prepareFailView("Repeat password doesn't match.");
        } else {
            LocalDateTime current = LocalDateTime.now();
            User newuser = userFactory.create(username, password, current);
            signupDataAccessObject.saveNewUser(newuser);
            SignupOutputData signupOutputData = new SignupOutputData(username, current.toString(), false);
            signupPresenter.prepareLoginView(signupOutputData);
        }
    }
}

package app;




import interface_adapter.ViewManagerModel;
import search.service.interface_adapter.SearchViewModel;
import user_manage.data_access.FileUserDataAccessObject;
import user_manage.entity.CommonUserFactory;
import user_manage.entity.UserFactory;
import user_manage.service.login.LoginDataAccessInterface;
import user_manage.service.login.LoginInputBoundary;
import user_manage.service.login.LoginInteractor;
import user_manage.service.login.LoginOutputBoundary;
import user_manage.service.login.interface_adapter.LoginController;
import user_manage.service.login.interface_adapter.LoginPresenter;
import user_manage.service.login.interface_adapter.LoginViewModel;
import user_manage.service.signup.SignupDataAccessInterface;
import user_manage.service.signup.SignupInputBoundary;
import user_manage.service.signup.SignupInteractor;
import user_manage.service.signup.SignupOutputBoundary;
import user_manage.service.signup.interface_adapter.SignupController;
import user_manage.service.signup.interface_adapter.SignupPresenter;
import user_manage.service.signup.interface_adapter.SignupViewModel;
import view.LoginView;

import javax.swing.*;
import java.io.IOException;

public class LoginUseCaseFactory {

    /** Prevent instantiation. */
    private LoginUseCaseFactory() {}

    public static LoginView create(
            ViewManagerModel viewManagerModel,
            LoginViewModel loginViewModel,
            SearchViewModel searchViewModel,
            SignupViewModel signupViewModel,
            LoginDataAccessInterface loginDataAccessObject,
            SignupDataAccessInterface signupDataAccessObject) {

        try {
            LoginController loginController = createLoginUseCase(viewManagerModel, loginViewModel, searchViewModel, loginDataAccessObject);
            SignupController signupController =createUserSignupUseCase(viewManagerModel, signupViewModel, loginViewModel, signupDataAccessObject);
            return new LoginView(loginViewModel, loginController,signupViewModel,signupController, searchViewModel,
                    viewManagerModel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }

        return null;
    }

    private static LoginController createLoginUseCase(
            ViewManagerModel viewManagerModel,
            LoginViewModel loginViewModel,
            SearchViewModel searchViewModel,
            LoginDataAccessInterface userDataAccessObject) throws IOException {

        // Notice how we pass this method's parameters to the Presenter.
        LoginOutputBoundary loginOutputBoundary = new LoginPresenter(viewManagerModel, searchViewModel, loginViewModel);

        UserFactory userFactory = new CommonUserFactory();

        LoginInputBoundary loginInteractor = new LoginInteractor(
                userDataAccessObject, loginOutputBoundary);

        return new LoginController(loginInteractor);
    }

    private static SignupController createUserSignupUseCase(ViewManagerModel viewManagerModel,
                                                            SignupViewModel signupViewModel, LoginViewModel loginViewModel,
                                                            SignupDataAccessInterface userDataAccessObject) throws IOException {

        // Notice how we pass this method's parameters to the Presenter.
        SignupOutputBoundary signupOutputBoundary = new SignupPresenter(viewManagerModel, signupViewModel, loginViewModel);

        UserFactory userFactory = new CommonUserFactory();

        SignupInputBoundary userSignupInteractor = new SignupInteractor(
                userDataAccessObject, signupOutputBoundary, userFactory);

        return new SignupController(userSignupInteractor);
    }
}

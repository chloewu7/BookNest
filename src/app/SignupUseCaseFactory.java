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
import user_manage.service.signup.SignupInteractor;
import user_manage.service.signup.SignupOutputBoundary;
import user_manage.service.signup.interface_adapter.SignupController;
import user_manage.service.signup.interface_adapter.SignupPresenter;
import user_manage.service.signup.interface_adapter.SignupViewModel;
import view.SignupView;

import javax.swing.*;
import java.io.IOException;

public class SignupUseCaseFactory {
    private SignupUseCaseFactory(){}

    public static SignupView create(ViewManagerModel viewManagerModel, SignupViewModel signupViewModel, LoginViewModel loginViewModel, FileUserDataAccessObject signupDataAccessObject) {
        try {
            SignupController signupController = createSignupUseCase(viewManagerModel, signupViewModel, loginViewModel, signupDataAccessObject);
            return new SignupView(signupController, signupViewModel, viewManagerModel);
        } catch (IOException e){
            JOptionPane.showMessageDialog(null, "Could not open review data file.");
        }
        return null;
    }

    private static SignupController createSignupUseCase(ViewManagerModel viewManagerModel,
                                                                   SignupViewModel signupViewModel, LoginViewModel loginViewModel,
                                                                   SignupDataAccessInterface signupDataAccessObject) throws IOException {
        SignupOutputBoundary signupPresenter = new SignupPresenter(viewManagerModel, signupViewModel, loginViewModel);

        UserFactory userFactory = new CommonUserFactory();

        SignupInteractor signupInteractor = new SignupInteractor(
                signupDataAccessObject, signupPresenter, userFactory);

        return new SignupController(signupInteractor);
    }
}

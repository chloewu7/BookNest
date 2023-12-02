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
            FileUserDataAccessObject loginDataAccessObject) {

        try {
            LoginController loginController = createLoginUseCase(viewManagerModel, loginViewModel, searchViewModel, loginDataAccessObject);
            return new LoginView(loginViewModel, loginController);
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
}

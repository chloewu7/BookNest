package user_manage.service.login.interface_adapter;

import user_manage.service.login.LoginInputBoundary;
import user_manage.service.login.LoginInputData;

public class LoginController {

    final LoginInputBoundary loginUseCaseInteractor;

    public LoginController(LoginInputBoundary loginUseCaseInteractor) {
        this.loginUseCaseInteractor = loginUseCaseInteractor;
    }

    public void execute(String username, String password) {
        LoginInputData loginInputData = new LoginInputData(username, password);

        loginUseCaseInteractor.execute(loginInputData);
    }
}

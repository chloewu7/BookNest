package user_manage.service.login.interface_adapter;

import interface_adapter.ViewManagerModel;
import user_manage.service.login.LoginOutputBoundary;
import user_manage.service.login.LoginOutputData;

public class LoginPresenter implements LoginOutputBoundary {

    private final LoginViewModel loginViewModel;

    private final SearchViewModel searchViewModel;

    private ViewManagerModel viewManagerModel;

    public LoginPresenter(ViewManagerModel viewManagerModel,SearchViewModel searchViewModel,

                          LoginViewModel loginViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.searchViewModel =searchViewModel;


        this.loginViewModel = loginViewModel;
    }

    @Override
    public void prepareSuccessView(LoginOutputData response) {
        // On success, switch to the logged in view.

        SearchState searchState = SearchViewModel.getState();
        searchState.setUsername(response.getUsername());
        this.searchViewModel.setState(searchState);
        this.searchViewModel.firePropertyChanged();

        this.viewManagerModel.setActiveView((searchViewModel.getViewName()));
        this.viewManagerModel.firePropertyChanged();

    }

    @Override
    public void prepareFailView(String error) {
        LoginState loginState = loginViewModel.getState();
        loginState.setUsernameError(error);
        loginViewModel.firePropertyChanged();
    }
}
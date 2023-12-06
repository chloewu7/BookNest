package view;



import interface_adapter.ViewManagerModel;
import search.service.interface_adapter.SearchState;
import search.service.interface_adapter.SearchViewModel;
import user_manage.service.collection_management.show_all_lists.interface_adapter.ShowAllListsController;
import user_manage.service.login.interface_adapter.LoginController;
import user_manage.service.login.interface_adapter.LoginState;
import user_manage.service.login.interface_adapter.LoginViewModel;
import user_manage.service.signup.interface_adapter.SignupController;
import user_manage.service.signup.interface_adapter.SignupState;
import user_manage.service.signup.interface_adapter.SignupViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Objects;

public class LoginView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "log in";


    private final LoginViewModel loginViewModel;

    private final SignupViewModel signupViewModel;

    final JTextField usernameInputField = new JTextField(15);
    private final JLabel usernameErrorField = new JLabel();

    final JPasswordField passwordInputField = new JPasswordField(15);
    private final JLabel passwordErrorField = new JLabel();

    JButton logIn;
    JButton signUp;

    private SearchViewModel searchViewModel;

    private ViewManagerModel viewManagerModel;

    private final LoginController loginController;

    private final SignupController signupController;
    private ShowAllListsController showAllListsController;

    public LoginView(LoginViewModel loginViewModel, LoginController controller, SignupViewModel signupViewModel ,
                     SignupController signupController,
                     SearchViewModel searchViewModel,
                     ViewManagerModel viewManagerModel,
                     ShowAllListsController showAllListsController) {

        this.loginController = controller;
        this.loginViewModel = loginViewModel;
        this.loginViewModel.addPropertyChangeListener(this);
        this.signupController = signupController;
        this.signupViewModel = signupViewModel;
        this.signupViewModel.addPropertyChangeListener(this);
        this.searchViewModel = searchViewModel;
        this.viewManagerModel = viewManagerModel;
        this.showAllListsController = showAllListsController;

        setupUI();

    }

    private void setupUI(){

        Color lightYellow = new Color(255, 255, 224);
        Color lightBlue = new Color(173, 216, 230);

        this.setBackground(lightBlue);


        JLabel title = new JLabel("Login Screen");
        title.setSize(1000, 200);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setFont(new Font("SansSerif", Font.BOLD, 16));


        LabelTextPanel usernameInfo = new LabelTextPanel(
                new JLabel("Username"), usernameInputField);


        LabelTextPanel passwordInfo = new LabelTextPanel(
                new JLabel("Password"), passwordInputField);

        usernameInfo.setBackground(lightYellow);
        passwordInfo.setBackground(lightYellow);

        logIn = new JButton(loginViewModel.LOGIN_BUTTON_LABEL);
        logIn.addActionListener(this);
        signUp = new JButton(SignupViewModel.SIGNUP_BUTTON_LABEL);
        signUp.addActionListener(this);

        logIn.setSize(100, 100);
        signUp.setSize(100, 100);

        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));


        usernameInputField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                LoginState currentState = loginViewModel.getState();
                currentState.setUsername(usernameInputField.getText() + e.getKeyChar());
                loginViewModel.setState(currentState);
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });


        passwordInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        LoginState currentState = loginViewModel.getState();
                        currentState.setPassword(new String(passwordInputField.getText() + e.getKeyChar()));
                        loginViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                    }
                });

        logIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginState loginState = loginViewModel.getState();

                String username = usernameInputField.getText();
                String password = new String(passwordInputField.getPassword());



                loginState.setUsername(username);
                loginState.setPassword(password);

                try {
                    loginController.execute(username, password);
                    String loginUserNameFail = loginState.getUsernameError();
                    String loginPasswordError = loginState.getPasswordError();

                    if (loginUserNameFail == null && loginPasswordError == null && !Objects.equals(username, "")
                            && !Objects.equals(password, "")) {
                        showAllListsController.executeWhenSearch(username);
                        SearchState searchState = searchViewModel.getState();
                        searchState.setUserName(username);
                        searchViewModel.setState(searchState);
                        viewManagerModel.setActiveView("Search");
                    }
                    else {

                        JOptionPane.showMessageDialog(LoginView.this,
                                "User does not exist or incorrect password.",
                                "Login Failed",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(LoginView.this,
                            ex.getMessage(),
                            "Login Error",
                            JOptionPane.ERROR_MESSAGE);
                }

                viewManagerModel.firePropertyChanged();
            }
        });

        signUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SignupState signupState = signupViewModel.getState();

                String username = usernameInputField.getText();
                String password = new String(passwordInputField.getPassword());

                signupState.setUsername(username);
                signupState.setPassword(password);

                signupViewModel.setState(signupState);

                viewManagerModel.setActiveView("sign up");
                viewManagerModel.firePropertyChanged();

            }
        });

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(usernameInfo);
        this.add(usernameErrorField);
        this.add(passwordInfo);
        this.add(passwordErrorField);

        JPanel buttons = new JPanel();
        buttons.add(logIn);
        buttons.add(signUp);
        buttons.setBackground(lightYellow);
        this.add(buttons);
    }


    @Override
    public void actionPerformed(ActionEvent evt) {
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        LoginState state = (LoginState) evt.getNewValue();
        setFields(state);
    }

    private void setFields(LoginState state) {
        usernameInputField.setText(state.getUsername());
    }}
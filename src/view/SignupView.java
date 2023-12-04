package view;

import interface_adapter.ViewManagerModel;
import search.service.interface_adapter.SearchState;
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

public class SignupView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "sign up";
    private final SignupController signupController;
    private final SignupViewModel signupViewModel;
    private final JButton signupButton;
    private final JTextField usernameInputField = new JTextField(20);
    private final JPasswordField passwordInputField = new JPasswordField(20);
    private final JPasswordField repeatPasswordInputField = new JPasswordField(20);

    private final JButton returnButton;



    public SignupView(ViewManagerModel viewManagerModel,
                      SignupController signupController,
                      SignupViewModel signupViewModel){
        this.signupController = signupController;
        this.signupViewModel = signupViewModel;
        signupViewModel.addPropertyChangeListener(this);
        this.setSize(1000, 600);
        Color lightBlue = new Color(173, 216, 230);
        Color darkBlue = new Color(80, 100, 230);
        Color lightYellow = new Color(255, 255, 224);
        this.setBackground(lightBlue);

        JLabel signupTitle = new JLabel(SignupViewModel.TITLE_LABEL);
        signupTitle.setFont(new Font("Lucida Grande", 0, 20));
        JPanel title = new JPanel();
        title.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0; // Expand horizontally
        gbc.weighty = 1.0; // Expand vertically
        gbc.anchor = GridBagConstraints.CENTER; // Center the label
        title.add(signupTitle, gbc);
        title.setPreferredSize(new Dimension(1000, 100));

        title.setBackground(lightYellow);

        usernameInputField.setPreferredSize(new Dimension(20, 40));
        passwordInputField.setPreferredSize(new Dimension(20, 40));
        repeatPasswordInputField.setPreferredSize(new Dimension(20, 40));

        JLabel username = new JLabel(SignupViewModel.USERNAME_LABEL);
        username.setFont(new Font("Lucida Grande", 0, 15));

        JLabel password = new JLabel(SignupViewModel.PASSWORD_LABEL);
        password.setFont(new Font("Lucida Grande", 0, 15));

        JLabel repeatPassword = new JLabel(SignupViewModel.REPEAT_PASSWORD_LABEL);
        repeatPassword.setFont(new Font("Lucida Grande", 0, 15));

        LabelTextPanel usernameInfo = new LabelTextPanel(
                username, usernameInputField);
        LabelTextPanel passwordInfo = new LabelTextPanel(
                password, passwordInputField);
        LabelTextPanel repeatPasswordInfo = new LabelTextPanel(
                repeatPassword, repeatPasswordInputField);

        usernameInfo.setBackground(lightBlue);
        //usernameInfo.setPreferredSize(new Dimension(1000, 80));
        passwordInfo.setBackground(lightBlue);
        //passwordInfo.setPreferredSize(new Dimension(1000, 80));
        repeatPasswordInfo.setBackground(lightBlue);
        //repeatPasswordInfo.setPreferredSize(new Dimension(1000, 80));

        JPanel infoPanel = new JPanel();
        infoPanel.setPreferredSize(new Dimension(1000, 330));
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.add(usernameInfo);
        infoPanel.add(passwordInfo);
        infoPanel.add(repeatPasswordInfo);

        JPanel bigInfoPanel = new JPanel();
        bigInfoPanel.setPreferredSize(new Dimension(1000, 380));
        bigInfoPanel.setLayout(new BorderLayout());
        bigInfoPanel.setBackground(lightBlue);
        bigInfoPanel.add(infoPanel, BorderLayout.CENTER);

        JPanel top = new JPanel();
        top.setPreferredSize(new Dimension(1000, 60));
        top.setBackground(lightBlue);
        bigInfoPanel.add(top, BorderLayout.NORTH);

        JPanel bottom = new JPanel();
        bottom.setPreferredSize(new Dimension(1000, 80));
        bottom.setBackground(lightBlue);
        bigInfoPanel.add(bottom, BorderLayout.SOUTH);



        signupButton = new JButton(SignupViewModel.SIGNUP_BUTTON_LABEL);
        returnButton = new JButton("Login");
        signupButton.setPreferredSize(new Dimension(200, 50));
        returnButton.setPreferredSize(new Dimension(200, 50));
        signupButton.setFont(new Font("Lucida Grande", 0, 15));
        returnButton.setFont(new Font("Lucida Grande", 0, 15));
        signupButton.setBackground(lightYellow);
        JPanel button = new JPanel();
        button.add(signupButton, BorderLayout.NORTH);
        button.add(returnButton);
        button.setBackground(lightBlue);
        button.setPreferredSize(new Dimension(1000, 120));

        signupButton.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(signupButton)) {
                            SignupState currentState = signupViewModel.getState();

                            signupController.execute(
                                    currentState.getUsername(),
                                    currentState.getPassword(),
                                    currentState.getRepeatPassword()
                            );
                        }
                    }
                }
        );

        returnButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (evt.getSource().equals(returnButton)) {
                    viewManagerModel.setActiveView("log in");
                    viewManagerModel.firePropertyChanged();
                }
            }
        });

        usernameInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        SignupState currentState = signupViewModel.getState();
                        String text = usernameInputField.getText() + e.getKeyChar();
                        currentState.setUsername(text);
                        signupViewModel.setState(currentState);
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
                        SignupState currentState = signupViewModel.getState();
                        currentState.setPassword(passwordInputField.getText() + e.getKeyChar());
                        signupViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {

                    }

                    @Override
                    public void keyReleased(KeyEvent e) {

                    }
                }
        );

        repeatPasswordInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        SignupState currentState = signupViewModel.getState();
                        currentState.setRepeatPassword(repeatPasswordInputField.getText() + e.getKeyChar());
                        signupViewModel.setState(currentState); // Hmm, is this necessary?
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {

                    }

                    @Override
                    public void keyReleased(KeyEvent e) {

                    }
                }
        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title, BorderLayout.NORTH);
        //this.add(usernameInfo);
        //this.add(passwordInfo);
        //this.add(repeatPasswordInfo);
        this.add(bigInfoPanel);
        this.add(button, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        SignupState state = (SignupState) evt.getNewValue();
        if (state.getUsernameError() != null) {
            JOptionPane.showMessageDialog(this, state.getUsernameError());
        }
    }
}

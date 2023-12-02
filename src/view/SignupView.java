package view;

import user_manage.service.reading_review.show_my_reviews.interface_adapter.ShowMyReviewsController;
import user_manage.service.reading_review.show_my_reviews.interface_adapter.ShowMyReviewsState;
import user_manage.service.reading_review.show_my_reviews.interface_adapter.ShowMyReviewsViewModel;
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

    public final String viewName = "my reviews";
    private final SignupController signupController;
    private final SignupViewModel signupViewModel;
    private final JTextField reviewContentInputField = new JTextField(30);
    private final JButton signupButton;
    private final JTextField usernameInputField = new JTextField(15);
    private final JPasswordField passwordInputField = new JPasswordField(15);
    private final JPasswordField repeatPasswordInputField = new JPasswordField(15);



    public SignupView(SignupController signupController,
                      SignupViewModel signupViewModel){
        this.signupController = signupController;
        this.signupViewModel = signupViewModel;
        signupViewModel.addPropertyChangeListener(this);
        this.setSize(1000, 1000);
        Color lightBlue = new Color(173, 216, 230);
        Color darkBlue = new Color(80, 100, 230);
        Color lightYellow = new Color(255, 255, 224);

        JLabel signupTitle = new JLabel(SignupViewModel.TITLE_LABEL);
        signupTitle.setFont(new Font("Lucida Grande", 0, 15));
        JPanel title = new JPanel();
        title.add(signupTitle);
        title.setPreferredSize(new Dimension(1000, 40));
        title.setBackground(lightYellow);

        LabelTextPanel usernameInfo = new LabelTextPanel(
                new JLabel(SignupViewModel.USERNAME_LABEL), usernameInputField);
        LabelTextPanel passwordInfo = new LabelTextPanel(
                new JLabel(SignupViewModel.PASSWORD_LABEL), passwordInputField);
        LabelTextPanel repeatPasswordInfo = new LabelTextPanel(
                new JLabel(SignupViewModel.REPEAT_PASSWORD_LABEL), repeatPasswordInputField);

        usernameInfo.setBackground(lightBlue);
        passwordInfo.setBackground(lightBlue);
        repeatPasswordInfo.setBackground(lightBlue);


        signupButton = new JButton(SignupViewModel.SIGNUP_BUTTON_LABEL);
        signupButton.setPreferredSize(new Dimension(200, 40));
        signupButton.setBackground(lightYellow);
        JPanel button = new JPanel();
        button.add(signupButton);
        button.setBackground(lightBlue);

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
        this.add(usernameInfo);
        this.add(passwordInfo);
        this.add(repeatPasswordInfo);
        this.add(button, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}

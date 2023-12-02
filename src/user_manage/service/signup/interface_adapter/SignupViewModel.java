package user_manage.service.signup.interface_adapter;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class SignupViewModel extends ViewModel {
    public static final String TITLE_LABEL = "Embark on Your Reading Adventure: Sign Up Today";
    public static final String SIGNUP_BUTTON_LABEL = "Create An Account";
    public static final String USERNAME_LABEL = "Enter Your Name";
    public static final String PASSWORD_LABEL = "Choose Your Password";
    public static final String REPEAT_PASSWORD_LABEL = "Confirm Your Password";
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    private SignupState state = new SignupState();

    public SignupViewModel(){
        super("sign up");
    }

    public void setState(SignupState state){
        this.state = state;
    }

    public SignupState getState(){
        return state;
    }

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}

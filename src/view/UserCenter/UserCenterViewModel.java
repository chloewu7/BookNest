package view.UserCenter;

import interface_adapter.ViewModel;
import user_manage.service.signup.interface_adapter.SignupState;

import javax.swing.text.View;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class UserCenterViewModel extends ViewModel {
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    private UserCenterState state = new UserCenterState();

    public UserCenterViewModel(){
        super("User Center");
    }

    public void setState(UserCenterState state){
        this.state = state;
    }

    public UserCenterState getState(){
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

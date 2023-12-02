package view;

import interface_adapter.ViewManagerModel;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ViewManager implements PropertyChangeListener {
    private final CardLayout cardLayout;
    private final JPanel views;
    private ViewManagerModel viewManagerModel;

    public ViewManager(JPanel views, CardLayout cardLayout, ViewManagerModel viewManagerModel) {
        this.views = views;
        this.cardLayout = cardLayout;
        this.viewManagerModel = viewManagerModel;
        this.viewManagerModel.addPropertyChangeListener(this);
    }

    public void addView(JPanel view, String name){
        views.add(view, name);
    }

    public void switchView(String viewName) {
        viewManagerModel.setActiveView(viewName);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("view".equals(evt.getPropertyName())) {
            String viewName = (String) evt.getNewValue();
            cardLayout.show(views, viewName);
        }
    }
}

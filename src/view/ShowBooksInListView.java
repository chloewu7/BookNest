package view;

import interface_adapter.ViewManagerModel;
import user_manage.service.collection_management.show_all_lists.interface_adapter.ShowAllListsState;
import user_manage.service.collection_management.show_books_in_list.interface_adapter.ShowBooksInListController;
import user_manage.service.collection_management.show_books_in_list.interface_adapter.ShowBooksInListState;
import user_manage.service.collection_management.show_books_in_list.interface_adapter.ShowBooksInListViewModel;
import view.UserCenter.UserCenterState;
import view.UserCenter.UserCenterViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Objects;

public class ShowBooksInListView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "books in list";
    private JButton returnButton;
    private JPanel mainPane;
    private ShowBooksInListController showBooksInListController;
    private ShowBooksInListViewModel showBooksInListViewModel;
    private ViewManagerModel viewManagerModel;
    private UserCenterViewModel userCenterViewModel;
    public ShowBooksInListView(ShowBooksInListController showBooksInListController,
                               ShowBooksInListViewModel showBooksInListViewModel,
                               ViewManagerModel viewManagerModel,
                               UserCenterViewModel userCenterViewModel){
        this.showBooksInListController = showBooksInListController;
        this.showBooksInListViewModel = showBooksInListViewModel;
        this.viewManagerModel = viewManagerModel;
        this.userCenterViewModel = userCenterViewModel;
        initializeUI();
    }
    private void initializeUI() {
        this.setLayout(new BorderLayout());
        this.setSize(1000, 600);
        showBooksInListViewModel.addPropertyChangeListener(this);

        returnButton = new JButton("Return");
        returnButton.setFont(new Font("SansSerif", Font.BOLD, 14));

        Color lightBlue = new Color(173, 216, 230);

        mainPane = new JPanel();
        mainPane.setPreferredSize(new Dimension());
        mainPane.setBackground(lightBlue);
        JScrollPane scrollPane = new JScrollPane(mainPane);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        this.add(scrollPane, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.add(returnButton);
        this.add(bottomPanel, BorderLayout.SOUTH);


        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //mainPane.removeAll();
                showBooksInListViewModel.firePropertyChanged();

                viewManagerModel.setActiveView("all collection lists");
                viewManagerModel.firePropertyChanged();
            }
        });
    }

    private void showBooksPerform(ShowBooksInListState state){
        mainPane.removeAll();

        for(String bookTitle : state.getBooks().keySet()){
            if(!Objects.equals(bookTitle, " ")) {
                String bookAuthor = state.getBooks().get(bookTitle);

                JPanel linePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
                linePanel.setBorder(BorderFactory.createLineBorder(Color.gray));
                linePanel.add(new JLabel("Title:" + bookTitle + ", Author: " + bookAuthor));
                mainPane.add(linePanel);
            }
        }
        mainPane.revalidate();
        mainPane.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        ShowBooksInListState state = (ShowBooksInListState) evt.getNewValue();
        showBooksPerform(state);
    }
}

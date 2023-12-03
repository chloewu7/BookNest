package view;

import interface_adapter.ViewManagerModel;
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

public class ShowBooksInListView extends JPanel implements ActionListener, PropertyChangeListener {
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
                showBooksInListViewModel.firePropertyChanged();

                viewManagerModel.setActiveView("show all collection lists");
                viewManagerModel.firePropertyChanged();
            }
        });
    }

    private void showBooksPerform(){
        mainPane.removeAll();
        UserCenterState userCenterState = userCenterViewModel.getState();
        String userName = userCenterState.getUsername();
        ShowBooksInListState showBooksInListState = showBooksInListViewModel.getState();
        String list = showBooksInListState.getListName();
        showBooksInListController.execute(userName,list);

        for(String bookTitle : showBooksInListState.getBooks().keySet()){
            String bookAuthor = showBooksInListState.getBooks().get(bookTitle);

            JPanel linePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            linePanel.setBorder(BorderFactory.createLineBorder(Color.gray));
            linePanel.add(new JLabel("Title:" + bookTitle + ", Author: " + bookAuthor));
            mainPane.add(linePanel);
        }
        mainPane.revalidate();
        mainPane.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}

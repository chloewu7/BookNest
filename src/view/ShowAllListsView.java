package view;

import interface_adapter.ViewManagerModel;
import user_manage.service.collection_management.create_list.interface_adapter.CreateListController;
import user_manage.service.collection_management.create_list.interface_adapter.CreateListState;
import user_manage.service.collection_management.create_list.interface_adapter.CreateListViewModel;
import user_manage.service.collection_management.show_all_lists.interface_adapter.ShowAllListsController;
import user_manage.service.collection_management.show_all_lists.interface_adapter.ShowAllListsState;
import user_manage.service.collection_management.show_all_lists.interface_adapter.ShowAllListsViewModel;
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
import java.util.List;

public class ShowAllListsView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "all collection lists";
    private JButton addButton;
    private JButton returnButton;
    private JPanel mainPane;
    private ShowAllListsController showAllListsController;
    private ShowAllListsViewModel showAllListsViewModel;
    private ViewManagerModel viewManagerModel;
    private CreateListController createListController;
    private CreateListViewModel createListViewModel;
    private UserCenterViewModel userCenterViewModel;
    private ShowBooksInListViewModel showBooksInListViewModel;

    public ShowAllListsView(ShowAllListsController showAllListsController,
                            ShowAllListsViewModel showAllListsViewModel,
                            ViewManagerModel viewManagerModel,
                            CreateListController createListController,
                            CreateListViewModel createListViewModel,
                            UserCenterViewModel userCenterViewModel,
                            ShowBooksInListViewModel showBooksInListViewModel) {
        this.showAllListsController = showAllListsController;
        this.showAllListsViewModel = showAllListsViewModel;
        this.viewManagerModel = viewManagerModel;
        this.createListController = createListController;
        this.createListViewModel = createListViewModel;
        this.userCenterViewModel = userCenterViewModel;
        this.showBooksInListViewModel = showBooksInListViewModel;
        initializeUI();
    }

    private void initializeUI() {
        this.setLayout(new BorderLayout());
        this.setSize(1000, 600);
        showAllListsViewModel.addPropertyChangeListener(this);

        Color lightYellow = new Color(255, 255, 224);
        addButton = new JButton("Add New Collection List");
        addButton.setBackground(lightYellow);
        addButton.setFont(new Font("SansSerif", Font.BOLD, 14));

        returnButton = new JButton("Return");
        returnButton.setFont(new Font("SansSerif", Font.BOLD, 14));

        Color lightBlue = new Color(173, 216, 230);

        mainPane = new JPanel();
        mainPane.setPreferredSize(new Dimension());
//        mainPane.setLayout(new BoxLayout(mainPane, BoxLayout.Y_AXIS));
        mainPane.setBackground(lightBlue);

        JScrollPane scrollPane = new JScrollPane(mainPane);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        this.add(scrollPane, BorderLayout.CENTER);

        JPanel topPanel = new JPanel();
        topPanel.add(addButton, BorderLayout.CENTER);
        this.add(topPanel, BorderLayout.NORTH);

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.add(returnButton);
        this.add(bottomPanel, BorderLayout.SOUTH);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 得到新list的名字
                String newListName = JOptionPane.showInputDialog(ShowAllListsView.this,
                        "New List Name:", "Add New List", JOptionPane.PLAIN_MESSAGE);
                UserCenterState userCenterState = userCenterViewModel.getState();
                String userName = userCenterState.getUsername();
                createListController.execute(userName, newListName);
                CreateListState createListState = createListViewModel.getState();
                if(createListState.getCreateListError() == null){
                    showListsPerform();
                }else {
                    JOptionPane.showMessageDialog(ShowAllListsView.this, createListState.getCreateListError(),
                            "Create List Error", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showAllListsViewModel.firePropertyChanged();

                viewManagerModel.setActiveView("User Center");
                viewManagerModel.firePropertyChanged();
            }
        });
        showListsPerform();
    }

    private void showListsPerform() {
        mainPane.removeAll();
        ShowAllListsState showAllListsState = showAllListsViewModel.getState();

        for (String list: showAllListsState.getListsName()) {
            JButton detailButton = new JButton("Show my books");

            JPanel linePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
//            linePanel.setPreferredSize(new Dimension());
            linePanel.setBorder(BorderFactory.createLineBorder(Color.gray));

            linePanel.add(detailButton);
            linePanel.add(new JLabel(list));
            mainPane.add(linePanel);
            detailButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    showAllListsViewModel.firePropertyChanged();
                    ShowBooksInListState showBooksInListState = showBooksInListViewModel.getState();
                    showBooksInListState.setListName(list);

                    viewManagerModel.setActiveView("show books in list");
                    viewManagerModel.firePropertyChanged();
                }
            });
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

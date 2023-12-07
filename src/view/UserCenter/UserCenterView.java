package view.UserCenter;

import interface_adapter.ViewManagerModel;
import search.service.interface_adapter.SearchController;
import search.service.interface_adapter.SearchState;
import search.service.interface_adapter.SearchViewModel;
import user_manage.service.collection_management.show_all_lists.interface_adapter.ShowAllListsController;
import user_manage.service.collection_management.show_all_lists.interface_adapter.ShowAllListsState;
import user_manage.service.collection_management.show_all_lists.interface_adapter.ShowAllListsViewModel;
import user_manage.service.history.read_history.interface_adpter.ReadingHistoryController;
import user_manage.service.history.read_history.interface_adpter.ReadingHistoryState;
import user_manage.service.history.read_history.interface_adpter.ReadingHistoryViewModel;
import user_manage.service.reading_review.show_my_reviews.interface_adapter.ShowMyReviewsController;
import user_manage.service.reading_review.show_my_reviews.interface_adapter.ShowMyReviewsState;
import user_manage.service.reading_review.show_my_reviews.interface_adapter.ShowMyReviewsViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;


public class UserCenterView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "User Center";
    JButton collectionButton;
    JButton historyButton;
    JButton reviewButton;
    JButton searchButton;
    private ViewManagerModel viewManagerModel;
    private UserCenterViewModel userCenterViewModel;
    private ShowMyReviewsViewModel showMyReviewsViewModel;
    private ShowMyReviewsController showMyReviewsController;
    private SearchController searchController;
    private SearchViewModel searchViewModel;
    private ShowAllListsController showAllListsController;
    private ShowAllListsViewModel showAllListsViewModel;
    private ReadingHistoryController readingHistoryController;
    private ReadingHistoryViewModel readingHistoryViewModel;
    JPanel title;

    private Color Orange = new Color(248, 152, 32);


    public UserCenterView(ViewManagerModel viewManagerModel, UserCenterViewModel userCenterViewModel,
                          ShowMyReviewsController showMyReviewsController, ShowMyReviewsViewModel showMyReviewsViewModel,
                          SearchController searchController, SearchViewModel searchViewModel,
                          ShowAllListsController showAllListsController, ShowAllListsViewModel showAllListsViewModel,
                          ReadingHistoryController readingHistoryController, ReadingHistoryViewModel
                                  readingHistoryViewModel){

        this.viewManagerModel = viewManagerModel;
        this.userCenterViewModel = userCenterViewModel;
        this.showMyReviewsController = showMyReviewsController;
        this.showMyReviewsViewModel = showMyReviewsViewModel;
        this.searchController = searchController;
        this.searchViewModel = searchViewModel;
        this.showAllListsController = showAllListsController;
        this.showAllListsViewModel = showAllListsViewModel;
        this.readingHistoryController = readingHistoryController;
        this.readingHistoryViewModel = readingHistoryViewModel;

        createUI();
    }

    private void createUI(){
        this.setSize(1000, 600);
        this.setLayout(new GridLayout(5, 1, 10, 10));
        userCenterViewModel.addPropertyChangeListener(this);

        Color lightYellow = new Color(255, 255, 224);
        Color lightBlue = new Color(173, 216, 230);

        SearchState searchState = searchViewModel.getState();

        UserCenterState state = userCenterViewModel.getState();
        state.setUsername(searchState.getUserName());

        JLabel username = new JLabel("User Center");
        username.setFont(new Font("Lucida Grande", 0, 20));
        title = new JPanel();
        title.setBackground(lightYellow);
        title.setPreferredSize(new Dimension(1000, 100));
        title.add(username);

        collectionButton = new JButton("My Collection");
        collectionButton.setBackground(lightBlue);
        collectionButton.setOpaque(true);
        collectionButton.setBorderPainted(false);
        collectionButton.setFont(new Font("SansSerif", Font.BOLD, 14));

        historyButton = new JButton("My Reading History");
        historyButton.setBackground(lightYellow);
        historyButton.setOpaque(true);
        historyButton.setBorderPainted(false);
        historyButton.setFont(new Font("SansSerif", Font.BOLD, 14));

        reviewButton = new JButton("My Review");
        reviewButton.setBackground(lightBlue);
        reviewButton.setOpaque(true);
        reviewButton.setBorderPainted(false);
        reviewButton.setFont(new Font("SansSerif", Font.BOLD, 14));

        searchButton = new JButton("Search");
        searchButton.setBackground(lightYellow);
        searchButton.setOpaque(true);
        searchButton.setBorderPainted(false);
        searchButton.setFont(new Font("SansSerif", Font.BOLD, 14));




        collectionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userName = state.getUsername();
                ShowAllListsState showAllListsState = showAllListsViewModel.getState();
                showAllListsState.setUserName(userName);
                showAllListsController.execute(userName);
                showAllListsViewModel.setState(showAllListsState);
                viewManagerModel.setActiveView("all collection lists");
                viewManagerModel.firePropertyChanged();
            }
        });



        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userName = state.getUsername();
                SearchState searchState = searchViewModel.getState();
                searchState.setUserName(userName);
                viewManagerModel.setActiveView("Search");
                viewManagerModel.firePropertyChanged();
            }
        });

        reviewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userName = state.getUsername();
                ShowMyReviewsState showMyReviewsState = showMyReviewsViewModel.getState();
                showMyReviewsState.setUsername(userName);
                showMyReviewsController.execute(userName);
                viewManagerModel.setActiveView("my reviews");
                viewManagerModel.firePropertyChanged();
            }
        });

        historyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userName = state.getUsername();
                ReadingHistoryState readingHistoryState = readingHistoryViewModel.getState();
                readingHistoryState.setUserName(userName);
                readingHistoryController.execute(userName, readingHistoryState.getReadBook());
                viewManagerModel.setActiveView("History");
                viewManagerModel.firePropertyChanged();
            }
        });


        add(title);
        add(collectionButton);
        add(historyButton);
        add(reviewButton);
        add(searchButton);
    }



    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        UserCenterState state = (UserCenterState) evt.getNewValue();
        title.removeAll();
        JLabel username = new JLabel("User Center for ");
        JLabel myUsername = new JLabel(state.getUsername());
        username.setFont(new Font("SansSerif", Font.PLAIN, 35));
        myUsername.setFont(new Font("SansSerif", Font.PLAIN, 40));
        myUsername.setForeground(Orange);
        title.add(username);
        title.add(myUsername);

        title.revalidate();
        title.repaint();
    }
}

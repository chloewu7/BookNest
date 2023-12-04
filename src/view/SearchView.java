package view;


import interface_adapter.ViewManagerModel;
import search.entity.Book;
import search.service.SearchInteractor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import search.service.SearchInputData;
import search.service.interface_adapter.SearchController;
import search.service.interface_adapter.SearchState;
import search.service.interface_adapter.SearchViewModel;
import user_manage.service.collection_management.add_book.interface_adapter.AddBookController;
import user_manage.service.collection_management.add_book.interface_adapter.AddBookState;
import user_manage.service.collection_management.add_book.interface_adapter.AddBookViewModel;
import user_manage.service.collection_management.show_all_lists.interface_adapter.ShowAllListsState;
import user_manage.service.collection_management.show_all_lists.interface_adapter.ShowAllListsViewModel;
import user_manage.service.history.add_history.Interface_adapter.AddingHistoryState;
import user_manage.service.history.add_history.Interface_adapter.AddingHistoryViewModel;
import user_manage.service.history.read_history.interface_adpter.ReadingHistoryController;
import user_manage.service.history.read_history.interface_adpter.ReadingHistoryViewModel;
import user_manage.service.reading_review.show_all_reviews.interface_adapter.ShowAllReviewsController;
import user_manage.service.reading_review.show_all_reviews.interface_adapter.ShowAllReviewsState;
import user_manage.service.reading_review.show_all_reviews.interface_adapter.ShowAllReviewsViewModel;
import view.UserCenter.UserCenterState;
import view.UserCenter.UserCenterViewModel;

public class SearchView extends JPanel implements ActionListener, PropertyChangeListener{
    JComboBox<String> searchTypeComboBox;
    private JTextField searchTextField;
    private JButton searchButton;
    private JPanel resultsPanel;

    private JLabel searchInstructionLabel;
    private SearchController searchController;
    private SearchViewModel searchViewModel;
    private ViewManagerModel viewManagerModel;

    private ShowAllReviewsController showAllReviewsController;
    private ShowAllReviewsViewModel showAllReviewsViewModel;

    private AddBookController addBookController;
    private AddBookViewModel addBookViewModel;
    private ShowAllListsViewModel showAllListsViewModel;
    private UserCenterViewModel userCenterViewModel;

    private AddingHistoryViewModel addingHistoryViewModel;

    public final ArrayList<Book> collectedBooks = new ArrayList<Book>();

    public final String viewName = "Search";


    public SearchView(SearchController searchController,
                      SearchViewModel searchViewModel,
                      ViewManagerModel viewManagerModel,
                      ShowAllReviewsController showAllReviewsController,
                      ShowAllReviewsViewModel showAllReviewsViewModel,
                      AddBookController addBookController,
                      AddBookViewModel addBookViewModel,
                      ShowAllListsViewModel showAllListsViewModel,
                      UserCenterViewModel userCenterViewModel,
                      AddingHistoryViewModel addingHistoryViewModel){

        this.searchController = searchController;
        this.searchViewModel = searchViewModel;
        this.viewManagerModel = viewManagerModel;
        this.showAllReviewsController = showAllReviewsController;
        this.showAllReviewsViewModel = showAllReviewsViewModel;
        this.addBookController = addBookController;
        this.addBookViewModel = addBookViewModel;
        this.showAllListsViewModel = showAllListsViewModel;
        this.userCenterViewModel = userCenterViewModel;
        this.addingHistoryViewModel = addingHistoryViewModel;

        createUI();
    }

    private void createUI(){

        this.setLayout(new BorderLayout());
        this.setSize(1000, 600);
        searchViewModel.addPropertyChangeListener(this);

        searchTypeComboBox = new JComboBox<>(new String[]{"Title", "Author", "Category", "ISBN"});
        searchTextField = new JTextField(20);
        Font font = new Font("SansSerif", Font.PLAIN, 24);
        searchTextField.setFont(font);

        Color lightYellow = new Color(255, 255, 224);

        searchButton = new JButton("Search");

        searchButton.setPreferredSize(new Dimension(100, 80));

        searchButton.setBackground(lightYellow);
        searchButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        searchTextField.setBackground(lightYellow);
        searchTypeComboBox.setBackground(lightYellow);

        searchTextField.setPreferredSize(new Dimension(200, 80));
        Color lightBlue = new Color(173, 216, 230);


        JPanel inputPanel = new JPanel(new BorderLayout());
        inputPanel.add(searchTypeComboBox, BorderLayout.WEST);
        inputPanel.add(searchTextField, BorderLayout.CENTER);
        inputPanel.add(searchButton, BorderLayout.EAST);
        this.add(inputPanel, BorderLayout.NORTH);

        searchInstructionLabel = new JLabel("Search Instructions: " + "\n" +
                "Please choose Search Criteria -> " + "\n" +
                "Enter search keywords and press 'Search'. " + "\n" +
                "Click 'User Center' to manage account", SwingConstants.CENTER);

        searchInstructionLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));

        resultsPanel = new JPanel();
        resultsPanel.setLayout(new BoxLayout(resultsPanel, BoxLayout.Y_AXIS));
        resultsPanel.setBackground(lightBlue);
        resultsPanel.add(searchInstructionLabel);
        JScrollPane scrollPane = new JScrollPane(resultsPanel);
        resultsPanel.add(searchInstructionLabel);
        this.add(scrollPane, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton userCenterButton = new JButton("User Center");
        userCenterButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        bottomPanel.add(userCenterButton);
        this.add(bottomPanel, BorderLayout.SOUTH);


        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performSearch();
            }
        });

        userCenterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UserCenterState userCenterState = userCenterViewModel.getState();

                SearchState searchState = searchViewModel.getState();

                userCenterState.setUsername(searchState.getUserName());
                userCenterViewModel.setState(userCenterState);

                viewManagerModel.setActiveView("User Center");
                viewManagerModel.firePropertyChanged();
            }
        });

    }
    private void performSearch(){
        resultsPanel.removeAll();
        String searchType = (String) searchTypeComboBox.getSelectedItem();
        String keyword = searchTextField.getText();

        searchController.execute(searchType, keyword);
        SearchState searchState = searchViewModel.getState();

        System.out.println("Performing search for type: " + searchType + " with keyword: " + keyword);

        for(Book book: searchState.getBooks()) {

            JButton collectButton = new JButton("Collect");
            JButton readButton = new JButton("Read");
            JButton commentButton = new JButton("Comment");

            JPanel bookLinePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            bookLinePanel.setBackground(new Color(173, 216, 230));
            bookLinePanel.setBorder(BorderFactory.createLineBorder(Color.gray));

            bookLinePanel.add(collectButton);
            bookLinePanel.add(readButton);
            bookLinePanel.add(commentButton);

            bookLinePanel.add(new JLabel("Title: " + book.getTitle() + ", Author: " + book.getAuthor() +
                    ", Category: " + book.getCategory() + ", ISBN: " + book.getISBN() + "\n\n\n"));

            resultsPanel.add(bookLinePanel);

            collectButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    searchState.setCollectedBook(book);
                    Book Collectedbook = searchState.getCollectedBook();
                    ShowAllListsState showAllListsState = showAllListsViewModel.getState();
                    List<String> listsName = showAllListsState.getListsName();
                    Object[] listsArray = listsName.toArray();
                    Object wantList = JOptionPane.showInputDialog(SearchView.this, "Collect the book to:\n",
                            "Add Book to Collection List", JOptionPane.PLAIN_MESSAGE, null, listsArray, "Like");
                    UserCenterState userCenterState = userCenterViewModel.getState();
                    String userName = userCenterState.getUsername();
                    AddBookState addBookState = addBookViewModel.getState();
                    addBookState.setBook(Collectedbook);
                    addBookState.setBookName(Collectedbook.getTitle());
                    addBookState.setBookAuthor(Collectedbook.getAuthor());
                    addBookState.setListName(wantList.toString());
                    addBookState.setUserName(userName);
                    addBookController.execute(userName, wantList.toString(),Collectedbook);
                    if(addBookState.getAddBookSuccess() != null){
                        JOptionPane.showMessageDialog(SearchView.this, addBookState.getAddBookSuccess(),
                                "Add Book Response",JOptionPane.PLAIN_MESSAGE);
                    }else{
                        JOptionPane.showMessageDialog(SearchView.this, addBookState.getAddBookError(),
                                "Add Book Response",JOptionPane.WARNING_MESSAGE);
                    }

                }
            });

            commentButton.addActionListener(
                    new ActionListener() {
                        public void actionPerformed(ActionEvent evt) {
                            if (evt.getSource().equals(commentButton)) {
                                searchState.setCommentBookTitle(book.getTitle());
                                ShowAllReviewsState showAllReviewsState = showAllReviewsViewModel.getState();
                                showAllReviewsState.setBookTitle(searchState.getCommentBookTitle());
                                showAllReviewsState.setAuthor(book.getAuthor());

                                showAllReviewsState.setUsername(searchState.getUserName());
                                showAllReviewsViewModel.setState(showAllReviewsState);
                                showAllReviewsController.execute(book.getTitle());
                            }
                        }
                    });

            readButton.addActionListener(
                    new ActionListener() {
                        public void actionPerformed(ActionEvent evt) {
                            if (evt.getSource().equals(readButton)) {

                                searchState.setReadBookTitle(book.getTitle());
                                AddingHistoryState addingHistoryState = addingHistoryViewModel.getState();
                                addingHistoryState.setReadBook(searchState.getReadBookTitle());
                            }
                        }
                    });


        }
        resultsPanel.revalidate();
        resultsPanel.repaint();
        searchTextField.setText("");
        searchViewModel.setState(searchState);
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }


}
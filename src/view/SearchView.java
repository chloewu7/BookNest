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

import search.service.SearchInputData;
import search.service.interface_adapter.SearchController;
import search.service.interface_adapter.SearchState;
import search.service.interface_adapter.SearchViewModel;

public class SearchView extends JPanel implements ActionListener, PropertyChangeListener{
    JComboBox<String> searchTypeComboBox;
    private JTextField searchTextField;
    private JButton searchButton;
    private JPanel resultsPanel;

    private JLabel searchInstructionLabel;
    private SearchController searchController;
    private SearchViewModel searchViewModel;
    private ViewManagerModel viewManagerModel;

    public final ArrayList<Book> collectedBooks = new ArrayList<Book>();

    public final String viewName = "Search";


    public SearchView(SearchController searchController,
                      SearchViewModel searchViewModel,
                      ViewManagerModel viewManagerModel){
        this.searchController = searchController;
        this.searchViewModel = searchViewModel;
        this.viewManagerModel = viewManagerModel;
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
                searchViewModel.firePropertyChanged();

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
                    addBookToList(book);
                }
            });


        }
        resultsPanel.revalidate();
        resultsPanel.repaint();
        searchTextField.setText("");
    }

    // Method to add books to collectedBooks
    public void addBookToList(Book book){
        collectedBooks.add(book);
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }


}
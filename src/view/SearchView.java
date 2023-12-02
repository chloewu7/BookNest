package view;


import interface_adapter.ViewManagerModel;
import search.entity.Book;
import search.entity.NewResponseFactory;
import search.entity.ResponseFactory;
import search.service.SearchInteractor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import search.service.SearchInputData;
import search.service.SearchDataAccessInterface;
import search.data_access.SearchDataAccessObject;
import search.service.SearchOutputBoundary;
import search.service.interface_adapter.SearchController;
import search.service.interface_adapter.SearchPresenter;
import search.service.interface_adapter.SearchState;
import search.service.interface_adapter.SearchViewModel;
import view.UserCenterView;

public class SearchView extends JFrame{
    JComboBox<String> searchTypeComboBox;
    private JTextField searchTextField;
    private JButton searchButton;
    private JPanel resultsPanel;

    private String viewName = "search";

    private JLabel searchInstructionLabel;
    private SearchInteractor searchInteractor;
    private SearchViewModel searchViewModel;



    public SearchView(SearchInteractor searchInteractor,
                      SearchViewModel searchViewModel){
        this.searchInteractor = searchInteractor;
        this.searchViewModel = searchViewModel;
        createUI();
    }
    private void createUI(){

        setTitle("Book Search");
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

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


        resultsPanel = new JPanel();
        resultsPanel.setLayout(new BoxLayout(resultsPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(resultsPanel);
        add(scrollPane, BorderLayout.CENTER);

        JPanel inputPanel = new JPanel(new BorderLayout());
        inputPanel.add(searchTypeComboBox, BorderLayout.WEST);
        inputPanel.add(searchTextField, BorderLayout.CENTER);
        inputPanel.add(searchButton, BorderLayout.EAST);
        //JScrollPane scrollPane = new JScrollPane(resultsPanel);
        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        resultsPanel.setBackground(lightBlue);

        searchInstructionLabel = new JLabel("Search Instructions: " + "\n" +
                "Please choose Search Criteria -> " + "\n" +
                "Enter search keywords and press 'Search'. " + "\n" +
                "Click 'User Center' to manage account", SwingConstants.CENTER);

        searchInstructionLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));

        resultsPanel.add(searchInstructionLabel);

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        JButton userCenterButton = new JButton("User Center");
        userCenterButton.setFont(new Font("SansSerif", Font.BOLD, 14));

        bottomPanel.add(userCenterButton);

        add(bottomPanel, BorderLayout.SOUTH);


        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performSearch();
            }
        });

        userCenterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UserCenterView userCenterView = new UserCenterView();
                userCenterView.setVisible(true);
                SearchView.this.dispose();
            }
        });

    }
    private void performSearch(){
        resultsPanel.removeAll();
        String searchType = (String) searchTypeComboBox.getSelectedItem();
        String keyword = searchTextField.getText();

        SearchInputData inputData = new SearchInputData(searchType, keyword);
        searchInteractor.execute(inputData);
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

        }
        resultsPanel.revalidate();
        resultsPanel.repaint();
        searchTextField.setText("");
    }




}
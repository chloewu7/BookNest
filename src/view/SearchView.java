package view;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SearchView extends JFrame {
    private final JComboBox<String> searchCriteria;
    private final JTextField keywordField;
    private final JButton searchButton;

    public SearchView() {

        setTitle("Book Search");
        setSize(400, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);


        searchCriteria = new JComboBox<>(new String[]{"Title","Author", "Subject", "ISBN"});
        searchCriteria.setSelectedItem("Title"); // Default selection
        keywordField = new JTextField();
        searchButton = new JButton("Search");


        setLayout(new GridLayout(3, 2));


        add(new JLabel("Search By:"));
        add(searchCriteria);
        add(new JLabel("Please enter keywords here:"));
        add(keywordField);
        add(new JLabel());
        add(searchButton);


        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedCriteria = (String) searchCriteria.getSelectedItem();
                String keyword = keywordField.getText();
                performSearch(selectedCriteria, keyword);
            }
        });
    }

    private void performSearch(String searchCriteria, String keyword) {
        // connect to search request output boundary
        System.out.println("Search Option: " + searchCriteria);
        System.out.println("Keyword: " + keyword);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SearchView().setVisible(true);
            }
        });
    }
}
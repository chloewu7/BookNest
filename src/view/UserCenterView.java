package view;

import app.ShowMyReviewsUseCaseFactory;
import user_manage.service.reading_review.show_my_reviews.interface_adapter.ShowMyReviewsViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class UserCenterView extends JFrame {

    private String viewName = "userCenter";
    private JButton collectionButton;
    private JButton historyButton;
    private JButton reviewButton;
    private JButton searchButton;


    public UserCenterView(){
        createUI();
    }

    private void createUI(){
        setTitle("User Center");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(4, 1, 10, 10));

        Color lightYellow = new Color(255, 255, 224);
        Color lightBlue = new Color(173, 216, 230);

        collectionButton = new JButton("Collection");
        collectionButton.setBackground(lightBlue);
        collectionButton.setOpaque(true);
        collectionButton.setBorderPainted(false);
        collectionButton.setFont(new Font("SansSerif", Font.BOLD, 14));

        historyButton = new JButton("History");
        historyButton.setBackground(lightYellow);
        historyButton.setOpaque(true);
        historyButton.setBorderPainted(false);
        historyButton.setFont(new Font("SansSerif", Font.BOLD, 14));

        reviewButton = new JButton("Review");
        reviewButton.setBackground(lightBlue);
        reviewButton.setOpaque(true);
        reviewButton.setBorderPainted(false);
        reviewButton.setFont(new Font("SansSerif", Font.BOLD, 14));

        searchButton = new JButton("Search");
        searchButton.setBackground(lightYellow);
        searchButton.setOpaque(true);
        searchButton.setBorderPainted(false);
        searchButton.setFont(new Font("SansSerif", Font.BOLD, 14));

        //collectionButton.addActionListener to be completed

        //reviewButton.addActionListener



        add(collectionButton);
        add(historyButton);
        add(reviewButton);
        add(searchButton);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new UserCenterView().setVisible(true));
    }



}

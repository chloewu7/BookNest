package view;

import app.ShowMyReviewsUseCaseFactory;
import interface_adapter.ViewManagerModel;
import search.service.interface_adapter.SearchViewModel;
import user_manage.service.reading_review.show_my_reviews.interface_adapter.ShowMyReviewsViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class UserCenterView extends JPanel {

    public final String viewName = "User Center";
    private JButton collectionButton;
    private JButton historyButton;
    private JButton reviewButton;
    private JButton searchButton;

    private ViewManagerModel viewManagerModel;


    public UserCenterView(ViewManagerModel viewManagerModel){
        this.viewManagerModel = viewManagerModel;
        createUI();
    }

    private void createUI(){
        this.setSize(1000, 600);
        this.setLayout(new GridLayout(4, 1, 10, 10));

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

        //historyButton.addActionListener to be completed

        //reviewButton.addActionListener to be completed

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewManagerModel.setActiveView("Search");
                viewManagerModel.firePropertyChanged();
            }
        });



        add(collectionButton);
        add(historyButton);
        add(reviewButton);
        add(searchButton);
    }



}

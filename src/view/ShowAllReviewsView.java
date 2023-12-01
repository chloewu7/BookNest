package view;


import user_manage.service.reading_review.show_all_reviews.interface_adapter.ShowAllReviewsController;
import user_manage.service.reading_review.show_all_reviews.interface_adapter.ShowAllReviewsState;
import user_manage.service.reading_review.show_all_reviews.interface_adapter.ShowAllReviewsViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ShowAllReviewsView extends JFrame implements ActionListener, PropertyChangeListener {
    public final String viewName = "my reviews";
    private String bookTitle = "";
    private float rating;
    private final ShowAllReviewsController showAllReviewsController;
    private final ShowAllReviewsViewModel showAllReviewsViewModel;
    private final JTextField reviewContentInputField = new JTextField(30);
    private final JButton returnButton;
    public ShowAllReviewsView(ShowAllReviewsController showAllReviewsController,
                             ShowAllReviewsViewModel showAllReviewsViewModel){
        this.showAllReviewsController = showAllReviewsController;
        this.showAllReviewsViewModel = showAllReviewsViewModel;
        setTitle("All Reviews");
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Color lightBlue = new Color(173, 216, 230);
        Color lightYellow = new Color(255, 255, 224);
        Color Orange = new Color(248, 152, 32);

        ShowAllReviewsState state = showAllReviewsViewModel.getState();
        rating = state.getRating();
        String ratingTxt = String.valueOf(rating);
        JLabel rating = new JLabel("Rating:" + ratingTxt);
        rating.setForeground(Orange);
        rating.setFont(new Font("SansSerif", Font.PLAIN, 20));
        bookTitle = state.getBookTitle();
        JLabel myReviewTitle = new JLabel(ShowAllReviewsViewModel.TITLE_LABEL + " for " + bookTitle
                + "                                                                                             ");
        myReviewTitle.setFont(new Font("SansSerif", Font.PLAIN, 20));
        JPanel titleAndRating = new JPanel();
        titleAndRating.add(myReviewTitle);
        titleAndRating.add(rating);
        titleAndRating.setPreferredSize(new Dimension(1000, 40));
        titleAndRating.setBackground(lightYellow);
        myReviewTitle.setForeground(Color.black);

        returnButton = new JButton(ShowAllReviewsViewModel.RETURN_BUTTON_LABEL);
        returnButton.setPreferredSize(new Dimension(80, 40));
        JPanel button = new JPanel();
        button.add(returnButton);

        JPanel allReviewsPanel = new JPanel();
        allReviewsPanel.setLayout(new BoxLayout(allReviewsPanel, BoxLayout.Y_AXIS));
        JScrollPane reviewsScrollPane = new JScrollPane(allReviewsPanel);
        add(reviewsScrollPane, BorderLayout.CENTER);
        allReviewsPanel.setBackground(lightBlue);


        for (String reviews: state.getReviewList()){
            JPanel reviewPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            reviewPanel.setBackground(lightBlue);
            reviewPanel.setBorder(BorderFactory.createLineBorder(Color.gray));

            reviewPanel.add(new JLabel(reviews));
            allReviewsPanel.add(reviewPanel);
        }

        add(titleAndRating, BorderLayout.NORTH);
        add(reviewsScrollPane, BorderLayout.CENTER);
        add(button, BorderLayout.SOUTH);
    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}

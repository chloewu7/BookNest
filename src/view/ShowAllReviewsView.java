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

public class ShowAllReviewsView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "all reviews";
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
        this.setSize(1000, 600);
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
        titleAndRating.setPreferredSize(new Dimension(1000, 80));
        titleAndRating.setBackground(lightYellow);
        myReviewTitle.setForeground(Color.black);

        JPanel titleAndRatingPanel  = new JPanel();
        titleAndRatingPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0; // Expand horizontally
        gbc.weighty = 1.0; // Expand vertically
        gbc.anchor = GridBagConstraints.CENTER; // Center the label
        titleAndRatingPanel.add(titleAndRating, gbc);

        returnButton = new JButton(ShowAllReviewsViewModel.RETURN_BUTTON_LABEL);
        returnButton.setPreferredSize(new Dimension(130, 40));
        returnButton.setBackground(lightYellow);
        JPanel button = new JPanel();
        button.add(returnButton, BorderLayout.EAST);
        button.setBackground(lightBlue);
        button.setPreferredSize(new Dimension(1000, 70));
        returnButton.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(returnButton)) {
                            //TODO：跳转Write My Review
                        }
                    }
                }
        );

        JPanel allReviewsPanel = new JPanel();
        allReviewsPanel.setLayout(new BoxLayout(allReviewsPanel, BoxLayout.Y_AXIS));
        JScrollPane reviewsScrollPane = new JScrollPane(allReviewsPanel);
        reviewsScrollPane.setPreferredSize(new Dimension(1000, 450));
        allReviewsPanel.setBackground(lightBlue);

        if (state.getNoReviewMessage() != null){
            JLabel nonReviewMessage = new JLabel(state.getNoReviewMessage());
            nonReviewMessage.setFont(new Font("SansSerif", Font.PLAIN, 20));
            allReviewsPanel.add(nonReviewMessage);
        } else {
            for (String reviews : state.getReviewList()) {
                JPanel reviewPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
                reviewPanel.setBackground(lightBlue);
                reviewPanel.setBorder(BorderFactory.createLineBorder(Color.gray));

                reviewPanel.add(new JLabel(reviews));
                allReviewsPanel.add(reviewPanel);
            }
        }

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(lightBlue);
        this.add(titleAndRatingPanel);
        this.add(reviewsScrollPane);
        this.add(button);
    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}

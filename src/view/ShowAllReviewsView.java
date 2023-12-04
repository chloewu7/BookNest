package view;


import interface_adapter.ViewManagerModel;
import user_manage.service.reading_review.show_all_reviews.interface_adapter.ShowAllReviewsController;
import user_manage.service.reading_review.show_all_reviews.interface_adapter.ShowAllReviewsState;
import user_manage.service.reading_review.show_all_reviews.interface_adapter.ShowAllReviewsViewModel;
import user_manage.service.reading_review.write_reviews.interface_adapter.WriteReviewsController;
import user_manage.service.reading_review.write_reviews.interface_adapter.WriteReviewsState;
import user_manage.service.reading_review.write_reviews.interface_adapter.WriteReviewsViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DecimalFormat;

public class ShowAllReviewsView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "all reviews";
    private String bookTitle = "";
    private float rating;
    private final ShowAllReviewsController showAllReviewsController;
    private final ShowAllReviewsViewModel showAllReviewsViewModel;
    private final WriteReviewsController writeReviewsController;
    private final WriteReviewsViewModel writeReviewsViewModel;
    private final ViewManagerModel viewManagerModel;
    private final JTextField reviewContentInputField = new JTextField(30);
    private final JButton returnButton;
    private final JButton writeReviewsButton;
    private JLabel allReviewTitle;
    private JLabel ratingLable;
    private JPanel allReviewsPanel;
    private JPanel titleAndRatingPanel;
    private JPanel titleAndRating;
    private Color Orange = new Color(248, 152, 32);
    private Color lightBlue = new Color(173, 216, 230);
    private Color lightYellow = new Color(255, 255, 224);
    public ShowAllReviewsView(ViewManagerModel viewManagerModel,
                              ShowAllReviewsController showAllReviewsController,
                              ShowAllReviewsViewModel showAllReviewsViewModel,
                              WriteReviewsController writeReviewsController,
                              WriteReviewsViewModel writeReviewsViewModel){
        this.showAllReviewsController = showAllReviewsController;
        this.showAllReviewsViewModel = showAllReviewsViewModel;
        this.writeReviewsController = writeReviewsController;
        this.writeReviewsViewModel = writeReviewsViewModel;
        this.viewManagerModel = viewManagerModel;
        writeReviewsViewModel.addPropertyChangeListener(this);
        showAllReviewsViewModel.addPropertyChangeListener(this);
        this.setSize(1000, 600);
        ShowAllReviewsState state = showAllReviewsViewModel.getState();

        titleAndRating = new JPanel();
        titleAndRating.setLayout(new BoxLayout(titleAndRating, BoxLayout.Y_AXIS));

        titleAndRating.setPreferredSize(new Dimension(1000, 130));
        titleAndRating.setBackground(lightYellow);

        titleAndRatingPanel  = new JPanel();
        titleAndRatingPanel.setLayout(new GridBagLayout());
        titleAndRatingPanel.setBackground(lightYellow);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0; // Expand horizontally
        gbc.weighty = 1.0; // Expand vertically
        gbc.anchor = GridBagConstraints.CENTER; // Center the label
        titleAndRatingPanel.add(titleAndRating, gbc);

        returnButton = new JButton(ShowAllReviewsViewModel.RETURN_BUTTON_LABEL);
        returnButton.setPreferredSize(new Dimension(130, 60));
        returnButton.setBackground(lightYellow);
        JPanel button = new JPanel();
        button.add(returnButton);
        button.setBackground(lightBlue);
        button.setPreferredSize(new Dimension(1000, 90));
        returnButton.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(returnButton)) {
                            viewManagerModel.setActiveView("Search");
                            viewManagerModel.firePropertyChanged();
                        }
                    }
                }
        );

        writeReviewsButton = new JButton(ShowAllReviewsViewModel.WRITE_REVIEW_BUTTON_LABEL);
        writeReviewsButton.setPreferredSize(new Dimension(130, 60));
        JPanel middle = new JPanel();
        middle.setPreferredSize(new Dimension(150, 60));
        middle.setBackground(lightBlue);
        button.add(middle);
        button.add(writeReviewsButton);
        writeReviewsButton.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(writeReviewsButton)) {
                            WriteReviewsState writeReviewsState = writeReviewsViewModel.getState();
                            writeReviewsState.setUsername(state.getUsername());
                            writeReviewsState.setBookTitle(state.getBookTitle());
                            writeReviewsState.setAuthor(state.getAuthor());
                            writeReviewsViewModel.setState(writeReviewsState);
                            viewManagerModel.setActiveView("write review");
                            viewManagerModel.firePropertyChanged();
                        }
                    }
                }
        );



        allReviewsPanel = new JPanel();
        allReviewsPanel.setLayout(new BoxLayout(allReviewsPanel, BoxLayout.Y_AXIS));
        JScrollPane reviewsScrollPane = new JScrollPane(allReviewsPanel);
        reviewsScrollPane.setPreferredSize(new Dimension(1000, 490));
        allReviewsPanel.setBackground(lightBlue);




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
        ShowAllReviewsState state = (ShowAllReviewsState) evt.getNewValue();
        if (state.getNoReviewMessage() != null) {
            showNoReviewExistPage(state);
        } else {
            showAllReviewsPage(state);
        }
    }

    private void showNoReviewExistPage(ShowAllReviewsState state) {
        allReviewsPanel.removeAll();
        JLabel nonReviewMessage = new JLabel(state.getNoReviewMessage());
        nonReviewMessage.setFont(new Font("SansSerif", Font.PLAIN, 20));
        allReviewsPanel.add(nonReviewMessage);

        allReviewsPanel.revalidate();
        allReviewsPanel.repaint();
        updateTitleLabel(state);
    }

    private void updateTitleLabel(ShowAllReviewsState state) {
        titleAndRatingPanel.removeAll();
        bookTitle = state.getBookTitle();
        allReviewTitle = new JLabel(ShowAllReviewsViewModel.TITLE_LABEL + " for " + bookTitle
                + "             ");
        allReviewTitle.setFont(new Font("SansSerif", Font.PLAIN, 20));

        rating = state.getRating();
        // Create a DecimalFormat instance with the desired format
        DecimalFormat decimalFormat = new DecimalFormat("#.##");

        // Format the float value to a String with two decimal places
        ratingLable = new JLabel("Rating:" + decimalFormat.format(state.getRating()));
        ratingLable.setForeground(Orange);
        ratingLable.setFont(new Font("SansSerif", Font.PLAIN, 20));
        titleAndRatingPanel.add(allReviewTitle);
        titleAndRatingPanel.add(ratingLable);
        titleAndRatingPanel.repaint();
    }

    private void showAllReviewsPage(ShowAllReviewsState state){
        allReviewsPanel.removeAll();
        for (String reviews : state.getReviewList()) {
            JPanel reviewPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            reviewPanel.setBackground(lightBlue);
            reviewPanel.setBorder(BorderFactory.createLineBorder(Color.gray));

            JLabel review = new JLabel(reviews);
            review.setFont(new Font("SansSerif", Font.PLAIN, 20));

            reviewPanel.add(review);
            allReviewsPanel.add(reviewPanel);
        }
        allReviewsPanel.repaint();
        updateTitleLabel(state);
    }
}

package view;

import interface_adapter.ViewManagerModel;
import user_manage.service.reading_review.write_reviews.interface_adapter.WriteReviewsController;
import user_manage.service.reading_review.write_reviews.interface_adapter.WriteReviewsState;
import user_manage.service.reading_review.write_reviews.interface_adapter.WriteReviewsViewModel;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class
WriteReviewsView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "write review";
    private final WriteReviewsController writeReviewsController;
    private final WriteReviewsViewModel writeReviewsViewModel;
    private final JTextField reviewContentInputField = new JTextField(30);
    private final JButton writeReview;
    private final JButton returnAllReviews;
    private final JSlider ratingSlider;
    private final ViewManagerModel viewManagerModel;

    public WriteReviewsView(ViewManagerModel viewManagerModel,
                            WriteReviewsController writeReviewsController,
                            WriteReviewsViewModel writeReviewsViewModel){
        this.writeReviewsController = writeReviewsController;
        this.writeReviewsViewModel = writeReviewsViewModel;
        this.viewManagerModel = viewManagerModel;
        writeReviewsViewModel.addPropertyChangeListener(this);
        Color lightBlue = new Color(173, 216, 230);
        Color darkBlue = new Color(80, 100, 230);
        Color lightYellow = new Color(255, 255, 224);
        setBackground(lightBlue);
        setSize(1000, 600);
        WriteReviewsState state = writeReviewsViewModel.getState();

        JLabel title = new JLabel(WriteReviewsViewModel.TITLE_LABEL + " on " + state.getBookTitle());
        title.setFont(new Font("SansSerif", Font.PLAIN, 20));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(lightYellow);
        titlePanel.setPreferredSize(new Dimension(1000, 80));
        titlePanel.add(title);
        JLabel reviewTitle = new JLabel(WriteReviewsViewModel.REVIEW_LABEL);
        reviewTitle.setFont(new Font("SansSerif", Font.PLAIN, 15));


        JPanel reviewContentInfo = new JPanel();
        reviewContentInfo.setLayout(null); // Set the layout manager to null
        reviewContentInfo.setBackground(lightBlue);
        reviewContentInfo.setPreferredSize(new Dimension(1000, 300));
        reviewContentInputField.setPreferredSize(new Dimension(500, 200));
        reviewContentInputField.setBounds(260, 10, 500, 200); // Set the position and size
        reviewContentInputField.setAlignmentX(Component.CENTER_ALIGNMENT);
        reviewContentInfo.add(reviewContentInputField);
        //Implementing Input field


        reviewContentInputField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                WriteReviewsState currentState = writeReviewsViewModel.getState();
                String text = reviewContentInputField.getText() + e.getKeyChar();
                currentState.setReviewContent(text);
                writeReviewsViewModel.setState(currentState);
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        // Implementing Slider

        ratingSlider = new JSlider(JSlider.HORIZONTAL, 0, 5, 1);
        ratingSlider.setMajorTickSpacing(1);
        ratingSlider.setPaintLabels(true);
        ratingSlider.setPreferredSize(new Dimension(300, 200));
        JLabel rating = new JLabel(WriteReviewsViewModel.RATING_LABEL);
        rating.setFont(new Font("SansSerif", Font.PLAIN, 15));
        LabelSliderPanel reviewRatingInfo = new LabelSliderPanel(rating, ratingSlider);
        reviewRatingInfo.setBackground(lightBlue);
        reviewRatingInfo.setPreferredSize(new Dimension(1000, 200));
        ratingSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                WriteReviewsState currentState = writeReviewsViewModel.getState();
                int rating = ratingSlider.getValue();
                currentState.setRating(rating);
                writeReviewsViewModel.setState(currentState);
            }
        });


        // Implementing Buttons
        JPanel buttons = new JPanel();
        buttons.setPreferredSize(new Dimension(1000, 100));
        buttons.setBackground(lightBlue);
        writeReview = new JButton(WriteReviewsViewModel.WRITEREVIEW_BUTTON_LABEL);
        writeReview.setPreferredSize(new Dimension(200, 50));
        returnAllReviews = new JButton(WriteReviewsViewModel.RETURN_BUTTON_LABEL);
        returnAllReviews.setPreferredSize(new Dimension(200, 50));
        JPanel middle = new JPanel();
        middle.setPreferredSize(new Dimension(200, 50));
        middle.setBackground(lightBlue);
        buttons.add(writeReview);
        buttons.add(middle);
        buttons.add(returnAllReviews);
        writeReview.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(writeReview)) {
                            WriteReviewsState currentState = writeReviewsViewModel.getState();

                            writeReviewsController.execute(currentState.getBookTitle(), currentState.getAuthor(),
                                    currentState.getUsername(), currentState.getReviewContent(),
                                    currentState.getRating());


                        }
                    }
                }
        );

        returnAllReviews.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource().equals(returnAllReviews)) {
                    viewManagerModel.setActiveView("Search");
                    viewManagerModel.firePropertyChanged();
                }
            }
        });


        // Add all Component into the Panel
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(titlePanel);
        this.add(reviewRatingInfo);
        this.add(reviewTitle);
        this.add(reviewContentInfo);
        this.add(buttons);

    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        WriteReviewsState state = (WriteReviewsState) evt.getNewValue();
        JOptionPane.showMessageDialog(this, "Thank you for submitting your review for " + state.getBookTitle() + "!");
    }

}

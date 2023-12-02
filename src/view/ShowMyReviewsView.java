package view;

import user_manage.service.reading_review.show_my_reviews.interface_adapter.ShowMyReviewsController;
import user_manage.service.reading_review.show_my_reviews.interface_adapter.ShowMyReviewsState;
import user_manage.service.reading_review.show_my_reviews.interface_adapter.ShowMyReviewsViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ShowMyReviewsView extends JFrame implements ActionListener, PropertyChangeListener {
    public final String viewName = "my reviews";
    private final ShowMyReviewsController showMyReviewsController;
    private final ShowMyReviewsViewModel showMyReviewsViewModel;
    private final JTextField reviewContentInputField = new JTextField(30);
    private final JButton returnButton;
    public ShowMyReviewsView(ShowMyReviewsController showMyReviewsController,
                             ShowMyReviewsViewModel showMyReviewsViewModel){
        this.showMyReviewsController = showMyReviewsController;
        this.showMyReviewsViewModel = showMyReviewsViewModel;
        setTitle("My Reviews");
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Color lightBlue = new Color(173, 216, 230);
        Color darkBlue = new Color(80, 100, 230);
        Color lightYellow = new Color(255, 255, 224);

        JLabel myReviewTitle = new JLabel(ShowMyReviewsViewModel.TITLE_LABEL);
        myReviewTitle.setFont(new Font("SansSerif", Font.PLAIN, 20));
        JPanel title = new JPanel();
        title.add(myReviewTitle, BorderLayout.EAST);
        title.setPreferredSize(new Dimension(1000, 40));
        title.setBackground(lightYellow);
        myReviewTitle.setForeground(Color.black);

        returnButton = new JButton(ShowMyReviewsViewModel.RETURN_BUTTON_LABEL);
        returnButton.setPreferredSize(new Dimension(80, 40));
        JPanel button = new JPanel();
        button.add(returnButton);

        JPanel myReviewsPanel = new JPanel();
        myReviewsPanel.setLayout(new BoxLayout(myReviewsPanel, BoxLayout.Y_AXIS));
        JScrollPane reviewsScrollPane = new JScrollPane(myReviewsPanel);
        add(reviewsScrollPane, BorderLayout.CENTER);
        myReviewsPanel.setBackground(lightBlue);

        ShowMyReviewsState state = showMyReviewsViewModel.getState();
        for (String reviews: state.getReviewList()){
            JPanel reviewPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            reviewPanel.setBackground(lightBlue);
            reviewPanel.setBorder(BorderFactory.createLineBorder(Color.gray));

            reviewPanel.add(new JLabel(reviews));
            myReviewsPanel.add(reviewPanel);
        }

        add(title, BorderLayout.NORTH);
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

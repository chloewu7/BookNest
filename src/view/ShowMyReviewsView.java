package view;

import interface_adapter.ViewManagerModel;
import user_manage.service.reading_review.show_my_reviews.interface_adapter.ShowMyReviewsController;
import user_manage.service.reading_review.show_my_reviews.interface_adapter.ShowMyReviewsState;
import user_manage.service.reading_review.show_my_reviews.interface_adapter.ShowMyReviewsViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ShowMyReviewsView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "my reviews";
    private final ShowMyReviewsController showMyReviewsController;
    private final ShowMyReviewsViewModel showMyReviewsViewModel;
    private final JTextField reviewContentInputField = new JTextField(30);
    private final JButton returnButton;
    private final ViewManagerModel viewManagerModel;

    public ShowMyReviewsView(ViewManagerModel viewManagerModel,
                             ShowMyReviewsController showMyReviewsController,
                             ShowMyReviewsViewModel showMyReviewsViewModel){
        this.viewManagerModel = viewManagerModel;
        this.showMyReviewsController = showMyReviewsController;
        this.showMyReviewsViewModel = showMyReviewsViewModel;
        this.setSize(1000, 600);
        Color lightBlue = new Color(173, 216, 230);
        Color darkBlue = new Color(80, 100, 230);
        Color lightYellow = new Color(255, 255, 224);

        JLabel myReviewTitle = new JLabel(ShowMyReviewsViewModel.TITLE_LABEL);
        myReviewTitle.setFont(new Font("SansSerif", Font.PLAIN, 20));
        JPanel title = new JPanel();
        title.add(myReviewTitle, BorderLayout.EAST);
        title.setPreferredSize(new Dimension(1000, 80));
        title.setBackground(lightYellow);
        myReviewTitle.setForeground(Color.black);

        returnButton = new JButton(ShowMyReviewsViewModel.RETURN_BUTTON_LABEL);
        returnButton.setPreferredSize(new Dimension(150, 40));
        JPanel button = new JPanel();
        button.add(returnButton);
        button.setBackground(lightBlue);
        button.setPreferredSize(new Dimension(1000, 70));
        returnButton.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(returnButton)) {
                            viewManagerModel.setActiveView("User Center");
                            viewManagerModel.firePropertyChanged();
                        }
                    }
                }
        );

        JPanel myReviewsPanel = new JPanel();
        myReviewsPanel.setLayout(new BoxLayout(myReviewsPanel, BoxLayout.Y_AXIS));
        JScrollPane reviewsScrollPane = new JScrollPane(myReviewsPanel);
        myReviewsPanel.setBackground(lightBlue);
        reviewsScrollPane.setPreferredSize(new Dimension(1000, 450));

        ShowMyReviewsState state = showMyReviewsViewModel.getState();
        if (state.getNoReviewMessage() != null){
            JLabel nonReviewMessage = new JLabel(state.getNoReviewMessage());
            nonReviewMessage.setFont(new Font("SansSerif", Font.PLAIN, 20));
            myReviewsPanel.add(nonReviewMessage);
        } else {
            for (String reviews : state.getReviewList()) {
                JPanel reviewPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
                reviewPanel.setBackground(lightBlue);
                reviewPanel.setBorder(BorderFactory.createLineBorder(Color.gray));

                reviewPanel.add(new JLabel(reviews));
                myReviewsPanel.add(reviewPanel);
            }
        }
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(lightBlue);
        this.add(title);
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

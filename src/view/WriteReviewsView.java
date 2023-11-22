package view;

import user_manage.service.reading_review.write_reviews.WriteReviewsInteractor;
import user_manage.service.reading_review.write_reviews.interface_adapter.WriteReviewsController;
import user_manage.service.reading_review.write_reviews.interface_adapter.WriteReviewsViewModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class WriteReviewsView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "write review";
    private final WriteReviewsController writeReviewsController;
    private final WriteReviewsViewModel writeReviewsViewModel;

    public WriteReviewsView(WriteReviewsController writeReviewsController,
                            WriteReviewsViewModel writeReviewsViewModel){
        this.writeReviewsController = writeReviewsController;
        this.writeReviewsViewModel = writeReviewsViewModel;

    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}

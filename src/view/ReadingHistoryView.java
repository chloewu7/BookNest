package view;


import interface_adapter.ViewManagerModel;
//import user_manage.service.history.add_history.Interface_adapter.AddingHistoryController;
import user_manage.service.history.read_history.interface_adpter.ReadingHistoryController;
import user_manage.service.history.read_history.interface_adpter.ReadingHistoryState;
import user_manage.service.history.read_history.interface_adpter.ReadingHistoryViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ReadingHistoryView extends JPanel implements ActionListener, PropertyChangeListener {

    private final ReadingHistoryViewModel readingHistoryViewModel;
    private final ReadingHistoryController readingHistoryController;

    private final ViewManagerModel viewManagerModel;



    private final JButton historyButton = new JButton("History");
    private JButton backButton = new JButton("return"); // Back button

    public final String viewName = "History";
    private JTextArea historyTextArea;

    private JPanel History;

    private JPanel Title;

    private JLabel readBookTitle;

    private final JPanel historyPanel = new JPanel();
    private final JScrollPane scrollPane = new JScrollPane(historyPanel);

    private Color Orange = new Color(248, 152, 32);
    private Color lightBlue = new Color(173, 216, 230);
    private Color lightYellow = new Color(255, 255, 224);





    public ReadingHistoryView(ReadingHistoryViewModel readingHistoryViewModel, ReadingHistoryController controller, ViewManagerModel viewManagerModel) {
        this.readingHistoryViewModel = readingHistoryViewModel;
        this.readingHistoryController = controller;

        this.readingHistoryViewModel.addPropertyChangeListener(this);
        this.viewManagerModel = viewManagerModel;
        this.setSize(1000,600);

        historyPanel.setLayout(new BoxLayout(historyPanel, BoxLayout.Y_AXIS));
        historyPanel.setBackground(lightBlue);

        scrollPane.getViewport().setBackground(lightBlue);
        scrollPane.setPreferredSize(new Dimension(700, 300));
        JLabel titleLabel = new JLabel("Reading History", SwingConstants.CENTER);
        titleLabel.setOpaque(true);
        titleLabel.setBackground(lightYellow);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        titleLabel.setPreferredSize(new Dimension(900, 50));



        this.setLayout(new BorderLayout());
        this.add(titleLabel, BorderLayout.NORTH);
        this.add(scrollPane, BorderLayout.CENTER);

        backButton.setPreferredSize(new Dimension(130, 60));
        backButton.setBackground(lightBlue);
        backButton.setFont(new Font("SansSerif", Font.BOLD, 12));
        JPanel button = new JPanel();
        button.add(backButton);
        button.setBackground(lightYellow);
        button.setPreferredSize(new Dimension(1000, 90));
        this.add(button, BorderLayout.SOUTH);



        //addHistoryData();

        ReadingHistoryState state = readingHistoryViewModel.getState();
        controller.execute(state.getUserName(),state.getReadBook());




        backButton.addActionListener(
                new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == backButton){
                    viewManagerModel.setActiveView("Search");
                    viewManagerModel.firePropertyChanged();
                }
            }


        });

    }


    /*private void addHistoryData() {
        List<String> fakeHistory = Arrays.asList("Clean Code", "The Journey to The West", "Toronto");
        for (String record : fakeHistory) {
            JLabel historyLabel = new JLabel(record, SwingConstants.CENTER);
            historyLabel.setFont(new Font("SansSerif", Font.BOLD, 16)); // Increase font size
            historyLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Center alignment
            historyLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0)); // Adjust padding
            historyPanel.add(historyLabel);
        }
        historyPanel.revalidate();
        historyPanel.repaint();}

     */



    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        ReadingHistoryState state = (ReadingHistoryState) evt.getNewValue();
        if ("history".equals(evt.getPropertyName())) {
            updateHistoryArea(state);
        }
    }

    private void updateHistoryArea(ReadingHistoryState state) {

        for (String record : state.getHistory()) {
            JLabel historyLabel1 = new JLabel(record, SwingConstants.CENTER);
            historyLabel1.setFont(new Font("SansSerif", Font.BOLD, 16)); // Increase font size
            historyLabel1.setAlignmentX(Component.CENTER_ALIGNMENT); // Center alignment
            historyLabel1.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0)); // Adjust padding
            historyPanel.add(historyLabel1);
        }
        historyPanel.revalidate();
        historyPanel.repaint();}

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}

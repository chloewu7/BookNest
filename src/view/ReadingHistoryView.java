package view;


import interface_adapter.ViewManagerModel;
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





        /*History = new JPanel();
        History.setLayout(new BoxLayout(History, BoxLayout.Y_AXIS));
        History.setPreferredSize(new Dimension(900,500));
        History.setBackground(lightBlue);

        Title = new JPanel();
        Title.setLayout(new GridBagLayout());
        Title.setBackground(lightYellow);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx =1.0;
        gbc.weighty =1.0;
        gbc.anchor = GridBagConstraints.CENTER;
        Title.add(History, gbc);

         */



        backButton.setPreferredSize(new Dimension(130, 60));
        backButton.setBackground(lightBlue);
        backButton.setFont(new Font("SansSerif", Font.BOLD, 12));
        JPanel button = new JPanel();
        button.add(backButton);
        button.setBackground(lightYellow);
        button.setPreferredSize(new Dimension(1000, 90));


        this.add(button, BorderLayout.SOUTH);





        ReadingHistoryState state = readingHistoryViewModel.getState();
        readingHistoryController.fetchUserHistory();




        /*historyButton.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent evt) {
                    if (evt.getSource() == historyButton) {
                        ReadingHistoryState readingHistoryState = readingHistoryViewModel.getState();
                        readingHistoryState.setHistory(state.getHistory());
                        readingHistoryState.setUserName(state.getUserName());
                        createHistoryWindow();
                    }
                }
        });*/

        backButton.addActionListener(
                new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == backButton){
                    viewManagerModel.setActiveView("Search");
                    viewManagerModel.firePropertyChanged();
                }
            }


        });

        //this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        //this.add(historyButton);
        //this.add(backButton); // Adding the back button to the view
    }




    /*private void createHistoryWindow() {
        JDialog historyDialog = new JDialog();
        historyDialog.setTitle("User Reading History");
        historyDialog.setSize(500, 400);
        historyDialog.setLayout(new BorderLayout());

        historyTextArea = new JTextArea();
        historyTextArea.setEditable(false);
        historyTextArea.setLineWrap(true);
        historyTextArea.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(historyTextArea);
        historyDialog.add(scrollPane, BorderLayout.CENTER);

        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(e -> historyDialog.dispose());
        historyDialog.add(exitButton, BorderLayout.PAGE_END);

        historyDialog.setVisible(true);
        readingHistoryController.fetchUserHistory();
    }

     */

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("history".equals(evt.getPropertyName())) {
            updateHistoryArea((ArrayList<String>) evt.getNewValue());
        }
    }

    private void updateHistoryArea(ArrayList<String> history) {
        historyPanel.removeAll();
        for (String record : history) {
            JLabel historyLabel = new JLabel(record);
            historyLabel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
            historyPanel.add(historyLabel);
        }
        historyPanel.revalidate();
        historyPanel.repaint();}

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}

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
    private final JButton backButton = new JButton("Back"); // Back button

    public final String viewName = "History";
    private JTextArea historyTextArea;



    public ReadingHistoryView(ReadingHistoryViewModel readingHistoryViewModel, ReadingHistoryController controller, ViewManagerModel viewManagerModel) {
        this.readingHistoryViewModel = readingHistoryViewModel;
        this.readingHistoryController = controller;
        this.readingHistoryViewModel.addPropertyChangeListener(this);
        this.viewManagerModel = viewManagerModel;

        ReadingHistoryState state = readingHistoryViewModel.getState();

        historyButton.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent evt) {
                    if (evt.getSource() == historyButton) {
                        ReadingHistoryState readingHistoryState = readingHistoryViewModel.getState();
                        readingHistoryState.setHistory(state.getHistory());
                        readingHistoryState.setUserName(state.getUserName());
                        createHistoryWindow();
                    }
                }
        });
        backButton.addActionListener(
                new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == backButton){
                    viewManagerModel.setActiveView("Search");
                    viewManagerModel.firePropertyChanged();
                }
            }


        });

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(historyButton);
        this.add(backButton); // Adding the back button to the view
    }




    private void createHistoryWindow() {
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

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("history".equals(evt.getPropertyName())) {
            updateHistoryArea((ArrayList<String>) evt.getNewValue());
        }
    }

    private void updateHistoryArea(ArrayList<String> history) {
        StringBuilder historyText = new StringBuilder();
        for (String record : history) {
            historyText.append(record).append("\n");
        }
        historyTextArea.setText(historyText.toString());
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}

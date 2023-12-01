package view;


import user_manage.service.history.read_history.interface_adpter.ReadingHistoryController;
import user_manage.service.history.read_history.interface_adpter.ReadingHistoryViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class ReadingHistoryView extends JPanel implements ActionListener, PropertyChangeListener {

    private final ReadingHistoryViewModel readingHistoryViewModel;
    private final ReadingHistoryController readingHistoryController;

    private final JButton historyButton = new JButton("History");
    private JTextArea historyTextArea;

    public ReadingHistoryView(ReadingHistoryViewModel readingHistoryViewModel, ReadingHistoryController controller) {
        this.readingHistoryViewModel = readingHistoryViewModel;
        this.readingHistoryController = controller;
        this.readingHistoryViewModel.addPropertyChangeListener(this);

        historyButton.addActionListener(this);
        this.add(historyButton);
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == historyButton) {
            createHistoryWindow();
        }
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
            updateHistoryArea((Map<LocalDateTime, String>) evt.getNewValue());
        }
    }

    private void updateHistoryArea(Map<LocalDateTime, String> history) {
        StringBuilder historyText = new StringBuilder();
        for (Map.Entry<LocalDateTime, String> entry : history.entrySet()) {
            historyText.append(entry.getKey().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME))
                    .append(" - ")
                    .append(entry.getValue())
                    .append("\n");
        }
        historyTextArea.setText(historyText.toString());
    }
}

package view;


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

public class HistoryView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "History";
    private final ReadingHistoryViewModel historyViewModel;
    private final HistoryController historyController;

    private final JTextArea historyTextArea = new JTextArea(20, 40);
    private final JScrollPane scrollPane = new JScrollPane(historyTextArea);

    // New components for filtering
    private final JTextField filterTextField = new JTextField(15);
    private final JButton filterButton = new JButton("Filter");

    public HistoryView(HistoryViewModel historyViewModel, HistoryController controller) {
        this.historyViewModel = historyViewModel;
        this.historyController = controller;
        this.historyViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel("User History");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        historyTextArea.setEditable(false);
        historyTextArea.setLineWrap(true);
        historyTextArea.setWrapStyleWord(true);

        // Setting up the filter panel
        LabelTextPanel filterPanel = new LabelTextPanel(new JLabel("Filter:"), filterTextField);
        filterButton.addActionListener(this);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(filterPanel);
        this.add(filterButton);
        this.add(scrollPane);
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == filterButton) {
            // Implement filtering logic based on filterTextField's text
            // For example, you can request the controller to update the view model with filtered data
            System.out.println("Filtering history with criteria: " + filterTextField.getText());
        } else {
            System.out.println("Action performed: " + evt.getActionCommand());
        }
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

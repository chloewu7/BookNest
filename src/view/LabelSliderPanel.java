package view;

import javax.swing.*;

public class LabelSliderPanel extends JPanel {
    LabelSliderPanel(JLabel label, JSlider slider) {
        this.add(label);
        this.add(slider);
    }
}

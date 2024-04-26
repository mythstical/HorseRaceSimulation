import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Track extends JPanel {
    private final JLabel laneLabel;
    private final JLabel track;
    private final JLabel finish;

    private int horseIndex;

    private final JComboBox<String> horseSelector;

    public Track(String laneLabel, Horse[] horses, int distance){
        this.laneLabel = new JLabel(laneLabel);
        this.track = new JLabel(" ".repeat(distance));
        this.finish = new JLabel();
        this.horseSelector = new JComboBox<>();
        for(Horse horse: horses){
            this.horseSelector.addItem(horse.getName());
        }
        this.horseSelector.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                horseIndex = horseSelector.getSelectedIndex();
            }
        });

        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(0, 2, 2, 2);

        c.gridx = 0;
        c.gridy = 0;
        c.ipadx = 20;
        c.ipady = 5;

        add(this.laneLabel, c);

        c.gridx = 1;
        c.gridy = 0;
        c.ipadx = 20;
        c.ipady = 5;

        add(this.horseSelector, c);

        c.gridx = 2;
        c.gridy = 0;
        c.ipadx = 300;
        c.ipady = 5;
        add(this.track, c);

    }

    public int getHorseIndex() {
        return horseIndex;
    }

    public void setLane(String text){
        laneLabel.setText(text);
    }

    public void setTrack(String text){
        track.setText(text);
    }

    public void setFinish(String text){
        finish.setText(text);
    }
}

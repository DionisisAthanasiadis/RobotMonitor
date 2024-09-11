package robot;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class RobotGUI extends JFrame {
    private RobotMonitor robot = new RobotMonitor();
    private JPanel gridPanel = new JPanel();
    private JLabel[][] gridCells = new JLabel[6][6];
    private static final Color COLOR_DEFAULT = Color.WHITE;
    private static final Color COLOR_ROBOT_ON = Color.GREEN;
    private static final Color COLOR_ROBOT_OFF = Color.GRAY;
    private static final Color COLOR_DOOR = Color.RED;

    public RobotGUI() {
        super("Robot Monitor");
        setSize(500, 450);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        gridPanel.setLayout(new GridLayout(6, 6));
        initializeGrid();

        JPanel buttonPanel = new JPanel();
        String[] commands = {"UP", "DOWN", "LEFT", "RIGHT", "EXIT", "TOGGLE STATUS"};
        for (String command : commands) {
            JButton button = new JButton(command);
            button.addActionListener(new ButtonListener());
            buttonPanel.add(button);
        }

        add(gridPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        updateGrid();
        setVisible(true);
    }

    private void initializeGrid() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                gridCells[i][j] = new JLabel();
                gridCells[i][j].setOpaque(true);
                gridCells[i][j].setBackground(COLOR_DEFAULT);
                gridPanel.add(gridCells[i][j]);
            }
        }
        gridCells[5][5].setBackground(COLOR_DOOR); // Set door position
    }

    private void updateGrid() {
        for (JLabel[] row : gridCells) {
            for (JLabel cell : row) {
                cell.setBackground(COLOR_DEFAULT);
            }
        }
        gridCells[5][5].setBackground(COLOR_DOOR);
        Color robotColor = robot.getStatus() ? COLOR_ROBOT_ON : COLOR_ROBOT_OFF;
        gridCells[robot.getRow() - 1][robot.getColumn() - 1].setBackground(robotColor);
    }

    private class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            boolean result = false;
            switch (e.getActionCommand()) {
                case "UP": result = robot.moveUp(); break;
                case "DOWN": result = robot.moveDown(); break;
                case "LEFT": result = robot.moveLeft(); break;
                case "RIGHT": result = robot.moveRight(); break;
                case "TOGGLE STATUS": result = robot.toggleStatus(); break;
                case "EXIT":
                    if (robot.exit()) {
                        JOptionPane.showMessageDialog(null, "Exited successfully!");
                        return;
                    } else {
                        JOptionPane.showMessageDialog(null, "Not at the door!", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
            }
            if (!result && !e.getActionCommand().equals("TOGGLE STATUS")) {
                JOptionPane.showMessageDialog(null, "Move invalid!", "Error", JOptionPane.ERROR_MESSAGE);
            }
            updateGrid();
        }
    }

    public static void main(String[] args) {
        new RobotGUI();
    }
}

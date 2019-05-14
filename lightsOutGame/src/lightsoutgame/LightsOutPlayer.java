
package lightsoutgame;

import javax.swing.*;
import javax.swing.text.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import exceptions.*;

class LightsOutPlayer extends JFrame {

    public LightsOutPlayer() {
        lightsOut = new LightsOut(5);
        setSize(lightsOut.getSize() * 50, lightsOut.getSize() * 50);
        setTitle("LightsOut");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JMenuBar menubar = new JMenuBar();
        JMenu menu = new JMenu("File");
        JMenuItem loadMenuItem = new JMenuItem("Load...");

        loadMenuItem.addActionListener(new LoadMenuItemListener());

        menu.add(loadMenuItem);
        menubar.add(menu);
        setJMenuBar(menubar);

        JPanel grid = new JPanel();
        grid.setLayout(new GridLayout(lightsOut.getSize(), lightsOut.getSize()));
        add(grid, BorderLayout.CENTER);

        buttons = new JButton[lightsOut.getSize()][lightsOut.getSize()];
        for (int i = 0; i < lightsOut.getSize(); i++) {
            for (int j = 0; j < lightsOut.getSize(); j++) {
                JButton button = new JButton();
                buttons[i][j] = button;
                button.setBackground(Color.BLACK);
                button.setOpaque(true);
                grid.add(button);
                GridListener listener = new GridListener(i, j);
                button.addActionListener(listener);
            }
        }
        detectLit();
    }

    private final LightsOut lightsOut;
    private final JButton[][] buttons;

    private void detectLit() {
        for (int i = 0; i < lightsOut.getSize(); i++) {
            for (int j = 0; j < lightsOut.getSize(); j++) {
                Color buttonColor = Color.BLACK;
                if (lightsOut.isLit(i, j)) {
                    buttonColor = Color.WHITE;
                }
                buttons[i][j].setBackground(buttonColor);
            }
        }
    }

    class LoadMenuItemListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            JFileChooser fc = new JFileChooser();
            int fc_return = fc.showOpenDialog(LightsOutPlayer.this);
            if (fc_return == JFileChooser.APPROVE_OPTION) {
                try {
                    new LightsOutFileLoader().load(lightsOut, fc.getSelectedFile());
                    detectLit();
                } catch (FileNotFoundException ex) {
                    JOptionPane.showMessageDialog(LightsOutPlayer.this,
                            "No such file!");
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(LightsOutPlayer.this,
                            "Error reading the selected file!");
                } catch (UnsupportedLightsOutFileException ex) {
                    JOptionPane.showMessageDialog(LightsOutPlayer.this,
                            "Invalid file selected!");
                }

            }
        }
    }

    class GridListener implements ActionListener {

        private int row;
        private int col;

        public GridListener(int row, int col) {
            this.row = row;
            this.col = col;
        }

        public void actionPerformed(ActionEvent e) {
            lightsOut.press(row, col);
            detectLit();
        }
    }

    public static void main(String[] args) {
        LightsOutPlayer player = new LightsOutPlayer();
        player.setVisible(true);

    }
}
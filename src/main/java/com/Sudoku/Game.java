
package com.Sudoku;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;





public class Game extends JFrame {

	private Profile profile;

	private int ICON_LENGTH = 50;
	private long INTERVAL = 1;

	private JButton[][] icons = null;
	private JLabel timer = null;
	private JLabel steps = null;

	private TimerTask timer_task = null;



	public Game(Profile profile) {
		this.profile = profile;
	}



	public void initialize() {
		this.icons = new JButton[this.profile.SIZE][this.profile.SIZE];

		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		this.setMaximumSize(new Dimension(800, 800));
		this.setMinimumSize(new Dimension(800, 800));
		this.setResizable(false);
		this.setTitle("Game - " + this.profile.name);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);

		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.add(Box.createVerticalStrut(25));



		/* ----- Header ----- */

		JPanel panel_h = new JPanel();
		panel_h.setLayout(new BoxLayout(panel_h, BoxLayout.X_AXIS));
		panel_h.add(Box.createHorizontalGlue());

		this.steps = new JLabel(Long.toString(this.profile.steps));
		this.steps.setFont(new Font("Arial", Font.PLAIN, 10));
		this.steps.setForeground(Color.BLACK);
		this.steps.setVisible(true);
		panel_h.add(this.steps, BorderLayout.CENTER);

		panel_h.add(Box.createHorizontalGlue());

		this.timer = new JLabel(this.timerString());
		this.timer.setFont(new Font("Arial", Font.PLAIN, 10));
		this.timer.setForeground(Color.BLACK);
		this.timer.setVisible(true);

		panel_h.add(this.timer, BorderLayout.CENTER);
		panel_h.add(Box.createHorizontalGlue());

		panel.add(panel_h, BorderLayout.CENTER);
		panel.add(Box.createVerticalStrut(25));



		/* ----- Game Board ----- */

		JPanel board_panel = new JPanel();
		board_panel.setLayout(new GridLayout(this.profile.SIZE, this.profile.SIZE));
		board_panel.setMaximumSize(new Dimension(600, 600));
		board_panel.setMinimumSize(new Dimension(600, 600));

		for (int r = 0; r < this.profile.map.length; r++) {
			for (int c = 0; c < this.profile.map[r].length; c++) {
				final int row = (
					((r / this.profile.SECTION_SIZE) * this.profile.SECTION_SIZE) + (c / this.profile.SECTION_SIZE)
				);
				final int column = (
					((r % this.profile.SECTION_SIZE) * this.profile.SECTION_SIZE) + (c % this.profile.SECTION_SIZE)
				);
				Integer n = this.profile.map[row][column];

				JPanel panel_b = new JPanel();
				JButton b = new JButton((n != 0) ? String.valueOf(n) : "");
				this.icons[row][column] = b;
				b.addFocusListener(new FocusAdapter() {

					@Override
					public void focusGained(FocusEvent e) {
						((JButton) e.getSource()).setBackground(new Color(110, 170, 250, 128));
					}

					@Override
					public void focusLost(FocusEvent e) {
						((JButton) e.getSource()).setBackground(Color.WHITE);
					}
				});
				b.addKeyListener(new KeyListener() {

					public void keyPressed(KeyEvent e) {
						JButton b = (JButton) e.getSource();
						int v = Character.getNumericValue(e.getKeyChar());

						if (b.isEnabled() && b.isFocusOwner() && (v >= 1) && (v <= 9)) {
							try { setValue(row, column, v); } catch (Exception err) {}
						}
					}

					public void keyReleased(KeyEvent e) {}

					public void keyTyped(KeyEvent e) {}
				});
				b.setBackground(Color.WHITE);
				b.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, true));
				b.setEnabled(true);
				b.setFocusPainted(false);
				b.setFont(new Font("Arial", Font.PLAIN, 15));
				b.setPreferredSize(new Dimension(this.ICON_LENGTH, this.ICON_LENGTH));
				b.setVisible(true);

				panel_b.add(b);

				board_panel.add(panel_b);
			}
		}

		panel.add(board_panel);

		this.add(panel);

		this.timer_task = new TimerTask() {
			public void run() {
				try { updateTimer(); } catch (Exception err) {}
			}
		};
		new Timer().schedule(this.timer_task, 0, this.INTERVAL * 1000);
	}



	private void setValue(int row, int column, int value) throws Exception {
		this.profile.setValue(row, column, value);
		this.icons[row][column].setText(String.valueOf(this.profile.map[row][column]));
		this.steps.setText(Long.toString(this.profile.steps));

		if (this.profile.validate()) {
			this.setVisible(false);

			this.timer_task.cancel();

			new WonGame(this.profile).initialize();
		}
	}



	private void updateTimer() throws Exception {
		this.profile.updateTimer(this.INTERVAL);
		this.timer.setText(this.timerString());
	}



	private String timerString() {
		return String.format(
			"%02d:%02d:%02d",
			(this.profile.current_timer / (60 * 60)) % 24,
			(this.profile.current_timer / 60) % 60,
			this.profile.current_timer % 60
		);
	}
}

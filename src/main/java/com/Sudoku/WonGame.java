
package com.Sudoku;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;





public class WonGame extends JFrame {

	private Profile profile;



	public WonGame(Profile profile) {
		this.profile = profile;
	}



	public void initialize() {
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		this.setMaximumSize(new Dimension(500, 500));
		this.setMinimumSize(new Dimension(500, 500));
		this.setResizable(false);
		this.setTitle("Won Game");
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

		JLabel h = new JLabel("Won Game");
		h.setFont(new Font("Arial", Font.PLAIN, 50));
		h.setForeground(Color.BLACK);
		h.setVisible(true);

		panel_h.add(h, BorderLayout.CENTER);
		panel_h.add(Box.createHorizontalGlue());

		panel.add(panel_h, BorderLayout.CENTER);
		panel.add(Box.createVerticalStrut(25));



		/* ----- Name ----- */

		JPanel panel_n = new JPanel();
		panel_n.setLayout(new BoxLayout(panel_n, BoxLayout.X_AXIS));
		panel_n.add(Box.createHorizontalGlue());

		JLabel n = new JLabel("Name - " + this.profile.name);
		n.setFont(new Font("Arial", Font.PLAIN, 15));
		n.setForeground(Color.BLACK);
		n.setVisible(true);

		panel_n.add(n, BorderLayout.CENTER);
		panel_n.add(Box.createHorizontalGlue());

		panel.add(panel_n, BorderLayout.CENTER);
		panel.add(Box.createVerticalStrut(15));



		/* ----- Steps ----- */

		JPanel panel_s = new JPanel();
		panel_s.setLayout(new BoxLayout(panel_s, BoxLayout.X_AXIS));
		panel_s.add(Box.createHorizontalGlue());

		JLabel s = new JLabel("Steps - " + Long.toString(this.profile.steps));
		s.setFont(new Font("Arial", Font.PLAIN, 15));
		s.setForeground(Color.BLACK);
		s.setVisible(true);

		panel_s.add(s, BorderLayout.CENTER);
		panel_s.add(Box.createHorizontalGlue());

		panel.add(panel_s, BorderLayout.CENTER);
		panel.add(Box.createVerticalStrut(15));



		/* ----- Timer ----- */

		JPanel panel_t = new JPanel();
		panel_t.setLayout(new BoxLayout(panel_t, BoxLayout.X_AXIS));
		panel_t.add(Box.createHorizontalGlue());

		JLabel t = new JLabel("Timer - " + this.timerString());
		t.setFont(new Font("Arial", Font.PLAIN, 15));
		t.setForeground(Color.BLACK);
		t.setVisible(true);

		panel_t.add(t, BorderLayout.CENTER);
		panel_t.add(Box.createHorizontalGlue());

		panel.add(panel_t, BorderLayout.CENTER);
		panel.add(Box.createVerticalStrut(25));



		/* ----- Home Button ----- */

		JPanel panel_b1 = new JPanel();
		panel_b1.setLayout(new BoxLayout(panel_b1, BoxLayout.X_AXIS));
		panel_b1.add(Box.createHorizontalGlue());

		JButton b1 = new JButton("Go Home");
		b1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);

				new Welcome().initialize();
			}
		});
		b1.setFont(new Font("Arial", Font.PLAIN, 15));
		b1.setForeground(Color.BLACK);
		b1.setMaximumSize(new Dimension(100, 50));
		b1.setMinimumSize(new Dimension(100, 50));
		b1.setVisible(true);

		panel_b1.add(b1, BorderLayout.CENTER);
		panel_b1.add(Box.createHorizontalGlue());

		panel.add(panel_b1, BorderLayout.CENTER);

		panel.add(Box.createVerticalStrut(25));



		/* ----- Delete Button ----- */

		JPanel panel_b2 = new JPanel();
		panel_b2.setLayout(new BoxLayout(panel_b2, BoxLayout.X_AXIS));
		panel_b2.add(Box.createHorizontalGlue());

		JButton b2 = new JButton("Delete profile");
		b2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);

				profile.delete();

				new Welcome().initialize();
			}
		});
		b2.setFont(new Font("Arial", Font.PLAIN, 15));
		b2.setForeground(Color.BLACK);
		b2.setMaximumSize(new Dimension(100, 50));
		b2.setMinimumSize(new Dimension(100, 50));
		b2.setVisible(true);

		panel_b2.add(b2, BorderLayout.CENTER);
		panel_b2.add(Box.createHorizontalGlue());

		panel.add(panel_b2, BorderLayout.CENTER);

		this.add(panel, BorderLayout.CENTER);
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

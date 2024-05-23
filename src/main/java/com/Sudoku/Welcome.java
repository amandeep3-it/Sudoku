
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





public class Welcome extends JFrame {

	private Profile profile;



	public Welcome() {
		this.profile = new Profile();
	}



	public void initialize() {
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		this.setMaximumSize(new Dimension(1000, 500));
		this.setMinimumSize(new Dimension(1000, 500));
		this.setResizable(false);
		this.setTitle("Sudoku");
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

		JLabel h = new JLabel("Welcome to sudoku puzzle world!");
		h.setFont(new Font("Arial", Font.PLAIN, 50));
		h.setForeground(Color.BLACK);
		h.setVisible(true);

		panel_h.add(h, BorderLayout.CENTER);
		panel_h.add(Box.createHorizontalGlue());

		panel.add(panel_h, BorderLayout.CENTER);
		panel.add(Box.createVerticalStrut(50));



		/* ----- Buttons ----- */

		JPanel panel_b1 = new JPanel();
		panel_b1.setLayout(new BoxLayout(panel_b1, BoxLayout.X_AXIS));
		panel_b1.add(Box.createHorizontalGlue());

		JButton b1 = new JButton("NEW GAME");
		b1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);

				new NewGame().initialize();
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

		if (this.profile.profiles_exist) {
			JPanel panel_b2 = new JPanel();
			panel_b2.setLayout(new BoxLayout(panel_b2, BoxLayout.X_AXIS));
			panel_b2.add(Box.createHorizontalGlue());

			JButton b2 = new JButton("CONTINUE GAME");
			b2.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					setVisible(false);

					new ContinueGame().initialize();
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
		}

		this.add(panel, BorderLayout.CENTER);
	}
}

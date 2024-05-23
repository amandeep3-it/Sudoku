
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
import javax.swing.JTextField;
import javax.swing.WindowConstants;





public class NewGame extends JFrame {

	private Profile profile;



	public NewGame() {
		this.profile = new Profile();
	}



	public void initialize() {
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		this.setMaximumSize(new Dimension(500, 500));
		this.setMinimumSize(new Dimension(500, 500));
		this.setResizable(false);
		this.setTitle("New Game");
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

		JLabel h = new JLabel("New Game");
		h.setFont(new Font("Arial", Font.PLAIN, 50));
		h.setForeground(Color.BLACK);
		h.setVisible(true);

		panel_h.add(h, BorderLayout.CENTER);
		panel_h.add(Box.createHorizontalGlue());

		panel.add(panel_h, BorderLayout.CENTER);
		panel.add(Box.createVerticalStrut(25));



		/* ----- Text Field ----- */

		JTextField f = new JTextField();
		f.setFont(new Font("Arial", Font.PLAIN, 25));
		f.setForeground(Color.BLACK);
		f.setMaximumSize(new Dimension(400, 50));
		f.setMinimumSize(new Dimension(400, 50));
		f.setVisible(true);

		panel.add(f, BorderLayout.CENTER);
		panel.add(Box.createVerticalStrut(25));



		/* ----- Button ----- */

		JPanel panel_b = new JPanel();
		panel_b.setLayout(new BoxLayout(panel_b, BoxLayout.X_AXIS));
		panel_b.add(Box.createHorizontalGlue());

		JButton b = new JButton("START");
		b.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);

				try { profile.create(f.getText()); } catch (Exception err) {}

				new Game(profile).initialize();
			}
		});
		b.setFont(new Font("Arial", Font.PLAIN, 15));
		b.setForeground(Color.BLACK);
		b.setVisible(true);

		panel_b.add(b, BorderLayout.CENTER);
		panel_b.add(Box.createHorizontalGlue());

		panel.add(panel_b, BorderLayout.CENTER);

		this.add(panel, BorderLayout.CENTER);
	}
}

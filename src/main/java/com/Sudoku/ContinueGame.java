
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





public class ContinueGame extends JFrame {

	private Profile profile;



	public ContinueGame() {
		this.profile = new Profile();
	}



	public void initialize() {
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		this.setMaximumSize(new Dimension(500, 500));
		this.setMinimumSize(new Dimension(500, 500));
		this.setResizable(false);
		this.setTitle("Continue Game");
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

		JLabel h = new JLabel("Continue Game");
		h.setFont(new Font("Arial", Font.PLAIN, 50));
		h.setForeground(Color.BLACK);
		h.setVisible(true);

		panel_h.add(h, BorderLayout.CENTER);
		panel_h.add(Box.createHorizontalGlue());

		panel.add(panel_h, BorderLayout.CENTER);
		panel.add(Box.createVerticalStrut(25));



		/* ----- Buttons ----- */

		if (this.profile.profiles_exist) {
			String[] names = this.profile.getNames();
			for (int i = 0; i < names.length; i++) {
				final Integer index = i;

				JPanel panel_b = new JPanel();
				panel_b.setLayout(new BoxLayout(panel_b, BoxLayout.X_AXIS));
				panel_b.add(Box.createHorizontalGlue());

				JButton b = new JButton(names[i]);
				b.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						try { openProfile(names[index]); } catch (Exception err) {}
					}
				});
				b.setFont(new Font("Arial", Font.PLAIN, 15));
				b.setForeground(Color.BLACK);
				b.setVisible(true);

				panel_b.add(b, BorderLayout.CENTER);
				panel_b.add(Box.createHorizontalGlue());

				panel.add(panel_b, BorderLayout.CENTER);
				if (i < (names.length - 1)) panel.add(Box.createVerticalStrut(25));
			}
		}

		this.add(panel, BorderLayout.CENTER);
	}



	private void openProfile(String name) throws Exception {
		this.setVisible(false);

		this.profile.get(name);

		if (this.profile.validate()) new WonGame(this.profile).initialize();
		else new Game(this.profile).initialize();
	}
}

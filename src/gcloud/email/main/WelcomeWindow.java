package gcloud.email.main;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JWindow;
import javax.swing.border.LineBorder;

public class WelcomeWindow extends JWindow {
	private static final long serialVersionUID = 1827257925101250962L;

	public WelcomeWindow() {
		init();
	}

	public void init() {
		System.out.println("OK???");
		this.setSize(430, 300);
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(BorderLayout.CENTER, new JLabel(new ImageIcon(this.getClass().getResource("welcome.png"))));
		panel.setBorder(new LineBorder(Color.GRAY));
		this.setContentPane(panel);
		this.setLocationRelativeTo(null);
	}
}

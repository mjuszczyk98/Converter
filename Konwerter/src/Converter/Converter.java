package Converter;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.lang.reflect.Array;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Converter extends JFrame{
	
	private static final long serialVersionUID = 1L;
	
	private static int width = 400;
	private static int height = 300;
	private static int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
	private static int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
	JPanel panel = new JPanel();
	
	JLabel[] label = {
			new JLabel("  Liczba:   "),
			new JLabel("  Podstawa przed:  "),
			new JLabel("  Podstawa po:     ")	
	};
	JTextField[] text = {
			new JTextField(20),
			new JTextField(4),
			new JTextField(4)
	};
	JButton[] button = {
		new JButton("Convert"),
		new JButton("Reset")
	};
	

	public Converter() {
		this.setVisible(true);
		this.setTitle("Converter");
		this.setBounds((screenWidth - width) / 2, (screenHeight - height) / 2, width, height);
		this.setContentPane(panel);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		initLayout();
		initButtons();
		this.pack();
		
	}
	
	private void initLayout() {
		GroupLayout layout = new GroupLayout(panel);
		panel.setLayout(layout);
		
		layout.setHorizontalGroup(
				layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup()
						.addGroup(layout.createSequentialGroup()
								.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
										.addComponent(label[0])
										.addGap(3)
										.addComponent(text[0])
										)
								.addGroup(layout.createParallelGroup()
										.addComponent(label[1])
										.addGap(3)
										.addComponent(text[1])
										)
								)
						.addComponent(button[0])
						)
				.addGroup(layout.createParallelGroup()
						.addComponent(label[2])
						.addGap(3)
						.addComponent(text[2])
						.addGap(3)
						.addComponent(button[1])
						)
				
		);
		layout.setVerticalGroup(
				layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup()
						.addGroup(layout.createSequentialGroup()
								.addGroup(layout.createParallelGroup()
										.addComponent(label[0])
										.addGap(3)
										.addComponent(label[1])
										)
								.addGroup(layout.createParallelGroup()
										.addComponent(text[0])
										.addGap(3)
										.addComponent(text[1])
										)
								)
						.addGroup(layout.createSequentialGroup()
									.addComponent(label[2])
									//.addGap(3)
									.addComponent(text[2])		
								)
						)
				.addGap(3)
				.addGroup(layout.createParallelGroup()
						.addComponent(button[0])
						.addComponent(button[1])
						)
				
		);
	}
	
	private void initButtons() {
		button[0].addActionListener((a)->{
			this.convert();
		});
		button[1].addActionListener((a)->{
			this.reset();
		});
	}
	
	private void convert() {
		int from;
		int to;
		try {
			from = Integer.parseInt(text[1].getText());
			to = Integer.parseInt(text[2].getText());
			if(from < 2 || from > 16 || to < 2 || to > 16)
				throw new Exception();
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(this, "Błędna wartość", null, JOptionPane.PLAIN_MESSAGE);
			return;
		}
		int v = 0;
		int r = 0;
		char val[] = text[0].getText().toCharArray();
		String ret = "";
		for(int i = 0; i < Array.getLength(val); i++) {
			
			if(val[i] >= 'A' && val[i] <= 'F')
				v = val[i] - 'A' + 10;
			else if(val[i] >= 'a' && val[i] <= 'f')
				v = val[i] - 'a' + 10;
			else if (val[i] >= '0' && val[i] <= '9')
				v = val[i] - '0';
			else {
				JOptionPane.showMessageDialog(this, "Błędna wartość", null, JOptionPane.PLAIN_MESSAGE);
				return;
			}
			if(v >= from){
				JOptionPane.showMessageDialog(this, "Błędna wartość", null, JOptionPane.PLAIN_MESSAGE);
				return;
			}
			
			r = r * from + v;
			
		}
		
		while(r != 0) {
			v = r % to;
			if(v >= 10)
				ret = (char)(v - 10 + 'A') + ret;
			else
				ret = v + ret;
			r /= to;
		}
		
		JOptionPane.showMessageDialog(this, ret, null, JOptionPane.PLAIN_MESSAGE);
		
		this.reset();
		
		return;
		
	}
	
	private JOptionPane JOptionPane() {
		// TODO Auto-generated method stub
		return null;
	}

	private void reset() {
		text[0].setText("");
		text[1].setText("");
		text[2].setText("");
	}
	
	public static void main(String[] args) {
		new Converter();
	}
}

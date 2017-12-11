import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.util.Scanner;

public class Maximizer extends JFrame implements ActionListener {
	
	private JLabel label;
	private JTextField textFieldInput;
 	
	private JButton input;
	private String storeInput = "";
	private String storeOutput = "";
	
	public Maximizer() {
	      setLayout(new FlowLayout());
	      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	      label = new JLabel("Input Letters: ");
	      add(label); 
	      
	      textFieldInput = new JTextField("", 20); 
	      textFieldInput.setEditable(true);
	  
	      
	      add(textFieldInput); 	      
	      
	      input = new JButton("Input");   // construct the Button component
	      add(input);
	      input.addActionListener(this);
	       
	      
	      
	      setTitle("Scrabble Maximizer"); 
	      setSize(450, 150);
	      
	      setVisible(true);	 
	}
		

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == input) {
			
			storeInput = textFieldInput.getText();
			Result a = new Result();
			try {
				storeOutput = a.getMaxPoints(storeInput);
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
		
			JOptionPane.showMessageDialog(null, storeOutput);
		}
		
	}
	
	public static void main(String[] args) {
		Maximizer scrabble = new Maximizer();
		scrabble.setVisible(true);
	}
	
}

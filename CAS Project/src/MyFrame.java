//import java.awt.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;

public class MyFrame extends JFrame implements ActionListener{
	
	ArrayList<Yearbook> yearbook = new ArrayList<Yearbook>();

	
	JComboBox comboBox;
	JComboBox<Integer> yearBox;
	JPanel yearPanel;
	
	JButton submit;
	JButton save;
	
	JLabel response;
	
	JScrollPane scroll;
	
	JLabel yearLabel;
	JLabel priceLabel;
	JLabel quantityLabel;
	
	JTextField yearInput;
	JTextField priceInput;
	JTextField quantityInput;
	
	JPanel background;
	JPanel input;
	
	ImageIcon backgroundImage;
	

	
	public MyFrame()
	{
		Border blackLine = BorderFactory.createLineBorder(Color.black, 1);
		
		String[] options = {"Add Yearbook", "Remove Yearbook", "Sell Yearbook", "Profit", "Available Profit", "Adjust Profit"};
		
		backgroundImage = new ImageIcon("background.jpg");
		
		
		//Saves Program
		save = new JButton("Save");
		save.setBounds(5, 375, 75, 25);
		save.setFocusable(false);
		save.setFont(new Font("Verdana", Font.BOLD, 12));
		save.addActionListener(this);
		
		
		
		//Program Title
		ImageIcon ram = new ImageIcon("Ram_Logo.png");
		ImageIcon bookIcon = new ImageIcon("book_icons.png");
		
		JLabel label1 = new JLabel();
		label1.setBounds(240, 0, 500, 50);
		label1.setText("Yearbook Manager");
		label1.setFont(new Font("Verdana", Font.BOLD, 24));
		label1.setIcon(ram);
		
		JPanel panel1 = new JPanel();
		panel1.setBounds(0, 0, 750, 50);
		panel1.setBackground(new Color(253, 253, 150));
		//panel1.setBackground(new Color(229, 229, 229));
		
		JPanel border = new JPanel();
		border.setBounds(0, 50, 750, 2);
		border.setBackground(Color.black);
		
		//Function Selection
		comboBox = new JComboBox(options);
		comboBox.addActionListener(this);
		comboBox.setPreferredSize(new Dimension(250, 40));
		comboBox.setFont(new Font("Verdana", Font.PLAIN, 20));
		
		JPanel dropDown = new JPanel();
		dropDown.setBounds(0, 100, 260, 50);
		dropDown.setOpaque(false);
		dropDown.setLayout(new FlowLayout(FlowLayout.LEFT));
		dropDown.add(comboBox);
		
		
		//Display output
		response = new JLabel();
		response.setFont(new Font("Verdana", Font.BOLD, 20));
		response.setVerticalAlignment(JLabel.TOP);
		response.setHorizontalAlignment(JLabel.LEFT);
		
		
		
		scroll = new JScrollPane(response, scroll.VERTICAL_SCROLLBAR_ALWAYS, scroll.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.getViewport().setBackground(new Color(253, 253, 150));
		
		JLabel cLabel = new JLabel("Inventory");
		cLabel.setFont(new Font("Verdana", Font.BOLD, 24));
		cLabel.setBounds(460, 5, 280, 150);
		cLabel.setIcon(bookIcon);
		
		JPanel console = new JPanel();
		console.setBackground(Color.black);
		console.setBounds(375, 100, 360, 250);
		console.setBorder(blackLine);
		console.setLayout(new BorderLayout());
		console.add(scroll);
		
		
		//Take User Input
		yearLabel = new JLabel("Input Year:");
		yearLabel.setFont(new Font("Verdana", Font.BOLD, 12));
		
		yearInput = new JTextField();
		
		priceLabel = new JLabel("Input Price:");
		priceLabel.setFont(new Font("Verdana", Font.BOLD, 12));
		
		priceInput = new JTextField();
		
		quantityLabel = new JLabel("Input Quantity:");
		quantityLabel.setFont(new Font("Verdana", Font.BOLD, 12));
		
		quantityInput = new JTextField();
		
		input = new JPanel();
		input.setLayout(new GridLayout(3, 2, 0, 3));  
		input.setBounds(5, 200, 230, 100);
		input.setBackground(new Color(253, 253, 150));
		input.setBorder(blackLine);
		//input.setOpaque(false);
		
		yearBox = new JComboBox<Integer>();
		
		input.add(yearLabel); input.add(yearInput);
		input.add(priceLabel); input.add(priceInput);
		input.add(quantityLabel); input.add(quantityInput);
		
		submit = new JButton("Submit");
		submit.setBounds(267, 100, 100, 50);
		submit.setFocusable(false);
		submit.setFont(new Font("Verdana", Font.BOLD, 12));
		submit.addActionListener(this);
		
		
		
		
		//Frame	
		this.setSize(750, 450);
		this.setLayout(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.getContentPane().setBackground(new Color(251, 243, 218));
		this.setResizable(false);
		this.setIconImage(ram.getImage());
		
		
		
		
		this.add(label1);
		this.add(panel1);
		this.add(border);
		this.add(console); 
		this.add(dropDown);
		this.add(input);
		this.add(cLabel);
		this.add(submit);
		this.add(save);

		
		
		Yearbook.loadData(yearbook);
		Yearbook.sortList(yearbook, response);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		//Neccessary Input Components
		String selected = "";
		if(e.getSource() == comboBox)
		{
			selected = comboBox.getSelectedItem().toString();
			

			if(selected.equals("Available Profit") || selected.equals("Profit"))
			{
				yearBox.setVisible(false);

				yearLabel.setVisible(false);
				yearInput.setVisible(false);
				
				priceLabel.setVisible(false);
				priceInput.setVisible(false);
				
				quantityLabel.setVisible(false);
				quantityInput.setVisible(false);
			}
			
			else if(selected.equals("Add Yearbook"))
			{
				input.remove(1);
				input.add(yearInput, 0, 1);
			
				
				input.add(priceLabel, 2);
				input.add(priceInput, 3);
				
				
				yearLabel.setVisible(true);
				yearInput.setVisible(true);
				
				priceLabel.setVisible(true);
				priceInput.setVisible(true);
				
				quantityLabel.setVisible(true);
				quantityInput.setVisible(true);
				
				this.setVisible(true);
				
				
			}
			
			else if(selected.equals("Remove Yearbook"))
			{
				yearBox.setVisible(true);
				yearLabel.setVisible(true);
				
				priceLabel.setVisible(true);
				priceInput.setVisible(true);
				
				quantityLabel.setVisible(true);
				quantityInput.setVisible(true);
				
				yearBox.removeAllItems();
				int subYear = yearbook.get(0).getYear();
				yearBox.addItem(yearbook.get(0).getYear());
				
				for(int i = 0; i < yearbook.size(); i++)
				{
					if(yearbook.get(i).getYear() != subYear)
					{
						subYear = yearbook.get(i).getYear();
						yearBox.addItem(subYear);
					}
				}
				
				
				
				input.remove(yearInput);
				input.add(yearBox, 0, 1);
				
				input.remove(priceLabel);
				input.remove(priceInput);
				
			
				this.setVisible(true);
				
			}
			
			else if(selected.equals("Sell Yearbook"))
			{
				yearBox.setVisible(true);
				yearLabel.setVisible(true);
				
				priceLabel.setVisible(true);
				priceInput.setVisible(true);
				
				quantityLabel.setVisible(true);
				quantityInput.setVisible(true);
				
				yearBox.removeAllItems();
				int subYear = yearbook.get(0).getYear();
				yearBox.addItem(yearbook.get(0).getYear());
				
				for(int i = 0; i < yearbook.size(); i++)
				{
					if(yearbook.get(i).getYear() != subYear)
					{
						subYear = yearbook.get(i).getYear();
						yearBox.addItem(subYear);
					}
				}
				
				
				
				input.remove(yearInput);
				input.add(yearBox, 0, 1);
				
				input.remove(priceLabel);
				input.remove(priceInput);
				
			
				this.setVisible(true);
			}
			
			else if(selected.equals("Adjust Profit"))
			{
				input.add(priceLabel, 2);
				input.add(priceInput, 3);
				
				yearBox.setVisible(false);

				yearLabel.setVisible(false);
				yearInput.setVisible(false);
				
				priceLabel.setVisible(true);
				priceInput.setVisible(true);
				
				quantityLabel.setVisible(false);
				quantityInput.setVisible(false);
			}
			
		}

		
		
		
		
		
		//Select Function Functionality
		if(e.getSource() == submit && comboBox.getSelectedItem().equals("Add Yearbook"))
		{
			try 
			{
				
				if(Integer.parseInt(yearInput.getText()) > 1961)
				{
					
					for(int i = 0; i < Integer.parseInt(quantityInput.getText()); i++)
					{
						yearbook.add(new Yearbook(Integer.parseInt(yearInput.getText()), Double.parseDouble(priceInput.getText())));
					}
					
					Yearbook.sortList(yearbook, response);
				}
				else
					JOptionPane.showMessageDialog(null, "Invalid Year", "Input Year", JOptionPane.ERROR_MESSAGE);
				
				
				
			}
			catch(Exception f)
			{
				JOptionPane.showMessageDialog(null, "Invalid Input", "Error", JOptionPane.ERROR_MESSAGE);
				System.out.println("Failure");
			}
		}
		
		else if(e.getSource() == submit && comboBox.getSelectedItem().equals("Remove Yearbook"))
		{
			try 
			{
				Yearbook.remove(yearbook, Integer.parseInt(yearBox.getSelectedItem().toString()), Integer.parseInt(quantityInput.getText()));
				Yearbook.sortList(yearbook, response);
				
				if(Yearbook.findYearbook(yearbook, Integer.parseInt(yearBox.getSelectedItem().toString())) == false)
				{
					yearBox.removeItem(Integer.parseInt(yearBox.getSelectedItem().toString()));
				}

			}
			catch(Exception f)
			{
				JOptionPane.showMessageDialog(null,  "Invalid Input", "Error", JOptionPane.ERROR_MESSAGE);
				System.out.println("Error");
			}
			
		}
		
		else if(e.getSource() == submit && comboBox.getSelectedItem().equals("Sell Yearbook"))
		{
			try 
			{
				Yearbook.sold(yearbook, Integer.parseInt(yearBox.getSelectedItem().toString()), Integer.parseInt(quantityInput.getText()));
				Yearbook.sortList(yearbook, response);
				
				if(Yearbook.findYearbook(yearbook, Integer.parseInt(yearBox.getSelectedItem().toString())) == false)
				{
					yearBox.removeItem(Integer.parseInt(yearBox.getSelectedItem().toString()));
				}
			}
			catch(Exception f)
			{
				JOptionPane.showMessageDialog(null,  "Invalid Input", "Error", JOptionPane.ERROR_MESSAGE);
				System.out.println("Error");
			}
		}
		
		
		else if(e.getSource() == submit && comboBox.getSelectedItem().equals("Profit"))
		{	
			JOptionPane.showMessageDialog(null, "Profit Earned: $" + Yearbook.getProfit(), "Profit", JOptionPane.PLAIN_MESSAGE);
		}
		
		else if(e.getSource() == submit && comboBox.getSelectedItem().equals("Available Profit"))
		{
			JOptionPane.showMessageDialog(null, "Available Profit: $" + Yearbook.getAvailableProfit(yearbook), "Available Profit", JOptionPane.PLAIN_MESSAGE);
		}
		
		else if(e.getSource() == submit && comboBox.getSelectedItem().equals("Adjust Profit"))
		{
			try 
			{
				Yearbook.setProfit(Double.parseDouble(priceInput.getText()));
				JOptionPane.showMessageDialog(null, "Successfully Adjusted", "Adjust Profit", JOptionPane.INFORMATION_MESSAGE);
			}
			catch(Exception f)
			{
				JOptionPane.showMessageDialog(null, "Invalid Input", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		
		else if(e.getSource() == save)
		{
			Yearbook.saveData(yearbook);
			JOptionPane.showMessageDialog(null, "Successfully Saved", "Save", JOptionPane.INFORMATION_MESSAGE);
		}
		// TODO Auto-generated method stub
		
	}
	

}

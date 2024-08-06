import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Yearbook {
	private int year;
	private double price;
	private static int inv;
	private static double profit = 0;
	private static int totSold = 0;
	private static double availableProfit;
	private static int size;
	private static ArrayList<Integer> counterList = new ArrayList<Integer>();
	
	
	
	
	
	public Yearbook(int year, double price)
	{
		this.year = year;
		this.price = price;
	}
	
	public static void saveData(ArrayList<Yearbook> yearbook)
	{
		try 
		{
			BufferedWriter bw = new BufferedWriter(new FileWriter("saveFile_2.txt"));
			
			
			bw.write("" + yearbook.size());
			bw.newLine();
			for(int i = 0; i < yearbook.size(); i++)
			{
				bw.write("" + yearbook.get(i).getYear());
				bw.newLine();
				bw.write("" + yearbook.get(i).getPrice());
				bw.newLine();
			}
			bw.write("" + getProfit());
			bw.newLine();
			
			bw.write("" + getTotSold());
			bw.newLine();
			
			bw.close();
		} 
		catch (Exception e) 
		{
			
		}
	}
	
	public static void loadData(ArrayList<Yearbook> yearbook)
	{
		int year = 0;
		double price = 0;
		int count = 0;
		
		try
		{
			BufferedReader br = new BufferedReader(new FileReader("saveFile_2.txt"));
			size = Integer.parseInt(br.readLine());
			
			for(int i = 0; i < size * 2; i++)
			{
				count++;
				if(i % 2 == 0)
				{
					year = Integer.parseInt(br.readLine());
				}
				else
				{
					price = Double.parseDouble(br.readLine());
				}
				
				
				if(count == 2)
				{
					yearbook.add(new Yearbook(year, price));
					count = 0;
				}
			}
			
			
			profit = Double.parseDouble(br.readLine());
			totSold = Integer.parseInt(br.readLine());
			
			
			
			br.close();
		}
		catch(Exception e)
		{
			
		}
	}
	
	public String toString() 
	{
		return year + ", $" + price;
	}
	
	public int getYear()
	{
		return year;
	}
	
	public double getPrice()
	{
		return price;
	}
	
	public static double getProfit()
	{
		return profit;
	}
	
	public static int getTotSold()
	{
		return totSold;
	}
	
	public static double getAvailableProfit(ArrayList<Yearbook> yearbook)
	{
		availableProfit = 0;
		for(int i = 0; i < yearbook.size(); i++)
		{
			availableProfit += yearbook.get(i).getPrice();
		}
		
		return availableProfit;
	}
	
	public static void setProfit(double prof)
	{
		profit = prof;
	}
	
	public static void remove(ArrayList<Yearbook> yearbook, int year, int quantity)
	{
		boolean flag;
		int count = 0;
		
		for(int i = 0; i < quantity; i++)
		{
			flag = false;
			for(int j = 0; j < yearbook.size(); j++)
			{
				if(yearbook.get(j).getYear() == year)
				{
					
					yearbook.remove(j);
					flag = true;
					break;
				}
			}
			
			if(flag != true)
				count++;
		}
		
		if(count == 0)
			System.out.println("Successfully Removed");
		
		else
		{
			JOptionPane.showMessageDialog(null, count + " Book(s) could not be found", "Not Found", JOptionPane.INFORMATION_MESSAGE);
			System.out.println(count + " Yearbook(s) could not be found");
		}
				
	}
	
	public static void sold(ArrayList<Yearbook> yearbook, int year, int quantity)
	{
		boolean flag;
		int count = 0;
		
		for(int i = 0; i < quantity; i++)
		{
			flag = false;
			for(int j = 0; j < yearbook.size(); j++)
			{
				if(yearbook.get(j).getYear() == year)
				{
					profit += yearbook.get(j).getPrice();
					totSold++;
					yearbook.remove(j);
					flag = true;
					break;
				}
				
			}
			
			if(flag != true)
			{
				count++;
			}
			
		}
		
		if(count == 0)
			System.out.println("Successfully Sold");
		
		else
		{
			JOptionPane.showMessageDialog(null, count + " Yearbook(s) could not be found", "Not Found", JOptionPane.INFORMATION_MESSAGE);
			System.out.println(count + " Book(s) could not be found");
		}

	}
	
	public static boolean findYearbook(ArrayList<Yearbook> yearbook, int year)
	{
		boolean flag = false;
		
		for(int i = 0; i < yearbook.size(); i++)
		{
			if(yearbook.get(i).getYear() == year)
			{
				flag = true;
			}
		}
		
		return flag;
	}
	
	
	
	public static void sortList(ArrayList<Yearbook> yearbook, JLabel label)
	{
		
		//Puts the ArrayList in order
		int subYear;
		double subPrice;
		int index;
		String response =  "<html>";
		
		try
		{
			for(int i = 0; i < yearbook.size(); i++)
			{
				index = i;
				if(i < yearbook.size() - 1)
				{
					for(int j = i + 1; j < yearbook.size(); j++)
					{
						if(yearbook.get(i).getYear() > yearbook.get(j).getYear())
						{
							index = j;
						}
						
						else if(yearbook.get(i).getYear() == yearbook.get(j).getYear() && yearbook.get(i).getPrice() > yearbook.get(j).getPrice())
						{
							index = j;
						}
					}
					
					subYear = yearbook.get(index).getYear();
					subPrice = yearbook.get(index).getPrice();
					
					yearbook.set(index, new Yearbook(yearbook.get(i).getYear(), yearbook.get(i).getPrice()));
					yearbook.set(i, new Yearbook(subYear, subPrice));
				}
				
			}
			
			//Outputs organized ArrayList
			
			int subYear2 = yearbook.get(0).getYear();
			double subPrice2 = yearbook.get(0).getPrice();
			
			int counter = 0;
		
		
			for(int i = 1; i < yearbook.size(); i++)
			{
				counter++;
				
				if(yearbook.get(i).getYear() != subYear2 || yearbook.get(i).getPrice() != subPrice2)
				{
					subYear2 = yearbook.get(i).getYear();
					subPrice2 = yearbook.get(i).getPrice();
					
					response += "x" + counter + " - " + yearbook.get(i - 1) + "<br>";
					
					counter = 0;
					
				}
				
				if(i == (yearbook.size() - 1))
				{
					response += "x" + (counter + 1) + " - " + yearbook.get(i) + "<br>";
				}
			}
			
			if(yearbook.size() == 1)
			{
				response += "x1"  + " - " + yearbook.get(0) + "<br>";
			}
			
			response += "</html>";
			label.setText(response);
			
		}
		
		catch(Exception e)
		{
			response = "No Recorded Yearbooks.";
			label.setText(response);
			System.out.println("No Recorded Yearbooks.");
		}
		
		//System.out.println("functioning");
	}
	
	public static void sortList(ArrayList<Yearbook> yearbook)
	{
		int subYear;
		double subPrice;
		int index;
		
		for(int i = 0; i < yearbook.size(); i++)
		{
			index = i;
			if(i < yearbook.size() - 1)
			{
				for(int j = i + 1; j < yearbook.size(); j++)
				{
					if(yearbook.get(i).getYear() > yearbook.get(j).getYear())
					{
						index = j;
					}
					
					else if(yearbook.get(i).getYear() == yearbook.get(j).getYear() && yearbook.get(i).getPrice() > yearbook.get(j).getPrice())
					{
						index = j;
					}
				}
				
				subYear = yearbook.get(index).getYear();
				subPrice = yearbook.get(index).getPrice();
				
				yearbook.set(index, new Yearbook(yearbook.get(i).getYear(), yearbook.get(i).getPrice()));
				yearbook.set(i, new Yearbook(subYear, subPrice));
			}
		}
	}
}

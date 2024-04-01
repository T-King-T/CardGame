package Driver;

import java.sql.*;
import java.util.Scanner;


public class Driver2 
{
	public static void main(String[] args) throws SQLException, ClassNotFoundException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		System.out.println("Driver loaded!");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost/javaTest", "javaUser", "password");
		
		System.out.println("Database Connected");
		
		Statement s = con.createStatement();
		
		Scanner in = new Scanner(System.in);
		
		int count = 1;
		
		
		
		do {
			System.out.println();
			System.out.println("Select what you want to do (1-4)");
			System.out.println("1: Create a card");
			System.out.println("2: Read all cards");
			System.out.println("3: Update a card");
			System.out.println("4: Delete a card");
			int selection = in.nextInt();
		switch (selection) 
		{
			case 1: System.out.println("Provide the NAME, or type 0 to end");
					String name = in.next();
					
					System.out.println("Provide the RARITY, 1-3, 1 = Rarest, 3 = Least Rare ");
					int rarity = in.nextInt();
		
					Boolean result = s.execute("INSERT INTO collectableCards VALUES (0, '"+name+"', '"+rarity+"')");
					break;
		
			case 2: ResultSet rs = s.executeQuery("Select * FROM collectableCards");
					while(rs.next())	
					{
						int ID = rs.getInt("id");
						String cardName = rs.getString("CardName");
						int cardRarity = rs.getInt("Rarity");
						System.out.println("ID: "+ID+ " - Name: "+cardName+" - Rarity: "+cardRarity);
					}
					break;
					
			case 3: System.out.println("Select the ID of the card to update");
					int uID = in.nextInt();
				
					System.out.println("Provide the new name");
					String nName = in.next();
					
					System.out.println("Provide the new rarity");
					int nRare = in.nextInt();
					
					Boolean result2 = s.execute("UPDATE collectableCards SET CardName = '"+nName+"', Rarity = '"+nRare+"' WHERE id = '"+uID+"'");
					break;
					
			case 4: System.out.println("Select the ID of the card you want to delete");
					int dID = in.nextInt();
					
					Boolean result3 = s.execute("DELETE FROM collectableCards WHERE id = '"+dID+"'");
					
					if(result3 == true)
					{
						System.out.println("Card "+dID+" has been deleted");
					}
					break;
			default: System.out.println("Incorrect input - Ending Program");
					 count = 0;
					 break;
		}
		}while(count == 1);
		
		
		
		
		
		
	
		
		
		
				
	}

}

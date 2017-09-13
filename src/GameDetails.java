import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextPane;

/*
 * 
 * Created By Sulabh Shrestha
 * ID: 17171780
 * 
 */

public class GameDetails {
	static String username;
	static String currentInput;
	static String title[] = new String[100];
	static int score[] = new int[100];
	static int minutesPlayed[] = new int[100];
	static int numberOfInput = 0;
	static int invalidEntries = 0;
	static int totalAchievementScore = 0;
	static int totalTimeSpent = 0;
	static int highestScore = 0;
	private static int hour;
	private static int minutes;

	public static void input(){
		Scanner scan = new Scanner(System.in);
		System.out.println("||==========================================================================||");
		System.out.println("||This Gaming Progam records Game Names with their Scores and Minutes Played||");
		System.out.println("||Press 'quit' at any time to QUIT the Game                                 ||");
		System.out.println("||==========================================================================||");
		do{
			System.out.print("Enter the Player Name: ");
			username = scan.nextLine();
			if	(username.equals("")){
				System.out.print("The Username is Null, Please Try Again: ");
			}else{
				break;
			}
		}while(true);
		
		System.out.println("Enter the Game Details");
		System.out.println("Pattern To Write The Game Details");
		System.out.print("(Game Name : Score : Minutes Played) : ");
		while((currentInput = scan.nextLine()).equals("quit") != true  ){
			if (numberOfInput > 100){
				break;
			}
			if(validate())
			{
				processData();
				numberOfInput++;
			}
			System.out.print("(Game Name : Score : Minutes Played) : ");
		}
		
	}
	
	public static void computeData() {
		hour = totalTimeSpent/ 60;
		minutes = totalTimeSpent - hour * 60;
	}
	
	public static void processData() {
		String item[] = currentInput.split(":");
		title[numberOfInput] = item[0];
		score[numberOfInput] = Integer.parseInt(item[1].trim());
		minutesPlayed[numberOfInput] = Integer.parseInt(item[2].trim());
		
		if(score[numberOfInput] < 0){
			invalidEntries++;
		}else{
			totalAchievementScore += score[numberOfInput];
			totalTimeSpent += minutesPlayed[numberOfInput];
			if(score[numberOfInput] > highestScore){
				highestScore = score[numberOfInput];
			}
		}
	}
	public static boolean validate(){
		String item[] = currentInput.split(":");
		
		int delemeterCount = 0;
		boolean isValid = false;
		for (int i = 0; i < currentInput.length(); i++){
			char value = currentInput.charAt(i);
			
			if(value == ':'){
				delemeterCount++;
			}
			
		}
		if(currentInput.trim().length() == 0){
			System.out.println("Please Enter the Game Name.");
		}else if(item.length < 3){
			System.out.println("There is Not Enough Data/Delemeter. Please Try Again.");
		}else{
			if(delemeterCount != 2){
				System.out.println("There is Not Enough Delemeter To Break the String");
			}
			else{
				String t = item[0];
				boolean valid = true;
				try {
					int s = Integer.parseInt(item[1].trim());
					int m = Integer.parseInt(item[2].trim());
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
					System.out.println("This is not a Valid Number, Please Try Again.");
					valid = false;
				}
				
				if(valid){
					isValid = true;
				}
				
			}
		}
		return isValid;
	}
	
	
	public static void output(){
		System.out.println("");
		System.out.println("Player: " + username);
		System.out.println("-------------------------------------");
		for	(int i = 0; i < numberOfInput; i ++){
			System.out.println("Game: " + title[i] + ", score= " + score[i] + ", minutes played= " + minutesPlayed[i] );	
		}
		System.out.println("Game played: " + (numberOfInput - invalidEntries));
		System.out.println("Total Achievement: " + totalAchievementScore);
		System.out.println("Total Time: "+ totalTimeSpent + "(" + hour + " hours and " + minutes + " minutes).");
		System.out.println("Invalid Entries: " + invalidEntries);
		System.out.println("The Highest Score Achievement: " + highestScore);
	}
	
	public static void generateHtml() throws IOException{		
		File file = new File("output.html");
		PrintWriter output = new PrintWriter(file);
		output.print("<h1>Player: " + username + "</h1>");
		output.println("<h4>-------------------------------------------</h4>");
		output.println("<h4>Game played: " + (numberOfInput - invalidEntries) + "</h4>");
		output.println("<h4>Total Achievement: " + totalAchievementScore + "</h4>");
		output.println("<h4>Total Time: "+ totalTimeSpent + "(" + hour + " hours and " + minutes + " minutes)." + "</h4>");
		output.println("<h4>Invalid Entries: " + invalidEntries + "</h4>");
		output.println("<h4>The Highest Score Achievement: " + highestScore + "</h4>");
		output.close();
	}
	
	public static void generateForHtml() throws IOException{		
		File file = new File("output2.html");
		PrintWriter output = new PrintWriter(file);
		output.println("<html>");
		output.println("<head>");
		output.println("<title>");	
		output.println("Game Details");
		output.println("</title>");
		output.println("<link rel=\"stylesheet\" href=\"custom.css\">");	
		output.println("</head>");
		output.println("<body background=\"a.jpg\" >");
		output.println("<h1> Game Details </h1>");
		output.print("<center><h2>Player: " + username + "</h2></center>");
		output.println("<center><h4>------------------------------------------------------------</h4></center>");
		output.println("<center><h4>Game played: " + (numberOfInput - invalidEntries) + "</h4></center>");
		output.println("<center><h4>Total Achievement: " + totalAchievementScore + "</h4></center>");
		output.println("<center><h4>Total Time: "+ totalTimeSpent + "(" + hour + " hours and " + minutes + " minutes)." + "</h4></center>");
		output.println("<center><h4>Invalid Entries: " + invalidEntries + "</h4></center>");
		output.println("<center><h4>The Highest Score Achievement: " + highestScore + "</h4></center>");
		output.println("<center><h4><a href=\"output3.html\">Click Here For More Details</a></h4></center>");
		output.println("</body>");
		output.println("</html>");
		output.close();
		
		file = new File("output3.html");
		output = new PrintWriter(file);
		output.println("<html>");
		output.println("<head>");
		output.println("<title>");	
		output.println("Game Details");
		output.println("</title>");
		output.println("<link rel=\"stylesheet\" href=\"custom.css\">");	
		output.println("</head>");
		output.println("<body background=\"a.jpg\" >");
		output.println("<h1> Game Details </h1>");
		output.print("<center><h2>Player: " + username + "</h2></center>");
		output.println("<center><h4>------------------------------------------------------------</h4></center>");
		output.println("<center><h4>Game played: " + (numberOfInput - invalidEntries) + "</h4></center>");
		for	(int i = 0; i < numberOfInput; i ++){
			output.println("<center><h4>Game: " + title[i] + ", score= " + score[i] + ", minutes played= " + minutesPlayed[i] + "</h4></center>" );	
		}
		output.println("<center><h4><a href=\"output2.html\">Click Here To Return To Original Details</a></h4></center>");
		output.println("</body>");
		output.println("</html>");
		output.close();
		
	}
	
	public static String loadHtml() throws IOException{
			StringBuilder sb = new StringBuilder(512); 
			try { 
				FileReader r = new FileReader("output.html"); 
				int c = 0; 
				while ((c = r.read()) != -1) {
					sb.append((char) c); 
					}
				} catch (IOException e) { 
					throw new RuntimeException(e); 
					}
			return sb.toString(); 		
	}
	
	public static void initGUI() throws IOException{
	    JFrame frame = new JFrame("Game Details");
	    JTextPane textPane = new JTextPane();	
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    textPane.setContentType("text/html");
	    
	    //frame.setSize(500, 500);
	    frame.setLocation(200,200);
	    frame.setVisible(true);
	    
	    frame.add(textPane);
	    frame.setAlwaysOnTop(true);
	    textPane.setText(loadHtml());
	    //textPane.setBackground(Color.cyan);
	    textPane.setSize(200, 200);
	    frame.pack();
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		input();
		computeData();
		output();
		generateHtml();
		generateForHtml();
		initGUI();
		Runtime.getRuntime().exec("explorer.exe output2.html");
	}

}

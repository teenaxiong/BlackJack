
/*
 * Teena Xiong
 * 11/7/2016
 * 
 * Assignment 2
 * 
 * The purpose of this code is a game call 21 blackjack. At the beginning of the code, it will call 
 * the displayIntruction() method, which contains a short introduction to the game. Next,
 * The game will all take place in a while loop. The player and dealer will each get two cards, which each
 * card is calling the method drawCard(). The drawCard will pick a random number between 1-13, and will return
 * the card value into the respective int variable.
 * 
 * Next, the code will ask the player if they want to be deal another card. If they answer yes, then the
 * drawCard() will be called again. If no, the dealer is dealt another card if their total of the first
 * two card is less than 17. 
 * 
 * Next, if the player has any Aces, they will be asked if they want to change it to a 1 or 11. 
 * 
 * Next, the determineWinner() method will be called. This is were the winner is decided. If both dealers
 * is above 21, then it's a tie. If whoever is 21, they are a winner. If both is 21, they are tied. If
 * one is above 21 and the other one isn't, then whoever is not above 21 is the winner. If they are both
 * below 21, then whoever is closes to 21 is the winner.
 * 
 * Next, user will be asked if they want to play again. If yes, the while loop repeats itself. If no, 
 * the number of times the user won is display and the game exit. 
 */
import java.util.Scanner; 
public class Blackjack { //class called BlackJack
	public static void main (String [] args) //main method. 
	{
		
		Scanner input = new Scanner (System.in); 
		displayInstruction(); //calling the method introduction here. 
		
		System.out.println("\nPress enter to began the game: "); //getting the game started. 
		String enter = input.nextLine(); 
		String playGame = "yes";  //setting the variable playGame to yes.
		int numberOfWinnings = 0; //setting the variable numberOfWinnings to 0. Keeps track of how many
									//times the player won. 
	
	while(playGame.contains("yes")) //this loop is ran as long as playGame is yes. 
	{
		System.out.println("\n\t------------------------"); 
		
		
		//-------Coding Player's hand --------------
		//*******************************************
		int playerNumberA = 0; //keeps track of how many Aces the player has. 
		int totalPlayer = 0; //keeps track of total of player points. 
		System.out.println("\nPlayer draws two cards:");
		System.out.print("Card 1: ");
		int playerCard1 = drawCard(); //drawCard() is called, returned value is stored in playerCard1
		System.out.print("Card 2: "); 
		int playerCard2 = drawCard(); //drawCard() is called, returned value is stored in playerCard2
		
		
		//the two if's statement below keeps track of how many Aces player has if the card 1 and card 2 is an Ace
		if(playerCard1==11)
			playerNumberA++; //our variable is incremented if playerCard1 is an Ace. 
		if(playerCard2==11)
			playerNumberA++; //this variable is incremented if playerCard2 is an Ace. 
		totalPlayer = playerCard1 + playerCard2; //adding the total of both cards and stored in totalPlayer. 
		
		
		
		//-------Coding Dealer's hand --------------
		//*******************************************
		int dealerNumberA = 0; 
		System.out.println("\nDealers draws two cards:");
		System.out.print("Card 1: "); 
		int dealerCard1=drawCard(); //calling the drawCard() method. Stored return value here. 
		System.out.print("Card 2: ");
		int dealerCard2 = drawCard(); //calling the drawCard(). stored return value here.
		
		//the below if's statements keeps track how many Aces dealer has. 
		if(dealerCard1 == 11)
			dealerNumberA++;
		if(dealerCard2 == 11)
			dealerNumberA++; 
		
		//the below if statement determines that if card 1 and card 2 is both 11, then card 2 is turned into
		//a value of 1 while card 1 still remains a value of 11. 
		if (dealerCard1==11 && dealerCard2==11 )
		{
			dealerCard2 = 1; 
			dealerNumberA--; //decrements the number of Aces the dealer has. 
		}
		
		
		
		//-------Coding that ask Player if they want to be dealt another card --------------
		//*******************************************
	
		int nextDraw =0;  //variable that stores what the next card is going to be. 
		System.out.print("\nWould you like to draw another card (yes or no)? "); 
		String yesOrNo = input.next(); //stored what user input into here. 
		String yesOrNoLowerCase = yesOrNo.toLowerCase(); //changing user's input into all lowercase. 

		//while loop that will keep running if user did not input yes or no. 
		while(!(yesOrNoLowerCase.equals("yes")) && !(yesOrNoLowerCase.equals("no")))
		{
		System.out.print("Please enter yes or no to draw another card.");
		yesOrNo= input.next(); 
		yesOrNoLowerCase = yesOrNo.toLowerCase();
		}
		
		//while loop that will run if user want to draw another card. 
		while(yesOrNoLowerCase.equals("yes"))
		{
			System.out.print("You drew a "); 
			nextDraw=drawCard(); //calling the drawCard() method again. Store next card in nextDraw. 
			if(nextDraw == 11)
				playerNumberA++; //keeping track of how many Aces the player has. 
			totalPlayer += nextDraw;  //adding the value to the totalPlayer amount. 
			
			System.out.print("\nWould you like to draw another card (yes or no)? "); 
			yesOrNo = input.next(); 
			yesOrNoLowerCase = yesOrNo.toLowerCase(); //changing to lowercase. 
			
			//this while loop is ran if user did not enter yes or no. 
			while(!(yesOrNoLowerCase.equals("yes")) && !(yesOrNoLowerCase.equals("no")))
					{
					System.out.print("Please enter yes or no to draw another card.");
					yesOrNo= input.next(); 
					yesOrNoLowerCase = yesOrNo.toLowerCase();
					}
				
		}
		
		
		
		//-------Coding Dealer's total hand --------------
		//*******************************************
		int totalDealer = dealerCard1 + dealerCard2; 
		int dealer17;  //will store the next draw card. 
		while(totalDealer<17) //dealer's total must be more than 17. if not, this is ran. 
		{
			System.out.print("Dealer drew a ");
			dealer17 = drawCard();  //calling drawCard() and storing it in dealer17. 
			if(dealer17==11)
				dealerNumberA++; //keeps track of how many Ace's dealer has. 
			totalDealer += dealer17;  //adding new card to total of Dealer's card. 
			
			//if total is >21 and the dealer has more than 1 Aces, this is ran
			if(totalDealer > 21 && dealerNumberA>0) 
				{
					totalDealer -= 10; //Aces was automatically counted as 11. We minus 10, which means
										//that the Aces is now counted as 1 instead. 
					dealerNumberA--; //minus the number of Aces that dealer has. 
				}
		
		}
		
	
		
		//-------Asking player if they want their Ace counted as 11 or 1 --------------
		//*******************************************
		System.out.println("\nYou have " + playerNumberA + " aces.");//displaying how many Aces player has. 
		int count =1; //setting a counter to 1. 
		while(playerNumberA > 0) //if Player has an aces, this is ran. 
		{
			System.out.print("Would you like to have Ace " + count + " be counted as 1 or 11?"); 
			int changA = input.nextInt(); //user's input is stored here. 
			
			//if user's input is not a 1 or 11, this is ran. 
			while(changA != 1 && changA != 11)
			{
				System.out.print("Please enter a 1 or 11.");
				changA = input.nextInt(); 
			}
			
			//if user's input is a 1, then we change the totalPlayer amount. 
			if((changA==1))
				{
					totalPlayer -= 10; //Ace was automatically counted as 11. We minus 10, cause user
										//wants the Ace's to be a 1 instead.  
					playerNumberA --; //decrementing how many Ace's the user has now. 
				}
			else playerNumberA --; //if user's wants the Aces to be counted as 11, we just minus 
								//the number of Ace's the player has. 
				
			count++; //incrementing a counter. 
		}
		
		
		 
		//-------Determing who is the winner --------------
		//*******************************************
		
		String winner = determineWinner(totalPlayer, totalDealer); //calling method, passing arguments. 
		
		//announcing who is the winner. 
		if(winner.contains("Player") || winner.contains("Dealer"))
				System.out.println("The " + winner + " won!"); 
		else System.out.println("It's a tie!"); 
		
		//keeping track of how many winnings the player has won so far. 
		if(winner.contains("Player"))
			numberOfWinnings++; 
			
		
		/*
		 * Play again or not????	
		 */
		
		//asking player if they want to play again. 
		System.out.print("\nDo you want to play again (yes or no)?");
		String playAgain = input.next(); 
		playGame = playAgain.toLowerCase();  //changing user's input to lowercase
		
		//if user did not enter yes or no, this while loop is ran. 
		while(!(playGame.equals("yes")) && !(playGame.equals("no")))
		{
			System.out.print("Please enter yes or no: ");
			playAgain = input.next(); 
			playGame = playAgain.toLowerCase();  
		}
		System.out.println("\n"); 
		
		
	}//end of the while loop. 
	
		//we have exit the while loop, meaning player has quit the game. This display how many
		//winnings user has got. 
		System.out.println("You have " + numberOfWinnings + " wins"); 
			
	}
		
		
		
	
	
//method displayInstruction. Returns void. Will return only print out the screen an instruction printout. 
public static void displayInstruction()
{
	System.out.println("First, the dealer and player each gets two cards."); 
	System.out.println("The goal of the game is to get as close, or equal, to 21" 
			+ " \nas possible without going over. Whoever is closest to 21 is the winner. "
			+ "\nIf anyone goes over 21, they automatically lose.  After the first two "
			+ "\ncards are drawn, you may decide whether or not to draw again.  Aces may "
			+ "\neither be 1 or 11. Good luck! ");
}


//method drawCard(); returns a int 
public static int drawCard()
{
	//randomly generating a number between 1-13. 
	int card = (int) (Math.random () * 13) + 1; 
	
	//displaying Jack, Queen, King, and Aces if 11, 12, 13, or 1 is randomly picked. 
	if (card==11)
		System.out.println("Jack"); 
	else if (card==12)
		System.out.println("Queen"); 
	else if (card==13)
		System.out.println("King"); 
	else if (card==1)
		System.out.println("Ace"); 
	else System.out.println(card);
	
	//making all Jack, Queen, and King equal to 10. 
	if(card==11 || card==12 || card==13)
		card=10; 
	
	//making Aces equal to 11 instead of 1. 
	if (card==1)
		card=11; 

	return card;  //return the value of card. 
	
}


//determineWinner() method. Returns a String. 
public static String determineWinner (int totalPlayer, int totalDealer)
{
	System.out.println("\nYour total is: " + totalPlayer); //display total of player cards
	System.out.println("Dealer total is: " + totalDealer); //display total of dealer cards.
	String winner = null; 
	
	//determing who is the winner here. 
	if(totalPlayer > 21 && totalDealer <= 21)
		winner = "Dealer";
	else if(totalPlayer <= 21 && totalDealer > 21)
		winner = "Player";
	else if( totalPlayer > totalDealer && totalPlayer<=21)
		winner = "Player";
	else if (totalPlayer < totalDealer  && totalDealer<=21)
		winner = "Dealer";
	else if ((totalPlayer == 21 && totalDealer == 21) || (totalPlayer > 21 && totalDealer > 21))
		winner = "Tie"; 

	return winner; //return the variable String winner. 
	
	
}


}

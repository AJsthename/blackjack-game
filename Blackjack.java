import java.util.Scanner;

public class Blackjack {
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        intro(scan);
        int yourSumOfCards = first2PlayerCards();
        int dealerSumOfCards = 0;
        System.out.println("Your total is: " + yourSumOfCards);
        int dealerCardValue1 = drawDealerCard1();
        int dealerCardValue2 = drawDealerCard2();
        // first2DealerCards();
        yourSumOfCards = playPlayerTurn(scan, yourSumOfCards);
        dealerSumOfCards = playDealerTurn(dealerCardValue1, dealerCardValue2);
        whoWins(yourSumOfCards, dealerSumOfCards);
        scan.close();
    }

    /**
     * Function name -- drawRandomCard
     * @return (int)
     * 
     * Description:
     *  - Gets a random number between 1 and 13
     *  - returns the random number
     */
    public static int drawRandomCard(){
        double randomNumber = Math.random() * 13;
        randomNumber +=  1;
        return (int)randomNumber;
    }

    /**
     * Function name -- intro
     * @param scan (Scanner)
     * 
     * Description:
     *  - Prints the introductory part of the game on the terminal
     */
    public static void intro(Scanner scan){
        System.out.println("Welcome to Java Casino!");
        System.out.println("Do you have a knack for Black Jack?");
        System.out.println("We shall see...");
        System.out.println("..Ready? Press anything to begin!");
        scan.nextLine();
    }

    /**
     * Function name -- first2PlayerCards
     * @return (int)
     * 
     * Description:
     *  - Draws two random cards for the player
     *  - Returns the sum of values of the cards as the
     *    player's total hand value
     */
    public static int first2PlayerCards(){
        String playerCard = "";
        int randomCardNumber1 = drawRandomCard();
        System.out.println("You get a\n");
        playerCard = getRandomCard(randomCardNumber1);
        System.out.println(playerCard);
        System.out.println("and a\n");
        int randomCardNumber2 = drawRandomCard();
        playerCard = getRandomCard(randomCardNumber2);
        System.out.println(playerCard);
        // Making sure that the program does not allow values or add card values > 10
        int sumOfCards = Math.min(randomCardNumber1, 10) + Math.min(randomCardNumber2, 10);
        return sumOfCards;
    }

    /**
     * Function name -- drawDealerCard1
     * @return (int)
     * 
     * Description: 
     *  - Draws 1 random card for the dealer. 
     *    This serves as his first hand card.
     *  - Returns the value of the card
     */
    public static int drawDealerCard1(){
        int randomCardNumber = drawRandomCard();
        String dealerCard1 = getRandomCard(randomCardNumber);
        System.out.println("The dealer shows\n");
        System.out.println(dealerCard1);
        // dealerSumOfCards += Math.min(randomCardNumber, 10);
        // return dealerCard1;
        return randomCardNumber;
    }

    /**
     * Function name -- drawDealerCard2
     * @return (int)
     * 
     * Description:
     *  - Draws 1 random card for the dealer (2 Hand card)
     *  - Prints out the card face down
     *  - Returns the value of the random card 
     */
    public static int drawDealerCard2(){
        int randomCardNumber = drawRandomCard();
        // dealerCard2 = getRandomCard(randomCardNumber);
        System.out.println("and has a card facing down\n");
        String faceDownCard = faceDown();
        System.out.println(faceDownCard);
        System.out.println("\nThe dealer's total is hidden.");
        // dealerSumOfCards += Math.min(randomCardNumber, 10);
        return randomCardNumber;
    }

    /**
     * Funnction name -- hitOrStay
     * @param scan (Scanner)
     * @return (String)
     * 
     * Description: 
     *  - Let's the user/Player choose either to hit or stay
     *  - Handles incorrect input
     *  - Returns the user's choice/answer
     */
    public static String hitOrStay(Scanner scan){
        System.out.println("Would you like to hit or stay?");
        String answer = scan.nextLine();
        while(!(answer.equalsIgnoreCase("hit") || answer.equalsIgnoreCase("stay"))){
            System.out.println("Please write 'hit' or 'stay'");
            answer = scan.nextLine();
        }
        return answer;
    }

    /**
     * Function name -- playPlayerTurn
     * @param scan (Scanner)
     * @param yourSumOfCards (int)
     * @return (int)
     * 
     * Description:
     *  - Actualises the player's turn to play 
     *  - Evaluates if the player decided to hit or stay:
     *      - If the player "hits":
     *          - Continuously draw random cards until the player 
     *            either 'stays' or the sum of cards exceed 21
     *      - If the player "stays":
     *          - Exit the loop and function - Transition to
     *            dealer's turn
     *  - Return player's total hand value
     */
    public static int playPlayerTurn(Scanner scan, int yourSumOfCards){
        while(true){
            String hitOrStayAns = hitOrStay(scan);
            if(hitOrStayAns.equalsIgnoreCase("hit")){
                int randomCardNumber = drawRandomCard();
                String hitCard = getRandomCard(randomCardNumber);
                System.out.println("\nYou get a\n" + hitCard);
                yourSumOfCards += Math.min(randomCardNumber,10);
                System.out.println("Your total is " + yourSumOfCards);
                if(yourSumOfCards > 21){
                    System.out.println("Bust! Player loses.");
                    System.exit(0);
                }
            } else{
                break;
            }
        }
        return yourSumOfCards;
    }

    /**
     * Function name -- playDealerTurn
     * @param dealerCardValue1 (int)
     * @param dealerCardValue2 (int)
     * @return (int)
     * 
     * Description:
     *  - Actualises the dealer's turn to play
     *  - Reveals the dealer's entire hand
     *  - Program continues to "hit" until the dealer's total
     *    hand value exceeds 17:
     *      - If the dealer's total hand value is more than 21, the 
     *        dealer loses
     *  - Returns dealer's total hand value 
     */
    public static int playDealerTurn(int dealerCardValue1, int dealerCardValue2){
        System.out.println("\nDealer's turn");
        String dealerCard1 = getRandomCard(dealerCardValue1);
        String dealerCard2 = getRandomCard(dealerCardValue2);
        System.out.println("\nThe dealer's cards are\n" + dealerCard1 + "\nand a\n" + dealerCard2);
        int dealerSumOfCards = dealerCardValue1 + dealerCardValue2;
        while(!(dealerSumOfCards >= 17)){
            int randomCardNumber = drawRandomCard();
            String hitCard = getRandomCard(randomCardNumber);
            System.out.println("\nDealer gets a\n" + hitCard);
            dealerSumOfCards += Math.min(randomCardNumber,10);
        }
        System.out.println("Dealer's total is " + dealerSumOfCards);
        if(dealerSumOfCards > 21){
            System.out.println("Bust! Dealer loses.");
            System.exit(0);
        }
        return dealerSumOfCards;
    }

    /**
     * Function name -- whoWins
     * @param yourSumOfCards (int)
     * @param dealerSumOfCards (int)
     * 
     * Description:
     *  - Compares the dealer's and player's individual total hand values
     *    to determine who won
     *  - If dealer's sum of cards and player's sum of cards are equal
     *      - prints "Everyone wins"
     *  - If player's sum of cards supercede dealer's
     *      - prints "Player wins!"
     *  - If dealer's sum of cards supercede player's
     *      - prints "Dealer wins!"
     */
    public static void whoWins(int yourSumOfCards, int dealerSumOfCards){
        if(yourSumOfCards == dealerSumOfCards){
            System.out.println("Everyone wins");
        }else if(yourSumOfCards > dealerSumOfCards){
            System.out.println("Player wins!");
        }else{
            System.out.println("Dealer wins!");
        }
    }

    /**
     * Function name -- getRandomCard
     * @param randomCardNumber (int)
     * @return (String)
     */
    public static String getRandomCard(int randomCardNumber){
        switch(randomCardNumber){
            case 1: return  "   _____\n"+
                            "  |A _  |\n"+ 
                            "  | ( ) |\n"+
                            "  |(_'_)|\n"+
                            "  |  |  |\n"+
                            "  |____V|\n";
                            
            case 2: return  "   _____\n"+              
                            "  |2    |\n"+ 
                            "  |  o  |\n"+
                            "  |     |\n"+
                            "  |  o  |\n"+
                            "  |____Z|\n";

            case 3: return  "   _____\n" +
                            "  |3    |\n"+
                            "  | o o |\n"+
                            "  |     |\n"+
                            "  |  o  |\n"+
                            "  |____E|\n";

            case 4: return  "   _____\n" +
                            "  |4    |\n"+
                            "  | o o |\n"+
                            "  |     |\n"+
                            "  | o o |\n"+
                            "  |____h|\n";

            case 5: return  "   _____ \n" +
                            "  |5    |\n" +
                            "  | o o |\n" +
                            "  |  o  |\n" +
                            "  | o o |\n" +
                            "  |____S|\n";

            case 6: return  "   _____ \n" +
                            "  |6    |\n" +
                            "  | o o |\n" +
                            "  | o o |\n" +
                            "  | o o |\n" +
                            "  |____6|\n";

            case 7: return  "   _____ \n" +
                            "  |7    |\n" +
                            "  | o o |\n" +
                            "  |o o o|\n" +
                            "  | o o |\n" +
                            "  |____7|\n";

            case 8: return  "   _____ \n" +
                            "  |8    |\n" +
                            "  |o o o|\n" +
                            "  | o o |\n" +
                            "  |o o o|\n" +
                            "  |____8|\n";

            case 9: return  "   _____ \n" +
                            "  |9    |\n" +
                            "  |o o o|\n" +
                            "  |o o o|\n" +
                            "  |o o o|\n" +
                            "  |____9|\n";

            case 10: return "   _____ \n" +
                            "  |10  o|\n" +
                            "  |o o o|\n" +
                            "  |o o o|\n" +
                            "  |o o o|\n" +
                            "  |___10|\n";

            case 11: return "   _____\n" +
                            "  |J  ww|\n"+ 
                            "  | o {)|\n"+ 
                            "  |o o% |\n"+ 
                            "  | | % |\n"+ 
                            "  |__%%[|\n";
                            
            case 12: return "   _____\n" +
                            "  |Q  ww|\n"+ 
                            "  | o {(|\n"+ 
                            "  |o o%%|\n"+ 
                            "  | |%%%|\n"+ 
                            "  |_%%%O|\n";

            case 13: return "   _____\n" +
                            "  |K  WW|\n"+ 
                            "  | o {)|\n"+ 
                            "  |o o%%|\n"+ 
                            "  | |%%%|\n"+ 
                            "  |_%%%>|\n";

            default: return "No such card in system";
        }
    }

    /**
     * Function name -- faceDown
     * @return (String)
     */
    public static String faceDown() {
        return
        "   _____\n"+
        "  |     |\n"+ 
        "  |  J  |\n"+
        "  | JJJ |\n"+
        "  |  J  |\n"+
        "  |_____|\n";
    }
}


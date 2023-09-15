package com.hampus.demo;

import java.util.ArrayList;
import com.hampus.demo.modules.Input;
import com.hampus.demo.modules.Player;

/**
 * Programmet kör ett tärningsspel med 2-10 spelare och 1-5 tärningar.
 * Spelarna sparas i objekt genom klassen Player och inmatning hanteras genom Input klassen
 * Programmet fungerar genom att spara x antal objekt med spelarinfo i en ArrayList
 * Genom denna ArrayList hämtas sedan all info om spelarna
 * Under spelets gång rullas x antal tärningar och poäng sparas i varje objekt
 * Efter att alla har spelat jämförs poängen och resultatet skrivs ut
 * Slutligen frågar programmet om användaren vill spela igen eller avsluta
 *
 * @author Hampus Källberg
 */
public class Main
{
    public static void main(String[] args)
    {
        //Anger variabler, objekt och listor
        int amountOfPlayers;
        int currentPlayerNumber = 0;
        int noOfDice;
        int playAgain;
        boolean gameIsRunning = true;
        String playerName;
        
        Input userInput = new Input();
        ArrayList<Player> listOfPlayers = new ArrayList<>();

        System.out.println("Hello and welcome to dice game\nPlease enter how many players are playing (2-10 players supported)");
        amountOfPlayers = userInput.getIntPlayers();

        //Lägger till alla spelare och deras namn
        for(int i = 0; i < amountOfPlayers; i++)
        {
            currentPlayerNumber++;
            System.out.println("Please enter the name of player number: " + currentPlayerNumber);
            playerName = userInput.getStringInput();
            Player addPlayer = new Player(playerName);
            listOfPlayers.add(addPlayer);
        }

        //Lägger till antal tärningar som ska användas under spelet
        System.out.println("Please enter how many dices you want to play with (1-5)");
        noOfDice = userInput.getIntDice();

        //Skriver ut ordningen och spelares namn
        for(Player player : listOfPlayers)
        {
            System.out.println(player);
        }

        //Startar spelet
        while(gameIsRunning)
        {
            //lista med vinnare
            ArrayList<Player> winningPlayers = new ArrayList<>();
            int currentMax = 0;

            //loopar igenom alla spelare för att spara deras poäng samt högsta poäng
            for(Player player : listOfPlayers)
            {
                saveScore(player, noOfDice);

                if(player.getCurrentScore() > currentMax)
                {
                    currentMax = player.getCurrentScore();
                }
            }
            System.out.println("----------------------------------");
            //Lägger till vinnare i en egen lista
            for(Player player : listOfPlayers)
            {
                if(player.getCurrentScore() == currentMax)
                {
                    winningPlayers.add(player);
                }
            }

            //kontrollerar ifall det finns fler än 1 vinnare och presenterar resultat
            if(winningPlayers.size() > 1)
            {
                for(Player players : winningPlayers)
                {
                    System.out.printf("The winners are %s with a score of %d\n", players.getName(), currentMax);
                }
            }
            else
            {
                System.out.printf("The winner is %s with a score of %d\n", winningPlayers.get(0).getName(), currentMax);
            }

            //Spela igen alternativ
            System.out.println("----------------------------------");
            System.out.println("Play another round?\nPress 1 for: YES\nPress 2 for: NO");
            playAgain = playAgain();
            if(playAgain == 1)
            {
                System.out.println("Starting another round");
            }
            if(playAgain == 2)
            {
                System.out.println("Thanks for playing");
                gameIsRunning = false;      //Avslutar spel
            }
        }
    }
    /**
     * Metod som sparar spelarens poäng
     *
     * @param player   - Objekt med spelarinfo
     * @param noOfDice - Antal tärningar som ska rullas
     */
    public static void saveScore(Player player, int noOfDice)
    {
        System.out.println("----------------------------------");
        System.out.printf("Rolling %d dice for player %s\n", noOfDice, player.getName());
        player.setCurrentScore(rollDice(noOfDice));         //Rullar tärningar i annan metod och sparar poängen här.
        System.out.printf("Total score for %s is : %d\n", player.getName(), player.getCurrentScore());

        //Saktar ner flödet i konsolen
        try
        {
            Thread.sleep(2000);
        }
        catch(InterruptedException e)
        {
            Thread.currentThread().interrupt();
        }
    }
    /**
     * Metod som kastar x antal tärningar och summerar poängen
     *
     * @param noOfDice - Antal tärningar
     * @return - Total poäng
     */
    public static int rollDice(int noOfDice)
    {
        //Variabler
        int score = 0;
        int diceThrow;

        //Rullar x antal tärningar
        for(int i = 1; i <= noOfDice; i++)
        {
            diceThrow = (int) (Math.random() * 6 + 1);
            score = score + diceThrow;
            System.out.printf("Dice %d is a %d\n", i, diceThrow);
        }

        return score;
    }

    /**
     * Metod som tar emot endast 1 eller 2 för att starta ny runda
     * @return - Integer med talet 1 eller 2
     */
    public static int playAgain()
    {
        Input userInput = new Input();
        while(true)
        {
            //Switch så att det går att använda userInput.getIntDice metoden utan behöva att modifiera den
            switch(userInput.getIntDice())
            {
                case 1 ->
                {
                    return 1;
                }
                case 2 ->
                {
                    return 2;
                }
                default -> System.out.println("Please enter a correct value");
            }
        }
    }

}

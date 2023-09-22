package com.hampus.demo.modules;

import java.util.ArrayList;

public class Game
{
    /**
     * Metod som kontrollerar att rätt antal spelare väljs
     *
     * @param scanner - Scanner för att göra inmatning
     * @return - heltal mellan 2-10
     */
    public int amountOfPlayers(Input scanner)
    {
        int tempNum;
        while(true)
        {
            tempNum = scanner.getInt();
            if(tempNum < 2 || tempNum > 10)
            {
                System.out.println("Please enter a valid number");
            }
            else
            {
                return tempNum;
            }
        }
    }

    /**
     * Metod som lägger till spelare till nuvarande spel
     *
     * @param noOfPlayers   - antal spelare som ska läggas till
     * @param listOfPlayers - lista som håller koll på alla spelare
     * @param userInput     - Scanner för inmatning av spelarnamn
     */
    public void addPlayers(int noOfPlayers, ArrayList<Player> listOfPlayers, Input userInput)
    {
        int currentPlayerNumber = 0;
        String playerName;
        for(int i = 0; i < noOfPlayers; i++)
        {
            currentPlayerNumber++;
            System.out.println("Please enter the name of player number: " + currentPlayerNumber);
            playerName = userInput.getStringInput();
            Player addPlayer = new Player(playerName);
            listOfPlayers.add(addPlayer);
        }
    }

    /**
     * Metod som kontrollerar så att rätt antal tärningar väljs
     *
     * @param scanner - Scanner för att göra inmatning
     * @return - heltal mellan 1-5
     */
    public int noOfDice(Input scanner)
    {
        int tempNum;
        while(true)
        {
            tempNum = scanner.getInt();
            if(tempNum < 1 || tempNum > 5)
            {
                System.out.println("Please enter a valid number");
            }
            else
            {
                return tempNum;
            }
        }
    }

    /**
     * Metod som sparar spelarens poäng
     *
     * @param player   - Objekt med spelarinfo
     * @param noOfDice - Antal tärningar som ska rullas
     */
    public void saveScore(Player player, int noOfDice)
    {
        System.out.println("----------------------------------");
        System.out.printf("Rolling %d dice for player %s\n", noOfDice, player.getName());
        player.setCurrentScore(rollDice(noOfDice));         //Rullar tärningar i annan metod och sparar poängen här.
        System.out.printf("Total score for %s is : %d\n", player.getName(), player.getCurrentScore());

        //Saktar ner flödet i konsolen
        waitTwoSeconds();
    }

    /**
     * Metod som rullar x antal tärningar och summerar poängen
     *
     * @param noOfDice - Antal tärningar
     * @return - Total poäng
     */
    public int rollDice(int noOfDice)
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
     *
     * @return - Integer med talet 1 eller 2
     */
    public int playAgain(Input scanner)
    {
        int tempNum = 0;
        while(tempNum == 0)
        {
            switch(scanner.getInt())
            {
                case 1 -> tempNum = 1;
                case 2 -> tempNum = 2;
                default -> System.out.println("Please only enter 1 or 2.");
            }
        }
        return tempNum;
    }

    /**
     * Metod som väntar i 2 sekunder innan den skickar ut något i konsolen
     */
    public void waitTwoSeconds()
    {
        try
        {
            Thread.sleep(2000);
        }
        catch(InterruptedException e)
        {
            Thread.currentThread().interrupt();
        }
    }
}

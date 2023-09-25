package com.hampus.demo.modules;

import java.util.ArrayList;

public class Game
{
    /**
     * Metod som kontrollerar att rätt heltal valts av användare
     *
     * @param scanner - Scanner för inmatning
     * @param min     - lägsta godtagna tal
     * @param max     - högsta godtagna tal
     * @return - heltal mellan min och max
     */
    public int specificInt(Input scanner, int min, int max)
    {
        int tempNum;
        while(true)
        {
            tempNum = scanner.getInt();
            if(tempNum < min || tempNum > max)
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
            addPlayer.setPlayerNumber(i + 1);
            listOfPlayers.add(addPlayer);
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
        waitTwoSeconds(noOfDice);
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
     * Metod som väntar i 2 sekunder innan den skickar ut något i konsolen
     */
    public void waitTwoSeconds(int noOfDice)
    {
        int secondsToWait = 1000 * noOfDice;
        try
        {
            Thread.sleep(secondsToWait);
        }
        catch(InterruptedException e)
        {
            Thread.currentThread().interrupt();
        }
    }
}

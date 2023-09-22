package com.hampus.demo;

import java.util.ArrayList;

import com.hampus.demo.modules.Game;
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
        //Anger variabler
        int noOfPlayers;
        int noOfDice;
        int playAgain;
        boolean gameIsRunning = true;

        //Skapar objekt och listor
        Input userInput = new Input();
        Game currentGame = new Game();
        ArrayList<Player> listOfPlayers = new ArrayList<>();

        //Lägger till spelarantal och namn
        System.out.println("Hello and welcome to dice game\nPlease enter how many players are playing (2-10 players supported)");
        noOfPlayers = currentGame.amountOfPlayers(userInput);
        currentGame.addPlayers(noOfPlayers, listOfPlayers,userInput);

        //Lägger till antal tärningar som ska användas under spelet
        System.out.println("Please enter how many dices you want to play with (1-5)");
        noOfDice = currentGame.noOfDice(userInput);

        //Skriver ut ordningen och spelares namn
        for(Player player : listOfPlayers)
        {
            System.out.println(player);
        }

        //Metod som väntar 2 sekunder innan den printar
        currentGame.waitTwoSeconds();

        //Startar spelet
        while(gameIsRunning)
        {
            //lista med vinnare
            ArrayList<Player> winningPlayers = new ArrayList<>();
            int currentMax = 0;

            //loopar igenom alla spelare för att spara deras poäng samt högsta poäng
            for(Player player : listOfPlayers)
            {
                currentGame.saveScore(player, noOfDice);

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
            playAgain = currentGame.playAgain(userInput);
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
}
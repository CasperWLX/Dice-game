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
        //Anger konstanter och variabler
        int noOfPlayers;
        int noOfDice;
        int playAgain;
        int currentMax = 0;
        boolean gameIsRunning = true;
        String divider = "----------------------------------";

        //Skapar objekt och listor
        Input userInput = new Input();
        Game currentGame = new Game();
        ArrayList<Player> listOfPlayers = new ArrayList<>();

        //Lägger till spelarantal och namn
        System.out.println("Hello and welcome to dice game\nPlease enter how many players are playing (2-10 players supported)");
        noOfPlayers = currentGame.specificInt(userInput, 2, 10);
        currentGame.addPlayers(noOfPlayers, listOfPlayers, userInput);

        //Lägger till antal tärningar som ska användas under spelet
        System.out.println("Please enter how many dices you want to play with (1-5)");
        noOfDice = currentGame.specificInt(userInput, 1, 5);

        currentGame.printPlayers(listOfPlayers);

        //Metod som väntar 2 sekunder innan den printar
        currentGame.waitTwoSeconds(noOfPlayers);

        //Startar spelet
        do
        {
            //lista med vinnare som återställs varje runda
            ArrayList<Player> winningPlayers = new ArrayList<>();

            //loopar igenom alla spelare för att spara deras poäng samt högsta poäng
            currentMax = currentGame.findCurrentMax(currentGame, listOfPlayers, noOfDice, currentMax);
            System.out.println(divider);

            //Lägger till vinnare i en egen lista
            currentGame.addWinners(listOfPlayers, winningPlayers, currentMax);

            //kontrollerar ifall det finns fler än 1 vinnare och presenterar resultat
            currentGame.printWinners(winningPlayers);
            System.out.println(divider);

            //Spela igen alternativ
            System.out.println("Play another round?\nPress 1 for: YES\nPress 2 for: NO");
            playAgain = currentGame.specificInt(userInput, 1, 2);

            if(playAgain == 1)
            {
                System.out.println("Starting another round");
                currentMax = 0;
            }
            else
            {
                System.out.println("Thanks for playing");
                gameIsRunning = false;      //Avslutar spel
            }
        } while(gameIsRunning);
    }
}
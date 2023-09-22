package com.hampus.demo.modules;

/**
 * Klass som hanterar spelare
 *
 * @author Hampus Källberg
 */
public class Player
{
    //Anger konstanter och variabler
    private final String NAME;
    private int currentScore;

    /**
     * Konstruktor för klassen
     *
     * @param name - spelarens namn
     */
    public Player(String name)
    {
        this.NAME = name;
    }

    /**
     * ToString metod
     *
     * @return - Player + namn
     */
    public String toString()
    {
        return "player: " + NAME;
    }

    /**
     * Metod som ändrar spelarens poäng
     *
     * @param currentScore - int med total poäng under denna runda
     */
    public void setCurrentScore(int currentScore)
    {
        this.currentScore = currentScore;
    }

    /**
     * Metod som hämtar spelarens namn
     *
     * @return - Spelarens namn
     */
    public String getName()
    {
        return this.NAME;
    }

    /**
     * Metod som hämtar en spelare totala poäng under denna runda
     *
     * @return - int med spelarens poäng
     */
    public int getCurrentScore()
    {
        return this.currentScore;
    }
}


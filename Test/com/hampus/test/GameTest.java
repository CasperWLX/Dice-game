package com.hampus.test;

import com.hampus.demo.modules.Game;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest
{
    @Test
    void rollDice()
    {
        Game tester = new Game();
        int number;
        for (int i = 0; i < 1000; i++)
        {
            number = tester.rollDice(1);
            assertTrue(number >= 1 && number <= 6, "out of bounds");
        }
    }
}
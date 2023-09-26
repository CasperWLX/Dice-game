package com.hampus.demo.modules;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InputTest
{
    Input input = new Input();

    @Test
    void isInputEmpty()
    {
        int result = input.isInputEmpty("");
        assertEquals(0,result);
    }

    @Test
    void isNumberAnInt()
    {
        int result = input.isNumberAnInt("aaabbbccc");
        assertEquals(0, result);

        result = input.isNumberAnInt("A1B2C3");
        assertEquals(0, result);

        result = input.isNumberAnInt("++++++++");
        assertEquals(0, result);
    }

    @Test
    void isNumberPositive()
    {
        int result = input.isNumberPositive("-123");
        assertEquals(0,result);

        result = input.isNumberPositive("0");
        assertEquals(0,result);

        result = input.isNumberPositive(String.valueOf(Integer.MIN_VALUE));
        assertEquals(0,result);
    }

    @Test
    void hasSpecialCharacterOrNumber()
    {
        String result = input.hasSpecialCharacterOrNumber("lma0");
        assertEquals(" ",result);

        result = input.hasSpecialCharacterOrNumber("lol");
        assertEquals("lol",result);

        result = input.hasSpecialCharacterOrNumber("1234");
        assertEquals(" ",result);

        result = input.hasSpecialCharacterOrNumber("Hampus");
        assertEquals("Hampus",result);
    }
}
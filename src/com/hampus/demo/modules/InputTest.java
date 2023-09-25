package com.hampus.demo.modules;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.*;

class InputTest
{



    @Test
    void getInt()
    {
        String fakeInput = "1\n";

        System.setIn(new ByteArrayInputStream(fakeInput.getBytes()));

        Input user = new Input();
        int result = user.getInt();

        assertEquals(10,result);
        assertEquals(1,result);
        assertEquals(1,result);

    }

    @Test
    void getStringInput()
    {

    }
}
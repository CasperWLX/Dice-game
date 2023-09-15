package com.hampus.demo.modules;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Klass som hanterar inmatning från användarna
 *
 * @author Hampus Källberg
 */
public class Input
{
    Scanner input = new Scanner(System.in);

    /**
     * Metod som tar emot inmatning från användaren men endast returnerar ifall det är ett heltal 2-10
     * @return - heltal mellan 2-10
     */
    public int getIntPlayers()
    {
        //Startar while loop
        int num = Integer.MAX_VALUE;
        while (num == Integer.MAX_VALUE)
        {
            //Kontrollerar ifall inmatning är en Int
            if (input.hasNextInt())
            {
                num = input.nextInt();

                //Kontrollerar så att Int är inom vår range av tal
                if(num < 2 || num > 10)
                {
                    System.out.println("Enter a valid number");
                    num = Integer.MAX_VALUE;
                }
            }
            //Felmeddelande ifall det inte är en Int
            else
            {
                System.out.println("Enter a valid number");
            }
            //Rensar input buffer
            input.nextLine();
        }
        return num;
    }

    /**
     * Metod som fungerar som ovanstående men som endast tar emot nummer mellan 1-5
     * @return - heltal mellan 1-5
     */
    public int getIntDice()
    {
        int num = Integer.MAX_VALUE;
        while (num == Integer.MAX_VALUE)
        {
            if (input.hasNextInt())
            {
                num = input.nextInt();
                if(num < 1 || num > 5)
                {
                    System.out.println("Enter a valid number");
                    num = Integer.MAX_VALUE;
                }
            }
            else
            {
                System.out.println("Enter a valid number");
            }
            input.nextLine();
        }
        return num;
    }

    /**
     * Metod som endast returnerar en String med alfabetiska bokstäver mellan A-Ö
     * @return - String med spelarens namn
     */
    public String getStringInput()
    {
        while(true)
        {
            //Sparar input i en String
            String userName = input.nextLine();

            //Sätter upp regler för vad stringen får innehålla
            Pattern special = Pattern.compile("[^a-ö0-9]", Pattern.CASE_INSENSITIVE);
            Pattern number = Pattern.compile("[0-9]", Pattern.CASE_INSENSITIVE);
            Matcher matcher = special.matcher(userName);
            Matcher numberMatcher = number.matcher(userName);

            //Kontrollerar ifall det finns siffror eller specialtecken i strängen
            boolean containsSymbols = matcher.find();
            boolean containsNumbers = numberMatcher.find();

            //Returnerar namn ifall inga siffror eller bokstäver finns med
            if(containsSymbols)
            {
                System.out.println("Please enter a valid name");
            }
            else if(containsNumbers)
            {
                System.out.println("Please enter a valid name");
            }
            else
            {
                return userName;
            }
        }
    }
}


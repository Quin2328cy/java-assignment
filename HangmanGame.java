import java.util.ArrayList;
import java.util.Random;
import java.util.*;


public class HangmanGame {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Hangman hangMan = new Hangman();
        String yesNo = "";
        do
        {
            yesNo = hangMan.playGame();
            if(yesNo.equalsIgnoreCase("Y"))
            {
                hangMan = new Hangman();
            }

        }while(!yesNo.equalsIgnoreCase("N"));

    }

}

class Hangman
{
    // secret words
    ArrayList words = null;
    //guessed letter
    ArrayList guessedLetter =null;

    //random secret word
    String secretWord;

    ArrayList correctList=null;
    ArrayList wrongList = null;

    Hangman()
    {
        words = new ArrayList();
        words.add("THREE");
        words.add("TECHNOLOGY");
        words.add("SOFTWARE");
        words.add("FILE");
        words.add("EMPLOYEE");
        words.add("SECURITY");
        words.add("DATA");
        words.add("REPORT");
        words.add("HELLO");
        words.add("JAVA");
        words.add("WORLD");
        words.add("CHINA");


        guessedLetter = new ArrayList();
        correctList = new ArrayList();
        wrongList= new ArrayList();




    }

    private void chooseWord()
    {
        int lenWords = words.size();
        int index = new Random().nextInt(lenWords);
        secretWord = words.get(index).toString();
        for(int i=0;i<secretWord.length();i++)
        {
            correctList.add("_");
        }


    }


    private String result()
    {
        String correct = "";
        for(int i=0;i<correctList.size();i++)
        {
            correct=correct+correctList.get(i);
        }

        if (wrongList.size() == 8)
        {
            return "LOSE";
        }
        else if (secretWord.toUpperCase().equals(correct.toUpperCase()))
        {
            return "WIN";
        }
        else
            return "CONTINUE"; // Else play the game
    }

    private boolean checkLetter(String letter)
    {

        char ch =letter.charAt(0);
        if (ch<'A'||ch>'Z')  // Check if the letter is a digit
        {
            System.out.println();
            System.out.println("'" + letter + "' is not a valid letter");
            return false;
        }
        else if (!this.guessedLetter.contains(letter))
        {
            System.out.println();
            guessedLetter.add(letter);
            System.out.print("Guessed Letters : " );

            for(int i=0;i<guessedLetter.size();i++)
            {
                System.out.print(guessedLetter.get(i)+" ");

            }
            System.out.println();
            return true;
        }
        else
        {
            System.out.println();
            System.out.println("Sorry, you already guessed '" + letter + "'");
        }
        return false;
    }

    private void handleGuess(String letter)
    {
        if(secretWord.contains(letter))
        {
            for(int i=0;i<secretWord.length();i++)
            {
                String ch = secretWord.substring(i,i+1);
                if(ch.equals(letter))
                {
                    correctList.remove(i);
                    correctList.add(i, letter);
                }
            }
        }
        else
        {
            wrongList.add(letter);
        }


        printHangman();
        displayWord();
        System.out.println("");
    }

    private void printHangman()
    {


        System.out.println();
        if (wrongList.size() == 1)
        {
            System.out.println("   _____");
            System.out.println("  |     |");
            System.out.println("  |     O");
            System.out.println("  |");
            System.out.println("  |");
            System.out.println("  |");
            System.out.println("  |");
            System.out.println("__|__");
        }
        else if (wrongList.size() == 2)
        {
            System.out.println("   _____");
            System.out.println("  |     |");
            System.out.println("  |     O");
            System.out.println("  |     |");
            System.out.println("  |");
            System.out.println("  |");
            System.out.println("  |");
            System.out.println("__|__");
        }
        else if (wrongList.size() == 3)
        {
            System.out.println("   _____");
            System.out.println("  |     |");
            System.out.println("  |     O");
            System.out.println("  |    \\|");
            System.out.println("  |");
            System.out.println("  |");
            System.out.println("  |");
            System.out.println("__|__");
        }
        else if (wrongList.size() == 4)
        {
            System.out.println("   _____");
            System.out.println("  |     |");
            System.out.println("  |     O");
            System.out.println("  |    \\|/");
            System.out.println("  |");
            System.out.println("  |");
            System.out.println("  |");
            System.out.println("__|__");

        }
        else if (wrongList.size() == 5)
        {
            System.out.println("   _____");
            System.out.println("  |     |");
            System.out.println("  |     O");
            System.out.println("  |    \\|/");
            System.out.println("  |    /");
            System.out.println("  |");
            System.out.println("__|__");
        }
        else if (wrongList.size() == 6)
        {
            System.out.println("   _____");
            System.out.println("  |     |");
            System.out.println("  |     O");
            System.out.println("  |    \\|/");
            System.out.println("  |    / \\");
            System.out.println("  |");
            System.out.println("__|__");
        }
        else if (wrongList.size() == 7)
        {
            System.out.println("   _____");
            System.out.println("  |     |");
            System.out.println("  |     O");
            System.out.println("  |    \\|/");
            System.out.println("  |   _/ \\");
            System.out.println("  |");
            System.out.println("__|__");
        }
        else if (wrongList.size() == 8)
        {
            System.out.println("   _____");
            System.out.println("  |     |");
            System.out.println("  |     O");
            System.out.println("  |    \\|/");
            System.out.println("  |   _/ \\_");
            System.out.println("  |");
            System.out.println("__|__");
        }
        else
            System.out.println();

        System.out.println();
    }

    private void  displayWord()
    {
        for(int i=0;i<correctList.size();i++)
        {
            System.out.print(correctList.get(i)+" ");
        }
        System.out.println();
    }


    public  String playGame()
    {
        Scanner scan = new Scanner(System.in);
        System.out.println("========Welcome to hangman. You get eight chances to guess the secret word.=======");

        //random choose word
        chooseWord();

        for (int i = 0; i < correctList.size(); i++)
        {
            System.out.print(" _ ");
        }
        System.out.println("");
        System.out.println("");
        System.out.println("");
        while (result().equals("CONTINUE"))
        {
            System.out.print("Pick a letter --> ");
            String input = scan.next().toUpperCase();
            input = Character.toString(input.charAt(0));
            if (checkLetter(input))
                handleGuess(input);
        }
        if (result().equals("LOSE"))
        {
            System.out.println("So sorry. You struck out.");
            System.out.println("The secret word was '" + secretWord.toUpperCase() + "'");
            System.out.println("Do you want to play again ? Y/N");
            String yesNo = scan.next();
            return yesNo;
        }
        else//WIN
        {
            System.out.println("You won !");
            System.out.println("Do you want to play again ? Y/N");
            String yesNo = scan.next();
            return yesNo;
        }
    }
} 

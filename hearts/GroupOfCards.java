package hearts;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class GroupOfCards {

    Card[] cards;
    int currentSize = 0;

    GroupOfCards(int num)
    {
        cards = new Card[num];
    }

    public int getCurrentSize()
    {
        return currentSize;
    }

    public Card getCard(int i)
    {
        if(i<0||i>=currentSize)
            return null;
        return cards[i];
    }
    public void addCard(Card card)
    {
        cards[currentSize] = card;
        currentSize++;

    }

    public Card removeCard(int i)
    {
        if(i<0||i>=currentSize)
            return null;
        Card card = cards[i];
        int j =i;
        for(;j<currentSize-1;j++)
        {
            cards[j]= cards[j+1];
        }
        cards[j] = null;

        currentSize--;

        return card;
    }

    public void display()
    {
        for(int i=0;i<currentSize;i++)
        {
            cards[i].display();
            System.out.println();
        }
    }
}

package hearts;

import java.util.Random;

public class Deck extends GroupOfCards{

    static final int TOTAL_CARDS = 52;
    Deck()
    {
        super(TOTAL_CARDS);


    }

    public void shuffle()
    {
        for(int i=0;i<=3;i++)
        {
            for (int j =2;j<=14;j++)
            {
                Card card = new Card(j,i);
                addCard(card);
            }
        }


        int n = getCurrentSize();
        Random random = new Random();
        for(int i=0;i<n;i++)
        {
            int index = random.nextInt(TOTAL_CARDS);
            Card temp = cards[index];
            cards[index] = cards[n-1];
            cards[n-1] = temp;
        }
    }

    public Card dealCard()
    {
        Card card = cards[0];
        removeCard(0);
        return card;
    }

}


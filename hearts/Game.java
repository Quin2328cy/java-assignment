package hearts;

public class Game {

    final int PLAYERS;
    Deck deck;
    Hand [] players;
    private Trick[] tricks;
    private int numberOfTricks= 0;
    private boolean hearts = false;
    private boolean queenOfSpades = false;

    Game(int numberOfPlayers)
    {
        PLAYERS = numberOfPlayers;
        deck = new Deck();
        players = new Hand[PLAYERS];
        for(int i=0;i<PLAYERS;i++)
        {
            players[i] = new Hand(i,deck.TOTAL_CARDS/PLAYERS);
        }

        tricks = new Trick[deck.TOTAL_CARDS/PLAYERS];


    }
    public int getNumberOfTricks()
    {
        return numberOfTricks;
    }
    public boolean getHearts()
    {
        return hearts;
    }
    public boolean getQueenOfSpades()
    {
        return queenOfSpades;
    }

    public void playAGame()
    {
        int cardsLeft = deck.TOTAL_CARDS % PLAYERS;
        deck.shuffle();
        for(int i=0;i<deck.TOTAL_CARDS/PLAYERS;i++)
        {
            for(int j=0;j<PLAYERS;j++)
            {
                players[j].addCard(deck.dealCard());
            }
        }

        for(int i=0;i<PLAYERS;i++)
        {
            players[i].sort();
            players[i].setShortest();
        }

        int lowestClub = 15;
        int playerNum = -1;
        for(int i=0;i<PLAYERS;i++)
        {

            System.out.println("\tPlayer "+players[i].NUM+" shortest="+players[i].getShortest());
            for(int j=0;j<players[i].currentSize;j++)
            {
                players[i].cards[j].display();
                System.out.println();
                if (players[i].cards[j].getSuit() == 0) {
                    if (players[i].cards[j].getNum() < lowestClub) {
                        playerNum = i;
                        lowestClub = players[i].cards[j].getNum();
                    }
                }
            }
        }




        for(int i=0;i<tricks.length;i++)
        {
            System.out.println("Round " + (i+1));
            tricks[i] = new Trick(PLAYERS);
            numberOfTricks++;
            int index = playerNum;

            for(int j=0;j<=players.length;j++)
            {
                Card card = players[j].playACard(this, tricks[i]);
                System.out.print(players[j].NUM+":");
                card.display();

                tricks[i].addCard(card);
                tricks[i].update(index,card);
                index++;
                if(index==PLAYERS)
                    index=0;
            }

            if (i == 0) {
                for (int j = 0; j < cardsLeft; j++ ) {
                    Card card = deck.dealCard();
                    tricks[i].addCard(card);
                    System.out.print("Undelt Card " + index + "     ");
                    card.display();
                    updateHeartsAndQueen(card);
                }
            }
            playerNum = tricks[i].getWinner();

        }

        for(int i=0;i<players.length;i++)
        {
            System.out.print("player "+players[i].NUM+" score "+computerPoints(players[i].NUM));
        }
    }

    public void updateHeartsAndQueen(Card card)
    {
        if(card.getSuit()==3)
        {
            if(hearts==false)
            {
                System.out.println("Hearts is now broken");
            }
            hearts = true;
        }
        else if(card.getSuit()==2&&card.getNum()==12)
            queenOfSpades = true;
    }
    public int computerPoints(int playerNum)
    {
        int score = 0;
        for(int i=0;i<tricks.length;i++)
        {
            if(playerNum == tricks[i].winner)
            {
                for(int j=0;j<tricks[i].currentSize;j++)
                {
                    if(tricks[i].cards[j].getSuit()==3)
                        score+=1;
                    if(tricks[i].cards[j].getSuit()==2&&tricks[i].cards[j].getNum()==12)
                        score+=13;
                }
            }
        }
        return score;
    }
}
package hearts;

public class Trick extends GroupOfCards {
    int winner;
    Card winningCard;
    boolean hearts=false;
    boolean queen=false;

    Trick(int players)
    {
        super(players);
    }
    public int getWinner()
    {
        return winner;
    }
    public Card getWinningCard()
    {
        return  winningCard;
    }
    public boolean getHearts()
    {
        return hearts;
    }
    public boolean getQueen()
    {
        return queen;
    }

    public void update(int playerNum,Card card)
    {
        if(isWinner(card))
        {
            winner = playerNum;
            winningCard=card;
            if(card.getSuit()==2)
                hearts = true;
            if(card.getSuit()==3)
                queen = true;
        }
    }

    public boolean isWinner(Card card)
    {
        if(winningCard!=null&&card.getSuit()!=winningCard.getSuit()||card.getNum()<winningCard.getNum())
        {
            return false;
        }
        else
            return true;
    }

}
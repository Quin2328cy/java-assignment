package hearts;

public class Hand  extends GroupOfCards{
    final int NUM;
    private int shortest = 0;
    Hand(int playerNum,int numberOfCards) {
        super(numberOfCards);
        NUM = playerNum;

    }

    public void sort()
    {
        int minIndex = 0;
        for(int i = 0 ; i < cards.length ; i ++){

            minIndex = i;
            for(int j = i +1; j < cards.length; j ++){
                if((cards.length*cards[j].getSuit()+cards[j].getNum())< (cards.length*cards[minIndex].getSuit()+cards[minIndex].getNum()))
                    minIndex = j;
            }
            if( i < minIndex){
                Card temp = cards[i];
                cards[i] = cards[minIndex];
                cards[minIndex] = temp;
            }
        }
    }

    public void setShortest()
    {
        int clubNum = 0;
        int diamondsNum = 0;
        int spadesNum = 0;
        int shortNum = 0;
        for(int i=0;i<cards.length;i++)
        {
            if(cards[i].getSuit()==0)
                clubNum++;
            else if(cards[i].getSuit()==1)
                diamondsNum++;
            else if(cards[i].getSuit()==2)
                spadesNum++;


        }
        shortNum = clubNum;

        if(diamondsNum<=clubNum)
        {
            this.shortest = 1;
            shortNum = diamondsNum;
        }

        if(spadesNum<=shortNum&&(find(12,2)==-1||find(13,2)==-1||find(14,2)==-1))
            this.shortest = 2;
    }
    public int getShortest()
    {
        return shortest;
    }

    public Card playACard(Game game,Trick trick)
    {
        int index = -1;
        int suit = trick.winningCard.getSuit();
        if ((trick.getCurrentSize() == game.PLAYERS - 1)
                && !trick.getHearts() && !trick.getQueen()
                && (index = findLastHigh(suit)) >= 0);
        else if ((index = findHighestBelow(trick.winningCard)) >= 0);
        else if ((index = findMiddleHigh(game, suit)) >= 0);
        else if ((index = find(12, 3)) >= 0); // queen of Spades
        else if ((index = find(14, 3)) >= 0); // Ace of Spades
        else if ((index = find(13, 3)) >= 0); // King of Spades
        else if ((index = findHighest(2)) >= 0); // heart
        else
        {
            index = findHighest();
        }

        removeCard(index);
        trick.update(NUM, cards[index]);
        game.updateHeartsAndQueen(cards[index]);

        return cards[index];
    }

    public int findLowest(int suit)
    {
        int index = -1;
        int minNum = 0;
        for(int i=0;i<currentSize;i++)
        {
            if (suit == cards[i].getSuit()&&minNum>=cards[i].getNum())
            {
                minNum=cards[i].getNum();
                index =i;
            }
        }
        return index;
    }
    public int findLowest(Game game)
    {
        int minNum = 0;
        int index = -1;
        int heartsCount = 0;
        for(int i=0;i<currentSize;i++)
        {
            if(game.getHearts()==false && cards[i].getSuit()==3)
            {
                heartsCount++;
                continue;
            }


            if(minNum>=cards[i].getNum())
            {
                minNum = cards[i].getNum();
                index = i;
            }
        }

        if(heartsCount==currentSize)
            index= -1;

        return index;
    }

    public int findCount(int suit)
    {
        return 0;
    }
    public int find(int num,int suit)
    {
        int index = -1;
        for(int i=0;i<currentSize;i++)
        {
            if(cards[i].getNum()==num&&cards[i].getSuit()==suit)
            {
                index = i;
                break;
            }

        }
        return index;
    }
    public int findHighest(int suit)
    {
        int index = -1;
        int maxNum = 0;
        for(int i=0;i<currentSize;i++)
        {
            if (suit == cards[i].getSuit()&&maxNum<=cards[i].getNum())
            {
                maxNum=cards[i].getNum();
                index =i;
            }
        }
        return index;

    }

    public int findHighest()
    {
        return 0;
    }

    public int findLastHigh(int suit)
    {
        int index = -1;
        int max = 0;
        for(int i=0;i<currentSize;i++)
        {
            if(cards[i].getSuit() ==suit && max<=cards[i].getNum())
            {
                max = cards[i].getNum();
                index = i;
            }
        }

        if(index>-1&&cards[index].getSuit()==2&&cards[index].getNum()==12)
        {
            max = 0;
            for(int i=0;i<currentSize;i++)
            {
                if(cards[i].getSuit()==2&&cards[i].getNum()<12&&max>=cards[i].getNum())
                {
                    max = cards[i].getNum();
                    index = i;
                }
            }
        }

        return index;
    }

    public int findHighestBelow(Card winningCard)
    {
        int index = -1;
        for(int i=0;i<currentSize;i++)
        {
            if(winningCard.getSuit() ==cards[i].getSuit()&&cards[i].getNum()<winningCard.getNum())
            {
                index = i;
                break;
            }
        }
        return index;
    }
    public int findMiddleHigh(Game game,int suit)
    {
        int index  =-1;
        int max=0;
        for(int i=0;i<currentSize;i++)
        {
            if(suit == cards[i].getSuit()&&max<=cards[i].getNum())
            {
                max = cards[i].getNum();
                index = i;
            }
        }
        if(suit==12&&game.getQueenOfSpades()==false)
        {
            for(int i=0;i<currentSize;i++)
            {
                if(cards[i].getSuit()==12&&cards[i].getNum()<=11)
                {
                    index = i;
                    break;
                }
            }
        }

        return index;
    }




}

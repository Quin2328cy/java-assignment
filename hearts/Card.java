package hearts;


public class Card {
    private  int num;//2-14
    private int suit;//0-3;

    Card(int num,int suit)
    {
        this.num = num;
        this.suit = suit;
    }

    public int getNum()
    {
        return num;
    }
    public int getSuit()
    {
        return suit;
    }

    public void display()
    {
        String strSuit="";
        switch(suit)
        {
            case 0:
                strSuit="clubs";
                break;
            case 1:
                strSuit = "diamonds";
                break;
            case 2:
                strSuit = "hearts";
                break;
            case 3:
                strSuit="spades";
                break;
        }

        String strNum="";
        switch(num)
        {
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
                strNum = num+"";
                break;
            case 11:
                strNum = "J";
                break;
            case 12:
                strNum = "Q";
                break;
            case 13:
                strNum = "K";
                break;
            case 14:
                strNum = "A";
                break;
        }

        System.out.print(strNum+" of "+strSuit);
    }

}


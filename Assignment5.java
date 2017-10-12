import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.Vector;

public class Assignment5 {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        System.out.println("============checkout test");
        Checkout checkout = new Checkout();

        checkout.enterItem(new Candy("Peanut Butter Fudge", 2.25, 399));
        checkout.enterItem(new IceCream("Vanilla Ice Cream", 105));
        checkout.enterItem(new Sundae("Choc. Chip Ice Cream", 145, "Hot Fudge", 50));
        checkout.enterItem(new Cookie("Oatmeal Raisin Cookies", 4, 399));

        System.out.println("\nNumber of items: " + checkout.numberOfItems() + "\n");
        System.out.println("\nTotal cost: " + checkout.totalCost() + "\n");
        System.out.println("\nTotal tax: " + checkout.totalTax() + "\n");
        System.out.println("\nCost + Tax: " + (checkout.totalCost() + checkout.totalTax()) + "\n");
        System.out.println(checkout);

        checkout.clear();

        checkout.enterItem(new IceCream("Strawberry Ice Cream", 145));
        checkout.enterItem(new Sundae("Vanilla Ice Cream", 105, "Caramel", 50));
        checkout.enterItem(new Candy("Gummy Worms", 1.33, 89));
        checkout.enterItem(new Cookie("Chocolate Chip Cookies", 4, 399));
        checkout.enterItem(new Candy("Salt Water Taffy", 1.5, 209));
        checkout.enterItem(new Candy("Candy Corn", 3.0, 109));

        System.out.println("\nNumber of items: " + checkout.numberOfItems() + "\n");
        System.out.println("\nTotal cost: " + checkout.totalCost() + "\n");
        System.out.println("\nTotal tax: " + checkout.totalTax() + "\n");
        System.out.println("\nCost + Tax: " + (checkout.totalCost() + checkout.totalTax()) + "\n");
        System.out.println(checkout);



        //matrix test
        System.out.println();System.out.println();
        System.out.println("============checkout test");
        int[][] arr ={{1,2,3},{4,5,6},{7,8,9}};

        Matrix matrix = new Matrix();
        LinkedList<Integer> ll =  matrix.spiralOrder(arr);
        for(int i=0;i<ll.size();i++)
        {
            System.out.print(ll.get(i)+" ");
        }


    }

}

class DessertShoppe
{
    public static final String SHOPPE_NAME="M & M Dessert Shoppe";
    public static final double TAX_RATE = 0.02;
    public static final int MAXIMUM=25;
    public static final int NAME_WIDTH=40;
    public static final int COST_WIDTH=6;

    public static String cents2Dollars(int cents)
    {
        DecimalFormat df = new DecimalFormat("#.00");
        return df.format(cents/100.0);
    }

}

abstract class DessertItem
{
    protected String name;
    public DessertItem()
    {

    }
    public DessertItem(String name)
    {
        this.name = name;
    }

    public final String getName()
    {
        return this.name;
    }

    public abstract int getCost();
    public abstract String getBuyInfo();
}

class Candy extends DessertItem
{
    private double weight;
    private int price;//cents

    public Candy(String name,double weight,int price)
    {
        super(name);
        this.weight = weight;
        this.price = price;
    }



    @Override
    public int getCost() {
        // TODO Auto-generated method stub
        DecimalFormat df = new DecimalFormat("#0");
        int cost = Integer.parseInt(df.format(weight*price));
        return cost;
    }



    @Override
    public String getBuyInfo() {
        // TODO Auto-generated method stub
        DecimalFormat df = new DecimalFormat("#.00");
        String buyInfo = df.format(weight)+" lbs. @ "+DessertShoppe.cents2Dollars(price)+"/lb.";
        return buyInfo;
    }

}



class Cookie extends DessertItem
{
    private int number;
    private int price;
    public Cookie(String name,int number,int price)
    {
        super(name);
        this.number = number;
        this.price = price;

    }
    @Override
    public int getCost() {
        // TODO Auto-generated method stub
        float unitPrice = price/12.0f;
        return Math.round(number*unitPrice);
    }
    @Override
    public String getBuyInfo() {
        // TODO Auto-generated method stub
        String buyInfo = number+" @ "+DessertShoppe.cents2Dollars(price)+"/dz.";
        return buyInfo;
    }

}

class IceCream extends DessertItem
{
    private int price;
    public IceCream(String name,int price)
    {
        super(name);
        this.price = price;
    }
    @Override
    public int getCost() {
        // TODO Auto-generated method stub
        return price;
    }
    @Override
    public String getBuyInfo() {
        // TODO Auto-generated method stub
        return "";
    }

}
class Sundae extends IceCream
{
    public Sundae(String icName,int icPrice,String sName,int sPrice) {
        super(sName+"sundae with "+icName,icPrice +sPrice);
        // TODO Auto-generated constructor stub
    }

}


class Checkout
{

    private Vector<DessertItem> listDessertItem=null;
    public Checkout()
    {
        listDessertItem = new Vector<DessertItem>();
    }
    public int numberOfItems()
    {
        if(listDessertItem==null)
            return 0;
        return listDessertItem.size();
    }

    public void enterItem(DessertItem item)
    {
		if(listDessertItem==null)
		{
			listDessertItem = new Vector<DessertItem>();
		}
        listDessertItem.add(item);
    }
    public void clear()
    {
        listDessertItem.clear();
    }

    public int totalCost()
    {
        int totalCost = 0;
        for(DessertItem di:listDessertItem)
        {
            totalCost+=di.getCost();
        }


        return totalCost;
    }
    public int totalTax()
    {
        double tax = 0;
        tax =totalCost()*DessertShoppe.TAX_RATE;

        return  Math.round((float)tax);
    }
    private String formatName(String name)
    {
        if(name.length()<=DessertShoppe.MAXIMUM)
        {
            return String.format("%-"+DessertShoppe.NAME_WIDTH+"s", name);
        }
        String formatName = "";

        StringBuffer sbf = new StringBuffer(name);

        for(int i=0,j=1;i<sbf.length()-1;i++,j++)
        {
            if(j%(DessertShoppe.MAXIMUM+1)==0)
            {
                if(sbf.charAt(i)!=' ')
                {
                    int index = sbf.lastIndexOf(" ", i);
                    i = index;
                }
                sbf.replace(i, i+1,"\n");
                j=1;
            }

        }
        String arrStr[] = sbf.toString().split("\n");
        for(int i=0;i<arrStr.length;i++)
        {
            formatName += String.format("%-"+DessertShoppe.NAME_WIDTH+"s", arrStr[i]);
            if(i<arrStr.length-1)
                formatName+="\n";
        }
        return formatName;
    }

    private String formatCost(int cost)
    {
        String formatCost = String.format("%"+DessertShoppe.COST_WIDTH+"s", DessertShoppe.cents2Dollars(cost));
        return formatCost;
    }

    public java.lang.String toString()
    {
        StringBuffer receipt = new StringBuffer();
        receipt.append("\t\t"+DessertShoppe.SHOPPE_NAME+"\n");
        receipt.append("\t\t--------------------\n\n");
        for(DessertItem di : listDessertItem)
        {
            receipt.append(di.getBuyInfo()==""?"":di.getBuyInfo()+"\n");
            String name = formatName(di.name);
            String cost = formatCost(di.getCost());
            receipt.append(name+cost+"\n");
        }
        receipt.append("\n");
        receipt.append(formatName("Tax")+formatCost(totalTax()));

        receipt.append("\n");
        receipt.append(formatName("Total Tax")+formatCost(totalCost()+totalTax()));

        return receipt.toString();
    }
}


class Matrix {

    public LinkedList<Integer> spiralOrder(int[][] matrix) {

        LinkedList<Integer> linkedList = new LinkedList<Integer>();

        if (matrix == null || matrix.length == 0) {
            return linkedList;
        }

        //left, right, up, down, 4 boundaries
        int left = 0;
        int right = matrix[0].length - 1;
        int top = 0;
        int bottom = matrix.length - 1;

        int i;
        while (true) {

            //up, from left to the right
            for (i = left; i <= right; i++) {
                linkedList.add(matrix[top][i]);
            }
            if (++top > bottom) {
                break;
            }

            //right, from up to down
            for (i = top; i <= bottom; i++) {
                linkedList.add(matrix[i][right]);
            }
            if (left > --right) {
                break;
            }

            //down , from right to left
            for (i = right; i >= left; i--) {
                linkedList.add(matrix[bottom][i]);
            }
            if (top > --bottom) {
                break;
            }

            //left , from down to up
            for (i = bottom; i >= top; i--) {
                linkedList.add(matrix[i][left]);
            }
            if (++left > right) {
                break;
            }

        }

        return linkedList;
    }
}


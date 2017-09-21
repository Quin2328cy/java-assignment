import java.util.Scanner;


public class solution2 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        //question 1
        double hours = 0;
        System.out.println("1.Calculate the salary of the employee");
        System.out.println("Please input the working hours of employee:");
        hours = scan.nextDouble();
        Employee e = new Employee();
        System.out.println("The salary is:"+e.employeeSalary(hours));

        //question 2
        System.out.println("2.Print the sum of the digits");
        System.out.println("Please input a positive integer:");
        int input = scan.nextInt();
        Digit d = new Digit();
        System.out.println("The sum of digits is:"+d.addDigits(input));

        //question 3
        System.out.println("3.print all perfect number between 1 and n");
        System.out.println("Please input number n:");
        int n=scan.nextInt();
        PerfectNumber pn = new PerfectNumber();
        pn.printPerfectNumbers(n);

        //question 4
        System.out.println("4.Pizza test");
        Pizza pizza = new Pizza();
        pizza.type="Bacon";
        pizza.unitPrice=59;
        pizza.points=10;
        pizza.showPizzaInfo();

        Pizza pizzaB = new Pizza("fruit",29,5);
        pizzaB.showPizzaInfo();

        //question 5
        System.out.println("5.Customer");
        Pizza p = new Pizza("fruit",29,2,5);
        Customer cus = new Customer(p,"eric");
        cus.showCustomerOrder();





        //question 6
        System.out.println("6.Print an isosceles right angled triangle made of asterisks");
        System.out.println("Please input a positive integer");
        n = scan.nextInt();
        Star star = new Star();
        star.printIsoscelesTriangle(n);


    }



}


class Employee
{
    double baseSalary = 15;
    public double employeeSalary(double hours)
    {

        double salary=0;
        if(hours>0&&hours<=36)
        {
            salary=hours*baseSalary;
        }
        else if(hours>36&&hours<=36+5)
        {
            salary=36*baseSalary+(hours-36)*baseSalary*1.5;
        }
        else if(hours>36+5&&hours<=48)
        {
            salary=36*baseSalary+5*baseSalary*1.5+(hours-36-5)*baseSalary*2;
        }
        else
        {
            salary=36*baseSalary+5*baseSalary*1.5+(48-36-5)*baseSalary*2;
        }
        return salary;
    }
}

class Digit
{
    public int addDigits(int input)
    {
        int sum = 0;
        int result = 0;
        do
        {
            result = 0;
            for(int num = input;num>0;num=num/10)
            {
                int d=0;
                d = num%10;
                sum=sum+d;
                result=result+d;
            }
            input =result;

        }while(result>9);
        return sum;
    }
}

class PerfectNumber
{
    public void printPerfectNumbers(int n)
    {
        for(int i=2;i<=n;i++)
        {
            int sum=0;
            for(int j=1;j<i;j++)
            {
                if(i%j==0)
                    sum=sum+j;
            }
            if(sum==i)
                System.out.println(i+"is a perfect number.");
        }
    }
}

class Pizza
{
    String type;
    float unitPrice;
    int num;
    int points;
    public Pizza()
    {

    }
    public Pizza(String t,float up,int p)
    {
        type = t;
        unitPrice = up;
        points = p;
    }
    public Pizza(String t, float up, int n, int p)
    {
        type =t;
        unitPrice=up;
        num=n;
        points=p;
    }

    public float getTotalPrice()
    {
        return unitPrice*num;
    }


    public void showPizzaInfo()
    {
        System.out.println("The type of Pizza"+type+",The unit price"+unitPrice+", Points"+points);
    }
}

class Customer
{
    Pizza pizza;
    String name;
    float totalPrice;
    public Customer()
    {

    }
    public Customer(Pizza pizza ,String name)
    {
        this.pizza = pizza;
        this.name = name;
        this.totalPrice = pizza.getTotalPrice();
    }

    public void showCustomerOrder()
    {
        System.out.println("The name of customer is "+name);
        System.out.println("The type of pizza is "+pizza.type+"pizza");
        System.out.println("The unit price is"+pizza.unitPrice);
        System.out.println("The number of pizza is "+pizza.num);
        System.out.println("total price is "+totalPrice);

    }
}

class Star
{
    public void printIsoscelesTriangle(int n)
    {
        for(int i=1;i<=n;i++){
            for(int j=1;j<=i;j++){
                if(i>2&&i<n&&j>1&&j<i)
                    System.out.print(" ");
                else
                    System.out.print("*");
            }
            System.out.println();
        }
    }
}

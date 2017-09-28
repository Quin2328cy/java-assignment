/**
 *
 * @author xiaohan Qiu
 *
 */

import java.text.DecimalFormat;
import java.util.*;
public class Assignment3 {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Scanner scan = new Scanner(System.in);
        //Remove vowels
        System.out.println("Remove Vowels from String:");
        System.out.println("Please Input a String:");
        String input = scan.next();
        RemoveVowels rv = new RemoveVowels();
        System.out.println("After Removing Vowels:"+rv.removeVowelsFromString(input));


        //Anagrams
        System.out.println("Check If Two Strings are Anagrams or Not");
        System.out.println("Please Input the First String:");
        String str1 = scan.next();
        System.out.println("Please Input the Second String:");
        String str2 = scan.next();
        Anagrams an = new Anagrams();
        boolean br = an.checkIfTwoStringsAreAnagrams(str1,str2);
        String strb =br?" are Anagrams":" are not Anagrams";

        System.out.println(str1+"and"+str2+strb);

        //calculator
        System.out.println("Calculator Test:");
        System.out.println("Please Input two Numbers:");
        double x = scan.nextDouble();
        double y = scan.nextDouble();
        System.out.println(x+"+"+y+"="+Calculator.add(x, y));
        System.out.println(x+"-"+y+"="+Calculator.subtract(x, y));
        System.out.println(x+"*"+y+"="+Calculator.multiply(x, y));
        System.out.println(x+"/"+y+"="+Calculator.divide(x, y));
        System.out.println(x+"^2"+"="+Calculator.square(x));
        System.out.println(x+"^3"+"="+Calculator.cute(x));
        System.out.println("The square root of"+x+"="+Calculator.sqrt(x));
        System.out.println("Please Input Celsius Degree:");
        double cc = scan.nextDouble();
        double fc =Calculator.celsiusToFahrenheit(cc);
        System.out.println(cc+"Celsius to Fahrenheit:"+fc);

        System.out.println("Please Input Fahrenheit Degree:");
        fc = scan.nextDouble();
        cc =Calculator.fahrenheitToCelsius(fc);
        System.out.println(fc+"Fahrenheit to Celsius"+cc);

        System.out.println("Please Input Feet:");
        double feet  = scan.nextDouble();
        double inches =Calculator.feetToInches(feet);
        System.out.println(feet+"Feet to Inches:"+inches);

        System.out.println("Please Input Inches:");
        inches  = scan.nextDouble();
        feet =Calculator.inchesToFeet(inches);
        System.out.println(inches+"Inches to Feet:"+feet);

        System.out.println("solve a quadratic equation");
        System.out.println("Please Input the Value of a");
        double a = scan.nextDouble();
        System.out.println("Please Input the Value of b");
        double b = scan.nextDouble();
        System.out.println("Please Input the Value of c");
        double c = scan.nextDouble();

        double [] result= new double[2];
        result = Calculator.quadraticEquation(a, b, c);
        double deta = b * b - 4 * a * c;
        if (deta>0)
        {
            System.out.println("There are two unequal real roots: x1="+result[0]+",x2="+result[1]);

        }
        else if(deta==0)
        {
            System.out.println("There are two equal real roots: x1="+result[0]+",x2="+result[1]);
        }
        else
        {
            System.out.println("There are two imaginary roots:");
            System.out.println("x1 = "+result[0]+" + "+result[1]+"i");
            System.out.println("x2 = "+result[0]+" - "+result[1]+"i");
        }

    }

}

/**
 * find error 1
 * @author xiaohan
 *
 */
class Book {
    private int size;
    private int price;
    private String name;

    public Book(int size, int price) {
        this.size = size;
        this.price = price;
    }

    public Book(int size, int price, String name) {
        super();
        this.size = size;
        this.price = price;
        this.name = name;
    }

    public void setName(String name) {
        this.name =  name;
    }
    public String getName()
    {
        return this.name;
    }
    public void setSize(int size)
    {
        this.size = size;
    }
    public int getSize()
    {
        return this.size;
    }

    public void setPrice(int price)
    {
        this.price =price;
    }
    public int getPrice()
    {
        return this.price;
    }
}


/**
 * find error 2
 * @author xiaohan
 *
 */
class Clock {
    private String time;

    public String getTime() {
        return time;
    }

    public void setTime(String t) {
        time = t;
    }
}

/**
 * Remove vowels
 * @author xiaohan
 *
 */
class RemoveVowels
{
    public String removeVowelsFromString(String input)
    {
        String[] vowels = {"a","e","i","o","u"};
        String removedStr = input;
        for(String v :vowels)
        {

            removedStr=removedStr.replace(v,"").replace(v.toUpperCase(), "");


        }

        return removedStr;
    }

}

class Anagrams
{
    public boolean checkIfTwoStringsAreAnagrams(String s1, String s2)
    {
        if(s1.length()!=s2.length())
            return false;


        if (s1.equalsIgnoreCase(s2))
            return false;

        s1 = s1.toLowerCase();
        s2 = s2.toLowerCase();

        char[] arr1 = s1.toCharArray();//Convert a string to a character array
        char[] arr2 = s2.toCharArray();

        char temp1,temp2;
        for(int i=0;i<arr1.length-1;i++){
            for(int j=0;j<arr1.length-i-1;j++){
                if(arr1[j+1]<arr1[j]){
                    temp1 = arr1[j];
                    arr1[j] = arr1[j+1];
                    arr1[j+1] = temp1;
                }
                if(arr2[j+1]<arr2[j]){
                    temp2 = arr2[j];
                    arr2[j] = arr2[j+1];
                    arr2[j+1] = temp2;
                }
            }
        }



        s1 = new String(arr1);
        s2 = new String(arr2);
        if(s1.equals(s2))
            return true;
        else
            return false;
    }
}

class Calculator
{
    static double add(double a,double b)
    {
        return a+b;
    }
    static double subtract(double a,double b)
    {
        return a-b;
    }
    static double multiply(double a,double b)
    {
        return a*b;
    }
    static double divide(double a,double b)
    {
        if(b==0)
            return 0;
        else
            return a/b;
    }

    static double square(double a)
    {
        return Math.pow(a, 2);
    }
    static double cute(double a)
    {
        return Math.pow(a, 3);
    }
    static double sqrt(double a)
    {
        return Math.sqrt(a);
    }

    static double fahrenheitToCelsius(double f)
    {
        double c = (f-32)*(5.0/9);

        DecimalFormat df = new DecimalFormat("#.00");

        return Double.parseDouble(df.format(c));
    }
    static double celsiusToFahrenheit(double c)
    {
        double f = c*9/5+32;
        String strf = String.format("%.2f", f);
        return Double.parseDouble(strf);
    }
    static double feetToInches(double f)
    {
        return f*12;
    }
    static double inchesToFeet(double i)
    {
        return i/12;
    }

    static double[] quadraticEquation(double a, double b, double c) {
        double deta = b * b - 4 * a * c;
        double[] result = new double[2];
        if (deta >= 0) {
            double x1 = (-b + Math.sqrt(deta)) / (2 * a);
            double x2 = (-b - Math.sqrt(deta)) / (2 * a);
            result[0] = x1;
            result[1] = x2;

        } else {
            double real = -b / (2 * a);
            DecimalFormat df = new DecimalFormat("#.00");
            double image = Math.sqrt(-deta) / (2 * a);
            String strImage = df.format(image);

            result[0] = real;
            result[1] = Double.parseDouble(strImage);
        }
        return result;
    }



}



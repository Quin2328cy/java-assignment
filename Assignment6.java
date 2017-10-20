import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.io.Serializable;
import java.util.*;

import javax.sound.midi.Receiver;


public class Assignment6 {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        //1.my exception test
        try
        {
            showArray();
        }
        catch(IndexOutOfBoundsException be)
        {
            System.out.println(be.getMessage());
        }


        //2.modify code test
        File file =new File("c://file.txt");
        try
        {
            parse(file);
        }
        catch(IOException ioe)
        {
            System.out.println(ioe.getMessage());
        }

        //3.ATM test
        System.out.println("\t\t\t===============Welcome to the ATM===============\n\n");
        Atm atm = null;
        atm = readAtmData();
        if (atm==null)
            atm = new Atm();
        atm.start();
    }

    //4.Read ATM data from the file
    private static Atm readAtmData()
    {
        Atm atm = null;
        try {
            ObjectInputStream is = new ObjectInputStream(new FileInputStream("C:/atm.txt"));
            atm = (Atm) is.readObject();// read ATM data from the stream
            is.close();

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return atm;
    }

    public static void showArray()
    {
        int [] arr = {3,5,9,2,15};
        for(int i=0;i<=arr.length;i++)
        {
            if(i<0||i>=arr.length)
            {
                System.out.println();
                throw new MyIndexOutOfBoundException(0, arr.length-1, i);
            }
            else
            {
                System.out.print(arr[i]+" ");
            }
        }
        System.out.println();
    }


    public static void parse(File file) throws IOException {
        RandomAccessFile input = null;
        String line = null;

        try {
            input = new RandomAccessFile(file,"r");

            while ((line = input.readLine()) != null) {
                System.out.println(line);
            }
            return;
        } finally {
            if (input != null) {
                input.close();
            }
        }
    }


}

class MyIndexOutOfBoundException extends IndexOutOfBoundsException
{
    int lowerBound;
    int upperBound;
    int index;

    MyIndexOutOfBoundException(int lowerBound,int upperBound,int index)
    {
        String msg = "Error Message: Index: "+index+", but Lower bound: "+lowerBound+", Upper bound: "+upperBound;
        throw new MyIndexOutOfBoundException(msg);
    }
    MyIndexOutOfBoundException(String msg)
    {
        super(msg);
    }

}


class User implements  Serializable
{
    String name;
    int age;
    String address;
    String phoneNumber;
    String bankAccountNumber;
    double balance;
    String password;
    LinkedList<String> recentTransaction;


    public User(String name,int age,String address,String phoneNumber,String bankAccountNumber,String password)
    {
        this.name = name;
        this.age = age;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.bankAccountNumber = bankAccountNumber;
        this.password = password;
        this.balance = 0;
        recentTransaction = new LinkedList<String>();

    }
}


class Atm implements  Serializable
{
    double availableAmountMachine;
    double transactionFee;
    Vector<User> userData;
    User loginUser;

    Atm()
    {
        availableAmountMachine=0;
        transactionFee=0;
        userData = new Vector<User>();
    }
    private boolean existsBankAccountNumber(String ban)
    {
        for(User user : userData)
        {
            if (user.bankAccountNumber.equalsIgnoreCase(ban))
                return true;

        }
        return false;
    }

    private boolean existsPhoneNumber(String pn)
    {
        for(User user : userData)
        {
            if (user.phoneNumber.equalsIgnoreCase(pn))
                return true;

        }
        return false;
    }
    public void createUserAccount()
    {
        Scanner scan = new Scanner(System.in);


        System.out.println("Please input user's information:");
        System.out.print("name:");
        String name = scan.next();
        System.out.print("age:");
        int age = scan.nextInt();
        System.out.print("address:");
        String address = scan.next();
        String phoneNumber;
        while(true)
        {
            System.out.print("phone number:");
            phoneNumber = scan.next();
            if ( existsPhoneNumber(phoneNumber)==true)
            {
                System.out.println("phone number has existed, re-enter or not: (y/n)");
                String yn = scan.next();
                if(yn.equalsIgnoreCase("y"))
                {
                    continue;
                }
                else
                {
                    return;
                }
            }
            else
            {
                break;
            }
        }

        String bankAccountNumber;
        while(true)
        {
            System.out.print("bank account:");
            bankAccountNumber = scan.next();
            if ( existsBankAccountNumber(bankAccountNumber)==true)
            {
                System.out.println("bank account has existed, re-enter or not: (y/n)");
                String yn = scan.next();
                if(yn.equalsIgnoreCase("y"))
                {
                    continue;
                }
                else
                {
                    return;
                }
            }
            else
            {
                break;
            }
        }

        System.out.print("password:");
        String password = scan.next();

        User user = new User(name,age,address,phoneNumber,bankAccountNumber,password);
        userData.add(user);

        System.out.println("You have created the user account successfully!");
    }

    public boolean login()
    {
        Scanner scan = new Scanner(System.in);
        System.out.println("Please input the information to sign in:");
        System.out.print("phone number:");
        String phone = scan.next();
        System.out.print("password:");
        String pwd = scan.next();
        boolean b=false;
        for(User user:userData)
        {
            if(user.password.equals(pwd)&&user.phoneNumber.equals(phone))
            {
                loginUser = user;
                b= true;
                break;
            }

        }

        return b;

    }
    public void forgotPwd()
    {
        Scanner scan = new Scanner(System.in);
        System.out.println("reset the password:");
        System.out.print("name:");
        String name = scan.next();
        System.out.print("phone number:");
        String phone = scan.next();

        for(User user:userData)
        {
            if(user.password.equals(name)&&user.phoneNumber.equals(phone))
            {
                System.out.print("verified, please input the password:");
                String pwd = scan.next();
                user.password = pwd;
                return;
            }

        }
        System.out.println("wrong verified information!");
    }

    public void changePassword()
    {
        Scanner scan = new Scanner(System.in);
        System.out.print("Please input the original password:");
        String pwd = scan.next();
        if(loginUser.password.equals(pwd))
        {
            System.out.print("Please input the new password:");
            pwd = scan.next();
            loginUser.password = pwd;
            System.out.println("password reset completed!");
        }
        else
        {
            System.out.println("The original password is not right!");
        }
    }

    private void withDrawal()
    {
        Scanner scan = new Scanner(System.in);
        System.out.print("Please input the amount of withdrawal money:");
        double amount = scan.nextDouble();
        if(amount>availableAmountMachine)
        {
            System.out.println("The withdrawal amount exceeds the balance");
            return;
        }
        if(amount>loginUser.balance)
        {
            System.out.println("Your account balance is insufficient！");
            return;
        }

        loginUser.balance-=amount;
        availableAmountMachine-=amount;
        addrecentTransaction("withdrawal-"+amount);
        System.out.println("Withdrawal is completed!");
    }

    private void deposit()
    {
        Scanner scan = new Scanner(System.in);
        System.out.print("Please input the amount of deposit money:");
        double amount = scan.nextDouble();


        loginUser.balance+=amount;
        availableAmountMachine+=amount;
        addrecentTransaction("deposit-"+amount);
        System.out.println("Deposit is completed!");
    }
    private void addrecentTransaction(String tranInfo)
    {
        if(loginUser.recentTransaction==null)
            loginUser.recentTransaction = new LinkedList<String>();

        loginUser.recentTransaction.add(tranInfo);
    }
    private void showRecentTransaction()
    {
        if(loginUser.recentTransaction.size()==0)
        {
            System.out.println("No transaction history");
            return;
        }
        int count = 1;
        for(int i = loginUser.recentTransaction.size()-1;i>=0;i--)
        {
            System.out.println(loginUser.recentTransaction.get(i));
            if(count>=10)
                return;
            count++;
        }
    }
    public void menu()
    {
        Scanner scan  =new Scanner(System.in);
        while(true)
        {
            System.out.println("===============1.withdrawal \t2.deposit \t3. recent transaction \t4. reset password \t5. exit===============");
            System.out.print("Please input the option:");
            int input = scan.nextInt();
            if(input==1)
            {
                withDrawal();
            }
            else if(input==2)
            {
                deposit();
            }
            else if(input==3)
            {
                showRecentTransaction();
            }
            else if(input==4)
            {
                changePassword();
            }
            else
            {
                System.out.println("sign out!");
                loginUser = null;
                break;
            }
        }

    }
    public void start()
    {
        Scanner scan  =new Scanner(System.in);
        while(true)
        {
            System.out.println("===============1. create new user \t2. sign in \t3. Retrieve the password\t4.exit===============");
            System.out.print("Please input the option:");
            int input = scan.nextInt();
            if(input==1)
            {
                createUserAccount();
            }
            else if(input ==2)
            {
                if(login())
                {
                    menu();
                }
                else
                {
                    System.out.println("wrong phone number or password!");
                }
            }
            else if(input ==3)
            {
                forgotPwd();
            }
            else
            {
                saveData(this);
                System.out.println("System exit!");
                break;
            }
        }


    }

    private boolean saveData(Atm atm)
    {
        boolean r = false;
        try {
            File file = new File("C:/atm.txt");
            if(file.exists()==false)
                file.createNewFile();

            ObjectOutputStream os = new ObjectOutputStream(
                    new FileOutputStream(file));
            os.writeObject(atm);// write the user object into the file
            os.close();
            r= true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return r;
    }



}

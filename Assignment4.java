import java.util.*;
public class Assignment4 {
    public static void main(String[]args){
        // TODO Auto-generated method stub
        Scanner scan =new Scanner(System.in);

        // 1.format the license
        System.out.println("Please input the String:");
        String ss=scan.next();
        String license = FormatLicense.getLicense(ss,4);
        System.out.println(license);

        // 2. RockPaperScissorGame
        Scissor s = new Scissor(5);
        Paper p = new Paper(7);
        Rock r = new Rock(15);

        System.out.println(s.fight(p)+","+p.fight(s));
        System.out.println(p.fight(r)+","+r.fight(p));
        System.out.println(r.fight(s)+","+s.fight(r));

        // 3. IPAddress Test
        IpAddress ip =new IpAddress("216.27.6.136");
        System.out.println(ip.getDottedDecimal());
        System.out.println(ip.getOctet(4));
        System.out.println(ip.getOctet(1));
        System.out.println(ip.getOctet(3));
        System.out.println(ip.getOctet(2));

        //4. Course Registration system
        Course java = new Course("Java");
        Course algorithms = new Course("Algorithms");
        Course coop = new Course("coop");
        Course[] arrCourse=new Course[3];
        arrCourse[0]=java;
        arrCourse[1]=algorithms;
        arrCourse[2]=coop;

        while(true)
        {
            System.out.println("==================Course Registration System==================");
            System.out.println("Course Information:");
            for(int i=0; i<arrCourse.length;i++) {
                System.out.print(i + ":" + arrCourse[i].getCourseName() + "(" + arrCourse[i].getNumberOfStudent());
            }
            System.out.println();
            System.out.println("System operating options:1. Register Course 2.View the course registration 0.Exit");
            System.out.print("please select:");
            int input =scan.nextInt();
            if (input==1)
            {
                System.out.print("Please input the number of course to register:");
                int index= scan.nextInt();
                if(arrCourse[index].isFull())
                {
                    System.out.println("The enrollment for this course is already full，please select other courses!");
                    continue;
                }
               System.out.print("Please input your name:");
               String name =scan.next();
               System.out.print("Please input your id:");
               String id =scan.next();
               Student stu=new Student(name, id);
               if (arrCourse[index].registerStudent(stu))
               {
                   System.out.println("The course you selected is :"+arrCourse[index].getCourseName()+",registration success！");
                   System.out.println("The registration of the course is as follows：");
                   Student[]arrStu =arrCourse[index].getStudents();
                   for(int i=0; i<arrStu.length; i++)
                   {
                       System.out.println("mname:"+arrStu[i].getName()+"\rid:"+arrStu[i].getId());
                   }
               }
            }
            else if (input==2)
            {
                System.out.print("Please input the course number you want to view:");
                int index= scan.nextInt();
                System.out.println("The course you selected is :"+arrCourse[index].getCourseName());
                System.out.println("The registration of the course is as follows： ");
                Student[]arrStu=arrCourse[index].getStudents();
                if (arrStu.length<=0)
                {
                    System.out.println("The course has no student registration");
                    continue;
                }
                for(int i=0;i<arrStu.length;i++)
                {
                    System.out.println("name:"+arrStu[i].getName()+"\tid:"+arrStu[i].getId());
                }
            }
            else if (input==0)
            {
                System.out.println("You exited the system!");
                break;


            }
        }


        //4. To roman number
        System.out.println("please input positive integer between 1-3999:");
        int num = scan.nextInt();
        if(num>3999)
        {
           System.out.println("input is out of range!");
           return;

        }
        String rn = RomanNumber.inToRoman(num);
        System.out.println(num+" to roman number: "+rn);


        //5. Find the median of the two sorted arrays

        int[] arr1= {1,2,4};
        int[] arr2= {3,5,6};
        double mid =Middle.findMedianSortedArrays(arr1, arr2);
        System.out.println("the median of the two arrays is :" +mid);

        
        

    }
}
  class FormatLicense
  {
      public static String getLicense(String s, int k)
      {
          s=s.trim();
          //  Determine whether the length exceeds the required
          if(s.length()>12000)
          {
              return "The length of String cannot be larger than 12000";
          }
          //   Determines whether the string is empty
          if(s.length()==0)
          {
              return "string cannot be empty!";
          }
          String regex="[A-Za-z0-9\\-]*";
          boolean b = s.matches(regex);

          if(b==false)
          {
              return "String includes illegal character(only including A-Za-z0-9 and -),please input again!";
              
          }
          String r = s.replace("-","");
          StringBuffer sbf = new StringBuffer(r);
          for(int i=r.length()-k;i>0;i=i-k)
          {
             sbf.insert(i,"-");
              
          }
          return sbf.toString().toUpperCase();
      }
  }

  abstract class Tool
  {
      protected int strength;
      protected char type;
      public Tool()
      {

      }
      public Tool(int strength, char type)
      {
          this.strength= strength;
          this.type=type;

      }
      public void setStrength(int strength)
      {
          this.strength=strength;

      }
      public int getStrength()
      {
          return this.strength;

      }
      abstract public boolean fight(Tool t);
  }

  class Rock extends Tool
  {
      public Rock()
      {

      }
      public Rock(int strength)
      {
          super(strength, 'r');
          
      }
      public boolean fight(Tool t)
      {
          int stre=0;
          if (t instanceof Paper)
          {
              stre= this.strength/2;

          }
          else if (t instanceof Scissor)
          {
              stre=this.strength*2;
          }
          if(stre>t.strength)
          {
              System.out.print( t.type+" is defeated by "+ this.type );
              return true;
          }
          else
          {
              System.out.println( this.type+" is defeated by "+t.type );
              return false;
          }
      }

  }

  class Paper extends Tool
  {
      public Paper()
      {

      }
      public Paper(int strength)
      {
          super(strength, 'p');
      }
      public boolean fight(Tool t)
      {
          int stre=0;
          if(t instanceof Scissor)
          {
              stre= this.strength/2;
          }
          else if (t instanceof Rock)
          {
              stre=this.strength*2;
          }
          if(stre>t.strength)
          {
              System.out.print( t.type+" is defeated by "+ this.type  );
              return true;
          }
          else
          {    System.out.println( this.type+" is defeated by "+t.type);
               return false;
          }
      }
  }
  class Scissor extends Tool
  {
      public Scissor()
      {

      }
      public Scissor(int strength)
      {
          super(strength, 's');

      }
      public boolean fight(Tool t)
      {
          int stre=0;
          if (t instanceof Paper)
          {
              stre=this.strength*2;

          }
          else if (t instanceof Rock)
          {
              stre= this.strength/2;
          }
          if(stre>t.strength)
          {
              System.out.print( t.type+"is defeated by "+ this.type ) ;
              return true;
          }
          else
          {
              System.out.println( this.type+" is defeated by "+t.type );
              return false;
          }
      }
  }
  class IpAddress
  {
      private String strIpAddress;
      private int firstOctet;
      private int secondOctet;
      private int thirdOctet;
      private int fourthOctet;

      public IpAddress(String ip)
      {
          this.strIpAddress=ip;//192.168.100.172
          String[] arr =ip.split("\\.") ;
          firstOctet=Integer.parseInt(arr[0]);
          secondOctet=Integer.parseInt(arr[1]);
          thirdOctet=Integer.parseInt(arr[2]);
          fourthOctet=Integer.parseInt(arr[3]);
      }
         public String getDottedDecimal()
         {
             return this.strIpAddress;
         }
         public int getOctet(int i)
         {
             int octet;
             switch(i)
             {
                 case 1:
                     octet=firstOctet;
                     break;
                 case 2:
                     octet=secondOctet;
                     break;
                 case 3:
                     octet=thirdOctet;
                     break;
                 case 4:
                     octet=fourthOctet;
                     break;
                 default:
                     octet=0;
                     break;

             }
             return octet;
         }
  }
  class Student
  {
      private String name;
      private String id;
      public Student(String name, String id)
      {
          this.name =name;
          this.id=id;
      }
      public String getName()
      {
          return this.name;
      }
      public String getId()
      {
          return this.id;
      }
  }
  class Course
  {
      private String courseName;
      private int numberOfStudent;
      private Student[] arrStu;

      public Course(String courseName)
      {
          this.courseName=courseName;
          arrStu=new Student[10];
          numberOfStudent=0;
      }
      public String getCourseName()
      {
          return this.courseName;
      }
      public int getNumberOfStudent()
      {
          return numberOfStudent;
      }
      public boolean isFull()
      {
          if(numberOfStudent>=10)
              return true;
          else
              return false;
      }
      public Student[]getStudents() {
          Student[] stu = new Student[numberOfStudent];
          for (int i = 0; i < numberOfStudent; i++) {
              stu[i] = arrStu[i];
          }
          return stu;
      }
      public boolean registerStudent(Student stu)
      {
          if(isFull())
              return false;
          else
          {
              arrStu[numberOfStudent]=stu;
              numberOfStudent++;
              return true;
          }
      }
  }

  class RomanNumber
  {
      public static String inToRoman(int num)
      {
          String digit[]={"","I","II","III","IV","V","VI","VII","VIII","IX"}  ;
          String ten[]={"","X","XX","XXX","XL","L","LX","LXX","LXXX","XC"};
          String hundred[]={"","C","CC","CCC","CD","D","DC","DCC","DCCC","CM"};
          String thousand[]={"","M","MM","MMM"};
          String str="";
          return str+thousand[num/1000]+hundred[num%100/100]+ten[num%100/10]+digit[num%10];
      }
  }

   class Middle
{
	public static double findMedianSortedArrays(int[] nums1, int[] nums2) {

        if (nums1 == null) {
            nums1 = new int[0];
        }

        if (nums2 == null) {
            nums2 = new int[0];
        }

        int len1 = nums1.length;
        int len2 = nums2.length;

        if (len1 < len2) {
            // 确保第一个数组比第二个数组长度大
            return findMedianSortedArrays(nums2, nums1);
        }

        // 如果长度小的数组的长度为0，就返回前一个数组的中位数
        if (len2 == 0) {
            return (nums1[(len1 - 1) / 2] + nums1[len1 / 2]) / 2.0;
        }


        int lo = 0;
        int hi = len2 * 2;
        int mid1;
        int mid2;
        double l1;
        double l2;
        double r1;
        double r2;

        while (lo <= hi) {
            mid2 = (lo + hi) / 2;
            mid1 = len1 + len2 - mid2;

            l1 = (mid1 == 0) ? Integer.MIN_VALUE : nums1[(mid1 - 1) / 2];
            l2 = (mid2 == 0) ? Integer.MIN_VALUE : nums2[(mid2 - 1) / 2];

            r1 = (mid1 == len1 * 2) ? Integer.MAX_VALUE : nums1[mid1 / 2];
            r2 = (mid2 == len2 * 2) ? Integer.MAX_VALUE : nums2[mid2 / 2];

            if (l1 > r2) {
                lo = mid2 + 1;
            } else if (l2 > r1) {
                hi = mid2 - 1;
            } else {
                return (Math.max(l1, l2) + Math.min(r1, r2)) / 2;
            }
        }

        return -1;
    }


	public static double findMedianSortedArrays1(int[] nums1, int[] nums2) {
		int N = nums1.length + nums2.length;
		if (N % 2 == 1)
			return findKth(nums1, 0, nums2, 0, N / 2 + 1);
		else
			return (findKth(nums1, 0, nums2, 0, N / 2 + 1) + findKth(nums1, 0, nums2, 0, N / 2)) / 2.0;
	}

	// 返回第K大的元素，从1开始
	private static int findKth(int[] a, int alo, int[] b, int blo, int k) {
		// a数组为空时，返回b数组第k个元素
		// k从1开始
		// alo, blo是第一个元素
		if (alo >= a.length)
			return b[blo + k - 1];
		// b数组为空时，返回a数组第K个元素
		if (blo >= b.length)
			return a[alo + k - 1];
		// 当k==1时，返回a[alo],b[blo]中小的那个
		if (k == 1)
			return Math.min(a[alo], b[blo]);
		// i表示mid到lo之间的整值
		int i = k / 2 - 1;
		int aMid = Integer.MAX_VALUE;
		int bMid = Integer.MAX_VALUE;
		// lo+i正好为中位数下标位置
		if (alo + i < a.length)
			aMid = a[alo + i];
		if (blo + i < b.length)
			bMid = b[blo + i];
		if (aMid < bMid)
			// 在amid之前，包括amid,不可能存在中位数，跳过查找（删除）
			// alo到amid（包括）之间有i+1个数字
			// 去掉i+1个数字，原来第K大，变成第k-(i+1)大
			return findKth(a, alo + i + 1, b, blo, k - i - 1);
		else
			return findKth(a, alo, b, blo + i + 1, k - i - 1);
	}
}
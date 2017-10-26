import java.util.ArrayList;
import java.util.Scanner;


public class MidTermPartB {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Scanner scan = new Scanner(System.in);

        // Question 1
        int n = 7;
        int[] arr = new int[n];
        System.out.println("Please input "+n+" integers");
        for(int i=0;i<arr.length;i++)
        {
            arr[i] = scan.nextInt();
        }
        int arr2[] = reverseEvenIndices(arr);
        System.out.println("New array elements:");
        for(int i=0;i<arr2.length;i++)
        {
            System.out.print(arr2[i]+" ");
        }


        // Question  2
        System.out.println(arrangeCoins(10));


        // Question 3
        int [] exam3 = {1,2,3};
        System.out.println(minMoves(exam3));

        //Question 4
        System.out.println(countNumberOfPossibleWays(6,2,5));



        // Question 5
        RatMaze rat = new RatMaze();
        int maze[][] = {
                {1, 0, 0, 0},
                {1, 1, 1, 1},
                {0, 1, 0, 0},
                {1, 1, 1, 1}
        };
        rat.solveMaze(maze);

    }

    public static int[] reverseEvenIndices(int[] nums)
    {
        int n = nums.length;
        int [] arr3 = new int[n];
        for(int i=0;i<n;i++)
        {
            if(i%2==0)
            {
                arr3[i] = nums[n-i-1];
            }
            else
            {
                arr3[i] = nums[i];
            }
        }

        return arr3;
    }

    public static int arrangeCoins(int n)
    {
        int count=0;
        int i;
        for(i=1;i<=n;i++)
        {
            n=n-i;
            if(n<i)
                break;
        }
        return i;
    }

    public static int minMoves(int[] nums) {
        int res = 0;
        int min = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (min > nums[i])
                min = nums[i];
        }

        for (int i = 0; i < nums.length; i++) {
            if (min != nums[i])
                res += nums[i] - min;
        }

        return res;
    }


    public static int countNumberOfPossibleWays(int faces, int dices, int sum) {
        if (sum < 1) { // sum is too small
            return 0;
        }
        if (dices == 1) { // ֻonly one dice, judge which is larger, the sum or the maximum face value
            return sum <= faces ? 1 : 0;
        }

        int ways = 0;
        for (int i = 1; i <= faces; i++) {
            ways += countNumberOfPossibleWays(faces, dices - 1, sum - i);
        }
        return ways;
    }




}





class RatMaze
{
    final int N = 4;
    ArrayList<Cell> alCell = new ArrayList<Cell>();




    boolean isSafe(int maze[][], int x, int y)
    {
        // if (x,y outside maze) return false
        return (x >= 0 && x < N && y >= 0 &&
                y < N && maze[x][y] == 1);
    }

    void printSolution()
    {
        for(int i=0;i<alCell.size();i++)
        {
            System.out.print(alCell.get(i).toString());
        }
    }


    boolean solveMaze(int maze[][])
    {

        if (solveMazeUtil(maze, 0, 0) == false)
        {
            System.out.print("Solution doesn't exist");
            return false;
        }
        printSolution();
        return true;
    }

    /* A recursive utility function to solve Maze
       problem */
    boolean solveMazeUtil(int maze[][], int x, int y)
    {
        // if (x,y is goal) return true
        if (x == N - 1 && y == N - 1)
        {
            alCell.add(new Cell(x,y));
            return true;
        }

        // Check if maze[x][y] is valid
        if (isSafe(maze, x, y) == true)
        {
            // mark x,y as part of solution path
            alCell.add(new Cell(x,y));

         /* Move forward in x direction */
            if (solveMazeUtil(maze, x + 1, y))
                return true;


            if (solveMazeUtil(maze, x, y + 1))
                return true;


            return false;
        }

        return false;
    }


}

class Cell {
    int x, y;

    Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public String toString() {
        return "[" + this.x + ", " + this.y + "]";
    }

}


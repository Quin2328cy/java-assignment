import java.util.*;


public class Assignment7 {
    static ShowHello sh;
    public static void main(String[] args) throws InterruptedException {
        // TODO Auto-generated method stub

        //1
        System.out.println("No.1:");
        int[] arr = new int[10];
        Random random = new Random();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(arr.length);
        }
        try
        {
            int max = MaxThread.max(arr);
            System.out.println("max: " + max);
        }
        catch(InterruptedException e)
        {

        }

        //2
        System.out.println("No.2:");
        sh = new ShowHello();
        ReverseHello rh1 = new ReverseHello(1);
        rh1.start();
        rh1.join();

        //3
        System.out.println("No.3:");
        Device device = new Device();
        Sensor heat = new Sensor(device);
        Sensor pressure = new Sensor(device);
        Controller controller = new Controller(device,heat,pressure);

        controller.start();
        heat.start();
        pressure.start();
        controller.join();


        //4
        System.out.println("No.4:");
        printPascalTriangle(6);

        //5
        System.out.println("No.5:");
        int [] arr1 = {11,1,5,5};
        System.out.println(findPartition(arr1));
    }

    public static boolean findPartition(int[] nums) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++)
            sum += nums[i];
        if (sum % 2 == 1)
            return false;
        else {
            int m = nums.length;
            int n = sum / 2;
            int[] value = nums;
            int f[][] = new int[m][n + 1];

            for (int i = nums[0]; i <= n; i++) {
                f[0][i] = value[0];
            }

            for (int i = 1; i < m; i++) {
                for (int j = nums[i]; j <= n; j++) {
                    f[i][j] = Math.max(f[i - 1][j], f[i - 1][j - nums[i]]
                            + value[i]);
                }
            }
            if (f[m - 1][n] == n)
                return true;
            else
                return false;
        }

    }

    //4
    public static void printPascalTriangle(int n){

        int lines = n;
        int[] a = new int[lines + 1];
        int previous = 1;
        for (int i = 1; i <= lines; i ++){
            for (int j = 1; j <= i; j++){
                int current = a[j];
                a[j] = previous + current;
                previous = current;
                System.out.print(a[j] + " ");
            }
            System.out.println();
        }
    }

}


//1
class MaxThread extends Thread {
    private int lo, hi;
    private int[] arr;
    private int max;

    public MaxThread(int[] arr, int lo, int hi) {
        this.lo = lo;
        this.hi = hi;
        this.arr = arr;
        max = arr[lo];
    }

    @Override
    public void run() {
        for (int i = lo+1; i < hi; i++) {
            if(max<arr[i])
                max = arr[i];
        }
    }


    public static int max(int[] arr) throws InterruptedException {
        int len = arr.length;
        int result = 0;

        // Create and start 4 threads.
        MaxThread[] ts = new MaxThread[4];
        for (int i = 0; i < 4; i++) {
            ts[i] = new MaxThread(arr, (i * len) / 4, ((i + 1) * len / 4));
            ts[i].start();
        }

        // Wait for the threads to finish and sum their results.
        result = ts[0].max;
        for (int i = 1; i < 4; i++) {
            ts[i].join();
            if(result <ts[i].max)
                result = ts[i].max;
        }
        return result;
    }
}

//2
class ReverseHello extends Thread
{

    int no;

    ReverseHello(int no)
    {
        this.no = no;
    }
    public void run()
    {
        if (no>50)
            return;

        Assignment7.sh.show(this.no);

    }

}

class ShowHello
{

    int curNo = 50;
    synchronized public void show(int no)
    {
        while(curNo!=no)
        {
            try {
                ReverseHello rh = new ReverseHello(no+1);
                rh.start();
                wait();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        System.out.println("Hello from Thread "+no+"!");
        curNo--;
        notifyAll();

    }
}
//3
class Device
{
    double heat;
    double pressure;

    Device()
    {
        heat = 0;
        pressure = 0;
    }



}

class Sensor extends Thread{

    Device device;

    Sensor(Device d) {
        device = d;
    }

    public void run() {
        while(true){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e){
            }
            this.updateValue();
        }
    }

    void updateValue() {
        String name = this.getName();
        double value = new Random().nextDouble() * 20;
        if("heat".equals(name)) {
            this.device.heat += value;
        }else {
            this.device.pressure += value;
        }
        synchronized (device) {
            device.notifyAll();
        }
    }

}
class Controller extends Thread{

    Device device;
    Sensor heat;
    Sensor pressure;

    Controller(Device d, Sensor h, Sensor p) {
        device = d;
        heat = h;
        pressure = p;
        this.heat.setName("heat");
        this.pressure.setName("pressure");
    }

    public void run() {
        System.out.println("Device started");
        while (true) {
            if(this.device.heat >= 70 || this.device.pressure >= 100){
                this.heat.interrupt();
                this.pressure.interrupt();
                this.interrupt();
                System.out.println("Device shutting down due to maintenance");
                break;
            }else{
                String msg = String.format("heat -> %.2f , pressure -> %.2f",this.device.heat,this.device.pressure);
                System.out.println(msg);
                synchronized(device){
                    try {
                        device.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}



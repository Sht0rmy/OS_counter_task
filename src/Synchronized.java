class Counter1 implements Runnable
{
    private int c = 0;

    private static int limit = 100;
    public synchronized void increment()
    {
        try
        {
            Thread.sleep(10);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        c++;
    }

    public int getValue()
    {
        return c;
    }
    @Override
    public void run()
    {
        while (c < limit) {
            System.out.println("Value for Thread After increment " + Thread.currentThread().getName() + " " + this.getValue());
            increment();
        }
    }
}
public class Synchronized
{
    public static void main(String args[])
    {
        Counter1 counter = new Counter1();
        Thread t1 = new Thread(counter, "Thread-1");
        Thread t2 = new Thread(counter, "Thread-2");
        t1.start();
        t2.start();
    }
}  
import java.util.concurrent.Semaphore;
class Counter2 implements Runnable
{
    private int c = 0;

    Semaphore semaphore;

    public Counter2(Semaphore semaphore){
        this.semaphore = semaphore;
    }
    private static int limit = 100;
    public void increment()
    {
        try {
            semaphore.acquire();
            System.out.println("Value for Thread After increment " + Thread.currentThread().getName() + " " + this.getValue());
            c++;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release();
        }
    }

    public int getValue()
    {
        return c;
    }
    @Override
    public void run()
    {
        while (c < limit) {
            increment();
        }
    }
}
public class Mutex
{
    public static void main(String args[])
    {
        Semaphore Mutex = new Semaphore(1);
        Counter2 counter = new Counter2(Mutex);
        Thread t1 = new Thread(counter, "Thread-1");
        Thread t2 = new Thread(counter, "Thread-2");
        t1.start();
        t2.start();
    }
}  
public class MyRunnable implements Runnable{
    @Override
    public void run() {
        System.out.println("Thread " + Thread.currentThread().getName() + " is executing...");
    }
}

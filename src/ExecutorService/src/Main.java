public class Main {
    public static void main(String[] args) {
       MyExecutor executorService = new MyExecutor();
       executorService.ThreadPool(5, new MyRunnable());

       executorService.execute(); // start the Thread
       executorService.shutdown();
       if (executorService.isTerminated() == true) {
           System.out.println("All Threads are finished!");
       }
    }
}
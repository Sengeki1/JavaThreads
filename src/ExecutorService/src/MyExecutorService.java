public interface MyExecutorService {
    void ThreadPool(int num, Runnable task);
    void execute();
    void shutdown();
    boolean isTerminated();
}

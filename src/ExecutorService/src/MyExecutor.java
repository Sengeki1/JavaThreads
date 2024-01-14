import java.util.ArrayList;
import java.util.List;
public class MyExecutor implements MyExecutorService {
    private final List<Thread> threadList = new ArrayList<>();
    public void ThreadPool(int numThreads, Runnable task) {
        for (int i = 0; i < numThreads; i++) {
            Thread thread = new Thread(task);
            threadList.add(thread);
        }
    }

    public void execute() {
        for (Thread thread : threadList) {
            thread.start();
        }
    }

    @Override
    public void shutdown() {
        for (Thread thread : threadList) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public boolean isTerminated() {
        List<Boolean> terminated = new ArrayList();
        Boolean threadTerminated = false;
        for (int i = 0; i < threadList.size(); i++) {
            if (!(threadList.get(i).isAlive())) {
                terminated.add(true);
            } else {
                terminated.add(false);
            }
        }
        if (terminated.contains(false)) {
            threadTerminated = false;
        } else threadTerminated = true;

        return threadTerminated;
    }

}

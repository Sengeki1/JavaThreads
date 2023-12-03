public class Fila {
    private final int[] buffer;
    private int cursor;
    public Fila() {
        buffer = new int[10];
        cursor = 0;
    }
    public synchronized void enqueue(int num) {
        if (cursor < buffer.length - 1) {
            buffer[cursor] = num;
            System.out.println("Element is insert at position " + cursor);
            cursor += 1;

            System.out.println("Thread " + Thread.currentThread().getName() + " finished");
            notify();
        } else {
            try{
                System.out.println("Thread " + Thread.currentThread().getName() + " is waiting for array to decrease!");
                wait();
            } catch (InterruptedException e) {
                System.out.println("The array is already full!");
            }
        }
    }

    public synchronized int dequeue() {
        int i = 0;
        if (cursor != 0) {
            i = buffer[cursor - 1];
            System.out.println("Element was consumed at position " + cursor);
            cursor--;

            System.out.println("Thread " + Thread.currentThread().getName() + " finished");
            notify();
        } else {
            try {
                System.out.println("Thread " + Thread.currentThread().getName() + " is waiting for integer to be inserted!");
                wait();
            } catch (Exception e) {
                System.out.println("The array is empty!");
            }
        }
        return i;
    }
};


public class Main {
    public static void main(String[] args) {
        Fila fila = new Fila();

        Thread produtor = new Thread(() -> {
            try {
                for (int i = 0; i < 100; i++) {
                    fila.enqueue(i);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        produtor.start();

        Thread consumidor = new Thread(() -> {
            try {
                while(true) {
                    int num = fila.dequeue();
                    System.out.println("Consumido: " + num);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        consumidor.start();
    }
}
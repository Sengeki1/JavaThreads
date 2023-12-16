class TeacherRunnable implements Runnable {
    private ExamRoom examRoom;
    public TeacherRunnable(ExamRoom examRoom) {
        this.examRoom = examRoom;
    }
    public void testReady() {
        System.out.println("Teste já está pronta");
    }
    @Override
    public void run() {
        for (int i = 0; i < examRoom.students; i += 2) {
            try {
                Thread.sleep(5000);
                System.out.println("Docente colocou 2 provas");
                testReady();
                examRoom.examsReady(2);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

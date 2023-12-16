import java.io.File;
import java.io.FileWriter;
public class Folder {
    public void createFileTxt(int studentId, String threadID, int time) {
        try {
            File file = new File("/home/marco/Transferências/","folha_presenca.txt");
            FileWriter writer = new FileWriter(file, true); // append each time a thread opens the file

            writer.write("Aluno: " + studentId + " ID: " + threadID + " terminei a prova em " + time + "ms.\n");
            writer.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

import java.io.*;
import java.nio.charset.StandardCharsets;

@SaveTo(path = "D:\\file.txt")
public class TextContainer {
    public String text = "Test class TextContainer";

    public TextContainer() {
    }

    @Saver
    public void save(String text, String path) {
        File file = new File(path);
        try(FileOutputStream os = new FileOutputStream(file)){
            os.write(text.getBytes(StandardCharsets.UTF_8));
        }catch (IOException e){
            e.printStackTrace();
        }

    }
}

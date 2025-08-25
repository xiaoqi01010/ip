package sophia;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestSophiaList {
    public void TestDelete() throws SophiaException, IOException {
        File file = new File("./data/test_storage.txt");
        if(!file.exists())file.createNewFile();
        BufferedWriter br = new BufferedWriter(new FileWriter(file));
        br.write("""
        T | 0 | read 
        D | 0 | return books | 2025-09-23
        E | 1 | group meeting | 2025-08-23 | 2025-08-24
        """);
        br.flush();
        Sophia sophia = new Sophia(file.getPath());
        assertEquals(sophia.testDeleteTask("delete 1"),"[T][ ] read");
    }
}

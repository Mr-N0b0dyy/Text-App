import org.junit.jupiter.api.Test;
import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;

class TextAppTest {

    @Test
    void openFile() {
        TextApp app = new TextApp();  // Checking if open-file Works
        app.openFile();
        System.out.println(app.area.getText());
        assertTrue(app.area.getText().length()>0);
    }

    @Test
    void WordCounterTest() {
        TextApp app = new TextApp();
        app.area.setText("WORD1 WORD2 WORD3 WORD4 WORD5 WORD6 WORD7 WORD8 WORD9 WORD10");
        assertEquals(10, app.WordCounter(app.area));
    }
    @Test
    void WordCounterTest2() {
        TextApp app = new TextApp();
        app.area.setText("WORD1 WORD2 WORD3 WORD4 WORD5 WORD6 WORD7 WORD8 WORD9 WORD10 WORD11 WORD12");
        assertEquals(12, app.WordCounter(app.area));
    }

    @Test
    void IsAppOpening(){

        TextApp app = new TextApp();

        app.area.setText("HELLO THIS IS A TEST");

        assertEquals("HELLO THIS IS A TEST",app.area.getText()); // Checking if texts exists and if we can write

        assertTrue(app.frame.isVisible()); // Checking if frame exists and visible

        assertInstanceOf(JMenuBar.class,app.frame.getJMenuBar()); // Check if menubar Exists

        assertInstanceOf(JPanel.class,app.panel); // Check if panel exists

    }
}

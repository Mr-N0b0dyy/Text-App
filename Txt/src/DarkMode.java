import java.awt.*;

class DarkMode implements Mode {
    public DarkMode() {
        display();
    } // constructor

    @Override
    public void display() { // Changing colors in text app
        Singleton.getApp().area.setBackground(Color.BLACK);
        Singleton.getApp().area.setForeground(Color.WHITE);
        Singleton.getApp().area.setCaretColor(Color.WHITE);
        Singleton.getApp().panel.setBackground((Color.BLACK));
        for (int i = 0; i < 3; i++) {
            Singleton.getApp().mb.getMenu(i).setForeground(Color.BLACK);
        }
    }
}

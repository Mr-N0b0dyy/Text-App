import java.awt.*;

class LightMode implements Mode {
    public LightMode() {
        display();
    }

    @Override
    public void display() { // Changing text app colors
        Singleton.getApp().area.setBackground(Color.WHITE);
        Singleton.getApp().area.setForeground(Color.BLACK);
        Singleton.getApp().area.setCaretColor(Color.BLACK);
        Singleton.getApp().panel.setBackground((Color.WHITE));
        for (int i = 0; i < 3; i++) {
            Singleton.getApp().mb.getMenu(i).setForeground(Color.BLACK);
        }
    }
}

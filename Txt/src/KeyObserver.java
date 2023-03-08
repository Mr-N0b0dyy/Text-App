import javax.swing.*;

class KeyObserver implements Observer {
    @Override
    public void update(JTextArea area) {  // Updating observer and undo&redo stack
        Singleton.getApp().undo.execute(area.getText()); // Adding typed text to stack
        int count = Singleton.getApp().WordCounter(area);
        Singleton.getApp().countLabel.setText(String.format("Word count: %d", count));
    }
}

import javax.swing.*;


class Commands implements Command {  // Commands class
    @Override
    public void execute(String s) {
        ModeFactory factory = new ModeFactory();

        switch (s) {
            // cut-copy-paste cases uses app's area and execute their purpose
            case ("Cut") -> {
                Singleton.getApp().undo.execute(Singleton.getApp().area.getText());
                Singleton.getApp().area.cut();
            }
            case ("Copy") -> Singleton.getApp().area.copy();
            case ("Paste") -> {
                Singleton.getApp().undo.execute(Singleton.getApp().area.getText());
                Singleton.getApp().area.paste();
                int count = Singleton.getApp().WordCounter(Singleton.getApp().area);
                Singleton.getApp().countLabel.setText(String.format("Word count: %d", count));
            }
            // Erases all the area
            case ("New") -> {

                Singleton.getApp().undo.undoStack.clear();
                Singleton.getApp().redo.redoStack.clear();
                Singleton.getApp().newFile();
                int count = Singleton.getApp().WordCounter(Singleton.getApp().area);
                Singleton.getApp().countLabel.setText(String.format("Word count: %d", count));
            }

            // Creates dark and light mode from factory
            case ("Dark-Mode") -> factory.createMode("Dark-Mode");
            case ("Light-Mode") -> factory.createMode("Light-Mode");

            // Undo And Redo button
            case ("Undo") -> {
                Singleton.getApp().redo.execute(Singleton.getApp().area.getText());
                Singleton.getApp().area.setText(Singleton.getApp().undo.popStack());
            }
            case ("Redo") -> {
                Singleton.getApp().undo.execute(Singleton.getApp().area.getText());
                Singleton.getApp().area.setText(Singleton.getApp().redo.popRedoStack());
            }
            // Open File command. Opens txt files
            case ("Open") -> {
                Singleton.getApp().undo.undoStack.clear();
                Singleton.getApp().redo.redoStack.clear();
                Singleton.getApp().openFile();

                int count = Singleton.getApp().WordCounter(Singleton.getApp().area);
                Singleton.getApp().countLabel.setText(String.format("Word count: %d", count));
            }

            // Save as command. Saves file in variations
            case ("Save-As") -> {
                Singleton.getApp().undo.undoStack.clear();
                Singleton.getApp().redo.redoStack.clear();
                Singleton.getApp().saveFileAs();
            }
            // Saves the current text file
            case ("Save") -> {
                Singleton.getApp().undo.undoStack.clear();
                Singleton.getApp().redo.redoStack.clear();
                Singleton.getApp().saveFile();
            }
            // getting FindWord and ReplaceWord and if contains replace it
            case ("Find&Replace") -> {
                String FWord = JOptionPane.showInputDialog(Singleton.getApp().frame, "Enter the word you want to find: ");
                String RWord = JOptionPane.showInputDialog(Singleton.getApp().frame, "Enter the word you want to replace: ");

                if (Singleton.getApp().area.getText().contains(FWord)) {
                    String newText = Singleton.getApp().area.getText().replace(FWord, RWord);
                    Singleton.getApp().undo.execute(Singleton.getApp().area.getText());
                    Singleton.getApp().area.setText(newText);
                    int count = Singleton.getApp().WordCounter(Singleton.getApp().area);
                    Singleton.getApp().countLabel.setText(String.format("Word count: %d", count));
                } else {
                    JOptionPane.showMessageDialog(null, "Word can not be found.");
                }
            }
        }
    }
}
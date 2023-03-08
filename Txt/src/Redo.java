import java.util.Stack;

class Redo implements Command { // Redo class
    Stack<String> redoStack = new Stack<String>(); // Redo stack

    @Override
    public void execute(String s) {
        redoStack.push(s);
    } // Pushing text into stack using command

    public String popRedoStack() { // Popping from redo stack
        try {
            return redoStack.pop();
        } catch (java.util.EmptyStackException e) {

            return Singleton.getApp().area.getText();
        }
    }
}

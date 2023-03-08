import java.util.Stack;

class Undo implements Command { // Undo class
    Stack<String> undoStack = new Stack<String>(); // Creating undo stack

    @Override
    public void execute(String s) { // Pushing text into stack using command
        undoStack.push(s);
    }

    public String popStack() { // Popping from stack
        try {
            return undoStack.pop();
        } catch (java.util.EmptyStackException e) {

            return Singleton.getApp().area.getText();
        }
    }
}

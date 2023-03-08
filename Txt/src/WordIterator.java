import javax.swing.*;
// implements iterator and overrides it
class WordIterator implements Iterator {
    private final String text;
    private int currentIndex;

    public WordIterator(JTextArea textArea) {
        currentIndex = 0;
        text = textArea.getText();
    }
    @Override
    public boolean hasNext() {
        // Check if there are anymore non-space characters in the text
        for (int i = currentIndex; i < text.length(); i++) {
            if (text.charAt(i) != ' ' && text.charAt(i) != '\n') {
                return true;
            }
        }
        return false;
    }

    @Override
    public String next() {
        // Skip any leading spaces or newline characters
        while (currentIndex < text.length() && (text.charAt(currentIndex) == ' ' || text.charAt(currentIndex) == '\n')) {
            currentIndex++;
        }
        int start = currentIndex;

        // Find the end of the current word
        while (currentIndex < text.length() && text.charAt(currentIndex) != ' ' && text.charAt(currentIndex) != '\n') {
            currentIndex++;
        }
        int end = currentIndex;

        // Advance the current index past the end of the current word
        currentIndex++;
        return text.substring(start, end);
    }


    @Override
    public void reset() {
        currentIndex = 0;
    }
}

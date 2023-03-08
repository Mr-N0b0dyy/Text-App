import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

class TextApp extends Component implements ActionListener {
    Undo undo = new Undo();
    Redo redo = new Redo();
    JFrame frame;
    JTextArea area;
    JMenuBar mb;
    JTextField field;
    JPanel panel;
    File currentFile;
    List<Observer> observers = new ArrayList<>();
    JLabel countLabel;

    public TextApp() {
/*
    Create frame-text area-menu-field...
 */
        frame = new JFrame();
        frame.setResizable(false);
        frame.setTitle("TextApp");
        frame.setVisible(true);
        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        area = new JTextArea();
        area.setLineWrap(true);
        area.setBounds(50, 50, 200, 200);

        mb = new JMenuBar();
        mb.setBackground(Color.GRAY);

        field = new JTextField();
        field.setLocation(100, 100);

        JMenu fileMenu = new JMenu("File");
        JMenuItem m1 = new JMenuItem("New");
        JMenuItem m2 = new JMenuItem("Save");
        JMenuItem m3 = new JMenuItem("Save-As");
        JMenuItem m4 = new JMenuItem("Open");

        m1.addActionListener(this);
        m2.addActionListener(this);
        m3.addActionListener(this);
        m4.addActionListener(this);

        fileMenu.add(m1);
        fileMenu.add(m2);
        fileMenu.add(m3);
        fileMenu.add(m4);


        JMenu editMenu = new JMenu("Edit");
        JMenuItem m5 = new JMenuItem("Cut");
        JMenuItem m6 = new JMenuItem("Copy");
        JMenuItem m7 = new JMenuItem("Paste");
        JMenuItem m8 = new JMenuItem("Find&Replace");

        m5.addActionListener(this);
        m6.addActionListener(this);
        m7.addActionListener(this);
        m8.addActionListener(this);

        editMenu.add(m5);
        editMenu.add(m6);
        editMenu.add(m7);
        editMenu.add(m8);

        JMenu undoMenu = new JMenu("Undo");
        JMenuItem m9 = new JMenuItem("Undo");
        JMenuItem m10 = new JMenuItem("Redo");

        m9.addActionListener(this);
        m10.addActionListener(this);
        undoMenu.add(m9);
        undoMenu.add(m10);

        JMenu modeMenu = new JMenu("Mode");
        JMenuItem m11 = new JMenuItem("Dark-Mode");
        JMenuItem m12 = new JMenuItem("Light-Mode");

        m11.addActionListener(this);
        m12.addActionListener(this);
        modeMenu.add(m11);
        modeMenu.add(m12);

        //
        KeyObserver keyChecker = new KeyObserver();
        observers.add(keyChecker);
        area.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                notifyObservers();
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        panel = new JPanel();
        panel.setBounds(0, 450, 600, 50);

        // adding objects to menu bar
        mb.add(fileMenu);
        mb.add(editMenu);
        mb.add(undoMenu);
        mb.add(modeMenu);

        frame.add(panel);
        JScrollPane scrollPane = new JScrollPane(area);
        frame.add(scrollPane, BorderLayout.CENTER);
        JPanel bottomPanel = new JPanel();
        bottomPanel.setPreferredSize(new Dimension(500, 20));

        bottomPanel.setBackground(Color.GRAY);
        countLabel = new JLabel("Word count: 0");
        bottomPanel.add(countLabel);
        frame.add(bottomPanel, BorderLayout.SOUTH);
        frame.setJMenuBar(mb);

        frame.setSize(500, 500);
    }
    // Get the event as string and execute its purpose
    @Override
    public void actionPerformed(ActionEvent e) {
        Commands command = new Commands();

        String s = e.getActionCommand();
        command.execute(s);
    }

    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(area);
        }
    }

    public int WordCounter(JTextArea area) {
        WordIterator iterator = new WordIterator(area);
        int count = 0;
        while (iterator.hasNext()) {
            iterator.next();
            count++;
        }
        return count;
    }
    // using JFileChooser setting area.text to a string created by string builder
    public void newFile() {
        int confirm = JOptionPane.showConfirmDialog(this, "Any unsaved changes will be lost. Continue?", "New File", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            area.setText("");
            currentFile = null;
        }
    }
    public void openFile() {
        JFileChooser fileChooser = new JFileChooser();
        int returnVal = fileChooser.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                StringBuilder sb = new StringBuilder();

                String line = reader.readLine();
                while (line != null) {
                    sb.append(line);
                    sb.append(System.lineSeparator());
                    line = reader.readLine();
                }
                reader.close();
                area.setText(sb.toString());
                currentFile = file;


            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error reading file", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void saveFileAs() {
        JFileChooser fileChooser = new JFileChooser();
        int returnVal = fileChooser.showSaveDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                writer.write(area.getText());
                writer.close();
                currentFile = file;

            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error saving file", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void saveFile() {
        if (currentFile == null) {
            saveFileAs();
        } else {
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(currentFile));
                writer.write(area.getText());
                writer.close();

            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error saving file", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

}

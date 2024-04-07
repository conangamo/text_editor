package MVC;

import javax.swing.*;
import java.awt.*;

public class TextView extends JFrame {
    private JTextArea textArea;
    private JScrollPane scrollPane;
    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenuItem openMenuItem;
    private JMenuItem saveMenuItem;

    public TextView() {
        setTitle("Notepad");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        textArea = new JTextArea();
        scrollPane = new JScrollPane(textArea);
        add(scrollPane);

        menuBar = new JMenuBar();
        fileMenu = new JMenu("File");
        openMenuItem = new JMenuItem("Open");
        saveMenuItem = new JMenuItem("Save");
        fileMenu.add(openMenuItem);
        fileMenu.add(saveMenuItem);
        menuBar.add(fileMenu);
        setJMenuBar(menuBar);
    }

    public JTextArea getTextArea() {
        return textArea;
    }

    public JMenuItem getOpenMenuItem() {
        return openMenuItem;
    }

    public JMenuItem getSaveMenuItem() {
        return saveMenuItem;
    }
}



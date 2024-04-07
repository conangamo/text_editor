package MVC;

import java.awt.event.ActionEvent;
import java.io.*;
import javax.swing.*;
import java.util.Arrays;

public class TextController {
    private TextModel model;
    private TextView view;

    public TextController(TextModel model, TextView view) {
        this.model = model;
        this.view = view;

        view.getTextArea().setText(model.getText());

        view.getOpenMenuItem().addActionListener(e -> openFile());
        view.getSaveMenuItem().addActionListener(e -> saveFile());
    }

    private void openFile() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(view);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            if (selectedFile.isDirectory()) {
                recursiveFileSearch(selectedFile, 0);
            } else {
                try (BufferedReader reader = new BufferedReader(new FileReader(selectedFile))) {
                    StringBuilder content = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        content.append(line).append("\n");
                    }
                    model.setText(content.toString());
                    view.getTextArea().setText(model.getText());
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    private void saveFile() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showSaveDialog(view);
        if (result == JFileChooser.APPROVE_OPTION) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileChooser.getSelectedFile()))) {
                writer.write(view.getTextArea().getText());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void recursiveFileSearch(File directory, int level) {
        if (directory.isDirectory()) {
            Arrays.stream(directory.listFiles())
                    .forEach(child -> {
                        view.getTextArea().append(getIndentation(level) + child.getName() + "\n");
                        if (child.isDirectory()) {
                            recursiveFileSearch(child, level + 1);
                        }
                    });
        }
    }

    private String getIndentation(int level) {
        StringBuilder indentation = new StringBuilder();
        for (int i = 0; i < level; i++) {
            indentation.append("  "); // Thêm khoảng trắng vào chuỗi indentation tương ứng với mức độ
        }
        return indentation.toString();
    }
}


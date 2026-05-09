package JernexMachineSystem.JernexApps;

import JernexMachineSystem.JernexSystemComponents.ImageManagement;

import javax.swing.*;
import java.awt.*;

public class NotesApp {
    public static void Create(JDesktopPane desktop, JPanel initMenu) {
        JButton appButton = new JButton("Notes");

        appButton.addActionListener(e -> {
            JInternalFrame appWindow = new JInternalFrame("Notes App", true, true, true, true);
            appWindow.setSize(300, 400);
            appWindow.setLocation(50, 50);
            appWindow.setVisible(true);
            appWindow.setLayout(new BorderLayout());

            ImageIcon appIconFile = ImageManagement.SearchArchiveInThisJarAndTransformToIcon("JernexMachineSystem/assets/JernexNotesAppIcon.png", NotesApp.class);

            Image appIcon = appIconFile.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
            appWindow.setFrameIcon(new ImageIcon(appIcon));


            appWindow.setCursor(desktop.getCursor());

            appWindow.setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);

            desktop.add(appWindow);

            appWindow.moveToFront();

            JTextArea textArea = new JTextArea();
            textArea.setBackground(Color.DARK_GRAY);
            textArea.setForeground(Color.gray);
            textArea.setLineWrap(true);
            textArea.setWrapStyleWord(true);
            JScrollPane scrollPane = new JScrollPane(textArea);
            appWindow.add(scrollPane, BorderLayout.CENTER);

        });


        initMenu.add(appButton);


    }
    public static void OpenArchive(JDesktopPane desktop, String ArchiveName, String ArchiveContent) {
        JInternalFrame appWindow = new JInternalFrame("Notes App", true, true, true, true);
        appWindow.setSize(300, 400);
        appWindow.setLocation(50, 50);
        appWindow.setVisible(true);
        appWindow.setLayout(new BorderLayout());
        ImageIcon appIconFile = ImageManagement.SearchArchiveInThisJarAndTransformToIcon("JernexMachineSystem/assets/JernexNotesAppIcon.png", DialogMsgApp.class);

        Image appIcon = appIconFile.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        appWindow.setFrameIcon(new ImageIcon(appIcon));

        appWindow.setCursor(desktop.getCursor());

        appWindow.setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);

        desktop.add(appWindow);

        appWindow.moveToFront();

        JTextArea textArea = new JTextArea();
        textArea.setText(ArchiveContent);
        JScrollPane scrollPane = new JScrollPane(textArea);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setBackground(Color.DARK_GRAY);
        textArea.setForeground(Color.gray);
        appWindow.add(textArea, BorderLayout.CENTER);
    }
}

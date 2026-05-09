package JernexMachineSystem.JernexInternalApps;

import JernexMachineSystem.JernexSystemComponents.ImageManagement;

import javax.swing.*;
import java.awt.*;
import JernexMachineSystem.JernexInternalApps.JernexJunorRuntimeEnv;

public class JernexTerminal {


    public static void Create(JDesktopPane desktop, JPanel initMenu) {
        JButton appButton = new JButton("Terminal");

        appButton.addActionListener(e -> {
            JInternalFrame appWindow = new JInternalFrame("Jernex Terminal", true, true, true, true);
            appWindow.setSize(700, 700);
            appWindow.setLocation(50, 50);
            appWindow.setLayout(new BorderLayout());

            ImageIcon appIconFile = ImageManagement.SearchArchiveInThisJarAndTransformToIcon("JernexMachineSystem/assets/JernexTerminalAppIcon.png", JernexTerminal.class);

            Image appIcon = appIconFile.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
            appWindow.setFrameIcon(new ImageIcon(appIcon));


            appWindow.setCursor(desktop.getCursor());

            appWindow.setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);

            desktop.add(appWindow);

            JPanel panel1 = new JPanel(new BorderLayout());




            appWindow.moveToFront();

            JTextArea textArea = new JTextArea();
            textArea.setBackground(Color.black);
            textArea.setForeground(Color.green);
            textArea.setEditable(false);
            textArea.append("Copyright (C) NWJ Software Labs\n");
            textArea.append("Digite \"help\" para listar comandos\n\n");

            JTextField textCommand = new JTextField(20);
            textCommand.setBackground(Color.black);
            textCommand.setForeground(Color.green);
            textArea.setLineWrap(true);


            JScrollPane cScrollPane = new JScrollPane(textCommand);
            JScrollPane scrollPane = new JScrollPane(textArea);


            JButton envButton = new JButton("⬆️");
            envButton.setPreferredSize(new Dimension(20, 20));

            envButton.addActionListener(e1 -> {
                String commandText = textCommand.getText();
                if (!commandText.isEmpty()) {
                    textArea.append("> " + commandText + "\n");

                    String commandReturn = JernexJunorRuntimeEnv.RuntimeStartVerify(commandText, desktop);


                    textArea.append(commandReturn + "\n\n");

                    textCommand.setText("");
                    textCommand.requestFocus();
                }
            });


            panel1.add(envButton, BorderLayout.SOUTH);
            panel1.add(textCommand, BorderLayout.NORTH);


            appWindow.add(scrollPane, BorderLayout.CENTER);
            appWindow.add(panel1, BorderLayout.SOUTH);
            appWindow.setVisible(true);

            appWindow.repaint();
            appWindow.revalidate();

        });


        initMenu.add(appButton);


    }
}

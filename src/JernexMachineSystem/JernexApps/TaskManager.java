package JernexMachineSystem.JernexApps;

import JernexMachineSystem.JernexSystemComponents.ImageManagement;

import javax.swing.*;
import java.awt.*;

public class TaskManager {
    public static void Create(JDesktopPane desktop, JPanel initMenu) {
        JButton appButton = new JButton("Task Manager");

        appButton.addActionListener(e -> {
            JInternalFrame appWindow = new JInternalFrame("Task Management", true, true, true, true);
            appWindow.setSize(500, 500);
            appWindow.setLocation(100, 100);
            appWindow.setVisible(true);
            appWindow.setLayout(null);
            appWindow.setBackground(Color.DARK_GRAY);

            ImageIcon appIconFile = ImageManagement.SearchArchiveInThisJarAndTransformToIcon("JernexMachineSystem/assets/JernexTaskManagerLogo.png", NotesApp.class);

            Image appIcon = appIconFile.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
            appWindow.setFrameIcon(new ImageIcon(appIcon));


            appWindow.setCursor(desktop.getCursor());

            appWindow.setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);

            desktop.add(appWindow);

            appWindow.moveToFront();

            //----------tasks-----------------
            JButton closeProcess = new JButton("❌");
            JButton runProcess = new JButton("Run Task");

            closeProcess.setBounds(2, 2, 50, 30);

            DefaultListModel<String> apps = new DefaultListModel<>();
            JList<String> appsLists = new JList<>(apps);
            JScrollPane appsListsScroll = new JScrollPane(appsLists);
            appsListsScroll.setBounds(30, 50, 400, 400);
            appsLists.setBackground(Color.gray);
            Timer timer = new Timer(1000, e1 -> {
                JInternalFrame[] appsInRun = desktop.getAllFrames();


                java.util.List<String> novosNomes = new java.util.ArrayList<>();
                for (JInternalFrame window : appsInRun) {
                    novosNomes.add(window.getTitle() + " (code: <#" + window.hashCode() + ">)");
                }


                boolean precisaAtualizar = false;
                if (novosNomes.size() != apps.getSize()) {
                    precisaAtualizar = true;
                } else {
                    for (int i = 0; i < novosNomes.size(); i++) {
                        if (!novosNomes.get(i).equals(apps.getElementAt(i))) {
                            precisaAtualizar = true;
                            break;
                        }
                    }
                }


                if (precisaAtualizar) {
                    String itemSelectedPreviously = appsLists.getSelectedValue();

                    apps.clear();
                    for (String nome : novosNomes) {
                        apps.addElement(nome);
                    }

                    if (itemSelectedPreviously != null) {
                        int index = apps.indexOf(itemSelectedPreviously);
                        if (index != -1) {
                            appsLists.setSelectedIndex(index);
                        }
                    }
                }


            });
            timer.start();
            closeProcess.addActionListener(e1 -> {

                String selectedWindow = appsLists.getSelectedValue();

                if (selectedWindow == null) {

                    DialogMsgApp.Create(desktop, "Warning", "Error: select window to close");

                    return;
                }

                JInternalFrame[] appsInRun = desktop.getAllFrames();

                for (JInternalFrame window : appsInRun) {
                    String identificadorJanela = window.getTitle() + " (code: <#" + window.hashCode() + ">)";

                    if (identificadorJanela.equals(selectedWindow)) {
                        window.dispose();
                        break;
                    }

                }
            });

            appWindow.addInternalFrameListener(new javax.swing.event.InternalFrameAdapter() {
                @Override
                public void internalFrameClosed(javax.swing.event.InternalFrameEvent e1) {
                    timer.stop();
                }
            });


            appWindow.add(runProcess);
            appWindow.add(closeProcess);
            appWindow.add(appsListsScroll);



        });


        initMenu.add(appButton);


    }
}

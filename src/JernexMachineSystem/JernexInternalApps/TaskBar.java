package JernexMachineSystem.JernexInternalApps;

import javax.swing.*;
import java.awt.*;

import JernexMachineSystem.CoreUI;
import JernexMachineSystem.JernexSystemComponents.ImageManagement;

public class TaskBar {
    public static boolean InitButtonIsOpened = false;

    public static void Create(JDesktopPane TheDesktop) {

        JInternalFrame TaskBarFrame = new JInternalFrame("components.internal.app.bars.task_bar.name", true, true, true, true);
        TaskBarFrame.getContentPane().removeAll();


        TaskBarFrame.setBounds(1, 1, 850, 50);


        InitMenu.Create(TheDesktop);


        //InitButtom
        ImageIcon init_ButtomIcon = ImageManagement.SearchArchiveInThisJarAndTransformToIcon("JernexMachineSystem/assets/JernexMachineA1Logo.png", CoreUI.class);
        Image initButtomIcon = init_ButtomIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        ImageIcon InitButtomIcon = new ImageIcon(initButtomIcon);
        JButton initButtom = new JButton(InitButtomIcon);

        initButtom.addActionListener(E -> {
            if (!InitButtonIsOpened) {
                InitMenu.Open();
                InitButtonIsOpened = true;
            } else {
                InitMenu.Close();
                InitButtonIsOpened = false;
            }
            TheDesktop.repaint();
        });

        TaskBarFrame.addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentMoved(java.awt.event.ComponentEvent e) {

                TaskBarFrame.setLocation(1, 1);
            }
        });

        ((javax.swing.plaf.basic.BasicInternalFrameUI) TaskBarFrame.getUI()).setNorthPane(null);
        TaskBarFrame.setBorder(null);



        JPanel taskbar = new JPanel();
        taskbar.setBackground(Color.ORANGE);
        taskbar.setLayout(new FlowLayout(FlowLayout.LEFT));
        taskbar.setBounds(0, 0, 1920, 45);
        TaskBarFrame.setVisible(true);
        taskbar.add(initButtom);
        taskbar.setCursor(TheDesktop.getCursor());

        TaskBarFrame.add(taskbar);
        EnergyOptions.Create(TheDesktop, taskbar);

        TheDesktop.add(TaskBarFrame);
    }
}

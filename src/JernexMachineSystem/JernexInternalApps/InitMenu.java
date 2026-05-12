package JernexMachineSystem.JernexInternalApps;

import javax.swing.*;
import JernexMachineSystem.JernexApps.*;
import JernexMachineSystem.JernexInternalApps.JernexTerminal;
import java.awt.*;

public class InitMenu {
    public static JInternalFrame TheInitMenuWindow = new JInternalFrame("components.internal.app.menus.init_menu.name", false, false, false, false);
    public static JPanel TheInitMenu = new JPanel();
    public static JDesktopPane SystemDesktop;

    public static void Create(JDesktopPane ThisDesktop) {
        SystemDesktop = ThisDesktop;

        TheInitMenuWindow.setBounds(10, 50, 300, 400);
        TheInitMenuWindow.setVisible(false);

        SystemDesktop.add(TheInitMenuWindow, Integer.valueOf(100));

        TheInitMenuWindow.add(TheInitMenu);

        TheInitMenuWindow.addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentMoved(java.awt.event.ComponentEvent e) {
                TheInitMenuWindow.setLocation(10, 50);
            }
        });

        ((javax.swing.plaf.basic.BasicInternalFrameUI) TheInitMenuWindow.getUI()).setNorthPane(null);
        TheInitMenuWindow.setBorder(null);
        TheInitMenu.setBackground(Color.green);

        NotesApp.Create(SystemDesktop, TheInitMenu);
        JernexTerminal.Create(SystemDesktop, TheInitMenu);
        TaskManager.Create(SystemDesktop, TheInitMenu);
    }

    public static void Open() {
        TheInitMenuWindow.setVisible(true);
        TheInitMenuWindow.setLocation(10, 50);
    }

    public static void Close() {
        TheInitMenuWindow.setVisible(false);
    }
}

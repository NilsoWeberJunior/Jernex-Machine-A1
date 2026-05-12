package JernexMachineSystem.JernexInternalApps;

import JernexMachineSystem.JernexApps.NotesApp;
import JernexMachineSystem.JernexSystemComponents.ImageManagement;

import javax.swing.*;
import java.awt.*;

public class EnergyOptions {

    public static void Create(JDesktopPane desktop, JPanel initMenu) {
        JButton appButton = new JButton("🔋");

        appButton.addActionListener(e -> {
            JInternalFrame appWindow = new JInternalFrame("Energy Options", true, true, true, true);
            appWindow.setSize(400, 400);
            appWindow.setLocation(200, 200);
            appWindow.setVisible(true);
            appWindow.setLayout(null);
            appWindow.setBackground(Color.DARK_GRAY);

            ImageIcon appIconFile = ImageManagement.SearchArchiveInThisJarAndTransformToIcon("JernexMachineSystem/assets/JernexEnergyOptions.png", NotesApp.class);
            Image appIcon = appIconFile.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
            appWindow.setFrameIcon(new ImageIcon(appIcon));

            appWindow.setCursor(desktop.getCursor());
            appWindow.setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);

            desktop.add(appWindow);
            appWindow.moveToFront();

            JButton ShutdownButton = new JButton("🛑");
            ShutdownButton.setToolTipText("Shutdown");
            ShutdownButton.setBounds(1, 1, 50, 50);
            ShutdownButton.setForeground(Color.red);

            ShutdownButton.addActionListener(e1 -> {
                JernexMachineSystem.CoreUI.Shutdow();
            });

            appWindow.add(ShutdownButton);
        });

        initMenu.add(appButton);
    }
}

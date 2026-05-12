package JernexMachineSystem.JernexApps.JeykanyAI;

// --------EM BREVE!------------
import javax.swing.*;

import JernexMachineSystem.JernexApps.DialogMsgApp;
import JernexMachineSystem.JernexApps.JeykanyAI.JeykanyBrain.Neuron;
import JernexMachineSystem.JernexSystemComponents.ImageManagement;

import java.awt.*;

public class Jeykany {
    public static void Create(JDesktopPane TheDesktop, JPanel TheInitMenu) {
        ImageIcon appIconFile = ImageManagement.SearchArchiveInThisJarAndTransformToIcon("JernexMachineSystem/assets/JernexWarningDialogMSGAppIcon.png", DialogMsgApp.class);

        JInternalFrame appWindow = new JInternalFrame("Jeykany AI - 1.0", false, true, false, true);
        appWindow.setSize(300, 200);
        appWindow.setLocation(100, 100);
        appWindow.setVisible(true);
        Image appIcon = appIconFile.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        appWindow.setFrameIcon(new ImageIcon(appIcon));



        appWindow.setCursor(TheDesktop.getCursor());

        appWindow.setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);

        TheDesktop.add(appWindow);

        appWindow.moveToFront();

    }
}

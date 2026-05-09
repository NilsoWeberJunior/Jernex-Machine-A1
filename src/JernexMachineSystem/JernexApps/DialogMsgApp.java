package JernexMachineSystem.JernexApps;

import javax.swing.*;
import java.awt.*;
import JernexMachineSystem.JernexSystemComponents.ImageManagement;

public class DialogMsgApp {
    public static void Create(JDesktopPane desktop, String msgTitle, String msg) {

        ImageIcon appIconFile = ImageManagement.SearchArchiveInThisJarAndTransformToIcon("JernexMachineSystem/assets/JernexWarningDialogMSGAppIcon.png", DialogMsgApp.class);

        JInternalFrame appWindow = new JInternalFrame(msgTitle, false, true, false, true);
        appWindow.setSize(300, 200);
        appWindow.setLocation(100, 100);
        appWindow.setVisible(true);
        Image appIcon = appIconFile.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        appWindow.setFrameIcon(new ImageIcon(appIcon));

//

        appWindow.setCursor(desktop.getCursor());

        appWindow.setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);

        desktop.add(appWindow);

        appWindow.moveToFront();
        JTextArea textArea = new JTextArea();
        textArea.setText(msg);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        appWindow.add(scrollPane);

    }
}

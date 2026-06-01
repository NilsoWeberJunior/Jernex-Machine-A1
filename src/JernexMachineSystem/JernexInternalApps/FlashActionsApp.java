package JernexMachineSystem.JernexInternalApps;

import javax.swing.*;
import JernexMachineSystem.JernexApps.ClockApp;

import java.awt.*;

public class FlashActionsApp {
    public static void Init(JDesktopPane desktop) {
        JInternalFrame thisWindow = new JInternalFrame("components.internal.widgets.flash_actions", false, false, false, false);


        Timer timer = new Timer(100, e -> {
            thisWindow.setLocation(0, 300);
        });

        ClockApp.hoursPanel(thisWindow, 30, 20);

        ((javax.swing.plaf.basic.BasicInternalFrameUI) thisWindow.getUI()).setNorthPane(null);
        thisWindow.setBounds(0, 300, 100, 500);
        thisWindow.setCursor(desktop.getCursor());
        thisWindow.getContentPane().setBackground(Color.cyan);
        thisWindow.setVisible(true);
        desktop.add(thisWindow);
    }
}

package JernexMachineSystem.JernexInternalApps;

import javax.swing.*;
import JernexMachineSystem.JernexApps.NotesApp;
import JernexMachineSystem.JernexInternalApps.JernexTerminal;
import java.awt.*;

public class InitMenu {
    public static JPanel TheInitMenu = new JPanel();
    public static JDesktopPane SystemDesktop;
    public static void Create(JDesktopPane ThisDesktop) {
        SystemDesktop = ThisDesktop;

        TheInitMenu.setBounds(10, 50, 300, 400);
        TheInitMenu.setVisible(false);

        SystemDesktop.add(TheInitMenu, Integer.valueOf(100));

        //apps (são infelizmente grudados no código... mas tentarei no futuro fazer com que pessoas consigam fazer apps sem precisar modificar diretamente a InitMenu)
        NotesApp.Create(SystemDesktop, TheInitMenu);
        JernexTerminal.Create(SystemDesktop, TheInitMenu);
    }

    public static void Open() {
        TheInitMenu.setVisible(true);
    }
    public static void Close() {
        TheInitMenu.setVisible(false);
    }
}

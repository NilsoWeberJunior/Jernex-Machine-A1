package JernexMachineSystem;

import java.awt.*;
import javax.swing.*;
import java.awt.event.WindowEvent;
import java.net.URL;

import JernexMachineSystem.JernexSystemComponents.FilesManagement;
import JernexMachineSystem.JernexSystemComponents.ImageManagement;
import JernexMachineSystem.JernexSystemComponents.VMFilesManagement;
import JernexMachineSystem.JernexInternalApps.FlashActionsApp;
import JernexMachineSystem.JernexApps.DialogMsgApp;
import JernexMachineSystem.JernexInternalApps.TaskBar;
import JernexMachineSystem.JernexSystemComponents.SoundManagement;
import JernexInitialBoot.JernexBootLoader;

public class CoreUI {
    public static JFrame theRoot;
    public static JDesktopPane theDesktop;

    public static void Main(JFrame mainRoot) {
        theRoot = mainRoot;
        theRoot.setLayout(new BorderLayout());

        //desktop---------------------------------
        theDesktop = new JDesktopPane();
        theDesktop.setBackground(Color.blue);
        try {
            URL cursorURL = FilesManagement.SearchArchiveInThisJar("JernexMachineSystem/assets/JernexMachineCursor.png", CoreUI.class);
            if (cursorURL != null) {
                Toolkit toolkit = Toolkit.getDefaultToolkit();
                Image cursorImage = toolkit.getImage(cursorURL);
                Cursor customCursor = toolkit.createCustomCursor(cursorImage, new Point(0, 0), "JernexCursor");
                theDesktop.setCursor(customCursor);
            }
        } catch (Exception e) {}

        //----------------------------------------

        FlashActionsApp.Init(theDesktop);

        //TaskBar
        TaskBar.Create(theDesktop);

        VMFilesManagement.TXT(theDesktop, "teste de texto", "Obrigado por utilizar a jernex Machine!");
        DialogMsgApp.CreateWarningMsg(theDesktop, "VERSÃO BETA", "AVISO: você está utilizando uma versão beta da jernex machine! significa que podem ocorrer falhas ou crashs durante o uso!");

        //end-----------------
        theRoot.add(theDesktop);
        theRoot.revalidate();
        theRoot.repaint();
    }

    public static void Shutdow() {

        URL shutdownAudio = FilesManagement.SearchArchiveInThisJar("JernexMachineSystem/assets/JernexShutdownAudio.wav", CoreUI.class);
        SoundManagement.PlaySoundClipInThisJar(shutdownAudio);

        if (theDesktop != null) {
            theRoot.remove(theDesktop);
            theDesktop = null;
        }

        URL iconeURL = FilesManagement.SearchArchiveInThisJar("JernexMachineSystem/assets/JernexMachineA1Logo.png", CoreUI.class);

        URL iconURL = JernexBootLoader.class.getResource("/JernexMachineSystem/assets/JernexMachineA1Logo.png");

        ImageIcon icone = new ImageIcon(iconURL);

        ImageIcon appIcon = ImageManagement.SearchArchiveInThisJarAndTransformToIcon("JernexMachineSystem/assets/JernexMachineA1Logo.png", CoreUI.class);


        JLabel loadingLabelIcon = new JLabel(icone);
        JLabel ShutdownLabel = new JLabel("Switching off...");
        Font fonte = new Font("Arial", Font.BOLD, 20);
        ShutdownLabel.setFont(fonte);


        int larguraImg = icone.getIconWidth();
        int alturaImg = icone.getIconHeight();
        ShutdownLabel.setBounds(10, 250, 200, 25);
        loadingLabelIcon.setBounds(10, 0, larguraImg, alturaImg);

        theRoot.add(loadingLabelIcon);
        theRoot.add(ShutdownLabel);

        theRoot.revalidate();
        theRoot.repaint();

        Timer timer = new Timer(7000, null);
        timer.addActionListener( e -> {
            timer.stop();

            theRoot.dispatchEvent(new WindowEvent(theRoot, WindowEvent.WINDOW_CLOSING));
        });


        timer.setRepeats(false);
        timer.start();
    }
    public static void Restart() {
        theDesktop.removeAll();
        JernexBootLoader.RestartFunction();
    }
}

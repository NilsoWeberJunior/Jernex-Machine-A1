package JernexMachineSystem;

import java.awt.*;
import javax.swing.*;
import java.awt.event.WindowEvent;
import java.net.URL;

import JernexMachineSystem.JernexSystemComponents.FilesManagement;
import JernexMachineSystem.JernexSystemComponents.ImageManagement;
import JernexMachineSystem.JernexSystemComponents.VMFilesManagement;
import JernexMachineSystem.JernexApps.DialogMsgApp;
import JernexMachineSystem.JernexInternalApps.TaskBar;
import JernexMachineSystem.JernexSystemComponents.SoundManagement;
import JernexMachineSystem.JernexSystemComponents.FilesManagement;

public class CoreUI {
    public static JFrame theRoot;
    public static JDesktopPane theDesktop;

    public static void Main(JFrame mainRoot) {
        // ... (Seu código do cursor igualzinho) ...
        theRoot = mainRoot;
        try {
            URL cursorURL = FilesManagement.SearchArchiveInThisJar("JernexMachineSystem/assets/JernexMachineCursor.png", CoreUI.class);
            if (cursorURL != null) {
                Toolkit toolkit = Toolkit.getDefaultToolkit();
                Image cursorImage = toolkit.getImage(cursorURL);
                Cursor customCursor = toolkit.createCustomCursor(cursorImage, new Point(0, 0), "JernexCursor");
                theRoot.setCursor(customCursor);
            }
        } catch (Exception e) {}

        //desktop---------------------------------
        theDesktop = new JDesktopPane();
        theDesktop.setBackground(Color.blue);
        //----------------------------------------

        try {
            theDesktop.setCursor(theRoot.getCursor());
        } catch (Exception e) {}

        //TaskBar
        TaskBar.Create(theDesktop);

        VMFilesManagement.TXT(theDesktop, "teste de texto", "Obrigado por utilizar a jernex Machine!");
        DialogMsgApp.Create(theDesktop, "VERSÃO BETA", "AVISO: você está utilizando uma versão beta da jernex machine! significa que podem ocorrer falhas ou crashs durante o uso!");

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


        JDesktopPane loading = new JDesktopPane();

        loading.setBackground(Color.black);

        URL iconeURL = FilesManagement.SearchArchiveInThisJar("JernexMachineSystem/assets/JernexMachineA1Logo.png", CoreUI.class);

        ImageIcon icone = new ImageIcon(iconeURL);

        ImageIcon appIcon = ImageManagement.SearchArchiveInThisJarAndTransformToIcon("JernexMachineSystem/assets/JernexMachineA1Logo.png", CoreUI.class);


        JLabel loadingLabelIcon = new JLabel(icone);
        JLabel ShutdownLabel = new JLabel("Switching off...");
        Font fonte = new Font("Arial", Font.BOLD, 20);
        ShutdownLabel.setFont(fonte);


        int larguraImg = icone.getIconWidth();
        int alturaImg = icone.getIconHeight();
        ShutdownLabel.setBounds(10, 250, 200, 25);
        loadingLabelIcon.setBounds(10, 0, larguraImg, alturaImg);


        loading.add(loadingLabelIcon);
        loading.add(ShutdownLabel);

        theRoot.add(loading);

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
}

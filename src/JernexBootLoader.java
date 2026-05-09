import JernexMachineSystem.CoreUI;
import javax.swing.*;
import java.awt.*;
import java.net.URL;
import JernexMachineSystem.JernexSystemComponents.FilesManagement;
import JernexMachineSystem.JernexSystemComponents.SoundManagement;
import JernexMachineSystem.JernexSystemComponents.ImageManagement;

public class JernexBootLoader {
    public static void Init(JDesktopPane Loading, JFrame root) {
        root.remove(Loading);
        Loading = null;
        CoreUI.Main(root);
        URL somInicializacao = FilesManagement.SearchArchiveInThisJar("JernexMachineSystem/assets/JernexMachineInitAudio.wav", JernexBootLoader.class);

        if (somInicializacao != null) {
            SoundManagement.PlaySoundClipInThisJar(somInicializacao);
        } else {
            System.err.println("Aviso: Áudio de inicialização não encontrado!");
        }
//
    }

    public static void main(String[] args) {
        //root----------------------------------------------------------------
        JFrame root = new JFrame("NWJ Software Labs - Jernex Machine A1 (BETA)");
        root.setSize(900, 900);
        root.setLocationRelativeTo(null);
        //--------------------------------------------------------------------

        JDesktopPane loading = new JDesktopPane();

        loading.setBackground(Color.black);

        URL iconeURL = FilesManagement.SearchArchiveInThisJar("JernexMachineSystem/assets/JernexMachineA1Logo.png", JernexBootLoader.class);

        ImageIcon icone = new ImageIcon(iconeURL);

        ImageIcon appIcon = ImageManagement.SearchArchiveInThisJarAndTransformToIcon("JernexMachineSystem/assets/JernexMachineA1Logo.png", JernexBootLoader.class);

        if (appIcon != null) {

            Image iconImage = appIcon.getImage();


            root.setIconImage(iconImage);
        }



        JLabel loadingLabelIcon = new JLabel(icone);
        JProgressBar loadingProgressBar = new JProgressBar(0, 100);
        loadingProgressBar.setUI(new javax.swing.plaf.basic.BasicProgressBarUI());
        loadingProgressBar.setValue(10);
        loadingProgressBar.setForeground(Color.blue);


        int larguraImg = icone.getIconWidth();
        int alturaImg = icone.getIconHeight();
        loadingProgressBar.setBounds((900 - larguraImg) / 2, (900 - alturaImg) / 2, 200, 25);
        loadingLabelIcon.setBounds((900 - larguraImg) / 2, (900 - alturaImg) / 5, larguraImg, alturaImg);


        loading.add(loadingLabelIcon);
        loading.add(loadingProgressBar);

        root.add(loading);

        Timer timer = new Timer(500, null);
        timer.addActionListener( e -> {
            int ProgressbarValue = loadingProgressBar.getValue();
            if (ProgressbarValue < 100) {
                loadingProgressBar.setValue(ProgressbarValue + 10);
            } else {
                timer.stop();
                Init(loading, root);
                root.revalidate();
                root.repaint();
            }
        });
        timer.start();


        //end-----------------------------------------------
        root.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        root.setVisible(true);
        //--------------------------------------------------
    }
}
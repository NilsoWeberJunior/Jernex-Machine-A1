package JernexInitialBoot;

import JernexMachineSystem.CoreUI;
import javax.swing.*;
import java.awt.*;
import java.net.URL;
import JernexMachineSystem.JernexSystemComponents.FilesManagement;
import JernexMachineSystem.JernexSystemComponents.SoundManagement;
import JernexMachineSystem.JernexSystemComponents.ImageManagement;


public class JernexBootLoader {

    public static JFrame root = new JFrame("NWJ Software Labs - Jernex Machine A1 (BETA)");

    public static void Init(JFrame root) {
        root.getContentPane().removeAll();
        root.revalidate();
        root.repaint();

        CoreUI.Main(root);
        URL somInicializacao = FilesManagement.SearchArchiveInThisJar("JernexMachineSystem/assets/JernexMachineInitAudio.wav", JernexBootLoader.class);

        if (somInicializacao != null) {
            SoundManagement.PlaySoundClipInThisJar(somInicializacao);
        } else {
            System.err.println("Aviso: Áudio de inicialização não encontrado!");
        }

    }

    public static void Igniter() {


        //root----------------------------------------------------------------
        root.setSize(900, 900);
        root.setLocationRelativeTo(null);
        root.setLayout(null);
        root.getContentPane().setBackground(Color.black);

        try {
            try {
                URL cursorURL = FilesManagement.SearchArchiveInThisJar("JernexMachineSystem/assets/JernexMachineNoCursor.png", JernexBootLoader.class);
                if (cursorURL != null) {
                    Toolkit toolkit = Toolkit.getDefaultToolkit();
                    Image cursorImage = toolkit.getImage(cursorURL);
                    Cursor customCursor = toolkit.createCustomCursor(cursorImage, new Point(0, 0), "JernexCursor");
                    root.setCursor(customCursor);
                }
            } catch (Exception e) {
            }

            //--------------------------------------------------------------------

            URL iconeURL = FilesManagement.SearchArchiveInThisJar("JernexMachineSystem/assets/JernexMachineA1Logo.png", JernexBootLoader.class);

            ImageIcon icone = new ImageIcon(iconeURL);

            ImageIcon appIcon = ImageManagement.SearchArchiveInThisJarAndTransformToIcon("JernexMachineSystem/assets/JernexMachineA1Logo.png", JernexBootLoader.class);

            if (appIcon != null) {

                Image iconImage = appIcon.getImage();

                root.setIconImage(iconImage);
            }


            JLabel loadingLabelIcon = new JLabel(icone);
            JLabel loadingWorkspaceLabel = new JLabel("Preparing your workspace...");
            loadingWorkspaceLabel.setFont(new Font("Arial", Font.BOLD, 20));


            int larguraImg = icone.getIconWidth();
            int alturaImg = icone.getIconHeight();
            loadingWorkspaceLabel.setBounds(10, 250, 270, 30);
            loadingLabelIcon.setBounds(10, 0, larguraImg, alturaImg);


            root.add(loadingLabelIcon);
            root.add(loadingWorkspaceLabel);




            Timer timer = new Timer(3000, null);
            timer.addActionListener(e -> {
                timer.stop();
                Init(root);
                root.revalidate();
                root.repaint();
            });
            timer.setRepeats(false);
            timer.start();


        } catch (Exception e) {
            JLabel FatalErrorLabel = new JLabel("Fatal Error has occurred!");
            FatalErrorLabel.setFont(new Font("Arial", Font.BOLD, 20));
            FatalErrorLabel.setBounds(1, 1, 300, 100);
            FatalErrorLabel.setForeground(Color.white);
            root.add(FatalErrorLabel);

            JLabel FatalErrorLabel2 = new JLabel("Bios Error: Error to load a boot from Jernex Machine");
            FatalErrorLabel2.setFont(new Font("Arial", Font.BOLD, 15));
            FatalErrorLabel2.setBounds(1, 50, 500, 100);
            FatalErrorLabel2.setForeground(Color.white);
            root.add(FatalErrorLabel2);

            JTextArea FatalErrorCode = new JTextArea();
            FatalErrorCode.setFont(new Font("Arial", Font.BOLD, 15));
            FatalErrorCode.setBounds(1, 150, 500, 500);
            FatalErrorCode.setLineWrap(true);
            FatalErrorCode.setEditable(false);
            FatalErrorCode.setWrapStyleWord(true);
            FatalErrorCode.setBackground(Color.black);
            FatalErrorCode.setForeground(Color.white);
            FatalErrorCode.append("Error Code (Internal): " + e);

            root.add(FatalErrorCode);
        }
            //end-----------------------------------------------
            root.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            root.setVisible(true);
            //--------------------------------------------------
    }

    public static void RestartFunction() {
        root.getContentPane().removeAll();

        root.revalidate();
        root.repaint();

        URL iconeURL = FilesManagement.SearchArchiveInThisJar("JernexMachineSystem/assets/JernexMachineA1Logo.png", CoreUI.class);

        ImageIcon icone = new ImageIcon(iconeURL);

        JLabel loadingLabelIcon = new JLabel(icone);
        JLabel RestartLabel = new JLabel("Restarting...");
        Font fonte = new Font("Arial", Font.BOLD, 20);
        RestartLabel.setFont(fonte);


        int larguraImg = icone.getIconWidth();
        int alturaImg = icone.getIconHeight();
        RestartLabel.setBounds(10, 250, 200, 25);
        loadingLabelIcon.setBounds(10, 0, larguraImg, alturaImg);

        root.add(RestartLabel);
        root.add(loadingLabelIcon);

        Timer timer = new Timer(5000, e -> {
            root.setVisible(false);
            root.dispose();
            root = new JFrame("NWJ Software Labs - Jernex Machine A1 (BETA)");
            Igniter();
        });
        timer.setRepeats(false);
        timer.start();


    }

    public static void main(String[] args) {
        Igniter();
    }
}
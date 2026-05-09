package JernexMachineSystem;

import java.awt.*;
import javax.swing.*;
import java.net.URL;

import JernexMachineSystem.JernexSystemComponents.FilesManagement;
import JernexMachineSystem.JernexSystemComponents.ImageManagement;
import JernexMachineSystem.JernexInternalApps.InitMenu;
import JernexMachineSystem.JernexSystemComponents.VMFilesManagement;
import JernexMachineSystem.JernexApps.DialogMsgApp;

public class CoreUI {
    public static boolean InitButtonIsOpened = false;
    public static void Main(JFrame mainRoot) {
        //cursor-----------------------------------------------------------------------------------
        try {
            URL cursorURL = FilesManagement.SearchArchiveInThisJar("JernexMachineSystem/assets/JernexMachineCursor.png", CoreUI.class);

            if (cursorURL != null) {
                Toolkit toolkit = Toolkit.getDefaultToolkit();
                Image cursorImage = toolkit.getImage(cursorURL);

                Cursor customCursor = toolkit.createCustomCursor(cursorImage, new Point(0, 0), "JernexCursor");

                mainRoot.setCursor(customCursor);

            } else {
                System.out.println("Erro: Imagem do cursor não encontrada dentro do JAR!");
            }
        } catch (Exception e) {
            System.out.println("Erro ao carregar o cursor: " + e.getMessage());
        }

        //-------------------------------------------------------------------------------------------------------------------------------------

        //desktop---------------------------------
        JDesktopPane Desktop = new JDesktopPane();
        Desktop.setBackground(Color.blue);
        //----------------------------------------


        //InitButtom
        ImageIcon init_ButtomIcon = ImageManagement.SearchArchiveInThisJarAndTransformToIcon("JernexMachineSystem/assets/JernexMachineA1Logo.png", CoreUI.class);

        Image initButtomIcon = init_ButtomIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);

        ImageIcon InitButtomIcon = new ImageIcon(initButtomIcon);
        JButton initButtom = new JButton(InitButtomIcon);

        InitMenu.Create(Desktop);

        initButtom.addActionListener(E -> {
            if (!InitButtonIsOpened) {
                InitMenu.Open();
                InitButtonIsOpened = true;
            } else {
                InitMenu.Close();
                InitButtonIsOpened = false;
            }
            Desktop.repaint();
        });



        try {
            Desktop.setCursor(mainRoot.getCursor());
        } catch (Exception e) {}

        //TaskBar-------------------------------------------------------------------------------------------
        JPanel taskbar = new JPanel();
        taskbar.setBackground(new Color(30, 30, 30, 200)); // O '200' dá uma leve transparência!
        taskbar.setLayout(new FlowLayout(FlowLayout.LEFT));

        taskbar.setBounds(0, 0, 1920, 45);
        //---------------------------------------------------------------------------------------------------

        VMFilesManagement.TXT(Desktop, "teste de texto", "Obrigado por utilizar a jernex Machine!");

        DialogMsgApp.Create(Desktop, "VERSÃO BETA", "AVISO: você está utilizando uma versão beta da JernexMachine geração A1, ou seja, podem ocorrer falhas durante o uso ou crashs indevidos. Agradeço sua compreenção :)");


        //end-----------------
        taskbar.add(initButtom);
        Desktop.add(taskbar);
        mainRoot.add(Desktop);
        mainRoot.revalidate();
        mainRoot.repaint();
        //--------------------
    }

    public static void AppCreator(String appName, int appY, int appX, boolean resizable, boolean closable, boolean maximizable, boolean iconifiable) {
        JInternalFrame appWindow = new JInternalFrame(appName, resizable, closable, maximizable, iconifiable);
        appWindow.setSize(300, 400);
        appWindow.setLocation(50, 50);
        appWindow.setVisible(true);


        appWindow.setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
    }
}

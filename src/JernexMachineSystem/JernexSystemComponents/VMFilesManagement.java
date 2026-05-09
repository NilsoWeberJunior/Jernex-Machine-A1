package JernexMachineSystem.JernexSystemComponents;



import JernexMachineSystem.JernexApps.NotesApp;
import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import JernexMachineSystem.JernexSystemComponents.ImageManagement;



public class VMFilesManagement {

    public static HashMap<String, String> VirtualHD = new HashMap<>();



    public static void TXT(JDesktopPane Desktop, String ArchiveName, String Content) {
        String ArchiveFinalName = ArchiveName + ".txt";

        VirtualHD.put(ArchiveName, Content);

//

        ImageIcon TXTFileIcon = ImageManagement.SearchArchiveInThisJarAndTransformToIcon("JernexMachineSystem/assets/JernexTXTFileLogo.png", VMFilesManagement.class);

        JLabel TXTFile = new JLabel(ArchiveFinalName, TXTFileIcon, SwingConstants.CENTER);



        TXTFile.setBounds(50, 50, 50, 50);

        TXTFile.setVerticalTextPosition(SwingConstants.BOTTOM);
        TXTFile.setHorizontalTextPosition(SwingConstants.CENTER);

        TXTFile.setToolTipText("Archive name: '" + ArchiveFinalName + "'");



        TXTFile.addMouseListener(new java.awt.event.MouseAdapter() {

            public void mouseClicked(java.awt.event.MouseEvent evt) {

                if (evt.getClickCount() == 2) {

                    String ArchiveContent = VirtualHD.get(ArchiveName);

                    NotesApp.OpenArchive(Desktop, ArchiveName, ArchiveContent);

                    Desktop.repaint();
                }
            }
        });



        Desktop.add(TXTFile);
        Desktop.repaint();
    }

}

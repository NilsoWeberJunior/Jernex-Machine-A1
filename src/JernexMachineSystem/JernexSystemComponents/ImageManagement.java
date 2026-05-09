package JernexMachineSystem.JernexSystemComponents;

import javax.swing.*;
import java.net.URL;

public class ImageManagement {
    public static ImageIcon SearchArchiveInThisJarAndTransformToIcon(String fileName, Class<?> referenceClass) {
        URL fileURLIcon = referenceClass.getResource("/" + fileName);
        ImageIcon icon = new ImageIcon(fileURLIcon);

        return icon;
    }
}

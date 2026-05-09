package JernexMachineSystem.JernexSystemComponents;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class FilesManagement {
    public static URL SearchArchiveInThisJar(String fileName, Class<?> referenceClass) {
        URL fileURL = referenceClass.getResource("/" + fileName);

        return fileURL;
    }
    public static URL SearchArchiveInThisRelativeLocal(String fileName, Class<?> referenceClass) {
        URL fileURLRelative = referenceClass.getResource(fileName);

        return fileURLRelative;
    }
}

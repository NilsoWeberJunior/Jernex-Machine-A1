package JernexMachineSystem.JernexInternalApps;

import javax.swing.*;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import JernexMachineSystem.JernexSystemComponents.SoundManagement;
import JernexMachineSystem.JernexSystemComponents.FilesManagement;
import JernexMachineSystem.JernexApps.DialogMsgApp;

public class JernexJunorExecutionEnvironment {
    private static final Pattern SHOW_PATTERN = Pattern.compile("^show:\\(\"(.*)\"\\)\\|$");
    private static final Pattern SecretAUDIOPattern = Pattern.compile("^components\\.audio\\.play:\\(\"secret_audio\"\\)\\|$");
    private static final Pattern CreateMessageDialogPattern = Pattern.compile("^components\\.msg\\.msgbox\\.dialog\\.create:\\(title=\"(.*)\",\\smsg=\"(.*)\"\\)\\|");
    public static String RuntimeStartVerify(String mainInput, JDesktopPane TheDesktop) {
        String commandLine = mainInput.trim();


        Matcher showMatcher = SHOW_PATTERN.matcher(commandLine);
        Matcher secretAudioMatcher = SecretAUDIOPattern.matcher(commandLine);
        Matcher msgBoxCreatorMatcher = CreateMessageDialogPattern.matcher(commandLine);

        if (commandLine.isEmpty()) {
            return "";
        }

        else if (showMatcher.matches()) {
            return showMatcher.group(1);
        }


        else if (commandLine.equalsIgnoreCase("help")) {
            return "Comandos da Jernex:\nshow:(\"text\")| - Exibe um texto na tela verde\n" +
                    "components.audio.play:(\"secret_audio\")| - Easter Egg\n" +
                    "components.msg.msgbox.dialog.create:(title=\"text\", msg=\"text\")| - Easter Egg";
        }
        else if (msgBoxCreatorMatcher.matches()) {
            String msgTitle = msgBoxCreatorMatcher.group(1);
            String msgMsg = msgBoxCreatorMatcher.group(2);
            DialogMsgApp.Create(TheDesktop, msgTitle, msgMsg);
            return "create a dialog box: title: \"" + msgTitle + "\" msg: \"" + msgMsg + "\"";
        }

        else if (secretAudioMatcher.matches()) {
            URL somInicializacao = FilesManagement.SearchArchiveInThisJar("JernexMachineSystem/assets/JernexMachineOLDInitAudio.wav", JernexJunorExecutionEnvironment.class);

            SoundManagement.PlaySoundClipInThisJar(somInicializacao);

            return "Playing Secret Sound";
        }


        return "Erro de Sintaxe: Comando inválido ou mal formatado: \"" + commandLine + "\" <- AQUI";
    }
}

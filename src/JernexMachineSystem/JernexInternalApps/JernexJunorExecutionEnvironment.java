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
    private static final Pattern CreateMsgWarningDialogPattern = Pattern.compile("^components\\.msg\\.msgbox\\.dialog\\.createWarningMsg:\\(title=\"(.*)\",\\smsg=\"(.*)\"\\)\\|");
    private static final Pattern CreateMsgErrorDialogPattern = Pattern.compile("^components\\.msg\\.msgbox\\.dialog\\.createErrorMsg:\\(title=\"(.*)\",\\smsg=\"(.*)\"\\)\\|");
    private static final Pattern CreateMsgInfoDialogPattern = Pattern.compile("^components\\.msg\\.msgbox\\.dialog\\.createInfoMsg:\\(title=\"(.*)\",\\smsg=\"(.*)\"\\)\\|");

    public static String RuntimeStartVerify(String mainInput, JDesktopPane TheDesktop) {
        String commandLine = mainInput.trim();


        Matcher showMatcher = SHOW_PATTERN.matcher(commandLine);
        Matcher secretAudioMatcher = SecretAUDIOPattern.matcher(commandLine);
        Matcher msgWarningBoxCreatorMatcher = CreateMsgWarningDialogPattern.matcher(commandLine);
        Matcher msgErrorBoxCreatorMatcher = CreateMsgErrorDialogPattern.matcher(commandLine);
        Matcher msgInfoBoxCreatorMatcher = CreateMsgInfoDialogPattern.matcher(commandLine);

        if (commandLine.isEmpty()) {
            return "";
        }

        else if (showMatcher.matches()) {
            return showMatcher.group(1);
        }


        else if (commandLine.equalsIgnoreCase("help")) {
            return "Comandos da Jernex:\nshow:(\"text\")| - Exibe um texto na tela verde\n" +
                    "components.audio.play:(\"secret_audio\")| - Easter Egg\n" +
                    "components.msg.msgbox.dialog.createWarningMsg:(title=\"text\", msg=\"text\")| - cria uma caixa de dialogo de aviso\n" +
                    "components.msg.msgbox.dialog.createInfoMsg:(title=\"text\", msg=\"text\")| - cria uma caixa de dialogo de informação\n" +
                    "components.msg.msgbox.dialog.createErrorMsg:(title=\"text\", msg=\"text\")| - cria uma caixa de dialogo de erro";

        }
        else if (msgWarningBoxCreatorMatcher.matches()) {
            String msgTitle = msgWarningBoxCreatorMatcher.group(1);
            String msgMsg = msgWarningBoxCreatorMatcher.group(2);
            DialogMsgApp.CreateWarningMsg(TheDesktop, msgTitle, msgMsg);
            return "create a warning dialog box: title: \"" + msgTitle + "\" msg: \"" + msgMsg + "\"";
        }

        else if (msgInfoBoxCreatorMatcher.matches()) {
            String msgTitle = msgInfoBoxCreatorMatcher.group(1);
            String msgMsg = msgInfoBoxCreatorMatcher.group(2);
            DialogMsgApp.CreateInfoMsg(TheDesktop, msgTitle, msgMsg);
            return "create a info dialog box: title: \"" + msgTitle + "\" msg: \"" + msgMsg + "\"";
        }

        else if (msgErrorBoxCreatorMatcher.matches()) {
            String msgTitle = msgErrorBoxCreatorMatcher.group(1);
            String msgMsg = msgErrorBoxCreatorMatcher.group(2);
            DialogMsgApp.CreateErrorMsg(TheDesktop, msgTitle, msgMsg);
            return "create a error dialog box: title: \"" + msgTitle + "\" msg: \"" + msgMsg + "\"";
        }

        else if (secretAudioMatcher.matches()) {
            URL somInicializacao = FilesManagement.SearchArchiveInThisJar("JernexMachineSystem/assets/JernexMachineOLDInitAudio.wav", JernexJunorExecutionEnvironment.class);

            SoundManagement.PlaySoundClipInThisJar(somInicializacao);

            return "Playing Secret Sound";
        }


        return "Erro de Sintaxe: Comando inválido ou mal formatado: \"" + commandLine + "\" <- AQUI";
    }
}

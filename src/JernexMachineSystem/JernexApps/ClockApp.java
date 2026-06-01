package JernexMachineSystem.JernexApps;

import JernexMachineSystem.JernexSystemComponents.FilesManagement;
import JernexMachineSystem.JernexSystemComponents.ImageManagement;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.Date;
import JernexMachineSystem.JernexApps.DialogMsgApp;
import JernexMachineSystem.JernexSystemComponents.SoundManagement;

public class ClockApp {
    public static boolean timeStarted = false;
    public static void Create(JDesktopPane desktop, JPanel initMenu) {
        JButton appButton = new JButton("Clock");

        appButton.addActionListener(e -> {
            JInternalFrame appWindow = new JInternalFrame("Clock App", true, true, true, true);
            appWindow.setSize(400, 500);
            appWindow.setLocation(50, 50);
            appWindow.setVisible(true);
            Container windowPanel = appWindow.getContentPane();
            windowPanel.setBackground(Color.CYAN);

            windowPanel.setLayout(new BoxLayout(windowPanel, BoxLayout.Y_AXIS));

            Dimension maxDimension = new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE);


            //clock----------------------------------------------------------------------------------------------------------------------------------------------------------
            JPanel clockPanel = new JPanel();
            clockPanel.setMaximumSize(maxDimension);
            clockPanel.setBorder(BorderFactory.createLineBorder(Color.black, 2));
            windowPanel.add(clockPanel);
            JLabel clockLabel = new JLabel("getting hours...");
            clockLabel.setFont(new Font("Arial", Font.BOLD, 50));
            clockPanel.add(clockLabel);
            Timer clockTimer = new Timer(90, e1 -> {
                Date ClockTime = new Date();

                clockLabel.setText(Integer.toString(ClockTime.getHours()) + ":" + Integer.toString(ClockTime.getMinutes()) + ":" + Integer.toString(ClockTime.getSeconds()));
            });
            clockTimer.start();
            //----------------------------------------------------------------------------------------------------------------------------------------------------------------


            //timer-----------------------------------------------------------------------
            SpinnerNumberModel hSpinnerModel = new SpinnerNumberModel(0, 0, 60, 1);
            SpinnerNumberModel mSpinnerModel = new SpinnerNumberModel(0, 0, 60, 1);
            SpinnerNumberModel sSpinnerModel = new SpinnerNumberModel(0, 0, 60, 1);



            JPanel timerPanel = new JPanel();
            timerPanel.setLayout(new BoxLayout(timerPanel, BoxLayout.Y_AXIS)); // Alinha visor e controles verticalmente
            timerPanel.setMaximumSize(maxDimension);
            timerPanel.setBorder(BorderFactory.createLineBorder(Color.black, 2));
            windowPanel.add(timerPanel);

            // Visor do tempo centralizado
            Font timerVisorFont = new Font("Arial", Font.BOLD, 30);
            JLabel timerVisor = new JLabel("00:00:00");
            timerVisor.setFont(timerVisorFont);
            timerVisor.setAlignmentX(Component.CENTER_ALIGNMENT); // Centraliza horizontalmente no BoxLayout

            // Espaço entre o topo e o visor
            timerPanel.add(Box.createVerticalGlue());
            timerPanel.add(timerVisor);
            timerPanel.add(Box.createVerticalStrut(15)); // Espaço fixo entre o visor e os seletores


            JPanel TimersPart = new JPanel(new GridBagLayout());
            TimersPart.setOpaque(false);

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(2, 10, 2, 10); // Margens internas entre os componentes
            gbc.gridy = 0;

            // Label Horas (Coluna 0)
            JLabel SpinnerHoursLabel = new JLabel("Hours", SwingConstants.CENTER);
            gbc.gridx = 0;
            TimersPart.add(SpinnerHoursLabel, gbc);

            // Label Minutos (Coluna 1)
            JLabel SpinnerMinutesLabel = new JLabel("Minutes", SwingConstants.CENTER);
            gbc.gridx = 1;
            TimersPart.add(SpinnerMinutesLabel, gbc);

            // Label Segundos (Coluna 2)
            JLabel SpinnerSecondsLabel = new JLabel("Seconds", SwingConstants.CENTER);
            gbc.gridx = 2;
            TimersPart.add(SpinnerSecondsLabel, gbc);

            gbc.gridy = 1; // Linha 1: Os Spinners

            // Spinner Horas (Coluna 0)
            JSpinner SpinnerHours = new JSpinner(hSpinnerModel);
            SpinnerHours.setPreferredSize(new Dimension(60, 25));
            gbc.gridx = 0;
            TimersPart.add(SpinnerHours, gbc);

            // Spinner Minutos (Coluna 1)
            JSpinner SpinnerMinutes = new JSpinner(mSpinnerModel);
            SpinnerMinutes.setPreferredSize(new Dimension(60, 25));
            gbc.gridx = 1;
            TimersPart.add(SpinnerMinutes, gbc);

            // Spinner Segundos (Coluna 2)
            JSpinner SpinnerSeconds = new JSpinner(sSpinnerModel);
            SpinnerSeconds.setPreferredSize(new Dimension(60, 25));
            gbc.gridx = 2;
            TimersPart.add(SpinnerSeconds, gbc);

            gbc.gridy = 2;
            gbc.gridx = 1;

            //start/stop
            JButton stopStartTimer = new JButton("Iniciar");
            TimersPart.add(stopStartTimer, gbc);

            final int[] tempoRestanteSegundos = {0};

            Timer timerTimer = new Timer(1000, e1 -> {
                if (tempoRestanteSegundos[0] > 0) {
                    // Diminui 1 segundo
                    tempoRestanteSegundos[0]--;

                    // Calcula horas, minutos e segundos restantes para exibir no visor
                    int h = tempoRestanteSegundos[0] / 3600;
                    int m = (tempoRestanteSegundos[0] % 3600) / 60;
                    int s = tempoRestanteSegundos[0] % 60;

                    // Atualiza o visor formatando com dois dígitos (ex: 02:05:09)
                    timerVisor.setText(String.format("%02d:%02d:%02d", h, m, s));
                } else {
                    // O tempo acabou!
                    ((Timer) e1.getSource()).stop();
                    timeStarted = false;
                    stopStartTimer.setText("Iniciar");
                    URL finishTimerSong = FilesManagement.SearchArchiveInThisJar("JernexMachineSystem/assets/JernexClockAppFinishTimerSong.wav", ClockApp.class);
                    SoundManagement.PlaySoundClipInThisJar(finishTimerSong);
                    DialogMsgApp.CreateInfoMsg(desktop, "Tempo esgotado", "O tempo do timer acabou!");

                }
            });

            stopStartTimer.addActionListener(e1 -> {
                if (!timeStarted) {
                    // Pega os valores atuais que o usuário selecionou nos spinners
                    int horas = (int) hSpinnerModel.getValue();
                    int minutos = (int) mSpinnerModel.getValue();
                    int segundos = (int) sSpinnerModel.getValue();

                    // Converte tudo para um total geral de segundos
                    tempoRestanteSegundos[0] = (horas * 3600) + (minutos * 60) + segundos;

                    // Só inicia se o usuário tiver colocado algum tempo maior que zero
                    if (tempoRestanteSegundos[0] > 0) {
                        timerTimer.start();
                        int h = tempoRestanteSegundos[0] / 3600;
                        int m = (tempoRestanteSegundos[0] % 3600) / 60;
                        int s = tempoRestanteSegundos[0] % 60;
                        timeStarted = true;
                        stopStartTimer.setText("Parar");
                        timerVisor.setText(String.format("%02d:%02d:%02d", h, m, s));

                    } else {
                        DialogMsgApp.CreateErrorMsg(desktop, "Erro", "Insira um valor maior do que 0");
                    }
                } else {
                    // Se já estava rodando, para o timer
                    timerTimer.stop();
                    timeStarted = false;
                    stopStartTimer.setText("Iniciar");
                }
            });



            TimersPart.setAlignmentX(Component.CENTER_ALIGNMENT);
            timerPanel.add(TimersPart);
            timerPanel.add(Box.createVerticalGlue());
            appWindow.addInternalFrameListener(new javax.swing.event.InternalFrameAdapter() {
                @Override
                public void internalFrameClosed(javax.swing.event.InternalFrameEvent e1) {
                    timerTimer.stop();
                }
            });
            //----------------------------------------------------------------------------

            //em andamento
            //chronometer-----------------------------------------------------------------------
            //JPanel chronometerPanel = new JPanel();
            //chronometerPanel.setMaximumSize(maxDimension);
            //chronometerPanel.setBorder(BorderFactory.createLineBorder(Color.black, 2));
            //windowPanel.add(chronometerPanel);
            //----------------------------------------------------------------------------------


            appWindow.addInternalFrameListener(new javax.swing.event.InternalFrameAdapter() {
                @Override
                public void internalFrameClosed(javax.swing.event.InternalFrameEvent e1) {
                    clockTimer.stop();
                }
            });

            clockPanel.setBackground(Color.CYAN);
            timerPanel.setBackground(Color.CYAN);
            //chronometerPanel.setBackground(Color.CYAN);


            ImageIcon appIconFile = ImageManagement.SearchArchiveInThisJarAndTransformToIcon("JernexMachineSystem/assets/JernexClockAppIcon.png", NotesApp.class);
            Image appIcon = appIconFile.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
            appWindow.setFrameIcon(new ImageIcon(appIcon));

            appWindow.setCursor(desktop.getCursor());
            appWindow.setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);

            desktop.add(appWindow);
            appWindow.moveToFront();


        });

        initMenu.add(appButton);
    }

    public static void hoursPanel(JInternalFrame frame, int x, int y) {

        JPanel HoursPanel = new JPanel();
        HoursPanel.setOpaque(false);
        HoursPanel.setLayout(null);
        HoursPanel.setBackground(new Color(0,0,0,0));

        JLabel hoursVisor = new JLabel("00:00");
        hoursVisor.setBounds(x,y, 50, 10);
        HoursPanel.add(hoursVisor);


        Timer hours = new Timer(1000, e -> {
            Date Hours = new Date();
            hoursVisor.setText("");
            hoursVisor.setText(Integer.toString(Hours.getHours()) + ":" + Integer.toString(Hours.getMinutes()));
            HoursPanel.revalidate();
            HoursPanel.repaint();
        });

        hours.start();

        HoursPanel.setBounds(x, y, 50, 10);
        hoursVisor.setToolTipText("hora atual (do seu computador real)");

        HoursPanel.setVisible(true);


        frame.add(HoursPanel);
        frame.revalidate();
        frame.repaint();
    }
}

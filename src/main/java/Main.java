import model.Model;
import view.MainFrame;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        defineLookAndFeel();

        Model.getInstance().addObserver(MainFrame.getInstance());
        MainFrame.getInstance().start();
    }

    private static void defineLookAndFeel() {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            // If Nimbus is not available, fall back to cross-platform
            try {
                UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            } catch (Exception ignored) {

            }
        }
    }
}

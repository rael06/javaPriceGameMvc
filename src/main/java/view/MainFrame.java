package view;

import controller.Controller;
import model.Result;
import utils.MyObserver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MainFrame extends JFrame implements MyObserver<Result> {
    private static MainFrame instance;
    private Controller controller = Controller.getInstance();
    private JButton checkButton;
    private JTextField field;
    private JTextField resultScreen;
    private JTextField nbPlayedScreen;
    private JTextField lastNumberPlayedScreen;

    private MainFrame() {
    }

    public static MainFrame getInstance() {
        return instance = instance == null ? new MainFrame() : instance;
    }

    public void start() {
        init();
        initListeners();
    }

    private void init() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(800, 600));
        field = new JTextField(10);
        checkButton = new JButton("vérifier");
        resultScreen = new JTextField(10);
        nbPlayedScreen = new JTextField(10);
        lastNumberPlayedScreen = new JTextField(10);
        setButtonsProps(checkButton);

        gbc.weightx = 1;
        gbc.insets = new Insets(10, 0, 10, 0);
        gbc.gridy = 0;
        add(new JLabel("Saisir un nombre entre 1 et 100 inclus (vous pouvez appuyer sur la touche 'Entrer' pour valider)"), gbc);

        gbc.gridy++;
        gbc.weighty = 1;
        add(field, gbc);

        gbc.weighty = 0;
        gbc.gridy++;
        add(checkButton, gbc);

        gbc.gridy++;
        gbc.insets = new Insets(20, 0, 5, 0);
        add(new JLabel("C'est : "), gbc);

        gbc.gridy++;
        gbc.insets = new Insets(0, 0, 10, 0);
        add(resultScreen, gbc);

        gbc.gridy++;
        gbc.insets = new Insets(20, 0, 5, 0);
        add(new JLabel("Dernier coups joué : "), gbc);

        gbc.gridy++;
        gbc.insets = new Insets(0, 0, 10, 0);
        add(lastNumberPlayedScreen, gbc);

        gbc.gridy++;
        gbc.insets = new Insets(20, 0, 5, 0);
        add(new JLabel("Nombre de coups joué(s) : "), gbc);

        gbc.gridy++;
        gbc.insets = new Insets(0, 0, 10, 0);
        add(nbPlayedScreen, gbc);

        gbc.gridy++;
        gbc.weighty = 1;
        add(new JLabel(), gbc);

        pack();
        setVisible(true);
        setLocationRelativeTo(null);
    }

    private void setButtonsProps(JButton bouton) {
        bouton.setBackground(Color.CYAN);
    }

    private void initListeners() {
        checkButton.addActionListener(this::checkActionButton);
        field.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                if (e.getKeyChar() == KeyEvent.VK_ENTER) {
                    check();
                }
            }
        });
    }

    private void checkActionButton(ActionEvent e) {
        check();
    }

    private void check() {
        String number = field.getText();
        controller.compare(number);
    }

    @Override
    public void update(Result result) {
        if (result.isError()) {
            field.setText("");
            resultScreen.setText("erreur");
        } else {
            field.setText("");
            int comparison = result.getComparison();
            String comparisonStr = comparison == 0 ? "Gagné" : comparison > 0 ? "Plus" : "Moins";
            resultScreen.setText(comparisonStr);
            lastNumberPlayedScreen.setText(result.getLastNumberPlayed() + "");
            nbPlayedScreen.setText(result.getNbPlayed() + "");

            if (comparison == 0)
                replay();
        }
    }

    private void replay() {
        field.setEditable(false);

        Object[] options = {"Oui", "Non"};
        int n = JOptionPane.showOptionDialog(this,
                "Votre score est de : " + nbPlayedScreen.getText() + " tentative(s)\nVoulez - vous rejouer ? ",
                "Rejouer",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);

        if (n == 0) reset();
    }

    private void reset() {
        field.setEditable(true);
        resultScreen.setText("");
        nbPlayedScreen.setText("");
        lastNumberPlayedScreen.setText("");
        controller.restart();
    }
}

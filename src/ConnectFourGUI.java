package src;

import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class ConnectFourGUI extends JFrame {
    private static final int ROWS = 6;
    private static final int COLUMNS = 7;
    private JButton[][] buttons = new JButton[ROWS][COLUMNS];
    private Grid grid = new Grid();
    private Player player1;
    private Player player2;
    private Player currentPlayer;
    private JLabel[] scoreLabels = new JLabel[2];

    //Constructeur pour initialiser l'interface graphique et les joueurs
    public ConnectFourGUI(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        currentPlayer = player1;

        setTitle("Puissance 4");
        setSize(800, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        add(createBoardPanel(), BorderLayout.CENTER);
        add(createScorePanel(), BorderLayout.NORTH);
        setVisible(true);
    }

    //Crée le panneau de la grille de jeu
    private JPanel createBoardPanel() {
        JPanel boardPanel = new JPanel(new GridLayout(ROWS, COLUMNS));
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLUMNS; col++) {
                buttons[row][col] = createButton(row, col);
                boardPanel.add(buttons[row][col]);
            }
        }
        return boardPanel;
    }

    //Crée un bouton pour chaque cellule de la grille
    private JButton createButton(int row, int col) {
        JButton button = new JButton();
        button.setPreferredSize(new Dimension(100, 100));
        button.setBackground(Color.GRAY);
        button.setOpaque(true);
        button.addActionListener(e -> handleButtonClick(col));
        return button;
    }

    //Crée le panneau de score
    private JPanel createScorePanel() {
        JPanel scorePanel = new JPanel(new GridLayout(1, 2));
        scoreLabels[0] = new JLabel(player1.getPseudo() + ": " + player1.getScore());
        scoreLabels[1] = new JLabel(player2.getPseudo() + ": " + player2.getScore());
        scorePanel.add(scoreLabels[0]);
        scorePanel.add(scoreLabels[1]);
        return scorePanel;
    }

    //Gère les clics sur les boutons de la grille
    private void handleButtonClick(int col) {
        int row = grid.findAvailableRow(col);
        if (row != -1) {
            showTokenDropAnimation(col);
        }
    }

    //Affiche l'animation de la chute du jeton
    private void showTokenDropAnimation(int column) {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            int row = -1;
            int finalRow = grid.findAvailableRow(column);

            @Override
            public void run() {
                resetColumnColors(column);
                if (row < finalRow - 1) {
                    row++;
                    buttons[row][column].setBackground(currentPlayer.getToken() == 'O' ? Color.RED : Color.YELLOW);
                } else {
                    timer.cancel();
                    grid.makeMove(currentPlayer, column);
                    processEndOfTurn();
                }
            }
        };
        timer.scheduleAtFixedRate(task, 0, 100);
    }

    //Réinitialise les couleurs des cellules de la colonne spécifiée
    private void resetColumnColors(int column) {
        for (int i = 0; i < ROWS; i++) {
            char token = grid.getBoard()[i][column];
            buttons[i][column].setBackground(token == 'O' ? Color.RED : token == 'X' ? Color.YELLOW : Color.GRAY);
        }
    }

    //Traite la fin du tour
    private void processEndOfTurn() {
        updateBoard();
        if (ConnectFourGame.checkForWinner(grid, currentPlayer)) {
            JOptionPane.showMessageDialog(null, currentPlayer.getPseudo() + " wins!");
            currentPlayer.incrementScore();
            updateScoreLabels();
            resetBoard();
        } else if (grid.isBoardFull()) {
            JOptionPane.showMessageDialog(null, "It's a draw!");
            resetBoard();
        } else {
            switchPlayer();
        }
    }

    //Met à jour l'affichage de la grille
    private void updateBoard() {
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLUMNS; col++) {
                char token = grid.getBoard()[row][col];
                buttons[row][col].setBackground(token == 'O' ? Color.RED : token == 'X' ? Color.YELLOW : Color.GRAY);
            }
        }
    }

    //Met à jour le score
    private void updateScoreLabels() {
        scoreLabels[0].setText(player1.getPseudo() + ": " + player1.getScore());
        scoreLabels[1].setText(player2.getPseudo() + ": " + player2.getScore());
    }

    //Change de joueur pour le tour suivant
    private void switchPlayer() {
        currentPlayer = (currentPlayer == player1) ? player2 : player1;
    }

    //Réinitialise la grille de jeu
    private void resetBoard() {
        grid = new Grid();
        updateBoard();
    }

    //Lance l'interface graphique pour demander les noms des joueurs et démarrer le jeu
    public static void launchGUI() {
        JTextField player1NameField = new JTextField();
        JTextField player2NameField = new JTextField();
        Object[] message = {"Nom du Joueur 1:", player1NameField, "Nom du Joueur 2:", player2NameField};

        while (true) {
            int option = JOptionPane.showConfirmDialog(null, message, "Entrer les noms des joueurs", JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.OK_OPTION) {
                String player1Name = player1NameField.getText().trim();
                String player2Name = player2NameField.getText().trim();
                if (!player1Name.isEmpty() && !player2Name.isEmpty()) {
                    new ConnectFourGUI(new Player(player1Name, 0, 'O'), new Player(player2Name, 0, 'X'));
                    break;
                } else {
                    JOptionPane.showMessageDialog(null, "Les deux joueurs doivent entrer un nom!", "Erreur de saisie", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                System.exit(0);
            }
        }
    }

    //Méthode principale du code
    public static void main(String[] args) {
        launchGUI();
    }
}

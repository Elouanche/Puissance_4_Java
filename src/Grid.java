package src;

public class Grid {
    private static final int ROWS = 6;
    private static final int COLUMNS = 7;
    private char[][] grid = new char[ROWS][COLUMNS];

    //Constructeur pour initialiser la grille avec des cellules vides
    public Grid() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                grid[i][j] = '-';
            }
        }
    }

    //Retourne le nombre de rangées dans la grille
    public int getRows() {
        return ROWS;
    }

    //Retourne le nombre de colonnes dans la grille
    public int getColumns() {
        return COLUMNS;
    }

    //Retourne la grille de jeu
    public char[][] getBoard() {
        return grid;
    }

    //Effectue un coup pour un joueur dans la colonne spécifiée
    public boolean makeMove(Player player, int column) {
        if (column < 0 || column >= COLUMNS) {
            return false;
        }

        int row = findAvailableRow(column);
        if (row == -1) {
            return false;
        }

        grid[row][column] = player.getToken();
        return true;
    }

    //Trouve la rangée disponible la plus basse dans la colonne spécifiée
    public int findAvailableRow(int column) {
        for (int i = ROWS - 1; i >= 0; i--) {
            if (grid[i][column] == '-') {
                return i;
            }
        }
        return -1;
    }

    //Vérifie si la grille est pleine
    public boolean isBoardFull() {
        for (int col = 0; col < COLUMNS; col++) {
            if (findAvailableRow(col) != -1) {
                return false;
            }
        }
        return true;
    }
}
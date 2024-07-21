package src;

public class ConnectFourGame {
    //Vérifie si le joueur a gagné en vérifiant chaque ligne horizontales, verticales et diagonales
    public static boolean checkForWinner(Grid grid, Player player) {
        char token = player.getToken();

        return checkHorizontal(grid, token) || checkVertical(grid, token) || 
               checkDiagonal(grid, token);
    }

    //Vérifie si le joueur a gagné sur une ligne horizontale
    private static boolean checkHorizontal(Grid grid, char token) {
        for (int row = 0; row < grid.getRows(); row++) {
            for (int col = 0; col < grid.getColumns() - 3; col++) {
                if (grid.getBoard()[row][col] == token &&
                    grid.getBoard()[row][col + 1] == token &&
                    grid.getBoard()[row][col + 2] == token &&
                    grid.getBoard()[row][col + 3] == token) {
                    return true;
                }
            }
        }
        return false;
    }

    //Vérifie si le joueur a gagné sur une ligne verticale
    private static boolean checkVertical(Grid grid, char token) {
        for (int col = 0; col < grid.getColumns(); col++) {
            for (int row = 0; row < grid.getRows() - 3; row++) {
                if (grid.getBoard()[row][col] == token &&
                    grid.getBoard()[row + 1][col] == token &&
                    grid.getBoard()[row + 2][col] == token &&
                    grid.getBoard()[row + 3][col] == token) {
                    return true;
                }
            }
        }
        return false;
    }

    //Vérifie si le joueur a gagné sur une ligne diagonale
    private static boolean checkDiagonal(Grid grid, char token) {
        //Vérification des diagonales vers le bas 
        for (int row = 0; row < grid.getRows() - 3; row++) {
            for (int col = 0; col < grid.getColumns() - 3; col++) {
                if (grid.getBoard()[row][col] == token &&
                    grid.getBoard()[row + 1][col + 1] == token &&
                    grid.getBoard()[row + 2][col + 2] == token &&
                    grid.getBoard()[row + 3][col + 3] == token) {
                    return true;
                }
            }
        }

        //Vérification des diagonales vers le haut 
        for (int row = 3; row < grid.getRows(); row++) {
            for (int col = 0; col < grid.getColumns() - 3; col++) {
                if (grid.getBoard()[row][col] == token &&
                    grid.getBoard()[row - 1][col + 1] == token &&
                    grid.getBoard()[row - 2][col + 2] == token &&
                    grid.getBoard()[row - 3][col + 3] == token) {
                    return true;
                }
            }
        }

        return false;
    }
}

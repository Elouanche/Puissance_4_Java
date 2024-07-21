package src;

public class ClearTerminal {

    //Cette méthode permet de clear le terminal suivant l'os des joueurs 
    public static void clear() {
        try {
            final String os = System.getProperty("os.name");

            if (os.contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch (Exception e) {
            // Gérer les exceptions
            e.printStackTrace();
        }
    }
}
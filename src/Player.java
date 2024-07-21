package src;

public class Player {
    private String pseudo;
    private int score;
    private char token;

    //Getter et Setter du joueur
    public Player(String pseudo, int score, char token) {
        this.pseudo = pseudo;
        this.score = score;
        this.token = token;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String newPseudo) {
        this.pseudo = newPseudo;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int newScore) {
        this.score = newScore;
    }

    public char getToken() {
        return token;
    }

    public void incrementScore() {
        score++;
    }
}

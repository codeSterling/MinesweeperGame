public class Cell {
    private int r;
    private int c;
    private boolean revealed;
    private boolean flagged;
    private boolean hasBomb;
    private int numbersOfMinesNextTo;
    private char symbol;

    public Cell() {

        this.revealed = false;
        this.flagged = false;
        this.hasBomb = false;
        this.numbersOfMinesNextTo = 0;
        this.symbol = ' ';
    }

    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
    }

    public int getC() {
        return c;
    }

    public void setC(int c) {
        this.c = c;
    }

    public boolean isRevealed() {
        return revealed;
    }

    public void setRevealed(boolean revealed) {
        this.revealed = revealed;
    }

    public boolean isFlagged() {
        return flagged;
    }

    public void setFlagged(boolean flagged) {
        this.flagged = flagged;
    }

    public boolean isHasBomb() {
        return hasBomb;
    }

    public void setHasBomb(boolean hasBomb) {
        this.hasBomb = hasBomb;
    }

    public int getNumbersOfMinesNextTo() {
        return numbersOfMinesNextTo;
    }

    public void setNumbersOfMinesNextTo(int numbersOfMinesNextTo) {
        this.numbersOfMinesNextTo = numbersOfMinesNextTo;
        this.symbol = (char) (numbersOfMinesNextTo+'0');
    }

    public char getSymbol() {
        return symbol;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }
}

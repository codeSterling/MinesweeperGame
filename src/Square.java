public class Square {

    private boolean revealed;
    private boolean flagged;
    private boolean hasBomb;
    private int numbersOfMinesNextTo;


    public Square() {
        this.revealed = false;
        this.flagged = false;
        this.hasBomb = false;
        this.numbersOfMinesNextTo = 0;
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
    }


}

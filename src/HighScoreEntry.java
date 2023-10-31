public class HighScoreEntry {
    private String name;
    private String difficulty;
    private long time;

    public HighScoreEntry(String name, double difficulty, long time) {
        this.name = name;
        this.difficulty = getDifficultyString(difficulty);
        this.time = time;
    }

    public String getDifficultyString(double difficulty) {
        if(difficulty == 0.1) {
            return "Easy";
        } else if(difficulty == 0.2) {
            return "Medium";
        } else {
            return "Hard";
        }
    }

    public String getName() {
        return name;
    }
    public String getDifficulty() {
        return difficulty;
    }
    public long getTime() {
        return time;
    }

}

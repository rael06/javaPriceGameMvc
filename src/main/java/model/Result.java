package model;

public class Result {
    private int nbPlayed;
    private int lastNumberPlayed;
    private int comparison;
    private boolean error;

    public Result(int comparison, int nbPlayed, int lastNumberPlayed) {
        this.comparison = comparison;
        this.nbPlayed = nbPlayed;
        this.lastNumberPlayed = lastNumberPlayed;
    }

    public Result() {
        error = true;
    }

    public int getComparison() {
        return comparison;
    }

    public int getNbPlayed() {
        return nbPlayed;
    }

    public int getLastNumberPlayed() {
        return lastNumberPlayed;
    }

    public boolean isError() {
        return error;
    }
}

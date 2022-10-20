package core;

public class Line {
    private String numberLine;
    private String nameLine;

    public Line(String numberLine, String nameLine) {
        this.numberLine = numberLine;
        this.nameLine = nameLine;
    }

    public void setNumberLine(String numberLine) {
        this.numberLine = numberLine;
    }

    public void setNameLine(String nameLine) {
        this.nameLine = nameLine;
    }

    public String getNumberLine() {
        return numberLine;
    }

    public String getNameLine() {
        return nameLine;
    }

    @Override
    public String toString() {
        return "Line{" +
                "numberLine='" + numberLine + '\'' +
                ", nameLine='" + nameLine + '\'' +
                '}';
    }
}

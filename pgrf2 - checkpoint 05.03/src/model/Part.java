package model;

public class Part {

    private TopologyType type;

    private final int intex; // pocatecni index indexbufferu
    private final int count; // pocet primitiv (tech teles), nikoliv indexu

    public Part(TopologyType type, int intex, int count) {
        this.type = type;
        this.intex = intex;
        this.count = count;
    }

    public TopologyType getType() {
        return type;
    }

    public int getIntex() {
        return intex;
    }

    public int getCount() {
        return count;
    }
}

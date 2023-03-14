package model;

public class Part {

    private TopologyType type;

    private final int index; // pocatecni index indexbufferu
    private final int count; // pocet primitiv (tech teles), nikoliv indexu

    public Part(TopologyType type, int index, int count) {
        this.type = type;
        this.index = index;
        this.count = count;
    }

    public TopologyType getType() {
        return type;
    }

    public int getIndex() {
        return index;
    }

    public int getCount() {
        return count;
    }
}

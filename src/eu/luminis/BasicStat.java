package eu.luminis;

enum BasicStats {
    DX ("Dexterity", new Cost(Cost.CostType.PER_LEVEL, new Integer[]{20})),
    ST ("Strength", new Cost(Cost.CostType.PER_LEVEL, new Integer[]{10})),
    HT ("Health", new Cost(Cost.CostType.PER_LEVEL, new Integer[]{10})),
    IQ ("Intelligence", new Cost(Cost.CostType.PER_LEVEL, new Integer[]{10}));

    public String name;
    public Cost cost;

    BasicStats(String name, Cost cost) {
        this.name = name;
        this.cost = cost;
    }
}

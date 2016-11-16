package org.gurpsdomain.domain;

import java.util.ArrayList;
import java.util.List;

import static org.gurpsdomain.domain.description.CostType.*;

public class Advantage implements PageReference {
    private String name;
    private String pageReference;
    private int baseCost;
    private List<Modifier> modifiers;

    public Advantage(String name, int cost, String pageReference) {
        this(name, cost, pageReference, new ArrayList<Modifier>());
    }

    public Advantage(String name, int baseCost, String pageReference, List<Modifier> modifiers) {
        this.name = name;
        this.baseCost = baseCost;
        this.pageReference = pageReference;
        this.modifiers = modifiers;
    }

    private int cost() {
        // See B101 for rules concerning rounding and stacking modifiers
        // point cost modifiers do actually not exist in GURPS, these are really different advantage
        // By applying them first, we can have the percentage modifiers work on the subtotal
        // This would result in the same values as if we had treated them as different advantages
        int pointTotal = baseCost;
        int NEGATIVE_PERCENTAGE_CAP = -80;
        int percentageTotal = 0;
        int cost;

        for (Modifier modifier : modifiers) {
            switch (modifier.getCost().getCostType()) {
                case percentage:
                    percentageTotal += modifier.getCost().getValue();
                    break;
                case points:
                    pointTotal += modifier.getCost().getValue();
                    break;
                default: //TODO
            }
        }

        if (percentageTotal >= 0) {

            if ((percentageTotal * pointTotal) % 100 == 0) {
                cost = pointTotal + ((percentageTotal * pointTotal) / 100);
            } else {
                cost = pointTotal + ((percentageTotal * pointTotal) / 100) + 1;
            }

        } else {
            if  (percentageTotal < NEGATIVE_PERCENTAGE_CAP) { percentageTotal = NEGATIVE_PERCENTAGE_CAP;}
            cost = pointTotal + ((percentageTotal * pointTotal) / 100);
        }
        return cost;
    }


    public void payCost(Points points) {
        points.addAdvantage(cost());
    }

    @Override
    public String getPageReference() {
        return pageReference;
    }
}

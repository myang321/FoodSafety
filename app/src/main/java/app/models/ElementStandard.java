package app.models;

/**
 * Created by Steve on 8/20/2015.
 */
public class ElementStandard {
    private String elementName;
    private double goalValue;
    private String goalType;

    public String getElementName() {
        return elementName;
    }

    public double getGoalValue() {
        return goalValue;
    }

    public String getGoalType() {
        return goalType;
    }


    public ElementStandard(String elementName, double goalValue, String goalType) {
        this.elementName = elementName;
        this.goalValue = goalValue;
        this.goalType = goalType;
    }
}

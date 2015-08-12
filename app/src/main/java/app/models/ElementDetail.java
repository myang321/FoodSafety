package app.models;

/**
 * Created by Steve on 8/11/2015.
 */
public class ElementDetail {
    private String name;
    private double value;
    private String unit;

    @Override
    public String toString() {
        return name + ": " + value + unit;
    }

    public ElementDetail(String name, double value, String unit) {
        this.name = name;
        this.value = value;
        this.unit = unit;
    }

    public String getName() {
        return name;
    }

    public double getValue() {
        return value;
    }

    public String getUnit() {
        return unit;
    }
}

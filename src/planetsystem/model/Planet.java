package planetsystem.model;

public class Planet {
    String name;
    double periFocus;
    double period;
    double eccentricity;
    String[] description;

    public int bigHalfShaft;

    public int focus;

    public String getName() {
        return name;
    }

    public double getPeriFocus() {
        return periFocus;
    }

    public double getPeriod() {
        return period;
    }

    public double getEccentricity() {
        return eccentricity;
    }

    public String[] getDescription() {
        return description;
    }

    public Planet(String name, double radius, double period, double eccentricity, Model model,
                  String[] description) {
        this.name = name;
        this.periFocus = radius;
        this.period = period;
        this.eccentricity = eccentricity;
        this.focus = (int) (eccentricity * radius / (1 - eccentricity));
        this.bigHalfShaft = (int) (periFocus / (1 - eccentricity));
        this.description = description;

        if (model.getMaxBigHalfShaft() < bigHalfShaft)
            model.setMaxBigHalfShaft(bigHalfShaft);
    }
}

package planetsystem.model;

public class Planet {
    String name;
    double radius;
    double period;
    double eccentricity;

    public int bigHalfShaft;

    public int focus;

    public String getName() {
        return name;
    }

    public double getRadius() {
        return radius;
    }

    public double getPeriod() {
        return period;
    }

    public double getEccentricity() {
        return eccentricity;
    }

    public Planet(String name, double radius, double period, double eccentricity, planetsystem.model.Model model) {
        this.name = name;
        this.radius = radius;
        this.period = period;
        this.eccentricity = eccentricity;
        this.focus = (int) (eccentricity * radius / (1 - eccentricity));
        this.bigHalfShaft = (int) (radius + focus);

        if (model.getMaxBigHalfShaft() < bigHalfShaft)
            model.setBigHalfShaft(bigHalfShaft);
    }
}

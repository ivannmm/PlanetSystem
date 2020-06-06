package planetsystem.model;

import java.util.*;


public class Model {

    public List<Planet> dataBase = new ArrayList<>();
    int i = 1;

    double sunMass;
    int scaleTime;
    int planetCount;
    String systemName;
    String sunName;
    double bigHalfShaft;
    int numberOfThisPlanet = 1;
    boolean stopFlag = false;
    int showOrbitsFlag;
    double maxRadius;

    String[] atmosphere = {"без атмосферы", "с атмосферой", "со слабой атмосферой"};
    String[] climate = {"климат, непригодный для жизни, большой разброс температур", "климат, схожий " +
            "с земным", "климат, непригодный для жизни, много солнечной радиации"};
    String[] conditions = {"вода занимает большую часть поверхности планеты", "вода занимает" +
            "меньшую часть поверхности планеты", "планета не содержит воды в жидком виде", "планета не " +
            "содержит воды ни в каком-либо виде", "газовая планета"};


    public void setSunName (String sunName) {
        this.sunName = sunName;
    }

    public void setShowOrbitsFlag (int showOrbitsFlag) {
        this.showOrbitsFlag = showOrbitsFlag;
    }

    public void setSunMass (double sunMass) {
        this.sunMass = sunMass;
    }

    public void setScaleTime (String scaleTime) {
        switch (scaleTime){
            case "1 сек.": this.scaleTime = 1;
                break;
            case "1 мин.": this.scaleTime = 60;
                break;
            case "1 час": this.scaleTime = 3600;
                break;
            case "1 день": this.scaleTime = 86400;
                break;
            case "1 неделя": this.scaleTime = 604800;
                break;
            case "1 месяц": this.scaleTime = 2592000;
                break;
            case "1 год": this.scaleTime = 31104000;
                break;
            case "10 лет": this.scaleTime = 311040000;
                break;
        }
    }

    public void setNumberOfThisPlanet (int numberOfThisPlanet) {
        this.numberOfThisPlanet = numberOfThisPlanet;
    }

    public void changeStopFlag() {
        this.stopFlag = !this.stopFlag;
    }

    public void setPlanetCount (int planetCount) {
        this.planetCount = planetCount;
    }

    public void setSystemName (String systemName) {
        this.systemName = systemName;
    }

    public void setMaxBigHalfShaft (double bigHalfShaft) {
        this.bigHalfShaft = bigHalfShaft;
    }

    public void setMaxRadius (double radius) {
        this.maxRadius = radius;
    }

    public String getSystemName() {
        return systemName;
    }

    public double getMaxBigHalfShaft() {
        return bigHalfShaft;
    }

    public int getPlanetCount() {
        return planetCount;
    }

    public int getScaleTime() {
        return scaleTime;
    }

    public double getMaxRadius () {
        return maxRadius;
    }

    public int getShowOrbitsFlag() {
        return showOrbitsFlag;
    }

    public boolean getStopFlag() {
        return stopFlag;
    }

    public int getNumberOfThisPlanet () {
        return numberOfThisPlanet;
    }

    public void addPlanet(String name, long radius, double eccentricity) {

        double period = 2 * Math.PI * Math.sqrt((Math.pow(radius,3) * Math.pow(10,8))
                / (6.67 * sunMass));
        Random random = new Random();
        Planet planet = new Planet(name, radius, period, eccentricity, this,
                new String[] {atmosphere[random.nextInt(3)],
                        climate[random.nextInt(3)], conditions[random.nextInt(5)]});
        dataBase.add(planet);
        i++;

        if (radius > getMaxRadius())
            setMaxRadius(radius);
    }

    public int getX (int planetNumber, double angle, double coef) {
        return (int) (coef * 240 * Math.sin(angle) + (550 - dataBase.get(planetNumber).bigHalfShaft /
                getMaxBigHalfShaft() * 240 * dataBase.get(planetNumber).getEccentricity()));
    }

    public int getY(int planetNumber, double angle, double coef) {
        return (int) ((coef * 240) * Math.sqrt(1 - Math.pow(dataBase.get(planetNumber).getEccentricity(), 2))
                * Math.cos(angle) + 275);
    }
}

package planetsystem.model;

import java.util.HashMap;
import java.util.Map;


public class Model {

    public Map<Integer, Planet> dataBase = new HashMap<>();
    int i = 1;

    double sunMass;
    int scaleTime;
    int planetCount;
    String systemName;
    String sunName;
    double bigHalfShaft;


    public void setSunName (String sunName) {
        this.sunName = sunName;
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

    public void setPlanetCount (int planetCount) {
        this.planetCount = planetCount;
    }

    public void setSystemName (String systemName) {
        this.systemName = systemName;
    }

    public void setBigHalfShaft (double bigHalfShaft) {
        this.bigHalfShaft = bigHalfShaft;
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


    public void addPlanet(String name, long radius, double eccentricity) {

        double period = 2 * Math.PI * Math.sqrt((Math.pow(radius,3) * Math.pow(10,8))
                / (6.67 * sunMass));

        Planet planet = new Planet(name, radius, period, eccentricity, this);
        dataBase.put(i, planet);
        i++;

    }
}

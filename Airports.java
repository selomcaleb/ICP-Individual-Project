package IndividualProject;

public class Airports {
    protected String airportID;
    protected String airportName;
    protected String city;
    protected String country;
    protected String IACOCode;

    public Airports(String airportID, String airportName, String city, String country,String IACOCode) {
        this.airportID = airportID;
        this.airportName = airportName;
        this.city = city;
        this.country = country;
        this.IACOCode = IACOCode;
    }

    public String getAirportID() {
        return airportID;
    }

    public void setAirportID(String airportID) {
        this.airportID = airportID;
    }

    public String getAirportName() {
        return airportName;
    }

    public void setAirportName(String airportName) {
        this.airportName = airportName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }



    public String getIACOCode() {
        return IACOCode;
    }

    public void setIACOCode(String IACOCode) {
        this.IACOCode = IACOCode;
    }

    @Override
    public String toString() {
        return "Airports{" +
                "airportID='" + airportID + '\'' +
                ", airportName='" + airportName + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", IACOCode='" + IACOCode + '\'' +
                '}';
    }
}
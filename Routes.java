package IndividualProject;

import java.util.Objects;

public class Routes {
    //instance variables of the Routes class
    protected  String airlineCode;
    protected String airlineID;
    protected String destinationAirportCode;
    protected int stops;

    // constructor method for the instance variables
    public Routes(String airlineCode, String airlineID, String destinationAirportCode, int stops) {

        this.airlineCode = airlineCode;
        this.airlineID = airlineID;
        this.destinationAirportCode = destinationAirportCode;
        this.stops = stops;
    }

    public String getAirlineCode() {
        return airlineCode;
    }

    public void setAirlineCode(String airlineCode) {
        this.airlineCode = airlineCode;
    }

    public String getAirlineID() {
        return airlineID;
    }

    public void setAirlineID(String airlineID) {
        this.airlineID = airlineID;
    }

    public String getDestinationAirportCode() {
        return destinationAirportCode;
    }

    public void setDestinationAirportCode(String destinationAirportCode) {
        this.destinationAirportCode = destinationAirportCode;
    }

    public int getStops() {
        return stops;
    }

    public void setStops(int stops) {
        this.stops = stops;
    }


    // override toString method for this class.
    @Override
    public String toString() {
        return "Routes{" +
                "airlineCode='" + airlineCode + '\'' +
                ", airlineID='" + airlineID + '\'' +
                ", destinationAirportCode='" + destinationAirportCode + '\'' +
                ", stops=" + stops +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Routes routes = (Routes) o;
        return stops == routes.stops && Objects.equals(airlineCode, routes.airlineCode) && Objects.equals(airlineID, routes.airlineID) && Objects.equals(destinationAirportCode, routes.destinationAirportCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(airlineCode, airlineID, destinationAirportCode, stops);
    }
}

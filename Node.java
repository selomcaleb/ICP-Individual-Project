package IndividualProject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

public class Node {
    // instance variables
    public Node parentNode;
    public String airportCode;
    public String airlineCode;
    public int stops;
    public ArrayList<Routes>successors;

    public Node(Node parentNode, String airportCode, String airlineCode, int stops, ArrayList<Routes> successors) {
        this.parentNode = parentNode;
        this.airportCode = airportCode;
        this.airlineCode = airlineCode;
        this.stops = stops;
        this.successors = successors;
    }

    @Override
    public String toString() {
        return "Node{" +
                "parentNode=" + parentNode +
                ", airportCode='" + airportCode + '\'' +
                ", airlineCode='" + airlineCode + '\'' +
                ", stops=" + stops +
                ", successors=" + successors +
                '}';
    }

    @Override
    public boolean equals(Object obj){
       if (this == obj) {
           return true;
       }
       if (obj == null || getClass() != obj.getClass()){
           return false;
       }
       Node node = (Node) obj;
       return  Objects.equals(parentNode,node.parentNode) && Objects.equals(airportCode,node.airportCode)
               && Objects.equals(airlineCode,node.airlineCode) && Objects.equals(successors,node.successors)
               && stops == node.stops;
    }

    @Override
    public int hashCode() {
        return Objects.hash(parentNode, airportCode, airlineCode, stops, successors);
    }

    public Node getParentNode() {
        return parentNode;
    }

    public void setParentNode(Node parentNode) {
        this.parentNode = parentNode;
    }

    public String getAirportCode() {
        return airportCode;
    }

    public void setAirportCode(String airportCode) {
        this.airportCode = airportCode;
    }

    public String getAirlineCode() {
        return airlineCode;
    }

    public void setAirlineCode(String airlineCode) {
        this.airlineCode = airlineCode;
    }

    public int getStops() {
        return stops;
    }

    public void setStops(int stops) {
        this.stops = stops;
    }

    public ArrayList<Routes> getSuccessors() {
        return successors;
    }

    public void setSuccessors(ArrayList<Routes> successors) {
        this.successors = successors;
    }

    /**
     * This function takes the current node  and returns an ArrayList of Strings from the start node to the current node
     *
     * @return The solution path is being returned.
     */
    public ArrayList<String> solutionPath(){
        ArrayList<String> airportCodes = new ArrayList<>();
        ArrayList<String> airlineCodes = new ArrayList<>();
        ArrayList<String> solution = new ArrayList<>();
        ArrayList<Integer> stops = new ArrayList<>();
        Node thisNode = this;

        while(thisNode != null){
            airlineCodes.add(thisNode.getAirlineCode());
            airportCodes.add(thisNode.getAirportCode());
            stops.add(thisNode.getStops());
            thisNode = thisNode.parentNode;
        }
        Collections.reverse(airlineCodes);
        Collections.reverse((airportCodes));

        for (int i = 0;i < airlineCodes.size()-1;i++){
            String result = airlineCodes.get(i+1) + " from " + airportCodes.get(i) + " to " +
                    airportCodes.get(i+1) + " " + stops.get(i) + " stops.";
            solution.add(result);
        }

        return solution;

    }


}

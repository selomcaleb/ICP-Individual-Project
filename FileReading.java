package IndividualProject;

import java.io.*;
import java.util.*;

public class FileReading {
     static HashMap<String,String> airportsToPlacesHashMap = new HashMap<String,String>();
     static HashMap<String,ArrayList<String>> placesToAirportHashMap = new HashMap<String,ArrayList<String>>();
     static HashMap<String,ArrayList<Routes>> routeHashMap = new HashMap<String,ArrayList<Routes>>();
    public static void main(String[] args) throws FileNotFoundException {
        try {
            // reading the routes.csv and airports.csv files
            BufferedReader routeReader = new BufferedReader(new FileReader("/Users/selomcaleb/Desktop/LEVEL 200 SEC. SEM/ICP/ICP_Individual_Project/routes.csv"));
            BufferedReader airportReader = new BufferedReader(new FileReader("/Users/selomcaleb/IdeaProjects/ICP Project1/src/IndividualProject/airports.csv"));
            ArrayList<String[]> tempDataAirport = new ArrayList<>();
            String line = "";
            //line = airportReader.readLine();

            while((line = airportReader.readLine()) != null){
                String [] airportDetails = line.split(",");
                String airportToPlacesKey = airportDetails[4];
                String airportToPlacesValues = airportDetails[2] + ", " + airportDetails[3];
                airportsToPlacesHashMap.put(airportToPlacesKey,airportToPlacesValues);

                // working on placesToAirportHashMap
                String placesToAirportKey = airportDetails[2] + ", " + airportDetails[3];
                ArrayList<String> placesToAirportValues = placesToAirportHashMap.get(placesToAirportKey);
                ArrayList<String> placeValues = new ArrayList<>();
                if (placesToAirportValues != null){
                    placesToAirportValues.add(airportToPlacesKey);
                    placesToAirportHashMap.put(placesToAirportKey,placesToAirportValues);


                }
                else{
                    placeValues.add(airportToPlacesKey);
                    placesToAirportHashMap.put(placesToAirportKey,placeValues);

                }

            }
            //airportsToPlacesHashMap.forEach((k, v) -> System.out.println(k + " " + v));
            //placesToAirportHashMap.forEach((k, v) -> System.out.println(k + " " + v));

            airportReader.close();


            // reading from the routes.csv
            while ((line = routeReader.readLine()) != null){
                String [] routeDetails = line.split(",");
                Routes route = new Routes(routeDetails[0],routeDetails[1],routeDetails[4],Integer.parseInt(routeDetails[7]));
                //String sourceIATA = routeDetails[2];
                ArrayList<Routes> routesArrayList = routeHashMap.get(routeDetails[2]);
                ArrayList<Routes> routeValues = new ArrayList<>();
                if (routesArrayList != null){
                    routesArrayList.add(route);
                    routeHashMap.put(routeDetails[2],routesArrayList);
                }
                else{
                    routeValues.add(route);
                    routeHashMap.put(routeDetails[2],routeValues);
                }
            }
            //routeHashMap.forEach((k, v) -> System.out.println(k + " " + v));
            routeReader.close();
            BufferedReader inputFileReader = new BufferedReader(new FileReader("/Users/selomcaleb/IdeaProjects/ICP Project1/src/IndividualProject/kumasi-winnipeg.txt"));
            String initialLoc = inputFileReader.readLine();
            String destinationLoc = inputFileReader.readLine();
            inputFileReader.close();

            PrintWriter printWrite = new PrintWriter("kumasi-winnipeg_output.txt");

            ArrayList<String> path = breadthFirstSearch(initialLoc,destinationLoc);
            int numberOfFlights = 0;
            if (path != null){
                for (String flight:path) {
                    printWrite.println(flight);
                    numberOfFlights ++;
                }
                printWrite.println("Total flights: " + numberOfFlights);
            }
            else{
                printWrite.println("solution not found");
            }
            printWrite.close();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    /**
     * The methods takes in the initialLocation and destinationLocation and performs a breadth first search algorithm and returns a
     * solution path from the initialLocation to the destinationLocation
     *
     * @param initialLoc The initial location from the input file.
     * @param destinationLoc The destination location from the output file
     * @return returns an ArrayList of Strings.
     */
    public static ArrayList<String> breadthFirstSearch(String initialLoc, String destinationLoc){
        Queue<Node> frontier = new ArrayDeque<>();
        Set<String> exploredSet = new HashSet<>();
        ArrayList<String> airports = placesToAirportHashMap.get(initialLoc);
        for (String airport:airports) {
            Node airportNode = new Node(null,airport,null,0,null);
            frontier.add(airportNode);
        }
        while(frontier.size() != 0){
            Node currentNode = frontier.remove();
            exploredSet.add(currentNode.getAirportCode());
            ArrayList<Routes> successorStates = routeHashMap.get(currentNode.getAirportCode());
            if (successorStates != null){


                for (Routes successorState:successorStates) {
                    Node child = new Node(currentNode,successorState.getDestinationAirportCode(),successorState.getAirlineCode(),successorState.getStops(),null);

                    if (!frontier.contains(child) && !exploredSet.contains(child.getAirportCode())){
                        String destinationName = airportsToPlacesHashMap.get(child.getAirportCode());
                        if (destinationName != null && destinationName.equals(destinationLoc)){
                            return child.solutionPath();
                        }
                    }
                    frontier.add(child);
                }

            }
        }
        return null;
    }
}

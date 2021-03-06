/***********************************************************************************************************************

 File        : ThemePark.java
 Author      : 100237847
 Date        : 20/03/2020
 Description : This class is used to store information about a ThemePark such as a name, list of attractions at the
 ThemePark and a list of all the customers. It also contains several methods for adding and removing
 attractions/customers from the ThemePark and 3 special methods for calculating the totalTransportDistance,
 averageGentleCapacity and the medianCoasterSpeed.
 **********************************************************************************************************************/

package FunCorpPark;

import java.util.ArrayList;
import java.util.Collections;

public class ThemePark {

    private String name;
    private ArrayList<Attraction> attractions;
    private ArrayList<Customer> customers;

    //Default constructor for the ThemePark class
    public ThemePark() {
        this.attractions = new ArrayList<>();
        this.customers = new ArrayList<>();
        this.name = "";
    }

    public ArrayList<Customer> getCustomers() {
        return this.customers;
    }

    public ArrayList<Attraction> getAttractions() {
        return this.attractions;
    }

    //Method to calculate the total distance the transport rides will travel
    public int calculateTotalTransportDistance() {
        int totalDist = 0;
        for (Attraction attraction : attractions) {
            if (attraction.getType().equals("TRA")) {
                TransportAttraction attr = (TransportAttraction) attraction;
                totalDist = totalDist + attr.getDistance();
            }
        }
        return totalDist;
    }

    //Method to calculate the average capacity of all the gentle attractions
    public double calculateAverageGentleCapacity() {
        double totalCapacity = 0;
        int count = 0;
        for (Attraction attraction : attractions) {
            if (attraction.getType().equals("GEN")) {
                GentleAttraction attr = (GentleAttraction) attraction;
                totalCapacity = totalCapacity + attr.getNoPeople();
                count++;
            }
        }
        return totalCapacity / count;
    }

    //Method to calculate the median speed for all of the rollercoasters in the park
    public double calculateMedianCoasterSpeed() {
        //Use ArrayList to store all speeds and then choose middle value to find median
        ArrayList<Double> avgSpeed = new ArrayList<>();
        int count = 0;
        for (Attraction attraction : attractions) {
            if (attraction.getType().equals("ROL")) {
                RollerCoaster attr = (RollerCoaster) attraction;
                avgSpeed.add(attr.getSpeed());
                count++;
            }
        }
        Collections.sort(avgSpeed);
        //Determine whether list is odd or even in length and then work out median
        if(avgSpeed.size() % 2 == 0){

            //Average the two middle speeds
            double sp1 = avgSpeed.get(avgSpeed.size()/2);
            double sp2 = avgSpeed.get((avgSpeed.size()/2)+1);
            return (sp1 + sp2)/2;
        }
        else{
            return avgSpeed.get(count/2);
        }
    }

    public void addAttraction(Attraction newAttraction) {
        this.attractions.add(newAttraction);
    }

    public void addCustomer(Customer newCustomer) {
        this.customers.add(newCustomer);
    }

    //Method to find a particular customer in the park
    public Customer getCustomer(String accountNumber) {

        Customer foundCustomer = null;

        for (Customer customer : customers) {
            if (customer.getAccountNumber().equals(accountNumber)) {
                System.out.println("Found customer : " + customer.getName());
                foundCustomer = customer;
                break;
            }
        }
        return foundCustomer;
    }

    //Method to remove a particular customer from a park
    public void removeCustomer(String accountNumber) {

        for (int i = 0; i < customers.size(); i++) {
            try {

                if (customers.get(i).getAccountNumber().equals(accountNumber)) {
                    customers.remove(i);
                } else {
                    throw new CustomerNotFoundException();
                }
            } catch (CustomerNotFoundException e) {
                System.out.println(e.toString());
            }
        }
    }

    //Method to get a particular attraction from a park
    public Attraction getAttraction(String attractionName) {
        Attraction foundAttraction = null;

        for (int i = 0; i < attractions.size(); i++) {
            try {

                if (attractions.get(i).getName().equals(attractionName)) {
                    System.out.println("Found attraction : " + attractions.get(i).getName());
                    foundAttraction = attractions.get(i);
                    break;
                } else if (i == attractions.size() - 1) {
                    throw new AttractionNotFoundException();
                }
            } catch (AttractionNotFoundException e) {
                System.out.println(e.toString());
            }
        }
        return foundAttraction;
    }

    //Method to remove a particular attraction from a park
    public void removeAttraction(String attractionName) {

        for (int i = 0; i < attractions.size(); i++) {
            try {

                if (attractions.get(i).getName().equals(attractionName)) {
                    attractions.remove(i);
                } else {
                    throw new AttractionNotFoundException();
                }
            } catch (AttractionNotFoundException e) {
                System.out.println(e.toString());
            }
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override

    public String toString() {
        return name + " " + attractions.toString() + customers.toString();
    }

    //Test harness for the ThemePark class to test methods
    public static void main(String[] args) {
        ThemePark park = new ThemePark();

        Customer cus = new Customer("Dave", "12000", 15, 1200, Customer.personalDiscountEnum.FAMILY);
        park.addCustomer(cus);

        System.out.println(park.getCustomers());

        Attraction rol = new RollerCoaster("roller coaster ", 12, "ROL", 7, 12.1);
        park.addAttraction(rol);

        Attraction rol2 = new RollerCoaster("roller coaster2", 12, "ROL", 7, 450.5);
        Attraction rol3 = new RollerCoaster("roller coaster3", 12, "ROL", 7, 501.6);
        Attraction rol4 = new RollerCoaster("roller coaster4", 12, "ROL", 7, 100.10);
        park.addAttraction(rol2);
        park.addAttraction(rol3);
        park.addAttraction(rol4);


        System.out.println(park.calculateMedianCoasterSpeed());

       // System.out.println(rol.toString() + rol2.toString());
        //System.out.println(rol.getName().trim());
        System.out.println(rol2.getName().trim());
    }


}

/***********************************************************************************************************************

 File        : Attraction.java
 Author      : 100237847
 Date        : 20/03/2020
 Description : This class is designed to be an abstract class to store the basic information about all of the
 attractions at a ThemePark. It includes an abstract method for returning the offPeakPrice for any of the attractions
 along with various getter and setter methods and a toString.

 **********************************************************************************************************************/

package FunCorpPark;

public abstract class Attraction {

    private String name;
    private int basePrice;
    private String type;

    public abstract int getOffPeakPrice();

    public String getName() {
        return name;
    }

    public String getType() {
        return this.type;
    }

    public int getBasePrice() {
        return basePrice;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBasePrice(int basePrice) {
        this.basePrice = basePrice;
    }

    @Override

    public String toString() {
        String basePriceString = Integer.toString(basePrice);
        return name + " " + basePriceString + ":Base price";
    }

    public void setType(String type) {
        this.type = type;
    }

    public static void main(String[] args) {
        System.out.println("This is a test");

        Attraction att = new Attraction() {
            @Override
            public int getOffPeakPrice() {
                return 0;
            }
        };
        System.out.println(att.getBasePrice());
        System.out.println(att.getType());
        System.out.println(att.getName());
    }
}

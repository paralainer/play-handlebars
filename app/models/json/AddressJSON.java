package models.json;

/**
 * Created by Sergey Talov on 07.05.2014.
 * email: serg.talov@gmail.com
 */
public class AddressJSON {

    private String street = "street5";

    private int houseNumber = 5;

    public int getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(int houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }
}

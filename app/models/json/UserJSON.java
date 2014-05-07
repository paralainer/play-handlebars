package models.json;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Sergey Talov on 05.05.2014.
 * email: serg.talov@gmail.com
 */
public class UserJSON {

    private String name;

    private String age;

    private AddressJSON[] address = Arrays.asList(new AddressJSON(), new AddressJSON()).toArray(new AddressJSON[2]);

    public AddressJSON[] getAddress() {
        return address;
    }

    public void setAddress(AddressJSON[] address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}

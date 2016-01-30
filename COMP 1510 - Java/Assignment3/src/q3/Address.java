package q3;

/**
 * <p>Represents a street address.</p>
 *
 * @author Trevor Broderick
 * @version 1.0
 */
public class Address {
    /** 
     * <p>Street address.</p>
     */
    private String streetAddress;
    /** 
     * <p>City.</p> 
     */
    private String city;
    /**
     * <p>State.</p>
     */
    private String state;
    /**
     * <p>Postal code, any country.</p>
     */
    private String postalCode;

    /**
     * <p>Constructor: Sets up this address with the specified data.</p>
     *
     * @param street holds new streetAddress
     * @param town holds new city
     * @param st hlds new state
     * @param code holds new postalCode
     */
    public Address(String street, String town, String st, String code) {
        streetAddress = street;
        city          = town;
        state         = st;
        postalCode    = code;
    }

    /**
     * <p>Returns a description of this Address object.</p>
     *
     * @return formatted value of streetAddress, city, state, zipCode
     */
    public String toString() {
        String result;

        result  = streetAddress + "\n";
        result += city + ", " + state + "  " + postalCode;

        return result;
    }
}

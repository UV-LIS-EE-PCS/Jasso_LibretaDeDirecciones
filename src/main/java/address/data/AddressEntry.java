package address.data;

/**
 * The AddressEntry class represents a single entry in an address book.
 * It contains fields for the contact's name, last name, street, city, state,
 * postal code, email, and phone number, along with their respective getters,
 * setters, and a toString method for displaying the contact's information.
 */
public class AddressEntry {
    private String name, lastName, street, city, state;
    private int postalCode;
    private String email, phone;

    /**
     * Constructs an AddressEntry with the given details.
     *
     * @param name the first name of the contact
     * @param lastName the last name of the contact
     * @param street the street address of the contact
     * @param city the city of the contact
     * @param state the state of the contact
     * @param postalCode the postal code of the contact
     * @param email the email address of the contact
     * @param phone the phone number of the contact
     */
    public AddressEntry(String name, String lastName, String street, String city, String state, int postalCode, String email, String phone) {
        this.name = name;
        this.lastName = lastName;
        this.street = street;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
        this.email = email;
        this.phone = phone;
    }

    /**
     * Returns the first name of the contact.
     *
     * @return the first name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the first name of the contact.
     *
     * @param name the first name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the last name of the contact.
     *
     * @return the last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name of the contact.
     *
     * @param lastName the last name to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Returns the street address of the contact.
     *
     * @return the street address
     */
    public String getStreet() {
        return street;
    }

    /**
     * Sets the street address of the contact.
     *
     * @param street the street address to set
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * Returns the city of the contact.
     *
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets the city of the contact.
     *
     * @param city the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Returns the state of the contact.
     *
     * @return the state
     */
    public String getState() {
        return state;
    }

    /**
     * Sets the state of the contact.
     *
     * @param state the state to set
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * Returns the postal code of the contact.
     *
     * @return the postal code
     */
    public int getPostalCode() {
        return postalCode;
    }

    /**
     * Sets the postal code of the contact.
     *
     * @param postalCode the postal code to set
     */
    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }

    /**
     * Returns the email address of the contact.
     *
     * @return the email address
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email address of the contact.
     *
     * @param email the email address to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Returns the phone number of the contact.
     *
     * @return the phone number
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Sets the phone number of the contact.
     *
     * @param phone the phone number to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Returns a string representation of the AddressEntry.
     *
     * @return a string containing the contact's details
     */
    @Override
    public String toString() {
        return "Nombre: " + name + '\n' +
                "Apellido: " + lastName + '\n' +
                "Calle: " + street + '\n' +
                "Ciudad: " + city + '\n' +
                "Estado: " + state + '\n' +
                "Código postal: " + postalCode  +'\n' +
                "Email: " + email + '\n' +
                "Teléfono: " + phone;
    }
}

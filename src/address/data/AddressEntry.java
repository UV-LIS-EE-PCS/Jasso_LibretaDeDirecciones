package address.data;

public class AddressEntry {
    private String name, lastName, street, city, state;
    private int postalCode;
    private String email, phone;

    public AddressEntry () {

    }

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

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

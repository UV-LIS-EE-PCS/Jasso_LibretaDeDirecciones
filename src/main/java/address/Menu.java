package address;

import address.data.AddressBook;
import address.data.AddressEntry;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The Menu class handles the interaction with the user, including displaying
 * the menu, adding, deleting, searching, and displaying contacts in the address book.
 */
public class Menu {

    private static final Scanner in = new Scanner(System.in);
    private static final AddressBook addressBook = AddressBook.getInstance();
    private static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    private static final int LENGTH_OF_A_PHONE_WITHOUT_DASHES = 10;
    private static final int LENGTH_OF_A_PHONE_WITH_DASHES = 12;
    private static final int LENGTH_POSTAL_CODE = 5;

    /**
     * Displays the main menu options to the user.
     */
    public void displayMenu() {
        System.out.println("""
                ====================================\s
                Elige una opción del menú\s
                a) Cargar de archivo\s
                b) Agregar\s
                c) Eliminar\s
                d) Buscar\s
                e) Mostrar\s
                f) Salir\s
                ====================================""");
    }

    /**
     * Prompts the user to enter the filename and reads contacts from the specified text file.
     */
    public void readContactFromTextFile() {
        System.out.println("Ingresa el nombre del archivo:");
        String filename = in.nextLine();
        addressBook.readFromATextFile(filename);
    }

    /**
     * Adds a new contact to the address book by prompting the user to enter contact details.
     * Retries on error.
     */
    public void add() {
        try {
            AddressEntry newEntry = requestContactData();
            AddressBook addressBook = AddressBook.getInstance();
            addressBook.addAddressEntry(newEntry);
        } catch (Exception ex) {
            System.out.println("Error al agregar el contacto");
            add();
        }
    }

    /**
     * Prompts the user to enter contact details and validates the input.
     *
     * @return a validated AddressEntry object.
     * @throws Exception if any validation fails.
     */
    private AddressEntry requestContactData() throws Exception {
        System.out.println("Nombre:");
        String name = in.nextLine().trim();
        System.out.println("Apellido:");
        String lastName = in.nextLine().trim();
        System.out.println("Calle:");
        String street = in.nextLine().trim();
        System.out.println("Ciudad:");
        String city = in.nextLine().trim();
        System.out.println("Estado:");
        String state = in.nextLine().trim();
        System.out.println("CP:");
        int postalCode = in.nextInt();
        in.nextLine();
        System.out.println("Email:");
        String email = in.nextLine().trim();
        System.out.println("Telefono:");
        String phone = in.nextLine().trim();

        return isDataValidated(name, lastName, street, city, state, postalCode, email, phone);
    }

    /**
     * Validates the entered contact details and returns a new AddressEntry object.
     *
     * @param name the first name of the contact.
     * @param lastName the last name of the contact.
     * @param street the street address of the contact.
     * @param city the city of the contact.
     * @param state the state of the contact.
     * @param postalCode the postal code of the contact.
     * @param email the email address of the contact.
     * @param phone the phone number of the contact.
     * @return a new AddressEntry object with validated data.
     * @throws Exception if any validation fails.
     */
    public AddressEntry isDataValidated(String name, String lastName, String street, String city,
                                                String state, int postalCode, String email, String phone)
            throws Exception {
        if (name.isEmpty() || lastName.isEmpty() || street.isEmpty() || city.isEmpty() || state.isEmpty()
                || email.isEmpty() || phone.isEmpty()) {
            throw new Exception("Todos los campos deben ser completados.");
        }

        if (!validateEmail(email)) {
            throw new Exception("El email es incorrecto.");
        }

        if (!validatePhone(phone)) {
            throw new Exception("El telefono es incorrecto.");
        }

        if (!validatePostalCode(postalCode)) {
            throw new Exception("El codigo postal es incorrecto.");
        }

        try {
            return new AddressEntry(name, lastName, street, city, state, postalCode, email, phone);
        } catch (Exception ex) {
            throw new Exception("Error al agregar el contacto");
        }
    }

    /**
     * Validates the email format.
     *
     * @param emailStr the email address to validate.
     * @return true if the email format is valid, false otherwise.
     */
    private static boolean validateEmail(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.matches();
    }

    /**
     * Validates the phone number format.
     *
     * @param phone the phone number to validate.
     * @return true if the phone number format is valid, false otherwise.
     */
    private static boolean validatePhone(String phone) {
        return phone.length() == LENGTH_OF_A_PHONE_WITH_DASHES
                || phone.length() == LENGTH_OF_A_PHONE_WITHOUT_DASHES;
    }

    /**
     * Validates the postal code format.
     *
     * @param postalCode the postal code to validate.
     * @return true if the postal code format is valid, false otherwise.
     */
    private static boolean validatePostalCode(int postalCode) {
        return Integer.toString(postalCode).length() == LENGTH_POSTAL_CODE;
    }

    /**
     * Prompts the user to enter the last name of the contact to delete and deletes the contact.
     *
     * @throws Exception if an error occurs during deletion.
     */
    public void deleteAddressEntry() throws Exception {
        System.out.println("Ingresa el apellido completo del contacto a eliminar");
        String lastName = in.nextLine();
        ArrayList<AddressEntry> contactsToDelete;
        contactsToDelete = addressBook.findAddressEntry(lastName, true);
        addressBook.showContactsFound(contactsToDelete);
        selectEntryToDelete(contactsToDelete, lastName);
    }

    /**
     * Prompts the user to select a contact to delete from the list of found contacts.
     *
     * @param contactsToDelete the list of contacts to delete.
     * @param lastName the last name of the contact to delete.
     * @throws Exception if an error occurs during deletion.
     */
    public static void selectEntryToDelete(ArrayList<AddressEntry> contactsToDelete, String lastName) throws Exception {
        System.out.println("Ingresa 'y' para eliminar o 'n' para regresar al menú");
        char choice = in.next().charAt(0);
        if (choice == 'y' && contactsToDelete.size() == 1) {
            addressBook.deleteEntry(lastName);
        } else if (choice == 'y' && contactsToDelete.size() > 1) {
            System.out.println("Ingrese el indice del contacto que desea eliminar");
            int index = in.nextInt();
            addressBook.deleteEntry(lastName, index, contactsToDelete);
        } else if (choice != 'n') {
            System.out.println("Opcion incorrecta");
        }
    }

    /**
     * Prompts the user to enter a last name or part of it to search for contacts.
     *
     * @throws Exception if an error occurs during the search.
     */
    public void searchContact() throws Exception {
        System.out.println("Ingrese apellido completo o primeras letras:");
        String startOfLastName = in.nextLine();
        ArrayList<AddressEntry> contactsFound;
        try {
            contactsFound = addressBook.findAddressEntry(startOfLastName, false);
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        addressBook.showContactsFound(contactsFound);
    }
}

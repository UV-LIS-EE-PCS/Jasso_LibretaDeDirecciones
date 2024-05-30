package address.data;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;

public class AddressBook {
    private static AddressBook instance = null;
    private ArrayList<AddressEntry> entries;

    /**
     * Private constructor to prevent instantiation.
     */
    private AddressBook() {
        this.entries = new ArrayList<>();
    }


    /**
     * Returns the singleton instance of AddressBook.
     *
     * @return the singleton instance.
     */
    public static AddressBook getInstance() {
        if (instance == null) {
            instance = new AddressBook();
        }
        return instance;
    }

    /**
     * Displays the list of contacts, sorted by last name.
     * Prints a message if the list is empty.
     */
    public void showContactsList() {
        if (entries.isEmpty()) {
            System.out.println("La lista esta vacia:(");
        }
        else {
            Comparator<AddressEntry> byLastName = Comparator.comparing(AddressEntry::getLastName);

            entries.sort(byLastName);
            for (AddressEntry entry : entries) {
                System.out.println((entries.indexOf(entry) + 1) + ". " + entry.toString() + "\n");
            }
        }
    }

    /**
     * Finds address entries by last name.
     *
     * @param lastName the last name to search for.
     * @param strict whether the search should be strict.
     * @return a list of matching address entries.
     * @throws Exception if no matching entries are found in strict mode.
     */
    public ArrayList<AddressEntry> findAddressEntry(String lastName, boolean strict) throws Exception {
        if (strict) lastNameExistInContactsList(lastName);
        ArrayList<AddressEntry> matchingContacts = new ArrayList<>();
        if (entries.isEmpty()) {
            throw new Exception("La lista esta vacia:(");
        } else {
            for (AddressEntry entry : entries) {
                if (entry.getLastName().toLowerCase().contains(lastName.toLowerCase())) {
                    matchingContacts.add(entry);
                }
            }
        }
        return matchingContacts;
    }

    /**
     * Checks if a last name exists in the contacts list.
     *
     * @param lastName the last name to check for.
     * @throws Exception if the last name does not exist in the contacts list.
     */
    public void lastNameExistInContactsList(String lastName) throws Exception {
        if (entries.stream().noneMatch(entry -> entry.getLastName().equalsIgnoreCase(lastName))) {
            throw new Exception("El apellido no existe en la lista de contactos");
        }
    }

    /**
     * Adds a new address entry to the address book.
     *
     * @param addressEntry the address entry to add.
     */
    public void addAddressEntry(AddressEntry addressEntry) {
        if (entries.stream().noneMatch(entry -> entry.toString().equalsIgnoreCase(addressEntry.toString()))) {
            entries.add(addressEntry);
        }
    }

    /**
     * Deletes address entries by last name.
     *
     * @param lastName the last name of the entries to delete.
     */
    public void deleteAddressEntry(String lastName) {
        entries.removeIf(entry -> entry.getLastName().equalsIgnoreCase(lastName));
    }

    /**
     * Reads address entries from a text file and adds them to the address book.
     *
     * @param filename the name of the file to read from.
     */
    public void readFromATextFile(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String[] contactData = new String[8];
            int index = 0;
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\n");
                contactData[index] = parts[0];
                index++;
            }
            AddressEntry entry = new AddressEntry(contactData[0], contactData[1], contactData[2], contactData[3], contactData[4], Integer.parseInt(contactData[5]), contactData[6], contactData[7]);
            addAddressEntry(entry);
        } catch (IOException e) {
            System.err.println("Error al cargar el archivo: " + e.getMessage());
        }
    }

    /**
     * Displays the list of found contacts.
     *
     * @param contacts the list of found contacts.
     */
    public void showContactsFound(ArrayList<AddressEntry> contacts) {
        if (contacts.size() == 1) {
            System.out.println("El siguiente contacto fue encontrado:");
            System.out.println(contacts.toString());
        } else {
            System.out.println("Los siguientes " + contacts.size() + " contactos fueron encontrados");
            for (AddressEntry entry: contacts) {
                System.out.println((entries.indexOf(entry) + 1) + ". " + entry.toString());
            }
        }
    }
}

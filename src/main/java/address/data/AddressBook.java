package address.data;

import address.Menu;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * The AddressBook class is a singleton that manages a list of address entries.
 * It provides methods to add, delete, find, and display address entries, as well as read from a text file.
 */
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
     * Returns the list of contacts.
     *
     * @return entries
     */
    public ArrayList<AddressEntry> getEntries() {
        return entries;
    }

    /**
     * Automatically loads contacts from the "ContactsSaved.txt" file.
     * If the address book is empty and the file exists, it reads the contacts
     * from the file and adds them to the address book.
     */
    public void automaticLoadContacts() {
        String currentDirectory = System.getProperty("user.dir");
        String actualDirectory = currentDirectory + "\\contacts\\ContactsSaved.txt";

        File contactsSaved = new File(actualDirectory);
        if (entries.isEmpty() && contactsSaved.exists()) {
            readFromATextFile(contactsSaved.getName());
        }
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
     * @throws Exception if the last name does not exist in the contacts list in strict mode.
     */
    public ArrayList<AddressEntry> findAddressEntry(String lastName, boolean strict) throws Exception {

        if (strict) {
            try {
                lastNameExistInContactsList(lastName);
            } catch (Exception ex) {
                throw new Exception(ex.getMessage());
            }
        }

        ArrayList<AddressEntry> matchingContacts = new ArrayList<>();

        if (entries.isEmpty()) {
            System.out.println("La lista esta vacia:(");
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
    public void lastNameExistInContactsList(String lastName) throws Exception{
        if (entries
                .stream()
                .noneMatch(entry -> entry.getLastName().equalsIgnoreCase(lastName)))
        {
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
    public void deleteEntry(String lastName) {
        entries.removeIf(entry -> entry.getLastName().equalsIgnoreCase(lastName));
    }

    /**
     * Deletes a specific address entry by last name and index from the list of found contacts.
     *
     * @param lastName the last name of the entries to delete.
     * @param index the index of the specific entry to delete.
     * @param contacts the list of found contacts.
     */
    public void deleteEntry(String lastName, int index, ArrayList<AddressEntry> contacts) {
        if (index > 0 && index <= contacts.size()) {
            AddressEntry entryToDelete = contacts.get(index - 1);
            entries.removeIf(entry -> entry.getLastName().equalsIgnoreCase(lastName)
                    && entryToDelete.toString().equalsIgnoreCase(entry.toString()));
        } else {
            System.out.println("El indice no corresponde a ningun contacto");
        }
    }

    /**
     * Reads address entries from a text file and adds them to the address book.
     *
     * @param filename the name of the file to read from.
     */
    public void readFromATextFile(String filename) {
        String currentDirectory = System.getProperty("user.dir");
        String actualDirectory = currentDirectory + "\\contacts\\" + filename;

        try (BufferedReader br = new BufferedReader(new FileReader(actualDirectory))) {
            String line;
            ArrayList<String> contactData = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    loadContact(contactData);
                    contactData.clear();
                } else {
                    contactData.add(line.trim());
                }
            }
            loadContact(contactData);
        } catch (IOException e) {
            System.err.println("Error al cargar el archivo");
        }
    }

    /**
     * Loads a contact from a list of contact data strings. If the size of the list is 8,
     * it attempts to validate and create an AddressEntry object from the data.
     * If the data is validated successfully, the entry is added. If an exception occurs
     * during validation, an error message is printed.
     *
     * @param contactData an ArrayList of Strings containing the contact data, expected
     * to have exactly 8 elements.
     */
    private void loadContact(ArrayList<String> contactData) {
        if (contactData.size() == 8) {
            try {
                AddressEntry entry = new Menu().isDataValidated(contactData.get(0), contactData.get(1), contactData.get(2), contactData.get(3), contactData.get(4), Integer.parseInt(contactData.get(5)), contactData.get(6), contactData.get(7));
                addAddressEntry(entry);
            } catch (Exception validatedException) {
                System.out.println("Hubo un error al cargar el contacto desde el archivo, por favor valida los datos");
            }
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
        } else if (contacts.size() > 1) {
            System.out.println("Los siguientes " + contacts.size() + " contactos fueron encontrados");
            for (AddressEntry entry: contacts) {
                System.out.println((entries.indexOf(entry) + 1) + ". " + entry.toString());
            }
        }
    }

    /**
     * Saves the contacts to a file.
     * Each contact is written in a format with each attribute on a new line
     * and each contact separated by a blank line.
     */
    public void saveContacts() {
        String currentDirectory = System.getProperty("user.dir");
        String actualDirectory = currentDirectory + "\\contacts\\ContactsSaved.txt";

        File contactsSaved = new File(actualDirectory);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(contactsSaved))) {
            for (AddressEntry entry : entries) {
                writer.write(entry.getName());
                writer.newLine();
                writer.write(entry.getLastName());
                writer.newLine();
                writer.write(entry.getStreet());
                writer.newLine();
                writer.write(entry.getCity());
                writer.newLine();
                writer.write(entry.getState());
                writer.newLine();
                writer.write(String.valueOf(entry.getPostalCode()));
                writer.newLine();
                writer.write(entry.getEmail());
                writer.newLine();
                writer.write(entry.getPhone());
                writer.newLine();
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

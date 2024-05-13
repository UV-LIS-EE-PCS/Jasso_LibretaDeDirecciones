package address.data;

import java.util.ArrayList;

public class AddressBook {
    private static AddressBook instance = null;
    private ArrayList<AddressEntry> entriesList;

    private AddressBook() {
        this.entriesList = new ArrayList<>();
    }

    public static AddressBook getInstance() {
        if (instance == null) {
            instance = new AddressBook();
        }
        return instance;
    }

    public void showContactsList() {
        // TODO: Order by last name
        if (entriesList.isEmpty()) System.out.println("La lista esta vacia:(");
        else {
            for (AddressEntry entry : entriesList) {
                System.out.println((entriesList.indexOf(entry) + 1) + ". " + entry.toString() + "\n");
            }
        }
    }

    public ArrayList<AddressEntry> findAddressEntry(String lastName) throws Exception {
        ArrayList<AddressEntry> matchingContacts = new ArrayList<>();
        if (entriesList.isEmpty()) {
            throw new Exception("La lista esta vacia:(");
        } else {
            for (AddressEntry entry : entriesList) {
                if (entry.getLastName().toLowerCase().contains(lastName.toLowerCase())) matchingContacts.add(entry);
            }
        }
        return matchingContacts;
    }

    public void addAddressEntry(AddressEntry addressEntry) {
        entriesList.add(addressEntry);
    }

    public void deleteAddressEntry(String lastName) {
        for (AddressEntry entry : entriesList) {
            if (entry.getLastName().equalsIgnoreCase(lastName)) {
                entriesList.remove(entry);
            }
        }
    }

    public void readFromATextFile(String filename) {
        // TODO: Implement this method
    }

    public void showContactsFound(ArrayList<AddressEntry> contacts) {
        if (contacts.size() == 1) {
            System.out.println("El siguiente contacto fue encontrado:");
            System.out.println(contacts.toString());
        } else {
            System.out.println("Los siguientes " + contacts.size() + " fueron encontrados");
            for (AddressEntry entry: contacts) {
                System.out.println(entry.toString());
            }
        }
    }
}

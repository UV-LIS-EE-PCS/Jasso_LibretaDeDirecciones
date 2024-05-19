package address.data;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;

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
            // Definir el comparador
            Comparator<AddressEntry> byLastName = Comparator.comparing(AddressEntry::getLastName);

            // Ordenar la lista
            entriesList.sort(byLastName);
            for (AddressEntry entry : entriesList) {
                System.out.println((entriesList.indexOf(entry) + 1) + ". " + entry.toString() + "\n");
            }
        }
    }

    public ArrayList<AddressEntry> findAddressEntry(String lastName, boolean strict) throws Exception {
        if (strict) lastNameExistInContactsList(lastName);
        ArrayList<AddressEntry> matchingContacts = new ArrayList<>();
        if (entriesList.isEmpty()) {
            throw new Exception("La lista esta vacia:(");
        } else {
            for (AddressEntry entry : entriesList) {
                if (entry.getLastName().toLowerCase().contains(lastName.toLowerCase())) {
                    matchingContacts.add(entry);
                }
            }
        }
        return matchingContacts;
    }

    public void lastNameExistInContactsList(String lastName) throws Exception {
        if (entriesList.stream().noneMatch(entry -> entry.getLastName().equalsIgnoreCase(lastName))) {
            throw new Exception("El apellido no existe en la lista de contactos");
        }
    }

    public void addAddressEntry(AddressEntry addressEntry) {
        entriesList.add(addressEntry);
    }

    public void deleteAddressEntry(String lastName) {
        entriesList.removeIf(entry -> entry.getLastName().equalsIgnoreCase(lastName));
    }

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
            System.out.println("Entries loaded from file.");
        } catch (IOException e) {
            System.err.println("Error loading from file: " + e.getMessage());
        }
    }

    public void showContactsFound(ArrayList<AddressEntry> contacts) {
        if (contacts.size() == 1) {
            System.out.println("El siguiente contacto fue encontrado:");
            System.out.println(contacts.toString());
        } else {
            System.out.println("Los siguientes " + contacts.size() + " contactos fueron encontrados");
            for (AddressEntry entry: contacts) {
                System.out.println((entriesList.indexOf(entry) + 1) + ". " + entry.toString());
            }
        }
    }
}

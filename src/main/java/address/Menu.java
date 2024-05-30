package address;

import address.data.AddressBook;
import address.data.AddressEntry;


import java.util.ArrayList;
import java.util.Scanner;

public class Menu {

    private Scanner in = new Scanner(System.in);
    private AddressBook addressBook = AddressBook.getInstance();

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

    public void readContactFromTextFile() {
        System.out.println("Ingresa el nombre del archivo:");
        String filename = in.nextLine();
        addressBook.readFromATextFile(filename);
    }

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

    private AddressEntry requestContactData() throws Exception {
        System.out.println("Nombre:");
        String name = in.nextLine();
        System.out.println("Apellido:");
        String lastName = in.nextLine();
        System.out.println("Calle:");
        String street = in.nextLine();
        System.out.println("Ciudad:");
        String city = in.nextLine();
        System.out.println("Estado:");
        String state = in.nextLine();
        System.out.println("CP:");
        int postalCode = in.nextInt();
        in.nextLine();
        System.out.println("Email:");
        String email = in.nextLine();
        System.out.println("Telefono:");
        String phone = in.nextLine();

        if (name.isEmpty() || lastName.isEmpty() || street.isEmpty() || city.isEmpty() || state.isEmpty()
                || email.isEmpty() || phone.isEmpty()) {
            throw new Exception("Todos los campos deben ser completados.");
        }

        return new AddressEntry(name, lastName, street, city, state, postalCode, email, phone);
    }

    public void deleteAddressEntry() throws Exception {
        in.nextLine();
        System.out.println("Ingresa el apellido completo del contacto a eliminar");
        String lastname = in.nextLine();
        ArrayList<AddressEntry> contactsToDelete;
        try {
            contactsToDelete = addressBook.findAddressEntry(lastname, true);
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        addressBook.showContactsFound(contactsToDelete);
        System.out.println("Ingresa 'y' para eliminar o 'n' para regresar al menú");
        char choice = in.next().charAt(0);
        if (choice == 'y') {
            addressBook.deleteAddressEntry(lastname);
        } else if (choice != 'n') {
            System.out.println("Opcion incorrecta");
        }
    }

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

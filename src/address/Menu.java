package address;

import address.data.AddressBook;
import address.data.AddressEntry;

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;

public class Menu {

    private Scanner in = new Scanner(System.in);

    public void displayMenu() {
        System.out.println("==================================== \n" +
                "Elige una opción del menú \n" +
                "a) Cargar de archivo \n" +
                "b) Agregar \n" +
                "c) Eliminar \n" +
                "d) Buscar \n" +
                "e) Mostrar \n" +
                "f) Salir \n" +
                "====================================");
    }

    public void loadFile(String filename) {
        // TODO: Implement this method
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

    public void deleteAddressEntry() {
        // TODO: Implement or delete this method
    }
}

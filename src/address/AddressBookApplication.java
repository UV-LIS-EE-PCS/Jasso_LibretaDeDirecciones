package address;

import address.data.AddressBook;
import address.data.AddressEntry;

import java.util.ArrayList;
import java.util.Scanner;

public class AddressBookApplication {

    public static void main(String[] args) {
        Menu menu = new Menu();
        Scanner in = new Scanner(System.in);
        AddressBook addressBook = AddressBook.getInstance();

        do {
            menu.displayMenu();
            char option = in.next().toLowerCase().charAt(0);
            switch (option) {
                case 'a': // cargar archivo
                    System.out.println("Ingresa el nombre del archivo:");
                    String filename = in.nextLine();
                    addressBook.readFromATextFile(filename);
                    break;
                case 'b': // agregar
                    menu.add();
                    break;
                case 'c': // eliminar
                    in.nextLine();
                    System.out.println("Ingresa el apellido completo del contacto a eliminar");
                    String lastname = in.nextLine();
                    ArrayList<AddressEntry> contactsToDelete;
                    try {
                        contactsToDelete = addressBook.findAddressEntry(lastname, true);
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                        break;
                    }
                    addressBook.showContactsFound(contactsToDelete);
                    System.out.println("Ingresa 'y' para eliminar o 'n' para regresar al menú");
                    char choice = in.next().charAt(0);
                    if (choice == 'y') {
                        addressBook.deleteAddressEntry(lastname);
                    } else if (choice != 'n') {
                        System.out.println("Opcion incorrecta");
                    }
                    break;
                case 'd': // buscar
                    in.nextLine();
                    System.out.println("Ingrese apellido completo o primeras letras:");
                    String startOfLastName = in.nextLine();
                    ArrayList<AddressEntry> contactsFound;
                    try {
                        contactsFound = addressBook.findAddressEntry(startOfLastName, false);
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                        break;
                    }
                    addressBook.showContactsFound(contactsFound);
                    break;
                case 'e': // mostrar lista
                    addressBook.showContactsList();
                    break;
                case 'f': // salir
                    return;
                default:
                    System.out.println("Opcion erronea, intenta de nuevo");
            }
        } while (true);
    }
}

package address;

import address.data.AddressBook;
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
                    menu.readContactFromTextFile();
                    break;
                case 'b': // agregar
                    menu.add();
                    break;
                case 'c': // eliminar
                    try {
                        menu.deleteAddressEntry();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'd': // buscar

                    try {
                        menu.searchContact();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
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

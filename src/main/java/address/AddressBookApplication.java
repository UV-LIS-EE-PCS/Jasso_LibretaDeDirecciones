package address;

import address.data.AddressBook;
import java.util.Scanner;

/**
 * The AddressBookApplication class is the entry point of the address book application.
 * It handles the main menu and user interactions.
 */
public class AddressBookApplication {

    public static void main(String[] args) {
        Menu menu = new Menu();
        Scanner in = new Scanner(System.in);
        AddressBook addressBook = AddressBook.getInstance();

        do {
            addressBook.automaticLoadContacts();
            menu.displayMenu();
            char option = in.next().toLowerCase().charAt(0);
            switch (option) {
                case 'a':
                    menu.readContactFromTextFile();
                    break;
                case 'b':
                    menu.add();
                    break;
                case 'c':
                    delete(menu);
                    break;
                case 'd':
                    search(menu);
                    break;
                case 'e':
                    addressBook.showContactsList();
                    break;
                case 'f':
                    addressBook.saveContacts();
                    return;
                default:
                    System.out.println("Opción errónea, intenta de nuevo");
            }
        } while (true);
    }

    /**
     * Handles the search operation by calling the searchContact method
     * from the Menu class and managing exceptions.
     *
     * @param menu the Menu object to perform the search operation.
     */
    private static void search(Menu menu) {
        try {
            menu.searchContact();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Handles the delete operation by calling the deleteAddressEntry method
     * from the Menu class and managing exceptions.
     *
     * @param menu the Menu object to perform the delete operation.
     */
    private static void delete(Menu menu) {
        try {
            menu.deleteAddressEntry();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

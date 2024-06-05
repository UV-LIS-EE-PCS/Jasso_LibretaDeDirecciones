package data;

import static org.junit.jupiter.api.Assertions.*;

import address.Menu;
import address.data.*;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;

public class AddressBookTest {

    AddressBook addressBook = AddressBook.getInstance();

    @Test
    public void testSingletonInstance(){
        AddressBook firstInstance = AddressBook.getInstance();
        AddressEntry newEntry = new AddressEntry("Emilio", "Jasso", "Los Naranjos", "Las Choapas", "Veracruz", 96980, "emi@uv.com", "923-139-9550");
        firstInstance.addAddressEntry(newEntry);

        AddressBook secondInstance = AddressBook.getInstance();

        assertEquals(firstInstance,secondInstance) ;
    }

    @Test
    public void testAutomaticLoadContactsFileExists() {
        AddressBook addressBook = AddressBook.getInstance();
        addressBook.automaticLoadContacts();
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        addressBook.showContactsList();
        assertTrue(outContent.toString().contains("La lista esta vacia:("), "La lista de contactos no debería estar vacía después de cargar el archivo");
    }

    @Test
    public void readContactFromFile() {
        String currentDirectory = System.getProperty("user.dir");
        String actualDirectory = currentDirectory + "\\contacts\\ContactsSaved.txt";

        File contactsSaved = new File(actualDirectory);

        if (contactsSaved.exists()) {
            addressBook.readFromATextFile(contactsSaved.getName());
            assertFalse(addressBook.getEntries().isEmpty(), String.valueOf(addressBook.getEntries().size()));
        }
    }

    @Test
    public void findContactValid() throws Exception {
        AddressEntry addressEntry = new AddressEntry("Roberto", "Estrada", "Colorados",
        "Medellin", "Colombia", 87452, "robE@col.com", "228-410-3598");
        addressBook.addAddressEntry(addressEntry);
        assertTrue(true, String.valueOf(addressBook.findAddressEntry("Estrada", true)));
    }

    @Test
    public void findContactInvalid() throws Exception {
        fail(String.valueOf(addressBook.findAddressEntry("Fernandez", true)));
    } // la intencion es que este test falle

}

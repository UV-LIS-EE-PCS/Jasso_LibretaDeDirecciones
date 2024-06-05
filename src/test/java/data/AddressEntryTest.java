package data;

import address.data.AddressEntry;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddressEntryTest {

    AddressEntry addressEntry = new AddressEntry("Emilio", "Jasso", "Los Naranjos",
            "Las Choapas", "Veracruz", 96980, "emi@uv.mx", "923-139-9550");
    @Test
    void hasBeenInitialized() {
        assertEquals("Emilio", addressEntry.getName());
    }

    @Test
    void changeName() {
        addressEntry.setName("Andrés");
        assertEquals("Andrés", addressEntry.getName());
    }


}

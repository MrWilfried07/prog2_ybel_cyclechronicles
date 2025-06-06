import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import cyclechronicles.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

public class ShopRepairAndDeliverTest {

    private Shop shop;

    @BeforeEach
    public void setUp() {
        shop = new Shop();
    }

    @Test
    void testRepairReturnsOrderMock() {
        // Mock der Order
        Order order = mock(Order.class);

        // Mock die Klasse shop
        Shop mockedShop = mock(Shop.class);

        // repair() lifert ein Optional zurueck, der order enthält
        when(mockedShop.repair()).thenReturn(Optional.of(order));
        Optional<Order> result = mockedShop.repair();

        // Ueberpruefung
        assertTrue(result.isPresent());
        assertEquals(order, result.get());

        // ueberprueft, dass repair() gut aufgerufen wuder
        verify(mockedShop).repair();
    }

    @Test
    void testDeliverReturnsCorrectOrder() {
        Order order = mock(Order.class);
        when(order.getCustomer()).thenReturn("Wilfried");

        Shop mockedShop = mock(Shop.class);
        when(mockedShop.deliver("Wilfried")).thenReturn(Optional.of(order));

        Optional<Order> result = mockedShop.deliver("Wilfried");

        assertTrue(result.isPresent());
        assertEquals("Wilfried", result.get().getCustomer());

        verify(mockedShop).deliver("Wilfried");
    }

}

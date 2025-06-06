import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import cyclechronicles.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ShopAcceptTest {

    private Shop shop;

    @BeforeEach
    public void setUp() {
        shop = new Shop();
    }

    @Test
    public void testAcceptsRaceBikeIfNoPendingOrders() {
        // Mock von einem Order
        Order order = mock(Order.class);
        when(order.getBicycleType()).thenReturn(Type.RACE);
        when(order.getCustomer()).thenReturn("Alice");

        boolean result = shop.accept(order);

        // Ueberpruefung
        assertTrue(result);
    }

    @Test
    public void testRejectsGravelBike() {
        Order order = mock(Order.class);
        when(order.getBicycleType()).thenReturn(Type.GRAVEL);
        when(order.getCustomer()).thenReturn("Bob");

        boolean result = shop.accept(order);

        assertFalse(result);
    }

    @Test
    public void testRejectsIfSameCustomerHasPendingOrder() {
        Order order1 = mock(Order.class);
        when(order1.getBicycleType()).thenReturn(Type.FIXIE);
        when(order1.getCustomer()).thenReturn("Wilfried");

        Order order2 = mock(Order.class);
        when(order2.getBicycleType()).thenReturn(Type.RACE);
        when(order2.getCustomer()).thenReturn("Wilfried"); // derselbe Kunde hat dann einen anderen Auftrag

        assertTrue(shop.accept(order1)); // erste angenommen
        assertFalse(shop.accept(order2)); // zweite abgelehnt
    }

    @Test
    public void testRejectsIfTooManyPendingOrders() {
        for (int i = 0; i < 5; i++) {
            Order o = mock(Order.class);
            when(o.getBicycleType()).thenReturn(Type.RACE);
            when(o.getCustomer()).thenReturn("Client" + i);
            assertTrue(shop.accept(o));
        }

        // neuer Kunde aber Queue voll
        Order einZuViel = mock(Order.class);
        when(einZuViel.getBicycleType()).thenReturn(Type.RACE);
        when(einZuViel.getCustomer()).thenReturn("NewClient");

        assertFalse(shop.accept(einZuViel));
    }
}

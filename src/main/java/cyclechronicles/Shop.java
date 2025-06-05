package cyclechronicles;

import java.io.IOException;
import java.util.*;
import java.util.logging.*;

/** A small bike shop. */
public class Shop {
    private final Queue<Order> pendingOrders = new LinkedList<>();
    private final Set<Order> completedOrders = new HashSet<>();
    private Logger logger = Logger.getLogger(Shop.class.getName());

    public Shop() throws IOException {
        FileHandler fileHandler = new FileHandler("orders-log.csv", true);
        fileHandler.setLevel(Level.ALL);
        logger.addHandler(fileHandler);
    }

    /**
     * Accept a repair order.
     *
     * <p>The order will only be accepted if all conditions are met:
     *
     * <ul>
     *   <li>Gravel bikes cannot be repaired in this shop.
     *   <li>E-bikes cannot be repaired in this shop.
     *   <li>There can be no more than one pending order per customer.
     *   <li>There can be no more than five pending orders at any time.
     * </ul>
     *
     * <p>Implementation note: Accepted orders are added to the end of {@code pendingOrders}.
     *
     * @param o order to be accepted
     * @return {@code true} if all conditions are met and the order has been accepted, {@code false}
     *     otherwise
     */
    public boolean accept(Order o) {

        if (o.bycicleType() == Type.GRAVEL) return false;
        if (o.bycicleType() == Type.EBIKE) return false;

        if (pendingOrders.stream().anyMatch(x -> x.customer().equals(o.customer())))
            return false;
        if (pendingOrders.size() > 4) return false;

        return pendingOrders.add(o);
    }

    /**
     * Take the oldest pending order and repair this bike.
     *
     * <p>Implementation note: Take the top element from {@code pendingOrders}, "repair" the bicycle
     * and put this order in {@code completedOrders}.
     *
     * @return finished order
     */
    public Optional<Order> repair() {

        if(!pendingOrders.isEmpty()) {
            Optional<Order> temp = Optional.of(pendingOrders.peek());
            logger.info("METHODE: repair; KLASSE: Shop; ORDER: " + temp.get().toString() + "; pendingOrders");
            completedOrders.add(temp.get());
            logger.info("METHODE: repair; KLASSE: Shop; ORDER: " + temp.get().toString() + "; completedOrders");
            return temp;
        }

        logger.severe("METHODE: repair; KLASSE: Shop; ORDER: null; pendingOrder");
        return Optional.empty();
    }

    /**
     * Deliver a repaired bike to a customer.
     *
     * <p>Implementation note: Find any order in {@code completedOrders} with matching customer and
     * deliver this order. Will remove the order from {@code completedOrders}.
     *
     * @param c search for any completed orders of this customer
     * @return any finished order for given customer, {@code Optional.empty()} if none found
     */
    public Optional<Order> deliver(String c) {
        Optional temp = completedOrders.stream()
                        //.map(Order::customer)
                        .filter(s -> s.customer().equals(c))
                        .findFirst();

        //loggt nur, wenn das Objekt existiert
        if(!temp.equals(Optional.empty())){ logger.info("METHODE: deliver; KLASSE: Shop; ORDER: " + temp.get().toString() + "; completedOrders"); }

        completedOrders.remove(temp.get());
        return temp;

    }
}

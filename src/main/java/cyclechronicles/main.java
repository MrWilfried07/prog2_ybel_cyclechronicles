package cyclechronicles;

import java.io.IOException;

public class main {

    public static void main(String[] args) throws IOException {

        Shop shop = new Shop();

        shop.accept(new Order(Type.RACE, "Michel"));
        shop.repair();
        shop.deliver("Michel");

    }

}

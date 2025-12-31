package christmas.model;

import java.util.Arrays;
import java.util.List;

public enum MenuGroup {

    APPETIZER(Arrays.asList(Menu.SOUP, Menu.TAPAS, Menu.SALAD)),
    MAIN(Arrays.asList(Menu.T_STEAK, Menu.BBQ_RIBS, Menu.SEAFOOD_PASTA, Menu.CHRISTMAS_PASTA)),
    DESSERT(Arrays.asList(Menu.CAKE, Menu.ICE_CREAM)),
    BEVERAGE(Arrays.asList(Menu.ZERO_COLA, Menu.RED_WINE, Menu.CHAMPAGNE)),
    ;

    private final List<Menu> menus;

    MenuGroup(List<Menu> menus) {
        this.menus = menus;
    }

    public static MenuGroup findByMenu(Menu menu) {
        return Arrays.stream(MenuGroup.values())
                .filter(menuGroup -> menuGroup.hasMenu(menu))
                .findAny()
                .orElse(null);
    }

    private boolean hasMenu(Menu menu) {
        for (Menu m : menus) {
            if (m == menu) {
                return true;
            }
        }
        return false;
    }
}

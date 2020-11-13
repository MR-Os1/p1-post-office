package ir.ac.kntu.domain.enumeration;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public enum MenuOptions {
    EXIT(0,"Exit"),
    CREATE_BRANCH(1,"Create A Branch"),
    CREATE_CUSTOMER(2,"Create A Customer"),
    CREATE_CITY(3,"Create A City"),
    CREATE_PACKAGE(4,"Create A Package"),
    SEND_OR_RECEIVE_PACKAGE(5,"Send or Receive Package"),
    GET_PACKAGE_STATUS(6,"Check Package"),
    FIND_CUSTOMER(7,"Find Customer"),
    FIND_PACKAGE_BY_SOURCE_PLACE(8,"Find Package By Source City"),
    FIND_PACKAGE_BY_DESTINATION_PLACE(9,"Find Package By Destination City"),
    FILTER_PACKAGE_BY_STATUS(10,"Find Package By Destination City"),
    SET_PACKAGE_DATE(11,"Set Packages Date"),
    IMPORT_TEST_DATA(12,"Import test Data");

    MenuOptions(int menuId, String menuText) {
        this.menuId = menuId;
        this.menuText = menuText;
    }

    private final int menuId;
    private final String menuText;

    private static Map<Integer,MenuOptions> enumMap = new HashMap<>();

    static {
        for (MenuOptions m :MenuOptions.values())
            enumMap.put(m.getMenuId(), m);
        enumMap = Collections.unmodifiableMap(enumMap);
    }

    public int getMenuId() {
        return menuId;
    }

    public String getMenuText() {
        return menuText;
    }

    public static MenuOptions getMenuById(int menuId){
        return enumMap.get(menuId);
    }

}

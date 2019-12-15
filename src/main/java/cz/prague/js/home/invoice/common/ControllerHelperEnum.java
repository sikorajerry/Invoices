package cz.prague.js.home.invoice.common;

public enum ControllerHelperEnum {
    BASE_PAGE("base"),
    TEMPLATE("template");

    String name;

    ControllerHelperEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

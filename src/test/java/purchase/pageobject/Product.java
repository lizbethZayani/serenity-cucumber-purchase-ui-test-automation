package purchase.pageobject;

import org.openqa.selenium.By;

public class Product {

    public static String PRODUCT = "//a[normalize-space()='%s']";

    public static By ADDTOCARBTN = By.xpath("//a[normalize-space()='Add to cart']");

    public static By nameProduct(String product) {
        return By.xpath(String.format(PRODUCT, product));
    }


}


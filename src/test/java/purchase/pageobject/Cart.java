package purchase.pageobject;

import org.openqa.selenium.By;

public class Cart {

    public static String THEADDEDPRODUCT = "(//td[contains(text(),'%s')])";
    public static String DELETEDPRODUCT = "(//td[contains(text(), '%s')]/following-sibling::td)[2]/a";
    public static String PLACEORDERBTN = "//button[normalize-space()='Place Order']";
    public static String TOTALPURCHASE = "//h3[@id='totalp']";

    public static By theAddedProduct(String product) {
        return By.xpath(String.format(THEADDEDPRODUCT, product));
    }

    public static By theDeletedProduct(String product) {
        return By.xpath(String.format(DELETEDPRODUCT, product));
    }
}

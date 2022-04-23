package purchase.pageobject;

import org.openqa.selenium.By;


public class Purchase {

    public static By WEBFORMPLACEORDER = By.xpath("//div[@id='orderModal']//div[@class='modal-content']");
    public static By NAMEFIELD = By.id("name");
    public static By CREDITCARDFIELD = By.id("card");
    public static By PURCHASEBTN = By.xpath("//button[normalize-space()='Purchase']");
    public static By SUCCESSICON = By.xpath("//div[@class='sa-icon sa-success animate']");
    public static By PURCHASEDETAILS = By.xpath("//p[@class='lead text-muted ']");
    public static By OKBUTTON = By.cssSelector(".confirm.btn.btn-lg.btn-primary");


}

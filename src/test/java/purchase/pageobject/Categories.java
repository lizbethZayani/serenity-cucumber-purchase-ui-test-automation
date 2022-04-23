package purchase.pageobject;

import org.openqa.selenium.By;


public class Categories {

    public static String CATEGORY = "//a[contains(text(), '%s')]";
    public static String CATEGORIESLIST = " //div[@class='list-group']";


    public static By categories(String category) {
        return By.xpath(String.format(CATEGORY, category));
    }

}

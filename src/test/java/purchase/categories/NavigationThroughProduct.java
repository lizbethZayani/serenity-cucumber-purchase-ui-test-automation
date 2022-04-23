package purchase.categories;


import net.serenitybdd.core.steps.UIInteractionSteps;
import net.thucydides.core.annotations.Step;

import static purchase.pageobject.Categories.CATEGORIESLIST;
import static purchase.pageobject.Categories.categories;

public class NavigationThroughProduct extends UIInteractionSteps {

    @Step("Navigate through the categories {0}")
    public void category(String category) {
        find(categories(category)).click();
    }

    @Step("The Categories are displayed in the home page")
    public boolean categoriesList() {
        return find(CATEGORIESLIST).waitUntilVisible().isVisible();
    }


}

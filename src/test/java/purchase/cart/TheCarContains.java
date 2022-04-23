package purchase.cart;

import net.serenitybdd.core.steps.UIInteractionSteps;
import net.thucydides.core.annotations.Step;

import static purchase.pageobject.Cart.theAddedProduct;
import static purchase.pageobject.NavBar.NAVCART;

public class TheCarContains extends UIInteractionSteps {

    @Step("Go to the cart to validated the content {0}")
    public boolean product(String product) {
        return find(theAddedProduct(product)).isVisible();
    }

    @Step("Go to the cart menu")
    public void cartMenu() {
        find(NAVCART).click();
    }

    @Step("The product is not in the cart {0}")
    public void productIsNot(String product) {
        find(theAddedProduct(product)).shouldNotBeVisible();
    }
}

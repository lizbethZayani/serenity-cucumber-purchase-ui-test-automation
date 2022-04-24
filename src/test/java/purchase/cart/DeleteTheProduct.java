package purchase.cart;

import net.serenitybdd.core.steps.UIInteractionSteps;
import net.thucydides.core.annotations.Step;

import static purchase.pageobject.Cart.theDeletedProduct;

public class DeleteTheProduct extends UIInteractionSteps {

    /**
     *
     * @param product
     */
    @Step("Click on the deleted btn {0}")
    public void product(String product) throws InterruptedException {
        Thread.sleep(650);
        find(theDeletedProduct(product)).waitUntilVisible().click();
    }
}

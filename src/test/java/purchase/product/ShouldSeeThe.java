package purchase.product;

import net.serenitybdd.core.steps.UIInteractionSteps;
import net.thucydides.core.annotations.Step;
import static purchase.pageobject.Product.nameProduct;


public class ShouldSeeThe extends UIInteractionSteps {

    @Step("The customer should see the name about the product {0}")
    public String product(String product) {
       return find(nameProduct(product)).waitUntilEnabled().getText();
    }
}

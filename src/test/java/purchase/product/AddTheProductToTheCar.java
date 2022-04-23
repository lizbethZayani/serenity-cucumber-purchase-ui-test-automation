package purchase.product;

import net.serenitybdd.core.steps.UIInteractionSteps;
import net.thucydides.core.annotations.Step;
import org.openqa.selenium.support.ui.ExpectedConditions;
import static purchase.pageobject.Product.nameProduct;
import static purchase.pageobject.Product.ADDTOCARBTN;

public class AddTheProductToTheCar extends UIInteractionSteps {

    @Step("click on the Add to car btn {0}")
    public  void addTheProduct(String laptop) {
        find(nameProduct(laptop)).click();
        find(ADDTOCARBTN).click();
    }
    @Step("accept the alert")
    public void acceptThProductAddedAlert(){
        waitFor(ExpectedConditions.alertIsPresent());
        getAlert().accept();
    }

}

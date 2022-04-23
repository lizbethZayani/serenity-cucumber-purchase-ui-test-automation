package purchase.cart;

import net.serenitybdd.core.steps.UIInteractionSteps;
import net.thucydides.core.annotations.Step;
import org.junit.platform.commons.util.StringUtils;

import static purchase.pageobject.Cart.PLACEORDERBTN;
import static purchase.pageobject.Cart.TOTALPURCHASE;
import static purchase.pageobject.Purchase.*;


public class PlaceOrder extends UIInteractionSteps {

    @Step("Click on Place Order button")
    public void button() {
        find(PLACEORDERBTN).click();
    }

    @Step("Get the total amount from the cart")
    public String totalAmountCart() {
        return find(TOTALPURCHASE).getText();
    }


    @Step("The web form is displayed")
    public boolean webFormFields() {
        return find(WEBFORMPLACEORDER).waitUntilVisible().isVisible();
    }

    @Step("Fill the name {0}")
    public void fillTheName(String name) {
        find(NAMEFIELD).waitUntilVisible().type(name);
    }

    @Step("Fill the credit card {0}")
    public void fillTheCreditCard(String creditCard) {
        find(CREDITCARDFIELD).waitUntilVisible().type(creditCard);
    }

    @Step("Click on Purchase button")
    public void buttonPurchase() {
        find(PURCHASEBTN).waitUntilVisible().click();
    }

    @Step("The success icon is displayed")
    public boolean successPurchase() {
        return find(SUCCESSICON).waitUntilVisible().isVisible();
    }

    @Step("Get the confirmed Purchase Amount")
    public String confirmedPurchaseAmount() {
        String purchaseDetails = find(PURCHASEDETAILS).getText().replaceAll("\\n", "");
        String amountPurchase = purchaseDetails.substring(purchaseDetails.indexOf("Amount:"), purchaseDetails.indexOf("Card"))
                .replaceAll("\\D+", "").trim();
        return amountPurchase;
    }

    @Step("Get the Purchase Id generated")
    public boolean purchaseIdGenerated() {
        String purchaseDetails = find(PURCHASEDETAILS).getText().replaceAll("\\n", "");
        String idPurchase = purchaseDetails.substring(purchaseDetails.indexOf("Id:"), purchaseDetails.indexOf("Amount:"))
                .replaceAll("\\D+", "").trim();
        return StringUtils.isNotBlank(idPurchase);
    }

    @Step("Click on Ok button into the success modal")
    public void buttonOkPurchase() {
        find(OKBUTTON).waitUntilVisible().click();
    }


}

package purchase.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.thucydides.core.annotations.Steps;
import org.junit.Assert;
import purchase.cart.DeleteTheProduct;
import purchase.cart.PlaceOrder;
import purchase.cart.TheCarContains;
import purchase.categories.NavigationThroughProduct;
import purchase.navigation.GoToTheMenu;
import purchase.navigation.NavigateTo;
import purchase.product.AddTheProductToTheCar;
import purchase.product.ShouldSeeThe;


public class PurchaseStepDefinitions {

    @Steps
    NavigationThroughProduct navigationThroughProduct;

    @Steps
    ShouldSeeThe shouldSeeThe;

    @Steps
    AddTheProductToTheCar addTheProductToTheCar;

    @Steps
    TheCarContains theCarContains;

    @Steps
    DeleteTheProduct deleteTheProduct;

    @Steps
    GoToTheMenu goToTheMenu;

    @Steps
    PlaceOrder placeOrder;

    @Given("{actor} is searching products on the online demo shop")
    public void jordiIsResearchingThingsOnTheOnlineDemoShop(Actor actor) {
        actor.wasAbleTo(NavigateTo.demoOnlineShop());
    }

    @When("the customer clicks on the {string}")
    public void heClickOnTheCategory(String category) {
        navigationThroughProduct.category(category);
    }

    @Then("the customer should see information about the {string}")
    public void heShouldSeeInformationAboutTheProduct(String product) {
        String getProduct = shouldSeeThe.product(product).trim();
        Assert.assertEquals("The product is not fit with the category selected! Take a look!", product, getProduct);
    }

    @When("the customer adds the {string} to the cart")
    public void addTheToTheCar(String laptop) {
        navigationThroughProduct.category("Laptops");
        addTheProductToTheCar.addTheProduct(laptop);
    }

    @Then("the pop up confirmation is displayed")
    public void thePopUpConfirmationIsDisplayed() {
        addTheProductToTheCar.acceptThProductAddedAlert();
    }

    @And("the {string} is  added in the cart")
    public void theIsAddedInTheCar(String product) {
        theCarContains.cartMenu();
        Assert.assertTrue("The product was not added! keep an eye on it could be a bug", theCarContains.product(product));
        goToTheMenu.home();
    }

    @When("the customer deletes the {string} from the cart")
    public void deleteThe(String product) {
        theCarContains.cartMenu();
        deleteTheProduct.product(product);
    }

    @Then("the {string} is removed from the cart")
    public void theWasRemovedFromTheCart(String product) {
        goToTheMenu.home();
        theCarContains.cartMenu();
        theCarContains.productIsNot(product);
    }

    @When("the customer goes to place the order")
    public void theUserClickOnThePurchaseButtonInOrderToPurchase() {
        placeOrder.button();
    }

    @Then("the payment form is displayed")
    public void theWebFormIsDisplayedToFillTheRequiredFields() {
        Assert.assertTrue("The web form is not displayed! keep an eye on it could be a bug", placeOrder.webFormFields());
    }

    @When("the customer fills the {string} and the {string}")
    public void theUserFillTheAndThe(String name, String creditCard) {
        placeOrder.fillTheName(name);
        placeOrder.fillTheCreditCard(creditCard);
    }

    @And("the customer clicks on Purchase button")
    public void clickOnPurchaseButton() {
        placeOrder.buttonPurchase();
    }

    @Then("the purchase is successful")
    public void thePurchaseIsSuccessful() {
        Assert.assertTrue("The purchase is not successful! Take a look!", placeOrder.successPurchase());
    }

    @And("the total amount is the expected one")
    public void theTotalAmountIsTheExpected() {
        Assert.assertEquals("The total amount and the confirmed purchase amount are not equal! Take a look!",placeOrder.totalAmountCart(),placeOrder.confirmedPurchaseAmount());
    }

    @And("the purchase id is generated")
    public void thePurchaseIdIsGenerated() {
        Assert.assertTrue("The id purchase is not generated! Take a look!",placeOrder.purchaseIdGenerated());
    }

    @When("the customer clicks on the ok button to finish the purchase")
    public void theUserClickOnTheOkButtonToDoneThePurchase() {
        placeOrder.buttonOkPurchase();
    }

    @Then("the user is redirected to the home page")
    public void theUserIsRedirectedToTheHomePage() {
        Assert.assertTrue("The home page is not displayed!", navigationThroughProduct.categoriesList()
        );
    }
}

package purchase.navigation;

import net.serenitybdd.core.steps.UIInteractionSteps;
import net.thucydides.core.annotations.Step;

import static purchase.pageobject.NavBar.NAVHOME;

public class GoToTheMenu extends UIInteractionSteps {

    @Step("Go to Home page")
    public void home() {
        find(NAVHOME).click();
    }
}


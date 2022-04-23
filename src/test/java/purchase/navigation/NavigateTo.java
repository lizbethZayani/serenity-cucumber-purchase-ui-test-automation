package purchase.navigation;

import net.serenitybdd.core.steps.UIInteractionSteps;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Open;

public class NavigateTo extends UIInteractionSteps {

    public static Performable demoOnlineShop() {
        return Task.where("{0} opens the DEMO ONLINE SHOP",
                Open.browserOn().the(DemoOnlineShop.class));
    }
}

# Test automation framework UI with Serenity and Cucumber

Serenity BDD is a library that makes it easier to write high quality automated acceptance tests, with powerful reporting and living documentation features. It has strong support for both web testing with Selenium, and API testing using RestAssured.

Serenity strongly encourages good test automation design, and supports several design patterns, including classic Page Objects, the newer Lean Page Objects/ Action Classes approach, and the more sophisticated and flexible Screenplay pattern.

## The purchase project
The best place to start with the project is to clone or download the purchase project on Github. This project interact with the demo online shop: https://www.demoblaze.com/index.html. This is an online shop than offers products like phone, laptops and monitors where the customer could add and delete products from the cart and make the purchase. To interact with the site is not necessary a logged user.
The framework is developing with Maven then you have to have installed Java and Maven in your local machine to run it.
Serenity with Cucumber is implemented to write and describe the scenarios.
### The project directory structure
```Gherkin
src
  + main
  + test
    + java          Test runners and supporting code
       + purchase
          + cart        Interaction classes that need to interact with the cart and purchase pages
          + categories  Interaction classes that need to interact with the category menu
          + navigation  Interaction classes that need to interact with the web page and the menu
          + pageobject  Light-weight Page object classes
          + product     Interaction classes that need to interact with the product page
          + stepdefinitions Actions classes to link the steps test cases
    + resources          Feature and config file
      + features         Feature file
          purchase_demo_onlie_shop.feature
             
```

## The scenarios
The scenarios are described in Gherkin. In the first scenario the customer navigate through the three categories Phone, Laptops and Monitor to explore the shop. The second is to make the purchase of a laptop, where the customer interacts with the whole site until the purchase is completed, so we cover an e2e test.

```Gherkin
Feature: Purchase

  Scenario Outline: Customer navigation through product categories
    Given the customer is searching products on the online demo shop
    When the customer clicks on the "<category>"
    Then the customer should see information about the "<product>"

    Examples:
      | category |     product     |
      | Phones | Samsung galaxy s6 |
      | Laptops | Sony vaio i5     |
      | Monitors | Apple monitor 24 |

  Scenario Outline: Customer navigates through the site to make a laptop purchase
    Given the customer is searching products on the online demo shop
    When the customer adds the "Sony vaio i5" to the cart
    Then the pop up confirmation is displayed
    And the "Sony vaio i5" is  added in the cart
    When the customer adds the "Dell i7 8gb" to the cart
    Then the pop up confirmation is displayed
    And the "Dell i7 8gb" is  added in the cart
    When the customer deletes the "<deleteproduct>" from the cart
    Then the "<deleteproduct>" is removed from the cart
    When the customer goes to place the order
    Then the payment form is displayed
    When the customer fills the "<name>" and the "<credit card>"
    And the customer clicks on Purchase button
    Then the purchase is successful
    And the total amount is the expected one
    And the purchase id is generated
    When the customer clicks on the ok button to finish the purchase
    Then the user is redirected to the home page

    Examples:
      |  deleteproduct  |   name |  credit card     |
      |  Sony vaio i5   | Jordi  | 8938234567562345 |
```

### The Screenplay implementation
The Screenplay pattern describes tests in terms of actors and the tasks they perform. Tasks are represented as objects performed by an actor, rather than methods. This pattern is implemented in the task to open the site.The example is below:
```java
    @Given("{actor} is searching products on the online demo shop")
    public void jordiIsResearchingThingsOnTheOnlineDemoShop(Actor actor) {
        actor.wasAbleTo(NavigateTo.demoOnlineShop());
        }
```

The `NavigateTo` class is responsible for opening the demo online shop, this task is called in the previous method:
```java
public class NavigateTo extends UIInteractionSteps {
    public static Performable demoOnlineShop() {
        return Task.where("{0} opens the DEMO ONLINE SHOP",
                Open.browserOn().the(DemoOnlineShop.class));
    }
}
```
### The Action Classes implementation.

The action classes pattern is implemented in the rest of the actions. Here is an example:

```java
   @When("the customer clicks on the {string}")
public void heClickOnTheCategory(String category) {
        navigationThroughProduct.category(category);
        }

@Then("the customer should see information about the {string}")
public void heShouldSeeInformationAboutTheProduct(String product) {
        String getProduct = shouldSeeThe.product(product).trim();
        Assert.assertEquals("The product is not fit with the category selected! Take a look!", product, getProduct);
        }
```

These classes are declared using the Serenity `@Steps` annotation, shown below:
```java
    @Steps
    NavigationThroughProduct navigationThroughProduct;

    @Steps
    ShouldSeeThe shouldSeeThe;
```

The `@Steps`annotation tells Serenity to create a new instance of the class, and inject any other steps or page objects that this instance might need.

Each action class models a particular facet of the customer behaviour: navigating to a particular category, add the product to the cart, searching the product in the car, deleting a product, making the purchase. These action classes are designed to be small and self-contained, which makes them more stable and easier to maintain.

The `NavigationThroughProduct` class is an example of a very simple interaction class. This class interact with the page objets of the home page, and to enable this, we make the class extend the Serenity `UIInteractionSteps`.This is a pattern that allows to interact with an object the times we need:
```java
public class NavigationThroughProduct extends UIInteractionSteps {

    @Step("Navigate through the categories {0}")
    public void category(String category) {
        find(categories(category)).click();
    }
}
```

The `Product` class is typical of a light-weight Page Object: it is responsible uniquely for locating elements on the page, and it does this by defining locators or occasionally by resolving web elements dynamically.
```java
public class Product {
    public static String PRODUCT = "//a[normalize-space()='%s']";
    public static By ADDTOCARBTN = By.xpath("//a[normalize-space()='Add to cart']");
}
```

The main advantage of the approach used in this example is not in the lines of code written, although Serenity does reduce a lot of the boilerplate code that you would normally need to write in a web test. The real advantage is in the use of many small, stable classes, each of which focuses on a single job. This application of the _Single Responsibility Principle_ goes a long way to making the test code more stable, easier to understand, and easier to maintain.

## Executing the tests
To run the sample project, you can either just run the `CucumberTestSuite` test runner class, or run either `mvn verify` from the command line.

By default, the tests will run using Chrome.
```json
$ mvn clean verify
```
When the test are done, run the next command to get the full report:
```json
$ mvn serenity:aggregate
```
To open the full report on Mac OS run the command:
```json
open target/site/serenity/index.html
```
To open it on Windows OS run the command:
```json
start target/site/serenity/index.html
```


The test results will be recorded in the `target/site/serenity` directory.

### Webdriver configuration
The WebDriver configuration is managed entirely from this file, as illustrated below:
```java
webdriver {
    driver = chrome
        }
headless.mode = false

chrome.switches="""--start-maximized;--test-type;--no-sandbox;--ignore-certificate-errors;
                   --disable-popup-blocking;--disable-default-apps;--disable-extensions-file-access-check;
                   --incognito;--disable-infobars,--disable-gpu"""

```
Serenity uses WebDriverManager to download the WebDriver binaries automatically before the tests are executed.



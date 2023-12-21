package stepdefinitions;
    import io.cucumber.java.en.*;
    import workflows.SeleniumWorkFlow;
    import org.junit.Assert;
    import common.Assertion;
    public class CurrentScreenStepDefinition
	{
        SeleniumWorkFlow workFlow = new SeleniumWorkFlow();
        

            @When("^I click random Image sr in pdp$")			
            public void WhenIClickRandomImageSrInPdp()
            {
                workFlow.clickRandom(0,"Current Screen","Current Screen.ImagesrLinkXPATH","XPATH");
                
            }

            @When("^I scroll and click Add To Cart sr in pdp$")			
            public void WhenIScrollClickAddToCartSrInPdp()
            {
                workFlow.scrollAndClick(0,"Current Screen","Current Screen.AddToCartsrLinkXPATH","XPATH");
                
            }

            @When("^I selected Go to bag in pdp$")			
            public void WhenISelectedGoToBagInPdp()
            {
                workFlow.clickedElement(0,"Current Screen","Current Screen.GotobagButtonXPATH","XPATH");
                
            }

            @When("^I clicked Home in cart$")			
            public void WhenIClickedHomeInCart()
            {
                workFlow.clickedElement(0,"Current Screen","Current Screen.HomeLinkXPATH","XPATH");
                
            }

            @When("^I remove all items Cart in cart$")			
            public void WhenIRemoveAllItemsCartInCart()
            {
                workFlow.removeAllItems(0,"Current Screen","Current Screen.CartLinkXPATH","XPATH");
                
            }

            @When("^I selected Home Wishlist icon in home page$")			
            public void WhenISelectedHomeWishlistIconInHomePage()
            {
                workFlow.clickedElement(0,"Current Screen","Current Screen.HomeWishlisticonButtonXPATH","XPATH");
                
            }

            @When("^I remove all items products in home page$")			
            public void WhenIRemoveAllItemsProductsInHomePage()
            {
                workFlow.removeAllItems(0,"Current Screen","Current Screen.productsLinkXPATH","XPATH");
                
            }
    }
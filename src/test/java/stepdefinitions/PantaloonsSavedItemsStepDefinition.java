package stepdefinitions;
    import io.cucumber.java.en.*;
    import workflows.SeleniumWorkFlow;
    import org.junit.Assert;
    import common.Assertion;
    public class PantaloonsSavedItemsStepDefinition
	{
        SeleniumWorkFlow workFlow = new SeleniumWorkFlow();
        

             @Then("^verify text empty wishlist in home page$")			
            public void ThenVerifyTextEmptyWishlistInHomePage()
            {
                Assertion.IsTrue(workFlow.verifyTextInLink(0,"Pantaloons Saved Items","Pantaloons Saved Items.emptywishlistLabelXPATH","XPATH"), "Then verify text empty wishlist in home page");
                
            }

             @Then("^'(.*)' is displayed with '(.*)'$")			
            public void ThenpageIsDisplayedWithcontent(String  page, String content)
            {
                Assertion.IsTrue(workFlow.VerifyDefaultpageIsdisplayed(page), "Then '<page>' is displayed with '<content>'");
                Assertion.IsTrue(workFlow.VerifymessageIsDisplayed(content), "");;
                Assertion.assertAll();
            }
    }
package stepdefinitions;
    import io.cucumber.java.en.*;
    import workflows.SeleniumWorkFlow;
    import org.junit.Assert;
    import common.Assertion;
    public class PagenameStepDefinition
	{
        SeleniumWorkFlow workFlow = new SeleniumWorkFlow();
        

            @When("^I Click if Visible Close popup in login$")			
            public void WhenIClickIfVisibleClosePopupInLogin()
            {
                workFlow.clickIfVisible(0,"Page name","Page name.ClosepopupLinkXPATH","XPATH");
                
            }

            @When("^I entered Search keyword st in search as '(.*)'$")			
            public void WhenIEnteredSearchKeywordStInSearchAssearchKeywordSt(String  searchKeywordSt)
            {
                workFlow.enterText(searchKeywordSt,0,"Page name","Page name.SearchkeywordstTextBoxXPATH","XPATH");
                
            }

            @When("^I selected Search Trigger sr in search$")			
            public void WhenISelectedSearchTriggerSrInSearch()
            {
                workFlow.clickedElement(0,"Page name","Page name.SearchTriggersrButtonXPATH","XPATH");
                
            }

            @When("^I click if visible Size in pdp$")			
            public void WhenIClickIfVisibleSizeInPdp()
            {
                workFlow.clickIfVisible(0,"Page name","Page name.SizeButtonXPATH","XPATH");
                
            }

            @When("^I clicked Cart Icon in cart$")			
            public void WhenIClickedCartIconInCart()
            {
                workFlow.clickedElement(0,"Page name","Page name.CartIconLinkXPATH","XPATH");
                
            }

            @When("^I selected Place Order in shipping address edit$")			
            public void WhenISelectedPlaceOrderInShippingAddressEdit()
            {
                workFlow.clickedElement(0,"Page name","Page name.PlaceOrderButtonXPATH","XPATH");
                
            }
    }
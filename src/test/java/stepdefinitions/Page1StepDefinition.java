package stepdefinitions;
    import io.cucumber.java.en.*;
    import workflows.SeleniumWorkFlow;
    import org.junit.Assert;
    import common.Assertion;
    public class Page1StepDefinition
	{
        SeleniumWorkFlow workFlow = new SeleniumWorkFlow();
        

            @When("^I scroll down Scroll in pdp as '(.*)'$")			
            public void WhenIScrollDownScrollInPdpAsscroll(String  scroll)
            {
                workFlow.scrollDown(scroll,0,"Page1","Page1.ScrollTextBoxXPATH","XPATH");
                
            }

            @When("^I wait in seconds wait in login as '(.*)'$")			
            public void WhenIWaitInSecondsWaitInLoginAswait(String  wait)
            {
                workFlow.waitInSeconds(wait,0,"Page1","Page1.waitTextBoxXPATH","XPATH");
                
            }

            @When("^I network list capture date in pdp as '(.*)'$")			
            public void WhenINetworkListCaptureDateInPdpAscaptureDate(String  captureDate)
            {
                workFlow.WhenINetworkListCaptureDateInPdpAscaptureDate(captureDate,0,"Page1","Page1.capturedateTextBoxXPATH","XPATH");
                
            }

            @When("^I wait in seconds wait in cart as '(.*)'$")			
            public void WhenIWaitInSecondsWaitInCartAswait1(String  wait1)
            {
                workFlow.waitInSeconds(wait1,0,"Page1","Page1.waitTextBoxID","ID");
                
            }

            @When("^I entered mobile number in login as '(.*)'$")			
            public void WhenIEnteredMobileNumberInLoginAsmobileNumber(String  mobileNumber)
            {
                workFlow.enterText(mobileNumber,0,"Page1","Page1.mobilenumberTextBoxXPATH","XPATH");
                
            }

             @Then("^verify text vfy entered mobile number or not in login$")			
            public void ThenVerifyTextVfyEnteredMobileNumberOrNotInLogin()
            {
                Assertion.IsTrue(workFlow.verifyTextInLink(0,"Page1","Page1.vfyenteredmobilenumberornotLabelXPATH","XPATH"), "Then verify text vfy entered mobile number or not in login");
                
            }

             @Then("^verify text Checkout login after vfy in shipping address edit$")			
            public void ThenVerifyTextCheckoutLoginAfterVfyInShippingAddressEdit()
            {
                Assertion.IsTrue(workFlow.verifyTextInLink(0,"Page1","Page1.CheckoutloginaftervfyLabelXPATH","XPATH"), "Then verify text Checkout login after vfy in shipping address edit");
                
            }

             @Then("^verify text vfye empty cart in cart$")			
            public void ThenVerifyTextVfyeEmptyCartInCart()
            {
                Assertion.IsTrue(workFlow.verifyTextInLink(0,"Page1","Page1.vfyeemptycartLabelXPATH","XPATH"), "Then verify text vfye empty cart in cart");
                
            }
    }
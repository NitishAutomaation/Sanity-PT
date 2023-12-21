package stepdefinitions;
    import io.cucumber.java.en.*;
    import workflows.SeleniumWorkFlow;
    import org.junit.Assert;
    import common.Assertion;
    public class SigninStepDefinition
	{
        SeleniumWorkFlow workFlow = new SeleniumWorkFlow();
        

            @When("^I entered Email lg in login as '(.*)'$")			
            public void WhenIEnteredEmailLgInLoginAsemailLg(String  emailLg)
            {
                workFlow.enterText(emailLg,0,"Sign in","Sign in.EmaillgTextBoxXPATH","XPATH");
                
            }

            @When("^I selected Next in login$")			
            public void WhenISelectedNextInLogin()
            {
                workFlow.clickedElement(0,"Sign in","Sign in.NextButtonXPATH","XPATH");
                
            }

            @When("^I entered Password lg in login as '(.*)'$")			
            public void WhenIEnteredPasswordLgInLoginAspasswordLg(String  passwordLg)
            {
                workFlow.enterText(passwordLg,0,"Sign in","Sign in.PasswordlgTextBoxXPATH","XPATH");
                
            }

            @When("^I selected Next1 in login$")			
            public void WhenISelectedNext1InLogin()
            {
                workFlow.clickedElement(0,"Sign in","Sign in.Next1ButtonXPATH","XPATH");
                
            }
    }
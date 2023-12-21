package common;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevToolsException;
import org.openqa.selenium.devtools.v114.network.Network;
import org.openqa.selenium.devtools.v114.network.model.RequestId;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;

import common.CustomException;
import common.WebBrowser;
import common.WebBrowserUtil;

import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.DevToolsException;
import org.openqa.selenium.devtools.v114.network.Network;
import org.openqa.selenium.devtools.v114.network.Network.GetResponseBodyResponse;
import org.openqa.selenium.devtools.v114.network.model.RequestId;
import java.time.temporal.ChronoUnit;
import java.time.LocalDateTime;
import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import org.openqa.selenium.devtools.v114.network.model.RequestId;
import org.openqa.selenium.devtools.v114.network.Network;
import org.openqa.selenium.devtools.DevToolsException;
import org.openqa.selenium.devtools.DevTools;
import java.util.Optional;


public class customUtil {
	public static WebDriver browser;
	public static DevTools devTools;
	
	

// Method in workflow file for refresh

 public static void RefreshHomePage(String param, int index,String page,String XpathKey, String identifier)
  {
    
    boolean flag = false;
    String ListUrl1=CommonUtil.GetData(param);
    String[]  ListUrl = ListUrl1.split("&");
    String url = ListUrl[0];
    url = url.replaceAll("\\s", "");
    String list1= ListUrl[1];
    String[] strSplit = list1.split(",");
    ArrayList<String> list = new ArrayList<String>(Arrays.asList(strSplit));
    try {
		Thread.sleep(2000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    WebBrowser.setCurrentBrowser(index);
    browser=WebBrowser.getBrowser();
    WebBrowserUtil.AttachPage(page);
    
    browser.navigate().refresh();
    
    
    //List<String> list = Arrays.asList("apiClientKey", "apiKey", "includeStrategyData", "placements", "rcs", "rid", "sessionId",
           // "home_page.recs_1_desktop", "home_page.recs_2_desktop", "home_page.recs_3_desktop",
            //"home_page.recs_4_desktop","home_page.recs_4_desktopjasja");
    
   // String url = "https://apigate.abfrl.in/products/hppage";   
    try {
    	
    flag = WebBrowser.DevTool(list,url); 
    if(flag) {
    	System.out.println("All items are present");
    	ExtentCucumberAdapter.addTestStepLog("All items are present");
    }
    else {    	    
    throw new CustomException("Some items are not present");
    }   
    } catch (Exception e) {		
    	System.out.println("Some items are not present");
    	throw new CustomException(e.getMessage(), e);
	}         
  }
 
 // DevTool method should be pasted in WebBrowser file
 
	
 // APIclick Method in workflow file 
 public void APIclickHomePage(int index,String page,String XpathKey, String identifier) throws InterruptedException
 {
   WebBrowser.setCurrentBrowser(index);
   browser=WebBrowser.getBrowser();
   WebBrowserUtil.AttachPage(page);

   browser.navigate().refresh();
  
   Thread.sleep(500);
   //driver.execute_script("window.scrollTo(0, 2000);")
   WebBrowserUtil.ScrollAndWait();
   Thread.sleep(1000);
   WebElement product=browser.findElement(By.xpath("(//div[@class='Swiper_swiperWrapper__jCaq_'])[2]"));
   WebBrowserUtil.Click(product);    

   Set<String> windowHandles = browser.getWindowHandles();

   // Iterate through the handles to find the new window
   for (String windowHandle : windowHandles) {
       if (!windowHandle.equals(browser.getWindowHandle())) {
           // Switch to the new window
       	browser.switchTo().window(windowHandle);
       }
   }
   String URL = "https://recs.richrelevance.com/rrserver/apiclick?";
 
   Thread.sleep(300);
   String currentURL = browser.getCurrentUrl();
		System.out.println("Current Url: " + currentURL);
	// Extract numbers using regex
   Pattern pattern = Pattern.compile("\\d+");
   Matcher matcher = pattern.matcher(currentURL);
   
   StringBuilder numbers = new StringBuilder();
   while (matcher.find()) {
       numbers.append(matcher.group());	            
   }
   String Stringproductid = numbers.toString();
   int productid = Integer.parseUnsignedInt(Stringproductid);

   // Print the extracted numbers
   System.out.println("productid : " + productid);
   ExtentCucumberAdapter.addTestStepLog("productid : " + productid);
   
	// Cast WebDriver to DevTools to access DevTools methods					
   WebBrowser.devTools = ((ChromeDriver)browser).getDevTools();											 			    

   // Create a session to use the Network domain
	WebBrowser.devTools.createSession();
   
	WebBrowser.devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));

	WebBrowser.devTools.addListener(Network.requestWillBeSent(),request->{
		String requestUrl = request.getRequest().getUrl();
   	    RequestId requestId = request.getRequestId();		
		String	ReqString ="";
		
		if (requestUrl.startsWith(URL)) {
			System.out.println("requestUrl: " + requestUrl);
			 ExtentCucumberAdapter.addTestStepLog("requestUrl: " + requestUrl);
			System.out.println("Method: " + request.getRequest().getMethod());
			ExtentCucumberAdapter.addTestStepLog("Method: " + request.getRequest().getMethod());
			String[] ReqStrings = requestUrl.split("\\?");
           for (String key : ReqStrings) {
           	if (key.contains("pos")) {
           		ReqString = key;	
           		System.out.println("bodystr: " + ReqString);
           	 	break;
           	}	                	
           }	
           String[] ReqStrings1 = ReqString.split("&");     
           for (String key : ReqStrings1) {
           	if(key.contains("p=")) {
           		System.out.println("The product id click is : > "+ productid +"And the product is in clickevent param is " + key);
           		ExtentCucumberAdapter.addTestStepLog("The product id click is : "+ productid +"And the product is in clickevent param is " + key);
           	    break;}            	
           }
           for (String key : ReqStrings1) {
           	if(key.contains("pos=")) {
           		System.out.println("the product position is we clicked first product and  the product position in the url is " + key);	
           		ExtentCucumberAdapter.addTestStepLog("the product position is we clicked first product and  the product position in the url is " + key);
           		String[] ReqStrings2 = key.split("=");
           		if(ReqStrings2[1].equals('1'))
           			System.out.println("product position correct");
           			ExtentCucumberAdapter.addTestStepLog("product position correct");   
           		break;}            	
           }           
		}		
	});
 }
}
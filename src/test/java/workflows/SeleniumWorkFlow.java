package workflows;
import common.WebBrowser;
import common.CommonUtil;
import common.WebBrowserUtil;
import common.DbHelper;
import common.Hooks;
import common.CustomException;
import java.util.stream.Collectors;
import java.util.Random;
import java.util.Comparator;
import common.YMLUtil;
import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Calendar;
import java.io.BufferedWriter;
import java.text.DateFormat;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.http.Headers;
import io.restassured.specification.RequestSpecification;
import java.util.Arrays;
import java.io.File;
import org.openqa.selenium.interactions.Actions;
import java.nio.file.Path;
import org.openqa.selenium.Keys;
import java.nio.file.Paths;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.Assert;
import java.util.Optional;
import java.util.List;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.HashMap;
import common.PostgreSQLUtil;
import common.SqlServerUtil;
import common.MySqlServerUtil;
import common.MongoDBUtil;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogEntries;
import java.io.IOException;
import java.util.TimeZone;
import java.awt.Color;
import common.TFSUtil;
import common.RestAssuredUtil;
import io.percy.selenium.Percy;
import java.io.ByteArrayInputStream;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.OutputType;
import common.Constants;
import common.customUtil;
    
public class SeleniumWorkFlow
{
    public static WebDriver browser;
    public static int Number_of_Iteration = 5;
    private static String path = System.getProperty("user.dir");
    public static String APPLICATIONSETTINGS = "ApplicationSettings.xml";
    private static Percy percy;
    private static String appSettingsPath = Paths.get(path, "src", "test", "java", APPLICATIONSETTINGS).toString();
    private static String percyFlag =  CommonUtil.GetXMLData(appSettingsPath, "Percy");
    private static String language = CommonUtil.GetXMLData(appSettingsPath, "Language");
    private static String yes = "Yes";
    final static Logger log = Logger.getLogger(SeleniumWorkFlow.class);
 		 
  public void accessToPage()
  {
    WebBrowser.LaunchApplication(true);
    log.info("Method accessToPage completed.");
  }
    public void clickIfVisible(int index,String page,String XpathKey, String identifier)
    {
    WebBrowser.setCurrentBrowser(index);
    browser=WebBrowser.getBrowser();
    WebBrowserUtil.AttachPage(page);
    try {
      String xpath=YMLUtil.getYMLObjectRepositoryData(XpathKey);
      WebBrowserUtil.ClickIfVisible(WebBrowserUtil.findElement(xpath,identifier));
    }
    catch(Exception ex) {}
  }public void enterText(String textToBeEntered,int index, String page,String XpathKey,String identifier)
  {
        WebBrowser.setCurrentBrowser(index);
        browser=WebBrowser.getBrowser();
        WebBrowserUtil.AttachPage(page);
        textToBeEntered= CommonUtil.GetData(textToBeEntered);
      try
          {
            String xpath=YMLUtil.getYMLObjectRepositoryData(XpathKey);
            WebElement element = WebBrowserUtil.findElement(xpath,identifier);
            WebBrowserUtil.EnterText(element, textToBeEntered);
            log.info("Method enterText completed.");
          }
      catch(Exception e)
          {
            log.error("Method enterText not completed."+e);
            throw new CustomException(e.getMessage(), e);
      }
  }

    public void clickedElement(int index,String page,String XpathKey,String identifier)
    {
      WebBrowser.setCurrentBrowser(index);
      browser=WebBrowser.getBrowser();
      WebBrowserUtil.AttachPage(page);
      try
      {
        String xpath=YMLUtil.getYMLObjectRepositoryData(XpathKey);
        WebElement elementToBeClicked = WebBrowserUtil.findElement(xpath,identifier);
        WebBrowserUtil.Selected(elementToBeClicked);
        log.info("Method clickedElement completed.");

      }
      catch(Exception e)
      {
        log.error("Method clickedElement not completed."+e);
        throw new CustomException(e.getMessage(), e);
      }
  }
  public void clickRandom(int index,String page,String XpathKey, String identifier)
  {
    WebBrowser.setCurrentBrowser(index);
    browser=WebBrowser.getBrowser();
    WebBrowserUtil.AttachPage(page);
    boolean staleElement = true;
    int i = 0;
    while (staleElement && i < Constants.Number_of_Iteration)
    {
      try
      {
        String xpath=YMLUtil.getYMLObjectRepositoryData(XpathKey);
        List<WebElement> links =  WebBrowserUtil.getElementByXpath(xpath);
        Random random = new Random();
        int randomNumber=0;
        if(links.size()>1)
        {
          randomNumber =random.nextInt(links.size() - 1);
        }
        else{
          randomNumber=0;
        }
        WebBrowserUtil.ScrollAndClick(links.get(randomNumber));
        System.out.println("Clicked on Link no '" + (randomNumber + 1) + "' out of " + links.size());
        staleElement = false;
      }
      catch(Exception ex)
      {
        i++;
        staleElement = true;
        if(i == 4)
        {
          throw new CustomException(ex.getMessage(),ex);
        }
      }
    }
  }
  public void scrollAndClick(int index,String page,String XpathKey, String identifier)
  {
    try
    {
      WebBrowser.setCurrentBrowser(index);
      browser=WebBrowser.getBrowser();
      WebBrowserUtil.AttachPage(page);
      String xpath=YMLUtil.getYMLObjectRepositoryData(XpathKey);
      WebBrowserUtil.ScrollAndClickUsingJS(xpath,identifier);
    }
    catch(Exception e)
    {
      throw new CustomException(e.getMessage(), e);
    }
  }
  public void removeAllItems(int index,String page,String XpathKey, String identifier)
  {
    WebBrowser.setCurrentBrowser(index);
    browser=WebBrowser.getBrowser();
    try
    {
      WebBrowserUtil.AttachPage(page);
      String mainXpath=YMLUtil.getYMLObjectRepositoryData(XpathKey);
      if(mainXpath.contains("**"))
      {
        String[] splitXpath=mainXpath.split("\\*\\*");
        mainXpath=splitXpath[0];
      }
      List<WebElement> eleList= WebBrowserUtil.findElements(mainXpath,identifier);
      if(eleList.size()>0)
      {
        for (WebElement link : eleList)
        {
          boolean staleElement = true;
          int i = 0;
          while (staleElement && i < Constants.Number_of_Iteration)
          {
            try
            {
              WebBrowserUtil.ScrollAndClickUsingJS(eleList.get(0));
              staleElement = false;
              if(mainXpath.contains("**"))
              {
                String[] splitXpath=mainXpath.split("\\*\\*");
                for (int j = 0; j < 3; j++)
                {
                  try
                  {
                    WebBrowserUtil.Click(WebBrowserUtil.findElement(splitXpath[1],identifier));
                    eleList= WebBrowserUtil.findElements(splitXpath[0],identifier);
                    break;
                  }
                  catch(Exception e) { }
                }
              }
              else
              {
                try
                {
                  WebBrowserUtil.AcceptAlert();
                }
                catch(Exception exc)  { }
              }
            }
            catch (Exception ex)
            {
              i++;
              staleElement = true;
              if (i == 4)
              {
                throw new CustomException(ex.getMessage(),ex);
              }
            }
          }
          
        }
      }
    }
    catch (Exception ex)
    {}
  }
  public void scrollDown(String param,int index,String page,String XpathKey, String identifier)
  {
    try
    {
      WebBrowser.setCurrentBrowser(index);
      browser=WebBrowser.getBrowser();
      WebBrowserUtil.AttachPage(page);
      param = CommonUtil.GetData(param);
      WebBrowserUtil.ScrollDown(Integer.parseInt(param));
    }
    catch(Exception e)
    {
      throw new CustomException(e.getMessage(), e);
    }
  }
  public void waitInSeconds(String time, int index, String page, String Xpath, String identifier)
  {
  try{
      time = CommonUtil.GetData(time);
      int waitTime=Integer.parseInt(time);
      Thread.sleep((1000)*waitTime);
    }
    catch(Exception ex)
      {}
    log.info("Method waitInSeconds completed.");
  }
  public boolean verifyTextInLink(int index,String page,String XpathKey, String identifier)
  {
    WebBrowser.setCurrentBrowser(index);
    browser=WebBrowser.getBrowser();
    WebBrowserUtil.AttachPage(page);
    boolean staleElement = false;
    for( int i = 0; i < Constants.Number_of_Iteration; i++) {
      try
      {
        String xpath=YMLUtil.getYMLObjectRepositoryData(XpathKey);
        WebElement element = WebBrowserUtil.findElement(xpath,identifier);
        staleElement = WebBrowserUtil.verifyLabelDisplayed(element);
      }
      catch(Exception ex)
      {
        staleElement = false;
      }
    }
    return staleElement;
  }
  public boolean VerifyDefaultpageIsdisplayed(String defaultpage)
  {
    boolean isVerified = false;
    for (int i = 0; i <= 2; i++)
    {
      WebBrowser.setCurrentBrowser(0);
      browser=WebBrowser.getBrowser();
      try{
        browser = WebBrowserUtil.AttachPage(defaultpage);
      }
      catch(Exception ex){}
      if (defaultpage != null && !defaultpage.isEmpty()  && !defaultpage.toUpperCase().equals("NA".toUpperCase()) && !defaultpage.toUpperCase().equals("Page1".toUpperCase()) && !defaultpage.toUpperCase().equals("Page2".toUpperCase()) && !defaultpage.toUpperCase().equals("Page3".toUpperCase())&& !defaultpage.toUpperCase().equals("Page4".toUpperCase()))
      {
        isVerified = browser.getTitle().toUpperCase().contains(defaultpage.toUpperCase());
      }
      else
      {
        isVerified = true;
      }
      if (isVerified)
      {
        break;
      }
    }
      try
      {
        ExtentCucumberAdapter.addTestStepLog("Actual Page Name: "+browser.getTitle()+", Expected Page Name: "+defaultpage);
        log.info("Method VerifyDefaultpageIsdisplayed completed.");
      }
      catch(Exception e)
      {
        log.error("Method VerifyDefaultpageIsdisplayed not completed."+e);
      }
    return isVerified;
  }
  public boolean VerifymessageIsDisplayed(String message)
  {
    boolean isVerified = false;
    String[] messages;
    String[] messages1;
    List<String> boolvalues = null;
    if(message.contains("verifyalert_"))
    {
        String[] msgs=message.split("_");
        return WebBrowserUtil.verifyAlertText(msgs[1]);
    }
    if ((message.contains("frameid_")) && (message.contains("&or&")))
    {
    String[] msgs = message.split("_");
    String frameID = "";
    for (int i = 0; i < msgs.length - 1; i++)
    {
    if (i != 0)
    {
    frameID = frameID + msgs[i] + "_";
    }
    }
    frameID = frameID.replaceAll("_$", "");
    WebBrowserUtil.GetFrame(frameID);
    String[] multiplemessages = msgs[msgs.length - 1].toString().split("&or&");
    for (int i = 0; i < multiplemessages.length; i++)
    {
    boolean isVerified1 = WebBrowserUtil.IsElementPresent(multiplemessages[i]);
    if (isVerified1)
    {
    isVerified = true;
    }
    }
    return isVerified;
    }
    else if(message.contains("frameid_"))
    {
      String[] msgs=message.split("_");
      String frameID="";
      for(int i=0;i<msgs.length-1;i++)
      {
          if(i!=0)
          {
            frameID = frameID+msgs[i]+"_";
          }
      }
      frameID= frameID.replaceAll("_$", "");
      WebBrowserUtil.GetFrame(frameID);
      isVerified = WebBrowserUtil.IsElementPresent(msgs[msgs.length-1]);
      return isVerified;
    }
    if (message.contains(")_or_("))
    {
        messages = message.split(")_or_(");
      for(String msg : messages)
        {
            if (msg.toUpperCase().equals("NA"))
            {
                return true;
            }
          messages1 = msg.split("_and_");
    boolvalues.clear();
        for(String msg1 : messages1)
        {
          String messagetocheck = msg1;
          messagetocheck = msg1.replace("(", "").replace(")", "");
        browser = WebBrowser.getBrowser();
          isVerified = WebBrowserUtil.IsElementPresent(messagetocheck);
          if (isVerified)
          {
          boolvalues.add(Boolean.toString(isVerified));
        }
        }
          if (messages1.length == boolvalues.size())
      {
          Assert.assertTrue(isVerified);
          System.out.println("message Labelwith value : message is verified");
          return isVerified;
          }
          }
          return isVerified;
      }
    else if (message.contains("_and_"))
    {
      messages = message.split("_and_");
      for(String msg : messages)
      {
        browser = WebBrowser.getBrowser();
      isVerified = WebBrowserUtil.IsElementPresent(msg);
      if (isVerified == false)
      {
        return isVerified;
      }
    }
    return isVerified;
    }
    else if (message.contains("_or_"))
      {
      messages = message.split("_or_");
      for(String msg : messages)
      {
          browser = WebBrowser.getBrowser();
            if(msg=="NA")
          {
          Assert.assertTrue(isVerified);
          System.out.println("message Labelwith value : message is verified");
          return isVerified;
          }
          isVerified = WebBrowserUtil.IsElementPresent(msg);
          if (isVerified)
          {
              Assert.assertTrue(isVerified);
              System.out.println("message Labelwith value : message is verified");
              return isVerified;
          }
      }
    return isVerified;
    }
    if(!message.equals("NA"))
    {
      for (int i = 0; i <= 2; i++)
      {
      String content = "";
        browser = WebBrowser.getBrowser();
        isVerified =WebBrowserUtil.IsElementPresent(message);
        content = WebBrowserUtil.GetContent(message);
        if (isVerified)
          {
        Assert.assertTrue(isVerified);
        System.out.println("message Labelwith value : message is verified");
        if (content != null && !content.isEmpty())
        {
          ExtentCucumberAdapter.addTestStepLog("Actual content: "+content+", Expected content: "+message);
          log.info("Method  VerifymessageIsDisplayed completed.");
        }
          return isVerified;
          }
      }
        return isVerified;
    }
    else
    {
        return true;
    }
  }
		   public void WhenINetworkListCaptureDateInPdpAscaptureDate(String param,int index,String page, String XPath, String identification){
            try{
              customUtil customObject = new customUtil();
              customObject.RefreshHomePage(param, index,  page, XPath, identification);
            }
            catch(Exception e)
            {
              throw new CustomException(e.getMessage(), e);
            }
          }
    }
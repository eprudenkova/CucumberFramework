package com.hrms.utils;

import com.hrms.testbase.PageInitializer;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.UnexpectedTagNameException;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class CommonMethods extends PageInitializer {

    /**
     * this method will clear a textbox and send text to it
     *
     * @param element
     * @param textToSend
     */
    public static void sendText(WebElement element, String textToSend) {
        element.clear();
        element.sendKeys(textToSend);
    }

    /**
     * this method will return an object of Explicit wait with time set to 20 sec
     *
     * @return WebDriverWait
     */
    public static WebDriverWait getWait() {//explicit wait, use only with getWait().until(ExpectedCondition...);
        WebDriverWait wait = new WebDriverWait(driver, Constants.EXPLICIT_WAIT_TIME);
        return wait;
    }

    /**
     * this method will wait until given element becomes clickable
     *
     * @param element
     */
    public static void waitForClickability(WebElement element) {
        getWait().until(ExpectedConditions.elementToBeClickable(element));
    }

    /**
     * this method will wait until given element becomes visible
     *
     * @param element
     */
    public static void waitForVisibility(WebElement element) {
        getWait().until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * this method will wait till and then click
     */
    public static void click(WebElement element) {
        waitForClickability(element);
        element.click();
    }

    public static JavascriptExecutor getJSExecutor() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return js;
    }

    public static void jsExecutorClick(WebElement element) {
        getJSExecutor().executeScript("arguments[0].click()", element);
    }

    /**
     * take screenshot
     *
     * @param fileName
     */
    public static byte[] takeScreenshot(String fileName) {
        TakesScreenshot ts = (TakesScreenshot) driver;
        byte[] bytes = ts.getScreenshotAs(OutputType.BYTES);
        File file = ts.getScreenshotAs(OutputType.FILE);

        String destinationFile = Constants.SCREENSHOT_FILEPATH + fileName + getTimeStamp("yyyy-MM-dd-HH-mm-ss") + ".png";
        try {
            FileUtils.copyFile(file, new File(destinationFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bytes;
    }

    /**
     * get Time Stamp as unique name for screenshot
     *
     * @param pattern
     * @return
     */
    public static String getTimeStamp(String pattern) {//pattern example --> yyyy-MM-dd-HH-mm-ss
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(date);
    }

    /**
     * javadoc: This method will click on a radio button or check box by the given list of WebElements and the value
     *
     * @param radioOrCheckBoxes
     * @param value
     */
    public static void clickRadioOrCheckBox(List<WebElement> radioOrCheckBoxes, String value) {

        String actualValue;
        for (WebElement radioOrCheckBox : radioOrCheckBoxes) {
            actualValue = radioOrCheckBox.getAttribute("value").trim();
            if (radioOrCheckBox.isEnabled() && actualValue.equals(value)) {
                click(radioOrCheckBox);
                break;
            }
        }
    }

    /**
     * javadoc: This method will click on a radio button or check box by the given list of WebElements and the text value
     * @param radioOrCheckBoxes
     * @param value
     */
    public static void clickRadioOrCheckBoxByText(List<WebElement> radioOrCheckBoxes, String value) {

        String actualValue;
        for (WebElement radioOrCheckBox : radioOrCheckBoxes) {
            actualValue = radioOrCheckBox.getText().trim();
            System.out.println(actualValue);
            if (radioOrCheckBox.isEnabled() && actualValue.equals(value)) {
                click(radioOrCheckBox);
                break;
            }
        }
    }

//  We can Select by index, visibleText, value

    /**
     * javadoc: This method will select a value from a given DD by the given visible text
     * "try & catch" will help us to see and to catch any UnexpectedTagNameException
     * if our DD does not have a select tag.
     *
     * @param dd
     * @param visibleText
     */
    public static void selectDDValue(WebElement dd, String visibleText) {
        try {
            Select select = new Select(dd);
            List<WebElement> options = select.getOptions();
            for (WebElement option : options) {
                if (option.getText().trim().equalsIgnoreCase(visibleText)) {
                    select.selectByVisibleText(visibleText);
                    break;
                }
            }
        } catch (UnexpectedTagNameException e) {
            e.printStackTrace();
        }
    }

    /**
     * javadoc: This method will select a value from a given DD by the given index
     *
     * @param dd
     * @param index
     */
    public static void selectDDValue(WebElement dd, int index) {
        try {
            Select select = new Select(dd);
            List<WebElement> options = select.getOptions();
            int ddSize = options.size();
            if (ddSize > index) {
                select.selectByIndex(index);
            } else {
                System.out.println("Max Index is: " + (ddSize - 1));
            }
        } catch (UnexpectedTagNameException e) {
            e.printStackTrace();
        }
    }

//  We can switch to frame by index, by name or ID, by WebElement

    /**
     * javadoc: This method will switch to a frame by the given frame index
     *
     * @param frameIndex
     */
    public static void switchToFrame(int frameIndex) {
        try {
            driver.switchTo().frame(frameIndex);
        } catch (NoSuchFrameException e) {
            e.printStackTrace();
        }
    }

    /**
     * javadoc: This method will switch to a frame by the given name or ID
     *
     * @param nameOrID
     */
    public static void switchToFrame(String nameOrID) {
        try {
            driver.switchTo().frame(nameOrID);
        } catch (NoSuchFrameException e) {
            e.printStackTrace();
        }
    }

    /**
     * javadoc: This method will switch to a frame by the given WebElement
     *
     * @param element
     */
    public static void switchToFrame(WebElement element) {
        try {
            driver.switchTo().frame(element);
        } catch (NoSuchFrameException e) {
            e.printStackTrace();
        }
    }

    /**
     * javadoc: This method switches focus to a child window
     */
    public static void switchToChildWindow() {
        String mainWindow = driver.getWindowHandle();
        Set<String> allWindows = driver.getWindowHandles();//Set<String> --> because windows are always UNIQUE (unique id)
        for (String childWindow : allWindows) {
            if (!childWindow.equals(mainWindow)) {
                driver.switchTo().window(childWindow);
                break;
            }
        }
    }

    /**
     * This method will return and Object of Actions class
     *
     * @return
     */
    public static Actions getAction() {
        Actions action = new Actions(driver);
        return action;
    }

    /**
     * This method will move to element with help of Action Class
     *
     * @param element
     */
    public static void moveToElementAction(WebElement element) {
        getAction().moveToElement(element).perform();
    }

    /**
     * This method will return you all data from a drop down as a List of String
     * @param dropDown
     */
    public List<String> getAllDataFromDropDownIntoListOfString (WebElement dropDown) {
        Select select = new Select(dropDown);
        List<WebElement> allOptions = select.getOptions();
        List<String> allOptionsText = new ArrayList<>();
        for (int i = 1; i < allOptions.size(); i++) {
            String optionText = allOptions.get(i).getText();
            allOptionsText.add(optionText);
        }
        return allOptionsText;
    }
}

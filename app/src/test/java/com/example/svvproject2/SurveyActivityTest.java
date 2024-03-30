package com.example.svvproject2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;

public class SurveyActivityTest {

    private AndroidDriver<MobileElement> driver;

    @Before
    public void setUp() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("appPackage", "com.example.svvproject2");
        desiredCapabilities.setCapability("appActivity", ".SurveyActivity");
        desiredCapabilities.setCapability("automationName", "UiAutomator2");

        URL remoteUrl = new URL("http://localhost:4723");

        driver = new AndroidDriver<>(remoteUrl, desiredCapabilities);
    }

    @Test
    public void testFillSurveyForm() {
        MobileElement nameEditText = driver.findElementById("nameEditText");
        nameEditText.sendKeys("John Doe");

//        MobileElement birthDateButton = driver.findElementById("birthDateButton");
//        birthDateButton.click();
//        driver.findElementByXPath("//*[@class='android.widget.NumberPicker' and @index='0']").sendKeys("15");
//        driver.findElementByXPath("//*[@class='android.widget.NumberPicker' and @index='1']").sendKeys("10");
//        driver.findElementByXPath("//*[@class='android.widget.NumberPicker' and @index='2']").sendKeys("1990");
//        driver.findElementById("android:id/button1").click();

        MobileElement educationLevelSpinner = driver.findElementById("educationLevelSpinner");
        educationLevelSpinner.click();
        driver.findElementByXPath("//*[@text='Bachelor']").click();

        MobileElement cityEditText = driver.findElementById("cityEditText");
        cityEditText.sendKeys("New York");

        MobileElement maleRadioButton = driver.findElementById("maleRadioButton");
        maleRadioButton.click();

        MobileElement beneficialUseCaseEditText = driver.findElementById("beneficialUseCaseEditText");
        beneficialUseCaseEditText.sendKeys("AI can assist in various tasks");

        MobileElement chatgptCheckBox = driver.findElementById("chatgptCheckBox");
        chatgptCheckBox.click();
        MobileElement chatgptDefectsEditText = driver.findElementById("chatgptDefectsEditText");
        chatgptDefectsEditText.sendKeys("Some defects");

        // Wait for the send button to become visible
        WebDriverWait wait = new WebDriverWait(driver, 2);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("sendButton")));

        MobileElement sendButton = driver.findElementById("sendButton");
        assertTrue(sendButton.isDisplayed()); // Assert that the send button is visible
        sendButton.click();

        // Assert that the survey was submitted successfully
        String toastMessage = driver.findElementByXPath("//android.widget.Toast").getText();
        assertEquals("Survey submitted", toastMessage);
    }

    @Test
    public void testEmptyNameField() {
        // Fill all fields except the name field
        // Click the send button
        // Assert that an error message is displayed for the empty name field
    }

    @Test
    public void testUnselectAllAIModels() {
        // Fill all fields except AI model checkboxes
        // Click the send button
        // Assert that the survey can be submitted without selecting any AI model
    }

    @Test
    public void testSendButtonVisibility() {
        // Fill all required fields
        // Assert that the send button is visible
        // Clear a required field
        // Assert that the send button is not visible
    }

    @Test
    public void testAIModelDefectsVisibility() {
        // Check an AI model checkbox
        // Assert that the corresponding defects EditText is visible
        // Uncheck the AI model checkbox
        // Assert that the corresponding defects EditText is not visible
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}

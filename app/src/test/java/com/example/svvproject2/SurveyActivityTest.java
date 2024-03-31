package com.example.svvproject2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

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
import java.util.Calendar;
import java.util.List;

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
    public void testSendButtonVisiblity() {
        // Fill all fields with valid data
        MobileElement nameEditText = driver.findElementById("nameEditText");
        nameEditText.sendKeys("John Doe");

        MobileElement dayEditText = driver.findElementById("dayEditText");
        dayEditText.sendKeys("15");

        MobileElement monthEditText = driver.findElementById("monthEditText");
        monthEditText.sendKeys("6");

        MobileElement yearEditText = driver.findElementById("yearEditText");
        yearEditText.sendKeys("1990");

        MobileElement educationLevelSpinner = driver.findElementById("educationLevelSpinner");
        educationLevelSpinner.click();
        driver.findElementByXPath("//*[@text='Bachelor']").click();

        MobileElement cityEditText = driver.findElementById("cityEditText");
        cityEditText.sendKeys("New York");

        MobileElement maleRadioButton = driver.findElementById("maleRadioButton");
        maleRadioButton.click();

        MobileElement chatgptCheckBox = driver.findElementById("chatgptCheckBox");
        chatgptCheckBox.click();
        MobileElement chatgptDefectsEditText = driver.findElementById("chatgptDefectsEditText");
        chatgptDefectsEditText.sendKeys("Some defects");

        MobileElement beneficialUseCaseEditText = driver.findElementById("beneficialUseCaseEditText");
        beneficialUseCaseEditText.sendKeys("AI can assist in various tasks");

        // Wait for the send button to become visible
        WebDriverWait wait = new WebDriverWait(driver, 2);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("sendButton")));

        // Assert that the send button is visible
        MobileElement sendButton = driver.findElementById("sendButton");
        assertTrue(sendButton.isDisplayed());

        // Clear the "BeneficialUseCase" field
        beneficialUseCaseEditText.clear();

        // Wait for a short duration
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Assert that the send button is not visible
        List<MobileElement> sendButtonList = driver.findElementsById("sendButton");
        assertTrue(sendButtonList.isEmpty()); // Assert that the send button is not found
    }

    @Test
    public void testUnselectAllAIModels() {
        // Fill all fields except AI model checkboxes
        MobileElement nameEditText = driver.findElementById("nameEditText");
        nameEditText.sendKeys("John Doe");

        MobileElement dayEditText = driver.findElementById("dayEditText");
        dayEditText.sendKeys("15");

        MobileElement monthEditText = driver.findElementById("monthEditText");
        monthEditText.sendKeys("6");

        MobileElement yearEditText = driver.findElementById("yearEditText");
        yearEditText.sendKeys("1990");

        MobileElement educationLevelSpinner = driver.findElementById("educationLevelSpinner");
        educationLevelSpinner.click();
        driver.findElementByXPath("//*[@text='Bachelor']").click();

        MobileElement cityEditText = driver.findElementById("cityEditText");
        cityEditText.sendKeys("New York");

        MobileElement maleRadioButton = driver.findElementById("maleRadioButton");
        maleRadioButton.click();

        MobileElement beneficialUseCaseEditText = driver.findElementById("beneficialUseCaseEditText");
        beneficialUseCaseEditText.sendKeys("AI can assist in various tasks");

        // Wait for the send button to become visible
        WebDriverWait wait = new WebDriverWait(driver, 2);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("sendButton")));

        // Click the send button
        MobileElement sendButton = driver.findElementById("sendButton");
        sendButton.click();

        // Assert that the survey was submitted successfully
        String toastMessage = driver.findElementByXPath("//android.widget.Toast").getText();
        assertEquals("Survey submitted", toastMessage);
    }

    @Test
    public void testDefaultEducationLevelSelection() {
        MobileElement nameEditText = driver.findElementById("nameEditText");
        nameEditText.sendKeys("John Doe");

        MobileElement dayEditText = driver.findElementById("dayEditText");
        dayEditText.sendKeys("15");

        MobileElement monthEditText = driver.findElementById("monthEditText");
        monthEditText.sendKeys("6");

        MobileElement yearEditText = driver.findElementById("yearEditText");
        yearEditText.sendKeys("1990");

        MobileElement cityEditText = driver.findElementById("cityEditText");
        cityEditText.sendKeys("New York");

        MobileElement maleRadioButton = driver.findElementById("maleRadioButton");
        maleRadioButton.click();

        MobileElement beneficialUseCaseEditText = driver.findElementById("beneficialUseCaseEditText");
        beneficialUseCaseEditText.sendKeys("AI can assist in various tasks");

        // Wait for the send button to become visible
        WebDriverWait wait = new WebDriverWait(driver, 2);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("sendButton")));

        // Get the selected education level from the spinner
        MobileElement educationLevelSpinner = driver.findElementById("educationLevelSpinner");
        String selectedEducationLevel = educationLevelSpinner.getText();

        // Assert that the default education level is selected
        assertEquals("", selectedEducationLevel);

        // Click the send button
        MobileElement sendButton = driver.findElementById("sendButton");
        sendButton.click();

        // Assert that the survey was submitted successfully
        String toastMessage = driver.findElementByXPath("//android.widget.Toast").getText();
        assertEquals("Survey submitted", toastMessage);
    }

    @Test
    public void testInvalidBirthdayInput() {
        // Fill all fields except birthday
        MobileElement nameEditText = driver.findElementById("nameEditText");
        nameEditText.sendKeys("John Doe");

        MobileElement educationLevelSpinner = driver.findElementById("educationLevelSpinner");
        educationLevelSpinner.click();
        driver.findElementByXPath("//*[@text='Bachelor']").click();

        MobileElement cityEditText = driver.findElementById("cityEditText");
        cityEditText.sendKeys("New York");

        MobileElement maleRadioButton = driver.findElementById("maleRadioButton");
        maleRadioButton.click();

        MobileElement beneficialUseCaseEditText = driver.findElementById("beneficialUseCaseEditText");
        beneficialUseCaseEditText.sendKeys("AI can assist in various tasks");

        // Enter an invalid birthday (less than 12 years old)
        MobileElement dayEditText = driver.findElementById("dayEditText");
        dayEditText.sendKeys("15");

        MobileElement monthEditText = driver.findElementById("monthEditText");
        monthEditText.sendKeys("6");

        MobileElement yearEditText = driver.findElementById("yearEditText");
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        yearEditText.sendKeys(String.valueOf(currentYear - 10)); // Set year to 10 years ago

        // Wait for a short duration
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Assert that the send button is not visible
        List<MobileElement> sendButtonList = driver.findElementsById("sendButton");
        assertTrue(sendButtonList.isEmpty()); // Assert that the send button is not found

        // Assert that the error message is displayed
        MobileElement birthdayErrorTextView = driver.findElementById("birthdayErrorTextView");
        assertTrue(birthdayErrorTextView.isDisplayed());
        assertEquals("User must be at least 12 years old", birthdayErrorTextView.getText());
    }

    @Test
    public void testSendButtonVisibilityWithEmptyField() {
        // Fill all fields except one (e.g., name)
        MobileElement dayEditText = driver.findElementById("dayEditText");
        dayEditText.sendKeys("15");

        MobileElement monthEditText = driver.findElementById("monthEditText");
        monthEditText.sendKeys("6");

        MobileElement yearEditText = driver.findElementById("yearEditText");
        yearEditText.sendKeys("1990");

        MobileElement educationLevelSpinner = driver.findElementById("educationLevelSpinner");
        educationLevelSpinner.click();
        driver.findElementByXPath("//*[@text='Bachelor']").click();

        MobileElement cityEditText = driver.findElementById("cityEditText");
        cityEditText.sendKeys("New York");

        MobileElement maleRadioButton = driver.findElementById("maleRadioButton");
        maleRadioButton.click();

        MobileElement beneficialUseCaseEditText = driver.findElementById("beneficialUseCaseEditText");
        beneficialUseCaseEditText.sendKeys("AI can assist in various tasks");

        // Wait for a short duration
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Assert that the send button is not visible
        List<MobileElement> sendButtonList = driver.findElementsById("sendButton");
        assertTrue(sendButtonList.isEmpty()); // Assert that the send button is not found
    }

    @Test
    public void testGenderExclusivity() {
        // Fill all fields with valid data
        MobileElement nameEditText = driver.findElementById("nameEditText");
        nameEditText.sendKeys("John Doe");

        MobileElement dayEditText = driver.findElementById("dayEditText");
        dayEditText.sendKeys("15");

        MobileElement monthEditText = driver.findElementById("monthEditText");
        monthEditText.sendKeys("6");

        MobileElement yearEditText = driver.findElementById("yearEditText");
        yearEditText.sendKeys("1990");

        MobileElement educationLevelSpinner = driver.findElementById("educationLevelSpinner");
        educationLevelSpinner.click();
        driver.findElementByXPath("//*[@text='Bachelor']").click();

        MobileElement cityEditText = driver.findElementById("cityEditText");
        cityEditText.sendKeys("New York");

        MobileElement beneficialUseCaseEditText = driver.findElementById("beneficialUseCaseEditText");
        beneficialUseCaseEditText.sendKeys("AI can assist in various tasks");

        // Click on the maleRadioButton
        MobileElement maleRadioButton = driver.findElementById("maleRadioButton");
        maleRadioButton.click();

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.attributeToBe(maleRadioButton, "checked", "true"));
        assertEquals("true", maleRadioButton.getAttribute("checked"));
        assertEquals("false", driver.findElementById("femaleRadioButton").getAttribute("checked"));

        // Click on the femaleRadioButton
        MobileElement femaleRadioButton = driver.findElementById("femaleRadioButton");
        femaleRadioButton.click();

        wait.until(ExpectedConditions.attributeToBe(femaleRadioButton, "checked", "true"));
        assertEquals("true", femaleRadioButton.getAttribute("checked"));
        assertEquals("false", maleRadioButton.getAttribute("checked"));

        MobileElement chatgptCheckBox = driver.findElementById("chatgptCheckBox");
        chatgptCheckBox.click();
        MobileElement chatgptDefectsEditText = driver.findElementById("chatgptDefectsEditText");
        chatgptDefectsEditText.sendKeys("Some defects");

        // Wait for the send button to become visible
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("sendButton")));

        MobileElement sendButton = driver.findElementById("sendButton");
        sendButton.click();

        // Assert that the survey was submitted successfully
        String toastMessage = driver.findElementByXPath("//android.widget.Toast").getText();
        assertEquals("Survey submitted", toastMessage);
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}

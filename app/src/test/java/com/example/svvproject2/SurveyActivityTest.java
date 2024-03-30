package com.example.svvproject2;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

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
        // Select birth date from the date picker dialog

//        MobileElement educationLevelSpinner = driver.findElementById("educationLevelSpinner");
//        educationLevelSpinner.click();
        // Select education level from the spinner

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

        MobileElement sendButton = driver.findElementById("sendButton");
        sendButton.click();

        // Assert that the survey was submitted successfully
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

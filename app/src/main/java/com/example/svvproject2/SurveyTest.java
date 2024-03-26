package com.example.svvproject2;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class SurveyTest {

    private AndroidDriver<MobileElement> driver;

    @BeforeClass
    public void setUp() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName", "Android Emulator");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("appPackage", "com.example.surveyapp");
        capabilities.setCapability("appActivity", ".SurveyActivity");

        driver = new AndroidDriver<>(new URL("http://localhost:4723/wd/hub"), capabilities);
    }

    @Test
    public void testSurveySubmission() {
        MobileElement nameEditText = driver.findElementById("nameEditText");
        nameEditText.sendKeys("John Doe");

        MobileElement birthDateEditText = driver.findElementById("birthDateEditText");
        birthDateEditText.sendKeys("01/01/1990");

        MobileElement educationLevelSpinner = driver.findElementById("educationLevelSpinner");
        educationLevelSpinner.click();
        MobileElement bachelorOption = driver.findElementByXPath("//android.widget.CheckedTextView[@text='Bachelor']");
        bachelorOption.click();

        MobileElement cityEditText = driver.findElementById("cityEditText");
        cityEditText.sendKeys("New York");

        MobileElement maleRadioButton = driver.findElementById("maleRadioButton");
        maleRadioButton.click();

        MobileElement chatgptCheckBox = driver.findElementById("chatgptCheckBox");
        chatgptCheckBox.click();

        MobileElement chatgptDefectsEditText = driver.findElementById("chatgptDefectsEditText");
        chatgptDefectsEditText.sendKeys("Sometimes generates irrelevant responses");

        MobileElement beneficialUseCaseEditText = driver.findElementById("beneficialUseCaseEditText");
        beneficialUseCaseEditText.sendKeys("AI-powered virtual assistants");

        MobileElement sendButton = driver.findElementById("sendButton");
        sendButton.click();

        // Assert that the survey submission is successful
        MobileElement toastMessage = driver.findElementByXPath("//android.widget.Toast[@text='Survey submitted']");
        Assert.assertTrue(toastMessage.isDisplayed());
    }

    @Test
    public void testSendButtonVisibility() {
        MobileElement nameEditText = driver.findElementById("nameEditText");
        nameEditText.sendKeys("John Doe");

        MobileElement birthDateEditText = driver.findElementById("birthDateEditText");
        birthDateEditText.sendKeys("01/01/1990");

        MobileElement cityEditText = driver.findElementById("cityEditText");
        cityEditText.sendKeys("New York");

        MobileElement maleRadioButton = driver.findElementById("maleRadioButton");
        maleRadioButton.click();

        MobileElement beneficialUseCaseEditText = driver.findElementById("beneficialUseCaseEditText");
        beneficialUseCaseEditText.sendKeys("AI-powered virtual assistants");

        MobileElement sendButton = driver.findElementById("sendButton");
        Assert.assertTrue(sendButton.isDisplayed());
    }

    @Test
    public void testChatGPTDefectsVisibility() {
        MobileElement chatgptCheckBox = driver.findElementById("chatgptCheckBox");
        chatgptCheckBox.click();

        MobileElement chatgptDefectsEditText = driver.findElementById("chatgptDefectsEditText");
        Assert.assertTrue(chatgptDefectsEditText.isDisplayed());
    }

    @Test
    public void testBardDefectsVisibility() {
        MobileElement bardCheckBox = driver.findElementById("bardCheckBox");
        bardCheckBox.click();

        MobileElement bardDefectsEditText = driver.findElementById("bardDefectsEditText");
        Assert.assertTrue(bardDefectsEditText.isDisplayed());
    }

    @Test
    public void testClaudeDefectsVisibility() {
        MobileElement claudeCheckBox = driver.findElementById("claudeCheckBox");
        claudeCheckBox.click();

        MobileElement claudeDefectsEditText = driver.findElementById("claudeDefectsEditText");
        Assert.assertTrue(claudeDefectsEditText.isDisplayed());
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
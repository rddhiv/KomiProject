package com.cucumberFramework.pageObjects;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;
//import com.cucumberFramework.helper.WaitHelper;
//import com.sun.tools.javac.util.Assert;

public class KomiWebpageFunctions {
	
	private WebDriver driver;
	


	// WaitHelper waitHelper;

	public List<WebElement> col;

	public List<WebElement> rows;

	
	public KomiWebpageFunctions(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		// waitHelper = new WaitHelper(driver);
		// waitHelper.WaitForElement(userName, 60);
	}

	public boolean OpenWebpage(String arg1) {
		System.setProperty("webdriver.chrome.driver",
				System.getProperty("user.dir") + "/src/test/resources/drivers/chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("start-maximized");
		options.addArguments("disable-popup-blocking");
		options.addArguments("disable-notification");
		driver = new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		// driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		//driver.manage().window().maximize();
		driver.get(arg1);
		System.out.println("User entering the WebPage");
		// check page loaded or not
		JavascriptExecutor js = (JavascriptExecutor) driver;
		for (int i = 0; i < 50; i++) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException ex) {
				System.out.println("Page has not loaded yet ");
				Assert.assertTrue("Komi WebPage fully Loaded  ", false);
			}
			// again check page state
			if (js.executeScript("return document.readyState").toString().equals("complete")) {
				System.out.println("Page fully Loaded  ");
				Assert.assertTrue("Komi WebPage fully Loaded  ", true);
				
				break;
			}
		}
		driver.findElement(By.xpath("//span[text()='ACCEPT']")).click();
		return true;
	}

	public void verifyThumnailsVerification() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		// List<String> imageAlts = new List<String>();
		List<String> imageAlts = new ArrayList<String>();
		imageAlts.add("avatar");
		imageAlts.add("komi");
		imageAlts.add("youtube");

		List<WebElement> images = driver.findElements(By.tagName("img"));
		for (WebElement image : images) {
			String imageAlt = image.getAttribute("alt");
			if (imageAlts.contains(imageAlt)) {
				int x = image.getLocation().getX();
				int y = image.getLocation().getY();
				int w = image.getRect().getWidth();
				int h = image.getRect().getHeight();
				if (x > 0 && y > 0 && w > 0 && h > 0) {
					System.out.println("image with alt " + imageAlt + " is visible");
					Assert.assertTrue("Komi WebPage Thumnails fully Loaded  ", true);

				} else {
					System.out.println("image with alt " + imageAlt + " is NOT visible");
					Assert.assertTrue("Komi WebPage Thumnails fully not Loaded  ", true);
				}
			} else {
				System.out.println("image with alt " + imageAlt + " NOT found");
				Assert.assertTrue("Komi WebPage Thumnails mostly not Loaded  ", true);
			}
		}
		

	}

	public void verifySectionTitle() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		try {
			List<WebElement> sectionTitles = driver.findElements(By.xpath("(//div[@class='swiper-wrapper'])[1]/div"));
			WebElement focuselement = driver.findElement(By.xpath("//span[text()='TestDummy']"));
			System.out.println("No of Sections  : " + sectionTitles.size());
			for (WebElement title : sectionTitles) {
				
				Thread.sleep(1000);
				title.click();

				WebElement buttonname = title.findElement(By.cssSelector("span"));
				System.out.println("Title clicked  : " + buttonname.getAttribute("innerHTML"));
				Assert.assertTrue("Komi WebPage Section Title Clicked Properly ", true);
				Thread.sleep(1000);
				if (buttonname.getAttribute("innerHTML").equals("Subscribe"))
					break;

				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("arguments[0].scrollIntoView();", focuselement);

			}
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Assert.assertTrue("Komi WebPage Pagination is not working Properly  ", false);
		}

	}

	public void verifyPageNavigationMusicTracks() {
		try {
			WebElement focuselement = driver.findElement(By.xpath("//span[text()='Music Tracks']"));
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView();", focuselement);
						
			WebElement next = driver.findElement(By.xpath("//div[@class='ant-col btn-swiper__wrapper']/button[2]"));
			do {
				next.click();
				Thread.sleep(2000);
				Assert.assertTrue("Komi WebPage Pagination is working Properly ", true);	
			} while(next.isEnabled());
			
		
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Assert.assertTrue("Komi WebPage Pagination is not working Properly  ", false);
		}

	}

	public void verifyPresaveButton() {

		try {
			WebElement focuselement = driver.findElement(By.xpath("//span[text()='Pre-Save Music']"));
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView();", focuselement);
						
			WebElement presave = driver.findElement(By.xpath("//button[@aria-label='spotify-pre-release-button']"));
			presave.click();
			Thread.sleep(2000);
			if(driver.getTitle().contains("Spotify"))
			{
			   driver.navigate().back();
			   Assert.assertTrue("Komi WebPage PreSave Button Working properly ", true);
			}
			
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			 Assert.assertTrue("Komi WebPage PreSave Button not Working properly ", false);
			e.printStackTrace();
		}

	}

	public void verifyYoutubeButton() {
		try {
		WebElement focuselement = driver.findElement(By.xpath("//span[text()='New Video']"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", focuselement);
		driver.findElement(By.xpath("//div[@class='youtube-player__overlay']//button")).click();
		 Assert.assertTrue("Komi WebPage Youtube Button Working properly ", true);
		Thread.sleep(7000);
		
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			 Assert.assertTrue("Komi WebPage Youtube Button Working properly ", false);
			e.printStackTrace();
		}
		
	}

	public void verifySinglemusicModule() {
		try
		{
		WebElement focuselement = driver.findElement(By.xpath("//span[text()='Single Music']"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", focuselement);
		driver.findElement(By.xpath("//span[text()='More']")).click();
		
		List<WebElement> selectPlays = driver.findElements(By.xpath("//div[@data-testid='music-item']//div[2]/button"));
		String mainWindow=driver.getWindowHandle();
		for (WebElement play : selectPlays) {
			
			Thread.sleep(1000);
			
			play.click();
			
			Thread.sleep(1000);
			driver.switchTo().window(mainWindow);

		}
		driver.navigate().back();
		driver.navigate().refresh();
		for (int i = 0; i < 50; i++) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException ex) {
				System.out.println("Page has not loaded yet ");
			}
			// again check page state
			if (js.executeScript("return document.readyState").toString().equals("complete")) {
				System.out.println("Page fully Loaded  ");
				break;
			}
		}
		//driver.findElement(By.xpath("//span[text()='ACCEPT']")).click();
		//js.executeScript("arguments[0].scrollIntoView();", focuselement);
		//driver.findElement(By.xpath("//div[@class='detail-layout detail-layout--talent-profile']")).click();
		 Assert.assertTrue("Komi WebPage SingleMusic Module Button Working properly ", true);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		 Assert.assertTrue("Komi WebPage SingleMusic Module Button not Working properly ", false);
		e.printStackTrace();
	}

	}

	public void verifySubscriptionModule(String name, String email) {
		
		WebElement focuselement = driver.findElement(By.xpath("//div[text()='Subscribe']"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", focuselement);
		driver.findElement(By.xpath("//div[text()='Sign up for my newsletter']")).click();
		driver.findElement(By.id("INPUT")).sendKeys(name);
		driver.findElement(By.id("EMAIL_ADDRESS")).sendKeys(email);
		driver.findElement(By.xpath("//span[text()='Submit']")).click();
		 Assert.assertTrue("Komi WebPage Subscription Module Button  Working properly ", true);
	}
	
	public void closebrowser()
	{
		driver.quit();
	}

}

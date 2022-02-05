package CodeBase.Web;

import java.util.ArrayList;

import org.openqa.selenium.NoSuchWindowException;

import BaseClass.MainTestClass;
import PageObjects.AddToCartRep;
import Utilities.ReadExcel;

public class AmazonSeg extends MainTestClass{

	ReadExcel data = new ReadExcel();

	//Web functions
	public void AddToCart() throws Exception
	{
		waitForElementToAppear(AddToCartRep.SearchTxt, 20);
		sendKeys(AddToCartRep.SearchTxt, "iphone 13 pro max", "Entered iphone 13");
		waitForElementToAppear(AddToCartRep.SelectiPhonefromList, 20);
		click(AddToCartRep.SelectiPhonefromList, "Clicked on iPhone from suggestion list");
		waitForElementToAppear(AddToCartRep.ChooseiPhone, 20);
		click(AddToCartRep.ChooseiPhone, "Select iPhone");
		Thread.sleep(3000);
		ArrayList<String> tabs1 = new ArrayList<String> (driver.getWindowHandles());
	    driver.switchTo().window(tabs1.get(1));
		waitForElementToAppear(AddToCartRep.AddToCartBtn, 20);
		click(AddToCartRep.AddToCartBtn, "Click on Add to Cart button");
		waitForElementToAppear(AddToCartRep.SkipBtn, 20);
		if(isVisible(AddToCartRep.SkipBtn) == true)
		{
			click(AddToCartRep.SkipBtn, "Click on Skip button");
		}
		waitForElementToAppear(AddToCartRep.CartIcon, 20);
		click(AddToCartRep.CartIcon, "Click on Cart icon");
		waitForElementToAppear(AddToCartRep.ProceedToCheckoutBtn, 20);
		click(AddToCartRep.ProceedToCheckoutBtn, "Click on Proceed To Buy button");	
		waitForElementToAppear(AddToCartRep.SignInHeader, 20);
		verifyElement(AddToCartRep.SignInHeader, "Verify Sign in displayed");
		driver.close();
	}
}
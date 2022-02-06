package CodeBase.Mobile;

import BaseClass.MobileTestBase;
import PageObjects.MobAddToCart;
import Utilities.ReadExcel;

public class AmazonMobSeg extends MobileTestBase{

	ReadExcel data = new ReadExcel();

	//Mob functions
	public void AddToCart() throws Exception
	{
		waitForElementToAppear(MobAddToCart.SearchTxt, 20);
		sendKeys(MobAddToCart.SearchTxt, "iphone 13 pro max", "Entered iphone 13");
		waitForElementToAppear(MobAddToCart.SelectiPhonefromList, 20);
		javaScriptClick(MobAddToCart.SelectiPhonefromList, "Clicked on iPhone from suggestion list");
		waitForElementToAppear(MobAddToCart.ChooseiPhone, 15);
		javaScriptClick(MobAddToCart.ChooseiPhone, "Select iPhone");
		waitForElementToAppear(MobAddToCart.AddToCartBtn, 20);
		javaScriptClick(MobAddToCart.AddToCartBtn, "Click on Add to Cart button");
		waitForElementToAppear(MobAddToCart.ProceedToBuyBtn, 20);
		click(MobAddToCart.ProceedToBuyBtn, "Click on Proceed To Buy button");	
		waitForElementToAppear(MobAddToCart.SignInBtn, 20);
		verifyElement(MobAddToCart.SignInBtn, "Verify Sign in displayed");
	}
}
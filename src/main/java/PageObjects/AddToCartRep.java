package PageObjects;

public class AddToCartRep
{	
		public static final String SearchTxt = "@id=twotabsearchtextbox";
		public static final String SelectiPhonefromList = "@xpath=//div[@id='navbar']//div[@class='autocomplete-results-container']/div[1]//div[text()='iphone 13 pro max']";
		public static final String ChooseiPhone = "@xpath=//img[contains(@alt,'Sponsored Ad - Apple iPhone 13 Pro Max')]";
		public static final String AddToCartBtn = "@id=add-to-cart-button";
		public static final String SkipBtn = "@xpath=//span[@id='attachSiNoCoverage']//span[@class='a-button-text'][contains(text(),'Skip')]/../input";
		public static final String CartIcon = "@id=nav-cart-count";
		public static final String ProceedToCheckoutBtn = "@name=proceedToRetailCheckout";
		public static final String SignInHeader = "@xpath=//h1[contains(text(),'Sign-In')]";

}

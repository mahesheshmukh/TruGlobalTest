package Amazon.Mobile;

import org.testng.annotations.Test;
import BaseClass.MobileTestBase;
import CodeBase.Mobile.AmazonMobSeg;
import Utilities.MobileReports;
import Utilities.ReadExcel;
import Utilities.Reports;

public class AddToCartMobTest extends MobileTestBase
{
	ReadExcel data = new ReadExcel();
	AmazonMobSeg add = new AmazonMobSeg();

	@Test
	public void testAddToCartMob() throws Exception
	{
		MobileReports.test = MobileReports.extent.createTest("testAddToCartMob");
		try
		{
			add.AddToCart();
		}
		catch (Exception e)
		{
			MobileReports.failMobTest("Exception in testAddToCartMob" + e.getMessage());
		}
	}
}
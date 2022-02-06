package Amazon.Web;

import org.testng.annotations.Test;
import BaseClass.MainTestClass;
import CodeBase.Web.AmazonSeg;
import Utilities.ReadExcel;
import Utilities.Reports;

public class AddToCartTest extends MainTestClass
{
	ReadExcel data = new ReadExcel();
	AmazonSeg add = new AmazonSeg();

	@Test
	public void testAddToCart() throws Exception
	{
		Reports.test = Reports.extent.createTest("testAddToCartWeb");
		try
		{
			add.AddToCart();
		}
		catch (Exception e)
		{
			Reports.failTest("Exception in testAddToCartWeb" + e.getMessage());
		}
	}
}
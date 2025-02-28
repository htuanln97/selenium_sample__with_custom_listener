package Tiki;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import Enums.Tiki.MenuItems;
import ExtentReport.Logger;
import core.driver_wrapper.management.Driver;
import core.driver_wrapper.management.DriverManagement;

public class TikiTest extends TestBase {
	HomePage homePage = new HomePage();
	SearchPage searchPage;
	ItemDetailPage itemDetailPage = new ItemDetailPage();
	CartPage cartPage;
	
	@Test
	public void Testcase001() {
		String sExpectedPlaceHolder = "Tìm sản phẩm, danh mục hay thương hiệu mong muốn ...";
		
		Logger.info("Test case 001: Verify the product information loaded correctly");
		Logger.info("1. Navigate to 'TIKI' website");
		homePage.open();
		
		homePage.turnOffTikiPopUp(); //handle popup tiki promotion
		
		Logger.verify("Verify that 'Tìm sản phẩm ...' textbox is displayed");
		assertEquals(homePage.getSearchPlaceHolderText(),sExpectedPlaceHolder,
				" 'Tìm sản phẩm ...' textbox should be displayed");
		
		Logger.verify("Verify that 'Tìm kiếm' button is displayed");
		assertTrue(homePage.isDisplayedSearchButton(), " 'Tìm kiếm' button should be displayed");
		
		
		Logger.info("2. On home page, enter value in 'Tìm sản phẩm ...' text box - Test data: 'Điện thoại'");
		homePage.filterTextBox("Điện thoại");
		
		Logger.info("3. Click 'Tìm kiếm' button");
		searchPage = homePage.clickSearchButton();
		
		Logger.verify("Verify that Breadcrumb is 'Trang chủ > Điện thoại'");
		assertTrue(searchPage.isDisplayedBreadCrumb("Trang chủ > Điện thoại"),
				" Breadcrumb 'Trang chủ > Điện thoại' should be displayed");
		
		Logger.verify("Verify that 'Kết quả tìm kiếm cho `Điện thoại`' title is displayed");
		assertEquals(searchPage.getTextSearchResultTitle(),"Kết quả tìm kiếm cho `Điện thoại`",
				" 'Kết quả tìm kiếm cho `Điện thoại`' title should be displayed");
		
		Logger.info("4. Select any item from result grid");
		Logger.verify("Verify that the selected item is displayed correctly in details in details(Name, Price)");
		
		String sActualResult [] = searchPage.clickItemRandomAndGetInfo(); //click on any item and get info
		String sExpectedValue [] = itemDetailPage.getItemNameAndPrice(); // get info in new page
		
		assertEquals(sActualResult[0], sExpectedValue[0], String.format("Actual Item Name: %s should equal to Expected Item Name", sActualResult[0], sExpectedValue[0]));
		assertEquals(sActualResult[1], sExpectedValue[1], String.format("Actual Item Price: %s should equal to Expected Item Price", sActualResult[1], sExpectedValue[1]));
		
}
	@Test
	public void Testcase002() {
		
		Logger.info("Test case 002: Verify user can filter search condition for product");
		Logger.info("1. Navigate to 'TIKI' website");
		homePage.open();
		
		homePage.turnOffTikiPopUp(); //handle popup tiki promotion
		
		Logger.info("2. Select left menu: 'Điện Gia Dụng' > 'Đồ dùng nhà bếp' > 'Lò vi sóng' ");
		searchPage = homePage.clickSubItemInMenu(MenuItems.ELECTRIC_APPLIANCE, "Lò vi sóng");
		
		Logger.verify("Verify that Breadcrumb is 'Trang chủ > Điện Gia Dụng > Đồ dùng nhà bếp > Lò vi sóng'");
		assertTrue(searchPage.isDisplayedBreadCrumbWithFourParameters("Trang chủ > Điện Gia Dụng > Đồ dùng nhà bếp > Lò vi sóng"),
				" Breadcrumb 'Trang chủ > Điện Gia Dụng > Đồ dùng nhà bếp > Lò vi sóng' should be displayed");
		
		Logger.verify("Verify that 'Lò vi sóng' title is displayed");
		assertEquals(searchPage.getTextSearchResultTitle(),"Lò vi sóng",
				" 'Lò vi sóng' title should be displayed");
		
		Logger.info("3. Select 'Nhà cung cấp' > 'Tiki Trading'");
		searchPage.selectSupplier("Tiki Trading");
		searchPage.waitForLoading();
		
		Logger.info("4. Check on 'TIKINOW Giao Nhanh' checkbox");
		searchPage.checkOnTikiNowFastDelivery();
		searchPage.waitForLoading();
		
		Logger.info("5. Enter price range, then select OK button > '1.000.000 - 2.000.000'");
		searchPage.inputPriceAndClickOKButton(1000000, 2000000);
		searchPage.waitForLoading();
		
		Logger.verify("Verify that 'Nhà cung cấp: Tiki Trading','Giao hàng nhanh 2h: Có', 'Giá: 1.000.000đ đến 2.000.000đ'");
		assertTrue(searchPage.isDisplayedKeyWordTag("Giao hàng nhanh 2h: Có, Giá: 1.000.000đ đến 2.000.000đ, Nhà cung cấp: Tiki Trading"),
				"'Nhà cung cấp: Tiki Trading','Giao hàng nhanh 2h: Có', 'Giá: 1.000.000đ đến 2.000.000đ'");
			
	}
	
	@Test
	public void Testcase003() {
		// This test case is used for test assertion custom 
		Logger.info("1. Load the web base");
		homePage.open();
		String url = DriverManagement.getDriver().getCurrentUrl();
		System.out.println("Expected URL: " + url);
		Assert.assertFalse(false, "Verify that test");
		assertFalse(true, "Test assertion 1");
		Logger.verify("Verify wrong");
		assertFalse(true, "Test assertion 2");
	}
	
}
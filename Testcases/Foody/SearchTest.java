package Foody;


import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.Test;

import ExtentReport.Logger;
import core.driver_wrapper.management.Driver;



public class SearchTest extends TestBase{
	HomePage homePage = new HomePage();
	SearchPage searchPage;
	SearchDetailPage searchDetailPage = new SearchDetailPage();
	
	@Test
	public void TC001() {
		System.out.println("Test case 001: Verify the homepage information loaded correctly");
		
		Logger.info("1. Navigate to Foody website");
		homePage.open();
		
		Logger.verify("Verify that 'Giao tận nơi' title is displayed");
		
		Assert.assertTrue(homePage.isDisplayedTitle("Giao tận nơi"), 
				" 'Giao tận nơi' should be displayed");
		
		Logger.verify("'Đặt bàn ưu đãi' title is displayed");
		
		Assert.assertTrue(homePage.isDisplayedTitle("Đặt bàn ưu đãi"), 
				" 'Đặt bàn ưu đãi' should be displayed");
		
		Logger.info("2. On home page enter value in 'Filter' text box - Test data: 'Cơm tấm'");
		homePage.filterTextBox("Cơm tấm");
		
		Logger.info("3. Click 'Search' button");
		searchPage = homePage.clickSearchButton();
		
		Logger.verify("In new page verify that: 'Địa điểm' menu item is selected");
		Assert.assertTrue(searchPage.isSelectedMenuItem("Địa điểm"), " 'Địa điểm' menu item should be selected");
		
		Logger.info("4. Click tab 'Đánh giá tốt nhất'");
		searchPage.clickOnTab("Đánh giá tốt nhất");
		Driver.waitForLoadingPage();
		
		Logger.info("5. Select any item from result grid");
		
		//Get Name and Full Adress In Search Page and click on Item
		HashMap<String, String> itemInfoInSearchPage = searchPage.clickOnRandomItemAndGetItemDetails();
		System.out.println(itemInfoInSearchPage);
		Driver.switchToSpecificTab(1);
		
		//Get Name and Full Adress In Search Result Detail Page
		HashMap<String, String> itemInfoInSearchDetailPage = searchDetailPage.getItemDetails();
		System.out.println(itemInfoInSearchDetailPage);
		
		Logger.verify("Verify that the selected item is displayed correctly in details in next tab");
		Assert.assertEquals(itemInfoInSearchPage.keySet(), itemInfoInSearchDetailPage.keySet(), 
				"Item Name should be displayed correctly in details in next tab");
		
		
		Assert.assertEquals(itemInfoInSearchPage.values(), itemInfoInSearchDetailPage.values(), 
				"Item Address should be displayed correctly in details in next tab");
	}
	
	@Test
	public void TC002() {
		System.out.println("Test case 002: Verify user can search restaurant");
		
		
		Logger.info("1. Navigate to Foody website");
		homePage.open();
		
		Logger.info("2. Select 'Tỉnh thành' combobox => \"TP.HCM\"");
		homePage.selectLocation("TP. HCM");
		
		Logger.info("3. Select 'Category' combobox => \"Ăn uống\" > \"Quán ăn\"");
		homePage.selectCategory("Ăn uống", "Quán ăn");
		
		Logger.info("4. Enter value in 'Filter' textbox => \"Phở Bò\"");
		homePage.filterTextBox("Phở Bò");
		
		Logger.info("5. Click 'Search' button");
		searchPage = homePage.clickSearchButton();
		
		Logger.verify("Verify that the name of all displayed items in the result grid contains 'Phở/Bò'");
		Assert.assertTrue(searchPage.areDisplayedAllItemsInTheResultGridContain("Phởs/Bò"), 
				"The name of all displayed items in the result grid should contains keyword 'Phở/Bò'");
	}
	

}

package com.prod.testcases;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.prod.base.gudpplBaseSetup;
import com.prod.estimate.CategoryPage;
import com.prod.estimate.EstimatePage;
import com.prod.estimate.MaterialPage;
import com.prod.estimate.ProjectFilesPage;
import com.prod.estimate.ProjectPage;
import com.prod.signin.Pages.BasePage;
import com.prod.signin.Pages.SignInPage;

import ExcelLib.ExcelDataConfig;
import ExcelLib.TestUtil;

public class EstimateTest extends gudpplBaseSetup{


	public ExcelDataConfig excelDataConfig;
	
	private WebDriver driver;
	
	@BeforeClass
	public void setUp() throws InterruptedException {
		
		driver = getDriver();
		BasePage basePage  = new BasePage(driver);
		basePage.loginPage();
		
		SignInPage signInPage = new SignInPage(driver);
		signInPage.verifySigin("pasan.villvay@gmail.com", "letmein");
		Assert.assertTrue(signInPage.verifySignInPage());
		System.out.println("Successfully login Cabinet Face");
		
	}
	
	
	@DataProvider
	public Iterator<Object[]> getTestData() {
		
		ArrayList<Object[]> testdata = TestUtil.getEventDataFromFile();
		
		
		return testdata.iterator();
		
		
	}
	
	
	
	@Test(dataProvider="estimateDetails")
	public void testSignIn(String materials , String products , String products2 , String partName) throws InterruptedException, IOException {
	
		
		
		
		
		EstimatePage estimatePage = new EstimatePage(driver);
		estimatePage.estimsteProcess();
		System.out.println("Successfully landed estimate page");
	
		ProjectPage projectPage = new ProjectPage(driver);
		projectPage.createEstimste(getSaltString(), "Sektion");
		System.out.println("Successfully added project name and type");
		
		ProjectFilesPage projectFilesPage = new ProjectFilesPage(driver);
		projectFilesPage.skipFile();
		
		
		CategoryPage categoryPage = new CategoryPage(driver);
		categoryPage.estimsteProcess(materials, products, products2, partName);
		
	//	MaterialPage materialPage = new MaterialPage(driver);
	//	materialPage.calculateDiscount();
		
	}
	
	
	public String getSaltString() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 18) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        System.out.println(saltStr);
        return saltStr;

    }
	
	@DataProvider(name="estimateDetails")
	public Object[][] getLoginData(){
		
		
		excelDataConfig = new ExcelDataConfig("DIY Paint Grade Solid Wood Shaker.xlsx");
		
		
		
		int rowCount = excelDataConfig.getRowNum(0);
		
		Object[][] estimateDetails = new Object[rowCount][4];
		
		for(int r=0;r<rowCount;r++) {
			
			estimateDetails[r][0] = excelDataConfig.getDataa(0, r, 0);
			estimateDetails[r][1] = excelDataConfig.getDataa(0, r, 1);
			estimateDetails[r][2] = excelDataConfig.getDataa(0, r, 2);
			estimateDetails[r][3] = excelDataConfig.getDataa(0, r, 3);
			
		
			
			
		}
				return estimateDetails;
				
	}
	
	/*@AfterMethod
	public void tearDown() {
		driver.quit();
	}*/
}

package com.prod.estimate;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CategoryPage {

	
	private WebDriver driver;
	
	
	private By addPartBtn = By.xpath(".//*[@id='div-materials-parts']/div[3]/div/div[3]/table/tbody/tr[2]/td/button");
	private By deleteBtn = By.xpath(".//*[@id='div-materials-parts']/div[3]/div/div[3]/table/tbody/tr[2]/td[11]/button[2]");
	

	public CategoryPage(WebDriver driver) {
		this.driver=driver;
	}
	
	public MaterialPage estimsteProcess(String materials , String products , String products2 , String partName) throws InterruptedException {
	
		Collection<String> listOne = new ArrayList(Arrays.asList());
		Collection<String> listTwo = new ArrayList(Arrays.asList());
		
		listOne = 	estimate(materials , products , partName);
		
		listTwo = 	estimate(materials , products2 , partName);
		
		/*Iterator<String> iterator = listOne.iterator();
		while (iterator.hasNext()) {
		    System.out.println(iterator.next());
		}*/
		
		/*Iterator<String> iterator2 = listTwo.iterator();
		while (iterator.hasNext()) {
		    System.out.println(iterator.next());
		}*/
		
		
			List<String> firstProduct = new ArrayList<String>(listOne);
		    List<String> secoundProdct = new ArrayList<String>(listTwo);


		    firstProduct.removeAll( listTwo );
		    secoundProdct.removeAll( listOne );



		    System.out.println( firstProduct );
		    System.out.println( secoundProdct );
		    
		    
		return null;
		
		
	}

	public Collection<String> estimate(String materials , String products , String partName) throws InterruptedException {
		
		synchronized (driver)
		{
		    driver.wait(3000);
		}
		
		System.out.println("Print value "+materials);
		driver.findElement(By.xpath(".//*[@id='div-materials-parts']/div[3]/div/div[2]/div[1]/div")).click();
		driver.findElement(By.xpath(".//*[@id='div-materials-parts']/div[3]/div/div[2]/div[1]/div/select/option[text() = '"+materials+"' ]")).click();
		
		synchronized (driver)
		{
		    driver.wait(3000);
		}
		
		driver.findElement(By.xpath(".//*[@id='div-materials-parts']/div[3]/div/div[2]/div[2]/div")).click();
		driver.findElement(By.xpath(".//*[@id='div-materials-parts']/div[3]/div/div[2]/div[2]/div/select/option[text() = '"+products+"' ]")).click();
	
		
		synchronized (driver)
		{
		    driver.wait(6000);
		}
		
	
		clickOnAddPart();
	
		synchronized (driver)
		{
		    driver.wait(3000);
		}
	
				
		driver.findElement(By.xpath(".//*[@id='div-materials-parts']/div[3]/div/div[3]/table/tbody/tr[2]/td[3]/div[1]")).click();
		driver.findElement(By.xpath(".//*[@id='div-materials-parts']/div[3]/div/div[3]/table/tbody/tr[2]/td[3]/div[1]/select/option[text() =  '"+partName+"' ]")).click();
		
		
		synchronized (driver)
		{
		    driver.wait(3000);
		}
		
		List<WebElement> resultGrid = 	driver.findElements(By.xpath(".//*[@id='div-materials-parts']/div[3]/div/div[3]/table/tbody/tr[2]/td[5]/div[1]/select/option"));
		Collection<String> listOne = new ArrayList(Arrays.asList());
		int total_Employees = resultGrid.size();
	  
	    synchronized (driver)
		{
		    driver.wait(3000);
		}
		System.out.println("Total parts "+total_Employees);

		if(total_Employees == 0) {
			
			System.out.println("No parts found");
			
		}else {
			
			for(int x=0;x<total_Employees;x++) {
				
				String partss = resultGrid.get(x).getText();
				listOne.add(partss);
				//System.out.println("Parts Name "+partss);
			}
		}    
	     
		synchronized (driver)
		{
		    driver.wait(3000);
		}
		
		
		clickOnDelete();
		
		synchronized (driver)
		{
		    driver.wait(3000);
		}
		
		

		
		return listOne;
	}
	public void clickOnAddPart() {
		
		
		WebElement addPartBtnElement =  driver.findElement(addPartBtn);
		
		if(addPartBtnElement.isDisplayed()) {
			
			addPartBtnElement.click();
		}
		
		else {
			System.out.println("Add Part Button not found");
			driver.get("http://dev.villvay.com/thecabinetface");
		}
		
		
	}
	
	public void clickOnDelete() {
		
		
		WebElement deleteBtnElement =  driver.findElement(deleteBtn);
		
		if(deleteBtnElement.isDisplayed()) {
			
			deleteBtnElement.click();
		}
		
		else {
			System.out.println("Delete Button not found");
			driver.get("http://dev.villvay.com/thecabinetface");
		}
		
		
	}

	
	
}

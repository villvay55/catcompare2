package ExcelLib;

import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;

import com.prod.base.Xls_Reader;

public class TestUtil {
	
	static Xls_Reader reader;
	
	public static ArrayList<Object[]> getEventDataFromFile(){
		
		ArrayList<Object[]> myData = new ArrayList<Object[]>();
		
		try {
			
			
			reader = new Xls_Reader("E://TestData//DIY Paint Grade Solid Wood Shaker.xlsx");
		}catch (Exception e) {
			
			e.printStackTrace();
		}
		
		for(int rowNum = 3 ; rowNum <= reader.getRowCount("Event");  rowNum++ ) {
			
			DataFormatter formatter = new DataFormatter();
			//Cell cell = sheet.getRow(rowno).getCell(colNo);
			//String result = formatter.formatCellValue(cell);
			
			
			String materials = reader.getCellData("Event", "materials", rowNum);
			String products = reader.getCellData("Event", "products", rowNum);
			String products2 = reader.getCellData("Event", "products2", rowNum);
			String partName = reader.getCellData("Event", "partName", rowNum);
			
			
			Object ob[] = {materials, products, products2, partName};
			myData.add(ob);		
			
		}
		
		
		return myData;	
		
	}	
	
}

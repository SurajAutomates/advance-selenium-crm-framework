package genericUtilitis;

import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtility {
	public Object[][] getExcelData() throws EncryptedDocumentException, IOException {
		InputStream fis = ExcelUtility.class.getClassLoader().getResourceAsStream("shoppingAppData.xlsx");
		
		Workbook workbook = WorkbookFactory.create(fis);
		Sheet sheet = workbook.getSheet("loginData");
		int rowCount = sheet.getLastRowNum();
		short columnCount = sheet.getRow(0).getLastCellNum();
		Object[][] obj = new Object[rowCount][columnCount];
		for (int i = 0; i <obj.length; i++) {
			for (int j = 0; j<obj[i].length; j++) {
				obj[i][j] = sheet.getRow(i+1).getCell(j).toString();
			}
		}
		return obj;  
	}
	
	
}

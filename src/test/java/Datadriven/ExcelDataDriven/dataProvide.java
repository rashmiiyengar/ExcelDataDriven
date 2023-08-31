package Datadriven.ExcelDataDriven;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

class dataProvide {

	
	DataFormatter formatter = new DataFormatter();
	// with testng we can send multiple set of data to our tests
	// 5 sets of data as 5 arrays from data provider to your tests
	// then your test will run 5 times with 5 seperate sets of data (arrays)

	@DataProvider(name = "driveTest")
	public Object[][] getData() throws IOException {

		FileInputStream fis = new FileInputStream("C://Users//Rashmi//Documents//Demodata.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(fis);

		XSSFSheet sheet = workbook.getSheetAt(1);

		int rowCount = sheet.getPhysicalNumberOfRows();
		XSSFRow row = sheet.getRow(0);
		int columnCount = row.getLastCellNum();

		Object data[][]= new Object[rowCount - 1][columnCount];

		for (int i = 0; i < rowCount - 1; i++) {
			row=sheet.getRow(i);
			for (int j = 0; j < columnCount; j++) {
				
				XSSFCell cell = row.getCell(j);
				data[i][j]=	formatter.formatCellValue(cell);
					
			}
		}
		return data;
		// Object[][] data =
		// {{"hello","test",1},{"hello1","test1",2},{"hello2","test2",3 }};
		// object is superset of all datatypes

	}

	@Test(dataProvider = "driveTest")
	public void Testcase(String greet, String comm, String id){

		System.out.println(greet);

	}
}

package utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.regexp.recompile;

public class XLUtility {
	private static XSSFSheet excelWSheet;
	private static XSSFWorkbook excelWBook;
	private static XSSFCell cell;
	private static XSSFRow row;

	public static Object[][] getTableArray(String FilePath, String SheetName) throws Exception {   
		Object[][] tabArray = null;
		try {
			FileInputStream ExcelFile = new FileInputStream(FilePath);
			// Access the required test data sheet
			excelWBook = new XSSFWorkbook(ExcelFile);
			excelWSheet = excelWBook.getSheet(SheetName);
			row = excelWSheet.getRow(0);
			int totalCols = row.getLastCellNum();
			totalCols--;

			int startRow = 1;
			int startCol = 1;
			int ci,cj;
			int totalRows = excelWSheet.getLastRowNum();
			int activeRows = 0;
			//int totalCols = 3;
			ci=0;
			for (int i=startRow;i<=totalRows;i++, ci++) {                  
				if (getCellData(i,0).trim().toUpperCase().equals("YES") || getCellData(i,0).trim().toUpperCase().equals("Y")) {
					activeRows++;
				}
			}

			tabArray=new String[activeRows][totalCols];
			ci=0;
			for (int i=startRow;i<=totalRows;i++){//, ci++) {                  
				cj=0;
				if (getCellData(i,0).trim().toUpperCase().equals("YES") || getCellData(i,0).trim().toUpperCase().equals("Y")) {
					for (int j=startCol;j<=totalCols;j++){//, cj++){

						tabArray[ci][cj]=getCellData(i,j);
						System.out.println("(" + getCellData(0,j) + ") = " + tabArray[ci][cj]);
						//ci++;
						cj++;
					}
					ci++;
				}
			}
		}
		catch (FileNotFoundException e){
			System.out.println("Could not read the Excel sheet");
			e.printStackTrace();
		}
		catch (IOException e){
			System.out.println("Could not read the Excel sheet");
			e.printStackTrace();
		}
		return(tabArray);
	}

	public static String getCellData(int RowNum, int ColNum) throws Exception {

		try{
			cell = excelWSheet.getRow(RowNum).getCell(ColNum);
			int dataType = cell.getCellType();
			if  (dataType == 3) {
				return "";
			}else if (dataType == 0) {
				int numCellData = (int) cell.getNumericCellValue();
				return Integer.toString(numCellData);
			} else {
				String CellData = cell.getStringCellValue().toString();
				//CellData = Cell.getNumericCellValue();
				return CellData;
			}
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
			throw (e);
		}

	}
	
	public static String getCellValBasedOnOtherCellVal(String FilePath, String SheetName, String ColOne, String ColOneVal, String ColTwo) {
		String reqVal = null;
		try {
			FileInputStream ExcelFile = new FileInputStream(FilePath);
			// Access the required test data sheet
			excelWBook = new XSSFWorkbook(ExcelFile);
			excelWSheet = excelWBook.getSheet(SheetName);
			row = excelWSheet.getRow(0);
			int totalCols = row.getLastCellNum();
			totalCols--;
			int startRow = 1;
			int startCol = 1;
			int reqColOneNum = 0;
			int reqColTwoNum = 0;
			int reqRowNum = 0;
			int totalRows = excelWSheet.getLastRowNum();
			
			for (int i=startCol;i<=totalCols;i++) {                  
				if (getCellData(0,i).trim().toUpperCase().equals(ColOne.trim().toUpperCase())) {
					reqColOneNum = i;
					break;
				}
			}
			
			for (int t=startCol;t<=totalCols;t++) {                  
				if (getCellData(0,t).trim().toUpperCase().equals(ColTwo.trim().toUpperCase())) {
					reqColTwoNum = t;
					break;
				}
			}
			
			for (int j=startRow;j<=totalRows;j++) {                  
				if (getCellData(j,reqColOneNum).trim().toUpperCase().equals(ColOneVal.trim().toUpperCase())) {
					reqRowNum = j;
					break;
				}
			}
			if (reqRowNum == 0) {
				reqVal = "NF";
			} else {
				reqVal = getCellData(reqRowNum,reqColTwoNum);
			}
		} catch (Exception e) {
			System.out.println("Could not get value from the file");
			e.printStackTrace();
		}
		return reqVal;
		
		//ExcelWSheet.
		//return null;
	}
	
	//@SuppressWarnings("null")
	public static Map<String, String> getDataSet(String FilePath, String SheetName, String key)   {   
		Map<String, String> dataMap = new HashMap<String, String>();
		//dataMap = null;
		key = key.trim().toUpperCase();
		try {
			FileInputStream ExcelFile = new FileInputStream(FilePath);
			// Access the required test data sheet
			excelWBook = new XSSFWorkbook(ExcelFile);
			excelWSheet = excelWBook.getSheet(SheetName);
			row = excelWSheet.getRow(0);
			int totalCols = row.getLastCellNum();
			totalCols--;

			int startRow = 1;
			int startCol = 1;
			//int ci,cj;
			int totalRows = excelWSheet.getLastRowNum();
			//int activeRows = totalRows;
			//int totalCols = 3;
			//ci=0;
						
			//ci=0;
			for (int i=startRow;i<=totalRows;i++){//, ci++) {                  
				//cj=0;
				try {
					if (getCellData(i,0).trim().toUpperCase().equals(key)) {
						for (int j=startCol;j<=totalCols;j++){//, cj++){
							if (getCellData(i,j) != null){
								dataMap.put(getCellData(0,j), getCellData(i,j));
							}
							
							//tabArray[ci][cj]=getCellData(i,j);
							//System.out.println("(" + getCellData(0,j) + ") = " + dataMap.get(getCellData(0,j)));
							//ci++;
							//cj++;
						}
						//ci++;
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		catch (FileNotFoundException e){
			System.out.println("Could not read the Excel sheet");
			e.printStackTrace();
		}
		catch (IOException e){
			System.out.println("Could not read the Excel sheet");
			e.printStackTrace();
		}
//		catch (NullPointerException e){
//			System.out.println("Could not read the Excel sheet");
//			e.printStackTrace();
//		}
		return(dataMap);
	}
}

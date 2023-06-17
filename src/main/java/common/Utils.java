package common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public class Utils {
	/**
	 * This is a class which contains all common handing not related to selenium Including: read/write files (csv, json,
	 * excel, word,...); capture evidence; ....
	 */
	
	//Get config value by key
	public static String getConfigValueByKey(String configFileName, String configKey) {
		Properties prop = new Properties();
		String configValue="";
		try(InputStream fileInput = new FileInputStream("src/main/resources/"+configFileName)) {
			prop.load(fileInput);
			configValue = prop.getProperty(configKey);
		} catch (Exception e) {
			System.out.println("");
			e.printStackTrace();
		}
		return configValue;
	}

	// CSV
	public static List<String[]> readTestData(String csvFilePath) {
		List<String[]> testData = new ArrayList<>();
		String line;

		try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
			while ((line = br.readLine()) != null) {
				String[] data = line.split(",");
				testData.add(data);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return testData;
	}

	// Excel
	public static Object[][] getTestData(String filePath, String sheetName) throws IOException {
		// Load Excel file
		File file = new File(filePath);
		FileInputStream inputStream = new FileInputStream(file);
		Workbook workbook = WorkbookFactory.create(inputStream);

		// Get sheet by name
		Sheet sheet = workbook.getSheet(sheetName);

		// Get number of rows and columns
		int rowCount = sheet.getLastRowNum();
		int columnCount = sheet.getRow(0).getLastCellNum();

		// Create 2D object array to store data
		Object[][] data = new Object[rowCount][columnCount];

		// Loop through rows and columns to get data
		for (int i = 0; i < rowCount; i++) {
			Row row = sheet.getRow(i + 1);
			for (int j = 0; j < columnCount; j++) {
				Cell cell = row.getCell(j);
				data[i][j] = cell.toString();
			}
		}

		// Close workbook and input stream
		workbook.close();
		inputStream.close();

		// Return data
		return data;
	}

}

package utils;

import java.io.File;
import java.io.FileInputStream;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.*;

public class DatosExcel {
	public static Object[][] leerExcel(String ruta,String nombreHoja) throws Exception {
		FileInputStream file = new FileInputStream(new File(ruta));
		
		XSSFWorkbook libroExcel= new XSSFWorkbook(file);
		XSSFSheet Hoja=libroExcel.getSheet(nombreHoja);
		
		System.out.println(nombreHoja);
		
		XSSFRow fila;
		int filas = Hoja.getPhysicalNumberOfRows();
		int columnas= Hoja.getRow(0).getPhysicalNumberOfCells();
		
		Object cellValue[][]=new Object[filas][columnas];
		
		for (int r = 0; r < filas; r++) {
		 fila = Hoja.getRow(r);
		 if (fila == null){ 
			 break; 
	     }else{ 
	    	 for (int c = 0; c < columnas; c++) {
	    		 DataFormatter dataFormatter = new DataFormatter();
	    		 cellValue[r][c] = dataFormatter.formatCellValue(Hoja.getRow(r).getCell(c));
	    	 } 
	     }
		 }
		
		libroExcel.close();
		
		return cellValue; 
	} 
}

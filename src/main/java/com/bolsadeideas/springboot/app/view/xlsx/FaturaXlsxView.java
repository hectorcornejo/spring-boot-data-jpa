package com.bolsadeideas.springboot.app.view.xlsx;

import java.io.FileInputStream;
import java.util.Map;

import org.apache.commons.compress.utils.IOUtils;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Shape;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import com.bolsadeideas.springboot.app.models.entity.Factura;
import com.bolsadeideas.springboot.app.models.entity.ItemFactura;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component("factura/ver.xlsx")
public class FaturaXlsxView extends AbstractXlsxView{

	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		//Camniar nombre del archivo
		response.setHeader("Content-Disposition", "attachment; filename=\"factura_view.xlsx\" ");
		
		//Aqui creamos la factura que se crea en el controlador a traves del model (con el mismo nombre)
		Factura factura = (Factura) model.get("factura");
		
		//Se crea el documento xlsx
		Sheet sheet = workbook.createSheet("Factura Spring");
		
		//Para traducir la pagina
		MessageSourceAccessor mensajes = getMessageSourceAccessor();
		
		//Tamaños de celdas y combinar celdas
		sheet.setColumnWidth(6, 10000);
	    sheet.setColumnWidth(8, 4000);
	    sheet.setColumnWidth(5, 4000);
	    sheet.addMergedRegion(new CellRangeAddress(4, 4, 8, 10));
	    sheet.addMergedRegion(new CellRangeAddress(5, 5, 8, 10));
	    sheet.addMergedRegion(new CellRangeAddress(6, 6, 8, 10));
	    sheet.addMergedRegion(new CellRangeAddress(8, 8, 9, 10));
	    sheet.addMergedRegion(new CellRangeAddress(9, 9, 9, 10));
	    sheet.addMergedRegion(new CellRangeAddress(10, 10, 9, 10));
	    sheet.addMergedRegion(new CellRangeAddress(11, 11, 9, 10));
	    sheet.addMergedRegion(new CellRangeAddress(12, 12, 9, 10));
	    sheet.addMergedRegion(new CellRangeAddress(13, 13, 9, 10));
		 
		//Insercion de una foto en el documento
		FileInputStream imageFile = new FileInputStream("C:\\Users\\Usuario\\Desktop\\Curso\\Spring5\\workspace\\spring-boot-data-jpa\\src\\main\\resources\\templates\\img\\logo.PNG");
        byte[] imageData = IOUtils.toByteArray(imageFile);

        // Insertar la imagen en la hoja de cálculo
        CreationHelper helper = workbook.getCreationHelper();
        @SuppressWarnings("unchecked")
		Drawing<Shape> drawing = (Drawing<Shape>) sheet.createDrawingPatriarch();
        ClientAnchor anchor = helper.createClientAnchor();
        anchor.setCol1(8); // Columna de inicio
	    anchor.setRow1(1); // Fila de inicio
	    Picture picture = drawing.createPicture(anchor, workbook.addPicture(imageData, Workbook.PICTURE_TYPE_JPEG));
	
	    // Cambiar el tamaño de la imagen
	    picture.resize(); // Cambiar el tamaño de la imagen para ajustarse a la celda
	     
	    //Declaracin de estilos de las tablas del documento
	    CellStyle tablaClienteStyle = workbook.createCellStyle();
	    tablaClienteStyle.setBorderBottom(BorderStyle.MEDIUM);
	    tablaClienteStyle.setBorderTop(BorderStyle.MEDIUM);
	    tablaClienteStyle.setBorderRight(BorderStyle.MEDIUM);
	    tablaClienteStyle.setBorderLeft(BorderStyle.MEDIUM);
	    tablaClienteStyle.setFillForegroundColor(IndexedColors.AQUA.index);
	    tablaClienteStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
	    
	    CellStyle tablaCliente2Style = workbook.createCellStyle();
	    tablaCliente2Style.setBorderBottom(BorderStyle.MEDIUM);
	    tablaCliente2Style.setBorderTop(BorderStyle.MEDIUM);

	    CellStyle tablaCliente3Style = workbook.createCellStyle();
	    tablaCliente3Style.setBorderBottom(BorderStyle.MEDIUM);
	    tablaCliente3Style.setBorderRight(BorderStyle.MEDIUM);
	    tablaCliente3Style.setBorderTop(BorderStyle.MEDIUM);
	    
	    CellStyle tablaFacturaStyle = workbook.createCellStyle();
	    tablaFacturaStyle.setBorderBottom(BorderStyle.MEDIUM);
	    tablaFacturaStyle.setBorderTop(BorderStyle.MEDIUM);
	    tablaFacturaStyle.setBorderRight(BorderStyle.MEDIUM);
	    tablaFacturaStyle.setBorderLeft(BorderStyle.MEDIUM);
	    tablaFacturaStyle.setFillForegroundColor(IndexedColors.SKY_BLUE.index);
	    tablaFacturaStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
	    
	    CellStyle tablaFactura2Style = workbook.createCellStyle();
	    tablaFactura2Style.setBorderBottom(BorderStyle.THIN);
	    tablaFactura2Style.setBorderTop(BorderStyle.THIN);

	    CellStyle tablaFactura3Style = workbook.createCellStyle();
	    tablaFactura3Style.setBorderBottom(BorderStyle.THIN);
	    tablaFactura3Style.setBorderRight(BorderStyle.THIN);
	    tablaFactura3Style.setBorderTop(BorderStyle.THIN);
	    
	    CellStyle tablaClienteBodyStyle = workbook.createCellStyle();
	    tablaClienteBodyStyle.setBorderBottom(BorderStyle.THIN);
	    tablaClienteBodyStyle.setBorderTop(BorderStyle.THIN);
	    tablaClienteBodyStyle.setBorderRight(BorderStyle.THIN);
	    tablaClienteBodyStyle.setBorderLeft(BorderStyle.THIN);
	    
	    CellStyle tablaFacturaBodyStyle = workbook.createCellStyle();
	    tablaFacturaBodyStyle.setBorderBottom(BorderStyle.THIN);
	    tablaFacturaBodyStyle.setBorderTop(BorderStyle.THIN);
	    tablaFacturaBodyStyle.setBorderRight(BorderStyle.THIN);
	    tablaFacturaBodyStyle.setBorderLeft(BorderStyle.THIN);
	    
		//primera fila
		Row row = sheet.createRow(4);
		
		//Columnas de la primera fila (cell2 y cell3 son para modificar los estilos de las celdas)
		Cell cell = row.createCell(6);
		Cell cell1 = row.createCell(8);
		Cell cell2 = row.createCell(9);
		Cell cell3 = row.createCell(10);
		
		cell.setCellStyle(tablaClienteStyle);
		cell1.setCellStyle(tablaFacturaStyle);
		cell2.setCellStyle(tablaCliente2Style);
		cell3.setCellStyle(tablaCliente3Style);
		cell.setCellValue(mensajes.getMessage("text.factura.ver.datos.cliente"));
		cell1.setCellValue(mensajes.getMessage("text.factura.ver.datos.factura"));
		//Segunada fila
		row = sheet.createRow(5);
		//Primera columna
		cell = row.createCell(6);
		cell.setCellStyle(tablaClienteBodyStyle);
		cell1 = row.createCell(8);
		cell1.setCellStyle(tablaFacturaBodyStyle);
		cell2 = row.createCell(9);
		cell2.setCellStyle(tablaFactura2Style);
		cell3 = row.createCell(10);
		cell3.setCellStyle(tablaFactura3Style);
		cell.setCellValue(factura.getCliente().getNombre() + " " + factura.getCliente().getApellido());		
		cell1.setCellValue(mensajes.getMessage("text.cliente.factura.descripcion") + ": " + factura.getDescripcion());
		//Tercera fila
		row = sheet.createRow(6);
		//Primera columna
		cell = row.createCell(6);
		cell.setCellStyle(tablaClienteBodyStyle);
		cell1 = row.createCell(8);
		cell1.setCellStyle(tablaFacturaBodyStyle);
		cell2 = row.createCell(9);
		cell2.setCellStyle(tablaFacturaBodyStyle);
		cell3 = row.createCell(10);
		cell3.setCellStyle(tablaFacturaBodyStyle);
		cell.setCellValue(factura.getCliente().getEmail());		
		cell1.setCellValue(mensajes.getMessage("text.cliente.factura.fecha") + ": " + factura.getCreateAt());
		
		//Declaracion de estilos sin aplicar para la tabla dinamica con los items de la factura
		CellStyle theaderStyle = workbook.createCellStyle();
		theaderStyle.setBorderBottom(BorderStyle.MEDIUM);
		theaderStyle.setBorderTop(BorderStyle.MEDIUM);
		theaderStyle.setBorderRight(BorderStyle.MEDIUM);
		theaderStyle.setBorderLeft(BorderStyle.MEDIUM);
		theaderStyle.setFillForegroundColor(IndexedColors.GOLD.index);
		theaderStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		CellStyle theader2Style = workbook.createCellStyle();
		theader2Style.setBorderBottom(BorderStyle.MEDIUM);
		theader2Style.setBorderTop(BorderStyle.MEDIUM);
		theader2Style.setBorderRight(BorderStyle.MEDIUM);
		
		CellStyle tbodyStyle = workbook.createCellStyle();
		tbodyStyle.setBorderBottom(BorderStyle.THIN);
		tbodyStyle.setBorderTop(BorderStyle.THIN);
		tbodyStyle.setBorderRight(BorderStyle.THIN);
		tbodyStyle.setBorderLeft(BorderStyle.THIN);
		
		CellStyle tbody2Style = workbook.createCellStyle();
		tbody2Style.setBorderBottom(BorderStyle.THIN);
		tbody2Style.setBorderTop(BorderStyle.THIN);
		tbody2Style.setBorderRight(BorderStyle.THIN);
		
		//Creacion del header de la tabla
		Row header = sheet.createRow(8);
		header.createCell(6).setCellValue(mensajes.getMessage("text.factura.form.item.nombre"));
		header.createCell(7).setCellValue(mensajes.getMessage("text.factura.form.item.nombre"));
		header.createCell(8).setCellValue(mensajes.getMessage("text.factura.form.item.cantidad"));
		header.createCell(9).setCellValue(mensajes.getMessage("text.factura.form.item.total"));
		header.createCell(10);
		
		//Aplicar estilos
		header.getCell(6).setCellStyle(theaderStyle);
		header.getCell(7).setCellStyle(theaderStyle);
		header.getCell(8).setCellStyle(theaderStyle);
		header.getCell(9).setCellStyle(theaderStyle);
		header.getCell(10).setCellStyle(theader2Style);
		
		//Se rellena la tabla
		//Es 10 porque es la fila que toca despues del header
		int rownum = 9;
		
		for(ItemFactura item: factura.getItems())
		{
			
			Row fila = sheet.createRow(rownum ++);
			
			cell = fila.createCell(6);
			cell.setCellValue(item.getProducto().getNombre());
			cell.setCellStyle(tbodyStyle);
			
			cell = fila.createCell(7);
			cell.setCellValue(item.getProducto().getPrecio());
			cell.setCellStyle(tbodyStyle);
			
			cell = fila.createCell(8);
			cell.setCellValue(item.getCantidad());
			cell.setCellStyle(tbodyStyle);
			
			cell = fila.createCell(9);
			cell3 = fila.createCell(10);
			cell.setCellValue(item.calcularImporte());
			cell.setCellStyle(tbodyStyle);
			cell3.setCellStyle(tbody2Style);
		}
		
		Row filatotal = sheet.createRow(rownum);
		//dos columnas
		cell = filatotal.createCell(8);
		cell3 = filatotal.createCell(10);
		cell.setCellValue(mensajes.getMessage("text.factura.form.total") + ": ");
		cell.setCellStyle(tbodyStyle);
		cell3.setCellStyle(tbody2Style);
		
		
		cell = filatotal.createCell(9);
		cell.setCellValue(factura.getTotal());
		cell.setCellStyle(tbodyStyle);
	}

}


package CRUD;


import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ExportarPDF {
     public void exportarPDF(JTable t) throws DocumentException, IOException {
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos PDF", "pdf");
        chooser.setFileFilter(filter);
        chooser.setDialogTitle("Guardar archivo");
        chooser.setAcceptAllFileFilterUsed(false);
        if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            String ruta = chooser.getSelectedFile().toString().concat(".pdf");
            try {
                File archivoPDF = new File(ruta);
                if (archivoPDF.exists()) {
                    archivoPDF.delete();
                }
                archivoPDF.createNewFile();
                Document document = new Document();
                PdfWriter.getInstance(document, new FileOutputStream(archivoPDF));
                document.open();
                PdfPTable table = new PdfPTable(t.getColumnCount());
                table.setWidthPercentage(100);
                // Add table headers
                for (int c = 0; c < t.getColumnCount(); c++) {
                    PdfPCell cell = new PdfPCell(new Phrase(t.getColumnName(c)));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);
                }
                // Add table data
                for (int f = 0; f < t.getRowCount(); f++) {
                    for (int c = 0; c < t.getColumnCount(); c++) {
                        table.addCell(String.valueOf(t.getValueAt(f, c)));
                    }
                }
                document.add(table);
                document.close();
                Desktop.getDesktop().open(archivoPDF);
            } catch (DocumentException | IOException e) {
                throw e;
            }
        }
    }
}

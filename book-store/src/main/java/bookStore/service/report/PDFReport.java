package bookStore.service.report;

import java.io.File;
import java.io.IOException;
import java.util.List;

import bookStore.entity.Book;
import bookStore.repository.BookRepository;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.beans.factory.annotation.Autowired;

public class PDFReport implements ReportGenerator{
    public void generateReport(List<Book> booksOutOfStock) {
        writePdfFile((new File("src/report.pdf")).getAbsolutePath(), booksOutOfStock);
    }


    private void writePdfFile(String filePath, List<Book> books) {
        try {
            PDDocument document = new PDDocument();
            PDPage blankPage = new PDPage();
            document.addPage(blankPage);

            //Retrieving the pages of the document
            PDPage page = document.getPage(0);
            PDPageContentStream contentStream = new PDPageContentStream(document, page);

            //Begin the Content stream
            contentStream.beginText();

            //Setting the font to the Content stream
            contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);

            contentStream.setLeading(14.5f);

            //Setting the position for the line
            contentStream.newLineAtOffset(5, 500);

            for (Book book : books) {
                contentStream.showText(book.toString());
                contentStream.newLine();
            }

            //Ending the content stream
            contentStream.endText();

            //Closing the content stream
            contentStream.close();

            //Saving the document
            document.save(new File(filePath));

            //Closing the document
            document.close();
        } catch (IOException exc) {
            exc.printStackTrace();
        }
    }

}

package bookStore.service.report;

import bookStore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ReportFactory {
    public static ReportGenerator getReportType(String reportType) {
        if (reportType.equals("\"CSV\"")) {
            return new CSVReport();
        }
        if (reportType.equals("\"PDF\"")){
            return new PDFReport();
        }
        return null;
    }

}

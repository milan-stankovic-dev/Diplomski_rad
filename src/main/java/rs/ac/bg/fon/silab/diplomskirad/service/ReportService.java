package rs.ac.bg.fon.silab.diplomskirad.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rs.ac.bg.fon.silab.diplomskirad.domain.Product;
import rs.ac.bg.fon.silab.diplomskirad.domain.Report;
import rs.ac.bg.fon.silab.diplomskirad.domain.ReportItem;
import rs.ac.bg.fon.silab.diplomskirad.dto.ReportDTO;
import rs.ac.bg.fon.silab.diplomskirad.exception.CollectionSizeMismatchException;
import rs.ac.bg.fon.silab.diplomskirad.mapper.ProductMapper;
import rs.ac.bg.fon.silab.diplomskirad.mapper.ReportMapper;
import rs.ac.bg.fon.silab.diplomskirad.repository.ReportItemRepository;
import rs.ac.bg.fon.silab.diplomskirad.repository.ReportRepository;

import java.math.BigDecimal;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ReportService {
    private final ReportRepository reportRepository;
    private final ReportMapper reportMapper;
    private final ReportItemRepository reportItemRepository;
    private final ProductService productService;

    public ReportDTO insertReport(ReportDTO reportDTO)  {
        var report = reportMapper.dTOtoEntity(reportDTO);
        reportItemValidator(report);
        reportUsagePercentageValidator(report);
        allProductsPresentInReportValidator(report);

        var savedReport = reportRepository.save(report);
        return reportMapper.entityToDTO(savedReport);
    }
    public List<ReportDTO> getAllReports() {
        return reportMapper.listOfEntitiesToListOfDTOs(
                reportRepository.findAll());
    }

    private void reportItemValidator(Report report) {
        if(report == null){
            throw new IllegalArgumentException("Report must not be empty.");
        }
        var items = report.getReportItems();
        if(items == null || items.size() == 0){
            throw new IllegalStateException("Your report has" +
                    " no items.");
        }
    }
    private void reportUsagePercentageValidator(Report report) {
        final double totalPercentageAvailable = report.getTotalCapacity();
        int totalWarehouseSpace = 0;
        int totalNumberOfWarehouseItems = 0;

        for(ReportItem reportItem : report.getReportItems()){
            validateSingleItem(reportItem);
            totalWarehouseSpace += reportItem.getTotalAvailableCapacity();
            totalNumberOfWarehouseItems += reportItem.getProduct().getCurrentStock();
        }

        validatePercentageUsedState(totalPercentageAvailable,
                totalWarehouseSpace, totalNumberOfWarehouseItems);
    }

    private void validateSingleItem(ReportItem reportItem) {
        if(reportItem == null || reportItem.getProduct() == null){
            throw new IllegalArgumentException("Your report item must exist and" +
                    "it must contain product data.");
        }
        final double itemPercentageAvailable = reportItem.getProductCapacity();
        final int totalPossibleStock = reportItem.getTotalAvailableCapacity();
        final int productCurrentStock = reportItem.getProduct().getCurrentStock();

        validatePercentageUsedState(itemPercentageAvailable, totalPossibleStock, productCurrentStock);
    }
    private void validatePercentageUsedState(double percentageAvailable,
                                             int totalPossibleStock,
                                             int productCurrentStock) {
//        double calculatedPercentage = 100- (Math.round((100.0 - ((double) totalPossibleStock / productCurrentStock)) * 100.0) / 100.0);
        double calculatedPercentage = 100 - ((double)productCurrentStock)/totalPossibleStock * 100;

        if (Math.abs(percentageAvailable - calculatedPercentage) > 0.01) {
            throw new IllegalStateException("Your item capacity was wrongly calculated.");
        }
    }


    private void allProductsPresentInReportValidator(Report report) {
        final List<Product> productsfromDB = new ProductMapper()
                .listOfDTOsToListOfEntities(productService.getAllProductDTOs());
        final List<Product> productsFromReport = report.getReportItems().stream()
                .map(ReportItem::getProduct).toList();
        if(productsfromDB.size() != productsFromReport.size()){
            throw new CollectionSizeMismatchException("Your collection of " +
                    "report items is not the same length as the one fetched from the " +
                    "database. Reports must pertain to all products in the database.");
        }
        for(Product p : productsFromReport){
            System.out.println(p + "PRODUCT FOR CHECKING");
            System.out.println(productsfromDB + " DB PRODUCTS");
            if(!String.valueOf(p.getPrice()).contains(".")){
                p.setPrice(p.getPrice().multiply(BigDecimal.valueOf(1.00)));
            }

            if(!productsfromDB.contains(p)){
                throw new IllegalArgumentException("You have inputted a non existing product. " +
                        "This could be caused by a database update or another person editing " +
                        "products at the same time as you inserting the report. Please try again." + p);
            }
        }
    }
}

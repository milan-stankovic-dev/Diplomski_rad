package rs.ac.bg.fon.silab.masterrad.domain.report.SOInsert;

import io.jkratz.mediator.core.Mediator;
import io.jkratz.mediator.core.RequestHandler;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import rs.ac.bg.fon.silab.masterrad.domain.product.Product;
import rs.ac.bg.fon.silab.masterrad.domain.report.Report;
import rs.ac.bg.fon.silab.masterrad.domain.report_item.ReportItem;
import rs.ac.bg.fon.silab.masterrad.dto.ReportDTO;
import rs.ac.bg.fon.silab.masterrad.exception.CollectionSizeMismatchException;
import rs.ac.bg.fon.silab.masterrad.mapper.ReportMapper;
import rs.ac.bg.fon.silab.masterrad.repository.ProductRepository;
import rs.ac.bg.fon.silab.masterrad.repository.ReportRepository;

import java.math.BigDecimal;
import java.util.List;

@Component
@RequiredArgsConstructor
public class InsertReportRequestHandler
        implements RequestHandler<ReportDTO, ReportDTO> {

    private final Mediator mediator;
    private final ReportRepository repository;
    private final ReportMapper reportMapper;
    private final ProductRepository productRepository;

    private ReportDTO insertReport(ReportDTO reportDTO)  {
        var report = reportMapper.dTOtoEntity(reportDTO);
        reportItemValidator(report);
        reportUsagePercentageValidator(report);
        allProductsPresentInReportValidator(report);

        val savedReport = repository.save(report);
        return reportMapper.entityToDTO(savedReport);
    }

    private void reportItemValidator(Report report) {
        if(report == null){
            throw new IllegalArgumentException("Report must not be empty.");
        }
        var items = report.getReportItems();
        if(items == null || items.isEmpty()){
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
        double calculatedPercentage = 100 - ((double)productCurrentStock)/totalPossibleStock * 100;

        if (Math.abs(percentageAvailable - calculatedPercentage) > 0.01) {
            throw new IllegalStateException("Your item capacity was wrongly calculated.\n" +
                    "Calculated: " + calculatedPercentage + "\n Actual: " + percentageAvailable + "\n" +
                    "Product current stock: " + productCurrentStock + "\n Total possible Stock: " + totalPossibleStock);
        }
    }


    private void allProductsPresentInReportValidator(Report report) {
        final List<Product> productsFromDB = productRepository.findAll();

        final List<Product> productsFromReport = report.getReportItems().stream()
                .map(ReportItem::getProduct).toList();
        if(productsFromDB.size() != productsFromReport.size()){
            throw new CollectionSizeMismatchException("Your collection of " +
                    "report items is not the same length as the one fetched from the " +
                    "database. Reports must pertain to all products in the database.");
        }
        for(Product p : productsFromReport){
            System.out.println(p + "PRODUCT FOR CHECKING");
            System.out.println(productsFromDB + " DB PRODUCTS");
            if(!String.valueOf(p.getPrice()).contains(".")){
                p.setPrice(p.getPrice().multiply(BigDecimal.valueOf(1.00)));
            }

            if(!productsFromDB.contains(p)){
                throw new IllegalArgumentException("You have inputted a non existing product. " +
                        " Please try again. Violating product:" + p + "\n" +
                        "ALL PRODUCTS in DB: " + productsFromDB);
            }
        }
    }
    @Override
    public ReportDTO handle(@NotNull ReportDTO reportDTO) {
        val savedReport = insertReport(reportDTO);
        this.mediator.emit(savedReport);

        return savedReport;
    }
}

package remcv.com.github.amsunified.service;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import remcv.com.github.amsunified.model.dto.PharmacyOrderDto;
import remcv.com.github.amsunified.model.entity.PharmacyOrder;
import remcv.com.github.amsunified.repository.CimRepository;
import remcv.com.github.amsunified.repository.DepartmentAliasRepository;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class PharmacyOrdersCsvImporter {

    // methods
    public List<PharmacyOrderDto> importOrders(String filePath, String delim, String dateFormat) {
        // setup
        char delimiter = delim.translateEscapes().charAt(0);
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(dateFormat);
        List<PharmacyOrderDto> orders = new ArrayList<>();

        CSVFormat csvFormat = CSVFormat.Builder
                .create()
                .setDelimiter(delimiter)
                .setHeader()
                .setSkipHeaderRecord(true)
                .build();

        try (CSVParser csvParser = CSVParser.parse(new File(filePath), StandardCharsets.UTF_16, csvFormat)) {
            for (CSVRecord record : csvParser) {
                orders.add(extractOrderFromCsvRecord(record, dateFormatter));
            }
        } catch (IOException e) {
            // log
            throw new RuntimeException("Error importing " + filePath, e);
        }

        return orders;
    }

    private PharmacyOrderDto extractOrderFromCsvRecord(CSVRecord csvRecord, DateTimeFormatter dateFormatter) {
        String hospitalizationType = csvRecord.get("hospitalization_type");
        String orderNumber = csvRecord.get("order_number");
        String fo = csvRecord.get("fo");
        String cnp = csvRecord.get("cnp");
        LocalDate orderDate = LocalDate.parse(csvRecord.get("order_date"), dateFormatter);
        String departmentAlias = csvRecord.get("department_alias");
        String cim = csvRecord.get("cim");
        String physicianId = csvRecord.get("physician_id");
        Double quantity = Double.valueOf(csvRecord.get("quantity"));

        // result
        PharmacyOrderDto order = new PharmacyOrderDto();
        order.setHospitalizationType(hospitalizationType);
        order.setOrderNumber(orderNumber);
        order.setFo(fo);
        order.setCnp(cnp);
        order.setOrderDate(orderDate);
        order.setDepartmentAlias(departmentAlias);
        order.setCim(cim);
        order.setPhysicianId(physicianId);
        order.setQuantity(quantity);

        return order;
    }

}

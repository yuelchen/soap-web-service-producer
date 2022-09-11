package com.yuelchen.soap.service;

import com.yuelchen.soap.dataset.DatasetRequest;
import com.yuelchen.soap.dataset.DatasetResponse;
import org.springframework.stereotype.Service;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

@Service
public class DatasetService {

    public DatasetResponse createDataset(DatasetRequest request) throws DatatypeConfigurationException {
        DatasetResponse response = new DatasetResponse();
        List<String> datasetIssue = response.getCreationIssue();

        // invoke validation functions
        this.validateDatasetRequestName(request.getDatasetName(), datasetIssue);
        this.validateDatasetRequestDescription(request.getDatasetDescription(), datasetIssue);
        this.validateDatasetRequestFieldCount(request.getDatasetFieldsCount(), datasetIssue);

        // check validation results to determine creation
        if(datasetIssue.size() > 0) {
            response.setIsCreated(false);
            response.setDatasetName(request.getDatasetName());
        } else {
            response.setIsCreated(true);
            response.setDatasetName(request.getDatasetName());
            response.setCreationDate(this.getCreationDate());
            datasetIssue.clear();
        }

        return response;
    }

    private void validateDatasetRequestName(String datasetName, List<String> datasetIssue) {
        if(datasetName.length() < 12) {
            datasetIssue.add("Dataset name must be at least 12 characters.");
        }
    }

    private void validateDatasetRequestDescription(String datasetDescription, List<String> datasetIssue) {
        if(datasetDescription.length() < 1) {
            datasetIssue.add("Dataset description cannot be empty.");
        }
    }

    public void validateDatasetRequestFieldCount(int fieldsCount, List<String> datasetIssue) {
        if(fieldsCount < 1) {
            datasetIssue.add("Dataset request must contain at least one field.");
        }
    }

    private XMLGregorianCalendar getCreationDate() throws DatatypeConfigurationException {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(new Date());
        return DatatypeFactory.newInstance().newXMLGregorianCalendar(gregorianCalendar);
    }
}

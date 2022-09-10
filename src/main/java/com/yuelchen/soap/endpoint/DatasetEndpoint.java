package com.yuelchen.soap.endpoint;

import com.yuelchen.soap.dataset.DatasetRequest;
import com.yuelchen.soap.dataset.DatasetResponse;
import com.yuelchen.soap.service.DatasetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import javax.xml.datatype.DatatypeConfigurationException;

@Endpoint
public class DatasetEndpoint {

    @Autowired
    public DatasetService datasetService;

    @PayloadRoot(namespace = "http://www.yuelchen.com/soap/dataset", localPart = "DatasetRequest")
    @ResponsePayload
    public DatasetResponse getDatasetCreation(@RequestPayload DatasetRequest request)
            throws DatatypeConfigurationException {

        return datasetService.createDataset(request);
    }
}

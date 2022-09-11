# soap-web-service-producer
A Spring Web Service application for producing SOAP API. 

## Overview
This is a SOAP Web Service producer which will generate a WSDL file for a consumer to use. As a consumer, you can access and download the WSDL file for generating java classes for making SOAP API calls. The below is the URL for the dataset.wsdl via SOAP Producer Springboot project. 
```:
http://localhost:8080/ws/dataset.wsdl
```

## Validation
You can also call Spring application server endpoing for servicing dataset requests. Under src/test/resources directory path, you will find two files which you can use to test the create dataset endpoint which we've exposed. You can either use a third party tool such as SOAPUI or simply open the terminal and run the below commands. Note that the below syntax is for Linux systems; sorry Windows user's. 
```:
cd src/test/resources/
curl --header "content-type: text/xml" -d @datasetSuccessPayload.xml http://localhost:8080/ws
curl --header "content-type: text/xml" -d @datasetFailurePayload.xml http://localhost:8080/ws
```

## Determine Payload Response
The logic to determine what to return is within our Dataset service. For this example, we've enforced three validation checks:
1. Name must be at least 12 characters in length. 
2. Description cannot be empty. 
3. There must at least be a single schema field count. 

Obviously there are quite a bit of edge cases that this does not account for, such as leading or trailing spaces to by pass character count, but you get the idea of this example. Feel free to make this robust as you need. 

### Sample Success Payload Response
```XML:
<SOAP-ENV:Envelope xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/">
  <SOAP-ENV:Header/>
  <SOAP-ENV:Body>
    <ns2:DatasetResponse xmlns:ns2="http://www.yuelchen.com/soap/dataset">
      <ns2:isCreated>true</ns2:isCreated>
      <ns2:datasetName>DSET90000001</ns2:datasetName>
      <ns2:creationDate>2022-09-10-04:00</ns2:creationDate>
    </ns2:DatasetResponse>
  </SOAP-ENV:Body>
</SOAP-ENV:Envelope>
```

### Sample Failure Payload Response
```XML:
<SOAP-ENV:Envelope xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/">
  <SOAP-ENV:Header/>
  <SOAP-ENV:Body>
    <ns2:DatasetResponse xmlns:ns2="http://www.yuelchen.com/soap/dataset">
      <ns2:isCreated>false</ns2:isCreated>
      <ns2:datasetName>DSET0001</ns2:datasetName>
      <ns2:creationIssue>Dataset name must be at least 12 characters.</ns2:creationIssue>
      <ns2:creationIssue>Dataset description cannot be empty.</ns2:creationIssue>
      <ns2:creationIssue>Dataset request must contain at least one field.</ns2:creationIssue>
    </ns2:DatasetResponse>
  </SOAP-ENV:Body>
</SOAP-ENV:Envelope>
```

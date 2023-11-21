package org.service.service;

import java.io.IOException;
import java.util.List;

import org.service.api.ApiUtil;
import org.service.api.ServiceApiDelegate;
import org.service.dao.ServiceDAO;
import org.service.dao.ServiceDefinitionDAO;
import org.service.model.RequestInfo;
import org.service.model.ResponseInfo;
import org.service.model.Service;
import org.service.model.ServiceCriteria;
import org.service.model.ServiceDefinition;
import org.service.model.ServiceDefinitionCriteria;
import org.service.model.ServiceDefinitionRequest;
import org.service.model.ServiceDefinitionResponse;
import org.service.model.ServiceDefinitionSearchRequest;
import org.service.model.ServiceRequest;
import org.service.model.ServiceResponse;
import org.service.model.ServiceSearchRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@org.springframework.stereotype.Service
public class ServiceApiDelegateImpl implements ServiceApiDelegate {
	@Autowired
	private ServiceDefinitionDAO serviceDefinitionDAO;

	@Autowired
	private ServiceDAO serviceDAO;

	@Override
	public ResponseEntity<ServiceDefinitionResponse> serviceDefinitionV1CreatePost(
			ServiceDefinitionRequest serviceDefinitionRequest) {
		RequestInfo requestInfo = serviceDefinitionRequest.getRequestInfo();
		ServiceDefinitionResponse response = new ServiceDefinitionResponse();
		response.setResponseInfo(requestInfo.getResponseInfo());
		ServiceDefinition sd = serviceDefinitionRequest.getServiceDefinition();
		try {
			String res = ApiUtil.fetchURL("https://random-data-api.com/api/v2/users?size=1");
			ObjectMapper mapper = new ObjectMapper();
			JsonNode node = mapper.readTree(res).get("address");
			JsonNode addressNode = mapper.createObjectNode().set("address", node);
			sd.setAdditionalDetails(addressNode);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		serviceDefinitionDAO.insert(sd);
		response.addServiceDefinitionItem(sd);
		return new ResponseEntity(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ServiceDefinitionResponse> serviceDefinitionV1SearchPost(
			ServiceDefinitionSearchRequest serviceDefinitionSearchRequest) {
		ServiceDefinitionResponse serviceDefinitionResponse = new ServiceDefinitionResponse();
		serviceDefinitionResponse.setPagination(serviceDefinitionSearchRequest.getPagination());
		serviceDefinitionResponse.setResponseInfo(serviceDefinitionSearchRequest.getRequestInfo().getResponseInfo());
		ServiceDefinitionCriteria serviceDefinitionCriteria = serviceDefinitionSearchRequest
				.getServiceDefinitionCriteria();
		List<ServiceDefinition> searchResult = serviceDefinitionDAO.findByCriteria(serviceDefinitionCriteria,
				serviceDefinitionSearchRequest.getPagination());
		if (searchResult != null)
			searchResult.forEach(
					serviceDefinition -> serviceDefinitionResponse.addServiceDefinitionItem(serviceDefinition));
		return new ResponseEntity<>(serviceDefinitionResponse, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ServiceResponse> serviceV1CreatePost(ServiceRequest serviceRequest) {
		RequestInfo requestInfo = serviceRequest.getRequestInfo();
		ResponseInfo responseInfo = requestInfo.getResponseInfo();
		ServiceResponse response = new ServiceResponse();
		response.setResponseInfo(responseInfo);
		Service service = serviceRequest.getService();
		serviceDAO.insert(service);
		response.addServiceItem(service);
		return new ResponseEntity(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ServiceResponse> serviceV1SearchPost(ServiceSearchRequest serviceSearchRequest) {
		ServiceResponse serviceResponse = new ServiceResponse();
		serviceResponse.setPagination(serviceSearchRequest.getPagination());
		serviceResponse.setResponseInfo(serviceSearchRequest.getRequestInfo().getResponseInfo());
		ServiceCriteria serviceCriteria = serviceSearchRequest.getServiceDefinition();
		List<Service> searchResult = serviceDAO.findByCriteria(serviceCriteria, serviceSearchRequest.getPagination());
		if (searchResult != null)
			searchResult.forEach(service -> serviceResponse.addServiceItem(service));
		return new ResponseEntity<>(serviceResponse, HttpStatus.OK);
	}
}

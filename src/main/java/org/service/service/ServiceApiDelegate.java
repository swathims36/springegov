package org.service.service;

import java.util.Optional;

import org.service.model.ServiceDefinitionRequest;
import org.service.model.ServiceDefinitionResponse;
import org.service.model.ServiceDefinitionSearchRequest;
import org.service.model.ServiceRequest;
import org.service.model.ServiceResponse;
import org.service.model.ServiceSearchRequest;
import org.service.util.ApiUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.NativeWebRequest;


@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-06-14T12:42:44.555+05:30[Asia/Calcutta]")
public interface ServiceApiDelegate {

	default Optional<NativeWebRequest> getRequest() {
		return Optional.empty();
	}

	default ResponseEntity<ServiceDefinitionResponse> serviceDefinitionV1CreatePost(
			ServiceDefinitionRequest serviceDefinitionRequest) {
		getRequest().ifPresent(request -> {
			for (MediaType mediaType : MediaType.parseMediaTypes(request.getHeader("Accept"))) {
				if (mediaType.isCompatibleWith(MediaType.valueOf("*/*"))) {
					String exampleString = "{ \"pagination\" : { \"offSet\" : 5.637376656633329, \"limit\" : 59.621339166831824, \"sortBy\" : \"sortBy\", \"totalCount\" : 2.3021358869347655, \"order\" : \"asc\" }, \"serviceDefinition\" : [ { \"code\" : \"code\", \"clientId\" : \"clientId\", \"auditDetails\" : { \"lastModifiedTime\" : 1, \"createdBy\" : \"createdBy\", \"lastModifiedBy\" : \"lastModifiedBy\", \"createdTime\" : 6 }, \"tenantId\" : \"pb.amritsar\", \"attributes\" : [ { \"regEx\" : \"regEx\", \"code\" : \"code\", \"dataType\" : \"String\", \"values\" : [ \"values\", \"values\" ], \"auditDetails\" : { \"lastModifiedTime\" : 1, \"createdBy\" : \"createdBy\", \"lastModifiedBy\" : \"lastModifiedBy\", \"createdTime\" : 6 }, \"tenantId\" : \"pb.amritsar\", \"id\" : \"id\", \"isActive\" : true, \"additionalDetails\" : \"{}\", \"required\" : true, \"order\" : \"order\" }, { \"regEx\" : \"regEx\", \"code\" : \"code\", \"dataType\" : \"String\", \"values\" : [ \"values\", \"values\" ], \"auditDetails\" : { \"lastModifiedTime\" : 1, \"createdBy\" : \"createdBy\", \"lastModifiedBy\" : \"lastModifiedBy\", \"createdTime\" : 6 }, \"tenantId\" : \"pb.amritsar\", \"id\" : \"id\", \"isActive\" : true, \"additionalDetails\" : \"{}\", \"required\" : true, \"order\" : \"order\" } ], \"id\" : \"id\", \"isActive\" : true, \"additionalDetails\" : \"{}\" }, { \"code\" : \"code\", \"clientId\" : \"clientId\", \"auditDetails\" : { \"lastModifiedTime\" : 1, \"createdBy\" : \"createdBy\", \"lastModifiedBy\" : \"lastModifiedBy\", \"createdTime\" : 6 }, \"tenantId\" : \"pb.amritsar\", \"attributes\" : [ { \"regEx\" : \"regEx\", \"code\" : \"code\", \"dataType\" : \"String\", \"values\" : [ \"values\", \"values\" ], \"auditDetails\" : { \"lastModifiedTime\" : 1, \"createdBy\" : \"createdBy\", \"lastModifiedBy\" : \"lastModifiedBy\", \"createdTime\" : 6 }, \"tenantId\" : \"pb.amritsar\", \"id\" : \"id\", \"isActive\" : true, \"additionalDetails\" : \"{}\", \"required\" : true, \"order\" : \"order\" }, { \"regEx\" : \"regEx\", \"code\" : \"code\", \"dataType\" : \"String\", \"values\" : [ \"values\", \"values\" ], \"auditDetails\" : { \"lastModifiedTime\" : 1, \"createdBy\" : \"createdBy\", \"lastModifiedBy\" : \"lastModifiedBy\", \"createdTime\" : 6 }, \"tenantId\" : \"pb.amritsar\", \"id\" : \"id\", \"isActive\" : true, \"additionalDetails\" : \"{}\", \"required\" : true, \"order\" : \"order\" } ], \"id\" : \"id\", \"isActive\" : true, \"additionalDetails\" : \"{}\" } ], \"responseInfo\" : { \"ver\" : \"ver\", \"resMsgId\" : \"resMsgId\", \"msgId\" : \"msgId\", \"apiId\" : \"apiId\", \"ts\" : 0, \"status\" : \"SUCCESSFUL\" } }";
					ApiUtil.setExampleResponse(request, "*/*", exampleString);
					break;
				}
			}
		});
		return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

	}

	default ResponseEntity<ServiceDefinitionResponse> serviceDefinitionV1SearchPost(
			ServiceDefinitionSearchRequest serviceDefinitionSearchRequest) {
		getRequest().ifPresent(request -> {
			for (MediaType mediaType : MediaType.parseMediaTypes(request.getHeader("Accept"))) {
				if (mediaType.isCompatibleWith(MediaType.valueOf("*/*"))) {
					String exampleString = "{ \"pagination\" : { \"offSet\" : 5.637376656633329, \"limit\" : 59.621339166831824, \"sortBy\" : \"sortBy\", \"totalCount\" : 2.3021358869347655, \"order\" : \"asc\" }, \"serviceDefinition\" : [ { \"code\" : \"code\", \"clientId\" : \"clientId\", \"auditDetails\" : { \"lastModifiedTime\" : 1, \"createdBy\" : \"createdBy\", \"lastModifiedBy\" : \"lastModifiedBy\", \"createdTime\" : 6 }, \"tenantId\" : \"pb.amritsar\", \"attributes\" : [ { \"regEx\" : \"regEx\", \"code\" : \"code\", \"dataType\" : \"String\", \"values\" : [ \"values\", \"values\" ], \"auditDetails\" : { \"lastModifiedTime\" : 1, \"createdBy\" : \"createdBy\", \"lastModifiedBy\" : \"lastModifiedBy\", \"createdTime\" : 6 }, \"tenantId\" : \"pb.amritsar\", \"id\" : \"id\", \"isActive\" : true, \"additionalDetails\" : \"{}\", \"required\" : true, \"order\" : \"order\" }, { \"regEx\" : \"regEx\", \"code\" : \"code\", \"dataType\" : \"String\", \"values\" : [ \"values\", \"values\" ], \"auditDetails\" : { \"lastModifiedTime\" : 1, \"createdBy\" : \"createdBy\", \"lastModifiedBy\" : \"lastModifiedBy\", \"createdTime\" : 6 }, \"tenantId\" : \"pb.amritsar\", \"id\" : \"id\", \"isActive\" : true, \"additionalDetails\" : \"{}\", \"required\" : true, \"order\" : \"order\" } ], \"id\" : \"id\", \"isActive\" : true, \"additionalDetails\" : \"{}\" }, { \"code\" : \"code\", \"clientId\" : \"clientId\", \"auditDetails\" : { \"lastModifiedTime\" : 1, \"createdBy\" : \"createdBy\", \"lastModifiedBy\" : \"lastModifiedBy\", \"createdTime\" : 6 }, \"tenantId\" : \"pb.amritsar\", \"attributes\" : [ { \"regEx\" : \"regEx\", \"code\" : \"code\", \"dataType\" : \"String\", \"values\" : [ \"values\", \"values\" ], \"auditDetails\" : { \"lastModifiedTime\" : 1, \"createdBy\" : \"createdBy\", \"lastModifiedBy\" : \"lastModifiedBy\", \"createdTime\" : 6 }, \"tenantId\" : \"pb.amritsar\", \"id\" : \"id\", \"isActive\" : true, \"additionalDetails\" : \"{}\", \"required\" : true, \"order\" : \"order\" }, { \"regEx\" : \"regEx\", \"code\" : \"code\", \"dataType\" : \"String\", \"values\" : [ \"values\", \"values\" ], \"auditDetails\" : { \"lastModifiedTime\" : 1, \"createdBy\" : \"createdBy\", \"lastModifiedBy\" : \"lastModifiedBy\", \"createdTime\" : 6 }, \"tenantId\" : \"pb.amritsar\", \"id\" : \"id\", \"isActive\" : true, \"additionalDetails\" : \"{}\", \"required\" : true, \"order\" : \"order\" } ], \"id\" : \"id\", \"isActive\" : true, \"additionalDetails\" : \"{}\" } ], \"responseInfo\" : { \"ver\" : \"ver\", \"resMsgId\" : \"resMsgId\", \"msgId\" : \"msgId\", \"apiId\" : \"apiId\", \"ts\" : 0, \"status\" : \"SUCCESSFUL\" } }";
					ApiUtil.setExampleResponse(request, "*/*", exampleString);
					break;
				}
			}
		});
		return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

	}


	default ResponseEntity<ServiceResponse> serviceV1CreatePost(ServiceRequest serviceRequest) {
		getRequest().ifPresent(request -> {
			for (MediaType mediaType : MediaType.parseMediaTypes(request.getHeader("Accept"))) {
				if (mediaType.isCompatibleWith(MediaType.valueOf("*/*"))) {
					String exampleString = "{ \"pagination\" : { \"offSet\" : 5.637376656633329, \"limit\" : 59.621339166831824, \"sortBy\" : \"sortBy\", \"totalCount\" : 2.3021358869347655, \"order\" : \"asc\" }, \"service\" : [ { \"accountId\" : \"accountId\", \"clientId\" : \"clientId\", \"auditDetails\" : { \"lastModifiedTime\" : 1, \"createdBy\" : \"createdBy\", \"lastModifiedBy\" : \"lastModifiedBy\", \"createdTime\" : 6 }, \"tenantId\" : \"pb.amritsar\", \"serviceDefId\" : \"serviceDefId\", \"attributes\" : [ { \"attributeCode\" : \"attributeCode\", \"auditDetails\" : { \"lastModifiedTime\" : 1, \"createdBy\" : \"createdBy\", \"lastModifiedBy\" : \"lastModifiedBy\", \"createdTime\" : 6 }, \"id\" : \"id\", \"additionalDetails\" : \"{}\", \"value\" : \"{}\" }, { \"attributeCode\" : \"attributeCode\", \"auditDetails\" : { \"lastModifiedTime\" : 1, \"createdBy\" : \"createdBy\", \"lastModifiedBy\" : \"lastModifiedBy\", \"createdTime\" : 6 }, \"id\" : \"id\", \"additionalDetails\" : \"{}\", \"value\" : \"{}\" } ], \"id\" : \"id\", \"additionalDetails\" : \"{}\", \"referenceId\" : \"referenceId\" }, { \"accountId\" : \"accountId\", \"clientId\" : \"clientId\", \"auditDetails\" : { \"lastModifiedTime\" : 1, \"createdBy\" : \"createdBy\", \"lastModifiedBy\" : \"lastModifiedBy\", \"createdTime\" : 6 }, \"tenantId\" : \"pb.amritsar\", \"serviceDefId\" : \"serviceDefId\", \"attributes\" : [ { \"attributeCode\" : \"attributeCode\", \"auditDetails\" : { \"lastModifiedTime\" : 1, \"createdBy\" : \"createdBy\", \"lastModifiedBy\" : \"lastModifiedBy\", \"createdTime\" : 6 }, \"id\" : \"id\", \"additionalDetails\" : \"{}\", \"value\" : \"{}\" }, { \"attributeCode\" : \"attributeCode\", \"auditDetails\" : { \"lastModifiedTime\" : 1, \"createdBy\" : \"createdBy\", \"lastModifiedBy\" : \"lastModifiedBy\", \"createdTime\" : 6 }, \"id\" : \"id\", \"additionalDetails\" : \"{}\", \"value\" : \"{}\" } ], \"id\" : \"id\", \"additionalDetails\" : \"{}\", \"referenceId\" : \"referenceId\" } ], \"responseInfo\" : { \"ver\" : \"ver\", \"resMsgId\" : \"resMsgId\", \"msgId\" : \"msgId\", \"apiId\" : \"apiId\", \"ts\" : 0, \"status\" : \"SUCCESSFUL\" } }";
					ApiUtil.setExampleResponse(request, "*/*", exampleString);
					break;
				}
			}
		});
		return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

	}


	default ResponseEntity<ServiceResponse> serviceV1SearchPost(ServiceSearchRequest serviceSearchRequest) {
		getRequest().ifPresent(request -> {
			for (MediaType mediaType : MediaType.parseMediaTypes(request.getHeader("Accept"))) {
				if (mediaType.isCompatibleWith(MediaType.valueOf("*/*"))) {
					String exampleString = "{ \"pagination\" : { \"offSet\" : 5.637376656633329, \"limit\" : 59.621339166831824, \"sortBy\" : \"sortBy\", \"totalCount\" : 2.3021358869347655, \"order\" : \"asc\" }, \"service\" : [ { \"accountId\" : \"accountId\", \"clientId\" : \"clientId\", \"auditDetails\" : { \"lastModifiedTime\" : 1, \"createdBy\" : \"createdBy\", \"lastModifiedBy\" : \"lastModifiedBy\", \"createdTime\" : 6 }, \"tenantId\" : \"pb.amritsar\", \"serviceDefId\" : \"serviceDefId\", \"attributes\" : [ { \"attributeCode\" : \"attributeCode\", \"auditDetails\" : { \"lastModifiedTime\" : 1, \"createdBy\" : \"createdBy\", \"lastModifiedBy\" : \"lastModifiedBy\", \"createdTime\" : 6 }, \"id\" : \"id\", \"additionalDetails\" : \"{}\", \"value\" : \"{}\" }, { \"attributeCode\" : \"attributeCode\", \"auditDetails\" : { \"lastModifiedTime\" : 1, \"createdBy\" : \"createdBy\", \"lastModifiedBy\" : \"lastModifiedBy\", \"createdTime\" : 6 }, \"id\" : \"id\", \"additionalDetails\" : \"{}\", \"value\" : \"{}\" } ], \"id\" : \"id\", \"additionalDetails\" : \"{}\", \"referenceId\" : \"referenceId\" }, { \"accountId\" : \"accountId\", \"clientId\" : \"clientId\", \"auditDetails\" : { \"lastModifiedTime\" : 1, \"createdBy\" : \"createdBy\", \"lastModifiedBy\" : \"lastModifiedBy\", \"createdTime\" : 6 }, \"tenantId\" : \"pb.amritsar\", \"serviceDefId\" : \"serviceDefId\", \"attributes\" : [ { \"attributeCode\" : \"attributeCode\", \"auditDetails\" : { \"lastModifiedTime\" : 1, \"createdBy\" : \"createdBy\", \"lastModifiedBy\" : \"lastModifiedBy\", \"createdTime\" : 6 }, \"id\" : \"id\", \"additionalDetails\" : \"{}\", \"value\" : \"{}\" }, { \"attributeCode\" : \"attributeCode\", \"auditDetails\" : { \"lastModifiedTime\" : 1, \"createdBy\" : \"createdBy\", \"lastModifiedBy\" : \"lastModifiedBy\", \"createdTime\" : 6 }, \"id\" : \"id\", \"additionalDetails\" : \"{}\", \"value\" : \"{}\" } ], \"id\" : \"id\", \"additionalDetails\" : \"{}\", \"referenceId\" : \"referenceId\" } ], \"responseInfo\" : { \"ver\" : \"ver\", \"resMsgId\" : \"resMsgId\", \"msgId\" : \"msgId\", \"apiId\" : \"apiId\", \"ts\" : 0, \"status\" : \"SUCCESSFUL\" } }";
					ApiUtil.setExampleResponse(request, "*/*", exampleString);
					break;
				}
			}
		});
		return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

	}

}

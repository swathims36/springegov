/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (6.6.0).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package org.service.api;

import java.util.Optional;

import javax.annotation.Generated;
import javax.validation.Valid;

import org.service.model.ErrorRes;
import org.service.model.ServiceDefinitionRequest;
import org.service.model.ServiceDefinitionResponse;
import org.service.model.ServiceDefinitionSearchRequest;
import org.service.model.ServiceRequest;
import org.service.model.ServiceResponse;
import org.service.model.ServiceSearchRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.NativeWebRequest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-07-23T22:54:35.857770+05:30[Asia/Kolkata]")
@Validated
@Tag(name = "Service Definition", description = "the Service Definition API")
public interface ServiceApi {

	default Optional<NativeWebRequest> getRequest() {
		return Optional.empty();
	}

	default ServiceApiDelegate getDelegate() {
		return new ServiceApiDelegate() {
		};
	}

	// POST /service/definition/v1/_create
	@Operation(operationId = "serviceDefinitionV1CreatePost", tags = { "Service Definition" }, responses = {
			@ApiResponse(responseCode = "202", description = "Request accepted.", content = {
					@Content(mediaType = "*/*", schema = @Schema(implementation = ServiceDefinitionResponse.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid input.", content = {
					@Content(mediaType = "*/*", schema = @Schema(implementation = ErrorRes.class)) }) })
	@RequestMapping(method = RequestMethod.POST, value = "/service/definition/v1/_create", produces = {
			"*/*" }, consumes = { "application/json" })
	default ResponseEntity<ServiceDefinitionResponse> serviceDefinitionV1CreatePost(
			@Parameter(name = "ServiceDefinitionRequest", description = "") @Valid @RequestBody(required = false) ServiceDefinitionRequest serviceDefinitionRequest) {
		return getDelegate().serviceDefinitionV1CreatePost(serviceDefinitionRequest);
	}

	// POST /service/definition/v1/_search
	@Operation(operationId = "serviceDefinitionV1SearchPost", tags = { "Service Definition" }, responses = {
			@ApiResponse(responseCode = "202", description = "Request accepted.", content = {
					@Content(mediaType = "*/*", schema = @Schema(implementation = ServiceDefinitionResponse.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid input.", content = {
					@Content(mediaType = "*/*", schema = @Schema(implementation = ErrorRes.class)) }) })
	@RequestMapping(method = RequestMethod.POST, value = "/service/definition/v1/_search", produces = {
			"*/*" }, consumes = { "application/json" })
	default ResponseEntity<ServiceDefinitionResponse> serviceDefinitionV1SearchPost(
			@Parameter(name = "ServiceDefinitionSearchRequest", description = "") @Valid @RequestBody(required = false) ServiceDefinitionSearchRequest serviceDefinitionSearchRequest) {
		return getDelegate().serviceDefinitionV1SearchPost(serviceDefinitionSearchRequest);

	}

	// POST /service/v1/_create
	@Operation(operationId = "serviceV1CreatePost", tags = { "Service" }, responses = {
			@ApiResponse(responseCode = "202", description = "Request accepted.", content = {
					@Content(mediaType = "*/*", schema = @Schema(implementation = ServiceResponse.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid input.", content = {
					@Content(mediaType = "*/*", schema = @Schema(implementation = ErrorRes.class)) }) })
	@RequestMapping(method = RequestMethod.POST, value = "/service/v1/_create", produces = { "*/*" }, consumes = {
			"application/json" })
	default ResponseEntity<ServiceResponse> serviceV1CreatePost(
			@Parameter(name = "ServiceRequest", description = "") @Valid @RequestBody(required = false) ServiceRequest serviceRequest) {
		return getDelegate().serviceV1CreatePost(serviceRequest);
	}

	// POST /service/v1/_search
	@Operation(operationId = "serviceV1SearchPost", tags = { "Service" }, responses = {
			@ApiResponse(responseCode = "202", description = "Request accepted.", content = {
					@Content(mediaType = "*/*", schema = @Schema(implementation = ServiceResponse.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid input.", content = {
					@Content(mediaType = "*/*", schema = @Schema(implementation = ErrorRes.class)) }) })
	@RequestMapping(method = RequestMethod.POST, value = "/service/v1/_search", produces = { "*/*" }, consumes = {
			"application/json" })
	default ResponseEntity<ServiceResponse> serviceV1SearchPost(
			@Parameter(name = "ServiceSearchRequest", description = "") @Valid @RequestBody(required = false) ServiceSearchRequest serviceSearchRequest) {
		return getDelegate().serviceV1SearchPost(serviceSearchRequest);

	}

}

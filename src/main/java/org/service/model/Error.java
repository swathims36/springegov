package org.service.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.ArrayList;
import java.util.List;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * Error object will be returned as a part of reponse body in conjunction with ResponseInfo as part of ErrorResponse whenever the request processing status in the ResponseInfo is FAILED. HTTP return in this scenario will usually be HTTP 400.
 */

@Schema(name = "Error", description = "Error object will be returned as a part of reponse body in conjunction with ResponseInfo as part of ErrorResponse whenever the request processing status in the ResponseInfo is FAILED. HTTP return in this scenario will usually be HTTP 400.")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-07-23T22:54:35.857770+05:30[Asia/Kolkata]")
public class Error {

  private String code;

  private String message;

  private String description;

  @Valid
  private List<String> params;

  /**
   * Default constructor
   * @deprecated Use {@link Error#Error(String, String)}
   */
  @Deprecated
  public Error() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public Error(String code, String message) {
    this.code = code;
    this.message = message;
  }

  public Error code(String code) {
    this.code = code;
    return this;
  }

  /**
   * Error Code will be module specific error label/code to identiffy the error. All modules should also publish the Error codes with their specific localized values in localization service to ensure clients can print locale specific error messages. Example for error code would be User.NotFound to indicate User Not Found by User/Authentication service. All services must declare their possible Error Codes with brief description in the error response section of their API path.
   * @return code
  */
  @NotNull 
  @Schema(name = "code", description = "Error Code will be module specific error label/code to identiffy the error. All modules should also publish the Error codes with their specific localized values in localization service to ensure clients can print locale specific error messages. Example for error code would be User.NotFound to indicate User Not Found by User/Authentication service. All services must declare their possible Error Codes with brief description in the error response section of their API path.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("code")
  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public Error message(String message) {
    this.message = message;
    return this;
  }

  /**
   * English locale message of the error code. Clients should make a separate call to get the other locale description if configured with the service. Clients may choose to cache these locale specific messages to enhance performance with a reasonable TTL (May be defined by the localization service based on tenant + module combination).
   * @return message
  */
  @NotNull 
  @Schema(name = "message", description = "English locale message of the error code. Clients should make a separate call to get the other locale description if configured with the service. Clients may choose to cache these locale specific messages to enhance performance with a reasonable TTL (May be defined by the localization service based on tenant + module combination).", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("message")
  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public Error description(String description) {
    this.description = description;
    return this;
  }

  /**
   * Optional long description of the error to help clients take remedial action. This will not be available as part of localization service.
   * @return description
  */
  
  @Schema(name = "description", description = "Optional long description of the error to help clients take remedial action. This will not be available as part of localization service.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("description")
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Error params(List<String> params) {
    this.params = params;
    return this;
  }

  public Error addParamsItem(String paramsItem) {
    if (this.params == null) {
      this.params = new ArrayList<>();
    }
    this.params.add(paramsItem);
    return this;
  }

  /**
   * Some error messages may carry replaceable fields (say $1, $2) to provide more context to the message. E.g. Format related errors may want to indicate the actual field for which the format is invalid. Client's should use the values in the param array to replace those fields.
   * @return params
  */
  
  @Schema(name = "params", description = "Some error messages may carry replaceable fields (say $1, $2) to provide more context to the message. E.g. Format related errors may want to indicate the actual field for which the format is invalid. Client's should use the values in the param array to replace those fields.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("params")
  public List<String> getParams() {
    return params;
  }

  public void setParams(List<String> params) {
    this.params = params;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Error error = (Error) o;
    return Objects.equals(this.code, error.code) &&
        Objects.equals(this.message, error.message) &&
        Objects.equals(this.description, error.description) &&
        Objects.equals(this.params, error.params);
  }

  @Override
  public int hashCode() {
    return Objects.hash(code, message, description, params);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Error {\n");
    sb.append("    code: ").append(toIndentedString(code)).append("\n");
    sb.append("    message: ").append(toIndentedString(message)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    params: ").append(toIndentedString(params)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}


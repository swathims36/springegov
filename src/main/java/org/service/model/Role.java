package org.service.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * minimal representation of the Roles in the system to be carried along in UserInfo with RequestInfo meta data. Actual authorization service to extend this to have more role related attributes 
 */

@Schema(name = "Role", description = "minimal representation of the Roles in the system to be carried along in UserInfo with RequestInfo meta data. Actual authorization service to extend this to have more role related attributes ")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-07-23T22:54:35.857770+05:30[Asia/Kolkata]")
public class Role {

  private String name;

  private String description;

  /**
   * Default constructor
   * @deprecated Use {@link Role#Role(String)}
   */
  @Deprecated
  public Role() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public Role(String name) {
    this.name = name;
  }

  public Role name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Unique name of the role
   * @return name
  */
  @NotNull @Size(max = 64) 
  @Schema(name = "name", description = "Unique name of the role", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("name")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Role description(String description) {
    this.description = description;
    return this;
  }

  /**
   * brief description of the role
   * @return description
  */
  
  @Schema(name = "description", description = "brief description of the role", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("description")
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Role role = (Role) o;
    return Objects.equals(this.name, role.name) &&
        Objects.equals(this.description, role.description);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, description);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Role {\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
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


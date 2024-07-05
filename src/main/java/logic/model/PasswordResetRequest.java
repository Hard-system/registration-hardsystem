package logic.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.Objects;

/**
 * PasswordResetRequest
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-07-02T14:20:55.625327600+02:00[Europe/Zurich]")
public class PasswordResetRequest implements Serializable {

  private static final long serialVersionUID = 1L;

  private String email;

  public PasswordResetRequest() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public PasswordResetRequest(String email) {
    this.email = email;
  }

  public PasswordResetRequest email(String email) {
    this.email = email;
    return this;
  }

  /**
   * Name of the field that failed the validation
   * @return email
  */
  @NotNull 
  @Schema(name = "email", description = "Name of the field that failed the validation", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("email")
  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PasswordResetRequest passwordResetRequest = (PasswordResetRequest) o;
    return Objects.equals(this.email, passwordResetRequest.email);
  }

  @Override
  public int hashCode() {
    return Objects.hash(email);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PasswordResetRequest {\n");
    sb.append("    email: ").append(toIndentedString(email)).append("\n");
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


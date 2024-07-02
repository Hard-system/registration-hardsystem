package business.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;

import java.io.Serializable;
import java.util.Objects;

/**
 * PasswordResetResponse
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-07-02T14:20:55.625327600+02:00[Europe/Zurich]")
public class PasswordResetResponse implements Serializable {

  private static final long serialVersionUID = 1L;

  private String message;

  public PasswordResetResponse message(String message) {
    this.message = message;
    return this;
  }

  /**
   * Name of the field that failed the validation
   * @return message
  */
  
  @Schema(name = "message", description = "Name of the field that failed the validation", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("message")
  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PasswordResetResponse passwordResetResponse = (PasswordResetResponse) o;
    return Objects.equals(this.message, passwordResetResponse.message);
  }

  @Override
  public int hashCode() {
    return Objects.hash(message);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PasswordResetResponse {\n");
    sb.append("    message: ").append(toIndentedString(message)).append("\n");
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


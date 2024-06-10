package pl.kurs.magdalena_pikulska_test_3r.commands;
import pl.kurs.magdalena_pikulska_test_3r.validators.ShapeType;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import pl.kurs.magdalena_pikulska_test_3r.validators.ShapeTypeToFind;


@ShapeType
@pl.kurs.magdalena_pikulska_test_3r.validators.Parameters
public class CreateShapeCommand {

    @JsonProperty("type")
    @ShapeTypeToFind
    @NotBlank
    @NotEmpty
    private String type;

    @JsonProperty("parameters")
    @Valid
    public Parameters parameters;

    public String getType() {
        return type;
    }

    public Parameters getParameters() {
        return parameters;
    }

}

package guru.springframework.msscbrewery.web.model;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;

@lombok.Data
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
@lombok.Builder
public class CustomerDto {
    private UUID id;

    @NotNull
    @Size(min = 3, max = 100)
    private String name;
}

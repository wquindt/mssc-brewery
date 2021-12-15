package guru.springframework.msscbrewery.web.model;


import java.util.UUID;

@lombok.Data
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
@lombok.Builder
public class CustomerDto  {
    private UUID id;
    private String name;
}

# AGENTS.md

Spring Boot 2.6.1 monolith (Java 11, Maven) from the "Spring Boot Microservices with Spring Cloud" Udemy course. Single module, no CI, no formatter config.

## Commands

Use the Maven Wrapper (`mvnw.cmd` on Windows, `./mvnw` on *nix). Do not assume a system Maven.

- Build + test: `./mvnw verify`
- Run app: `./mvnw spring-boot:run` (defaults to port 8080, no `application.properties` settings)
- Single test class: `./mvnw test -Dtest=BeerControllerTest`
- Single test method: `./mvnw test -Dtest=BeerControllerTest#methodName`

## Toolchain quirks

- **Lombok + MapStruct annotation processors** are wired in `pom.xml` under `maven-compiler-plugin`. MapStruct is configured with `-Amapstruct.defaultComponentModel=spring`, so generated mappers are Spring beans. After editing any `@Mapper` interface in `web/mappers/`, run `./mvnw compile` to regenerate; IDEs without annotation processing enabled will show false errors.
- `pom.xml` references `${lombok.version}` without defining it; it resolves via the Spring Boot parent BOM. Don't "fix" by hardcoding a version.
- JAXB deps are pinned to 2.3.0 explicitly (Java 11 removed JAXB from the JDK). Keep them.
- H2 is `runtime` scope; the app uses `spring-boot-starter-data-jpa` but no entities or repositories exist yet — JPA is wired but unused.

## Layout

```
guru.springframework.msscbrewery
├── domain/          POJOs (Beer, Customer) — not JPA entities
├── services/        Service interfaces + Impl (stub data, no persistence)
├── web/controller/  REST controllers + MvcExceptionHandler (@ControllerAdvice for validation)
├── web/mappers/     MapStruct mappers (DateMapper is a helper used by others)
└── web/model/       DTOs returned by controllers
```

Route constants live in `web/controller/RequestUrls.java` — `/api/v1/beer`, `/api/v2/beer`, `/api/v1/customer`. Use these constants rather than hardcoding paths.

`src/api/openapi.yaml` is generated/scratch output, not a source of truth. Trust the controllers.

## Conventions

- Controllers throw on bad input; `MvcExceptionHandler` translates `ConstraintViolationException` and `MethodArgumentNotValidException` to 400 responses. Mirror this pattern for new validation.
- Tests use JUnit 5 + Mockito + `MockMvc` (see `BeerControllerTest`). No Spring context is started for controller tests — use `@WebMvcTest` style or standalone setup as in existing tests.
- No `application.properties` content; if you add config, expect everything to currently rely on Spring Boot defaults.

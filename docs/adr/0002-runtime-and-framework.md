# ADR 0002: Java 21 and Spring Boot 3

## Status
Accepted

## Decision
Use Java 21 and Spring Boot 3.4.x as the baseline runtime and framework stack.

## Consequences
The platform can use records, modern switch expressions, virtual-thread-ready request handling, Jakarta APIs, Micrometer observation, Spring AI, and current container/native build tooling. Old Spring 3, Boot 1.x, JSP/XML MVC, Java 1.6/1.8, and Log4j 1.x are removed.

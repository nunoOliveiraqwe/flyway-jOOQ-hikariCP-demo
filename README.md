# Flyway-SQLite-Jooq Example

## Overview
This demo serves as a practical example of integrating Flyway with SQLite and JOOQ. The motivation behind this project stems from the challenge of finding comprehensive examples online that demonstrate the integration of these technologies, particularly in scenarios where JOOQ's generation of POJOs and DAOs is involved. The primary hurdle encountered is the "chicken and egg" problem, where JOOQ requires a pre-existing database schema to generate its artifacts.

To address this challenge, this project employs Gradle to create a temporary SQLite database and apply Flyway migrations to it. Subsequently, JOOQ leverages this migrated database schema to generate the necessary POJOs and DAOs. This approach offers a streamlined solution to navigate the dependencies and sequencing issues inherent in integrating Flyway, SQLite, and JOOQ.

## Features
- Integration of Flyway for database migrations.
- Usage of SQLite as the database backend.
- Generation of POJOs and DAOs using JOOQ.
- Gradle setup for orchestrating the migration process and JOOQ code generation.
- Simplified solution to the "chicken and egg" problem in JOOQ integration.

## Usage
1. Run the build task, the configuration in build.gradle ensures that the migration are ran and jooq is used to generate the pojos and daos 

```
./gradlew build
```

2. Alternativly it's possible to run the Gradle task to generate JOOQ POJOs and DAOs:

```
./gradlew generateJooq
```

3. Utilize the generated JOOQ artifacts in your application code to interact with the SQLite database seamlessly.

## License
This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

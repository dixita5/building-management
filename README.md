# building-management
Java-based HVAC control system for buildings with support for rooms, temperature monitoring, heating/cooling control, and scheduled updates.
=======
# Project (Maven)
This Java project simulates an apartment building HVAC control system.

## Features
- Rooms with unique IDs, temperature, heating, and cooling status.
- Apartments with owner name.
- Common rooms (Gym, Library, Laundry).
- Building requested temperature, adjustable by user.
- HVAC recalculation based on requested temperature.
- Temperature updates scheduled every 5 seconds, simulating gradual heating/cooling.
- Console menu to add rooms and set building temperature.
- Maven build tool configuration.
- Docker container support.
- Git versioning

## How to build and run

### Build with Maven
```bash
mvn clean package
```

### Run application
```bash
mvn exec:java
```

### Build Docker image
```bash
mvn clean package
docker build -t apartmentbuilding:1.0 .
```

### Run Docker container
```bash
docker run -it apartmentbuilding:1.0
```

## Assumptions
- Temperature "close enough" threshold is +/- 0.5 degrees.
- Initial temperature per room is random between 10 and 40 degrees.
- Building starts with 2 apartments (101 and 102) and 2 common rooms (Gym1, Lib1).
- User can add apartments and common rooms at runtime.
- Scheduling runs every 5 seconds to simulate gradual temperature changes.

## Git Branching / Commits
- Initial commit with feature branch : feature/building-management
- Implemented temperature scheduling.
- Added console interaction for dynamic room management.
- Committed code in feature branch with proper messages
- Merged the feature branch with main branch via pull request

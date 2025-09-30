# Movie Server JavaFX Project

## Project Overview

The Movie Server JavaFX Project is a comprehensive client-server application designed for managing movie databases across multiple production companies. The application features a JavaFX-based graphical user interface that allows production companies to authenticate, view their movie collections, add new movies, transfer movies between companies, and calculate profit statistics. The system operates on a multi-client server architecture, enabling simultaneous access by multiple production companies.

## Architecture

The application follows a client-server architecture pattern with the following key components:

- **Client Side**: JavaFX-based GUI application for production companies
- **Server Side**: Multi-threaded socket server handling concurrent client connections
- **Data Layer**: Movie database management with file-based persistence
- **Communication**: Object serialization over TCP sockets using custom wrapper classes

## Features

### Core Functionality

1. **Authentication System**: Production companies login with company-specific passwords
2. **Movie Management**: View, add, and transfer movies between production companies
3. **Real-time Updates**: Live notifications when movies are transferred between companies
4. **Profit Calculation**: Comprehensive financial analysis for production companies
5. **Multi-client Support**: Concurrent access for multiple production companies
6. **Multimedia Integration**: Movie trailers playback via embedded web browser
7. **Visual Interface**: Rich GUI with movie posters and detailed information displays

### User Interface Features

- Login screen with production company selection
- Main dashboard displaying company's movie collection
- Individual movie detail views with poster and trailer access
- Movie transfer interface for inter-company transactions
- Add new movie functionality with validation
- Total profit calculation and display

## File and Folder Structure

```
Movie_Server_JavaFX_Project_1-2/
├── build.gradle                          # Gradle build configuration
├── gradlew                               # Gradle wrapper script (Unix)
├── gradlew.bat                          # Gradle wrapper script (Windows)
├── settings.gradle                      # Gradle settings
├── movies.txt                           # Movie database file (CSV format)
├── movieResources.txt                   # Movie trailer URLs (CSV format)
├── gradle/wrapper/                      # Gradle wrapper files
│   ├── gradle-wrapper.jar
│   └── gradle-wrapper.properties
└── src/main/
    ├── java/
    │   ├── module-info.java             # Java module definition
    │   └── com/example/javatermproject/
    │       ├── Main.java                # Application entry point and client logic
    │       ├── LoginScene.java          # Login interface controller
    │       ├── ProductionCompanyController.java  # Main dashboard controller
    │       ├── moviePanesController.java         # Movie display panel controller
    │       ├── showMovieController.java          # Individual movie view controller
    │       ├── AddMoviesController.java          # Add movie interface controller
    │       ├── TransferMovieController.java      # Movie transfer interface controller
    │       ├── TotalProfitController.java        # Profit display controller
    │       ├── HelloController.java              # Initial welcome screen controller
    │       ├── SocketWrapper.java               # Client-side socket wrapper
    │       └── serverpackage/
    │           ├── server.java          # Main server implementation
    │           ├── SocketWrapper.java   # Server-side socket wrapper
    │           ├── movies.java          # Movie data model
    │           ├── productions.java     # Production company model
    │           ├── movieDatabase.java   # Database management class
    │           ├── searchfile.java      # Movie search functionality
    │           ├── DataWrapper.java     # Communication protocol wrapper
    │           ├── moviechange.java     # Movie transfer data structure
    │           ├── sort.java           # Movie sorting utilities
    │           └── passcheck.java      # Password validation utilities
    └── resources/
        ├── Assets/
        │   ├── image_55.jpg            # Application icon
        │   ├── projector.gif           # Animated graphics
        │   └── Thumbnails/             # Movie poster images
        │       ├── 12AngryMenposter.jpg
        │       ├── 2001ASpaceOdysseyposter.jpg
        │       └── [100+ movie posters]
        └── com/example/javatermproject/
            ├── login-scene.fxml        # Login interface layout
            ├── production-company.fxml # Main dashboard layout
            ├── moviepanes.fxml        # Movie panel layout
            ├── showMovie.fxml         # Movie detail view layout
            ├── addMovies.fxml         # Add movie interface layout
            ├── transferMovie.fxml     # Transfer movie interface layout
            ├── totalProfit.fxml       # Profit display layout
            └── hello-view.fxml        # Welcome screen layout
```

## Technical Implementation

### Core Classes

#### Client-Side Classes

**Main.java**
- Application entry point extending JavaFX Application
- Manages client-server communication via SocketWrapper
- Handles application state and data synchronization
- Provides utility methods for password checking, profit calculation, and data management

**LoginScene.java**
- Implements user authentication interface
- Manages production company selection from dropdown
- Validates passwords against server database
- Transitions to main application upon successful authentication

**ProductionCompanyController.java**
- Main application dashboard controller
- Displays movie collections in scrollable view
- Implements search functionality for movies
- Provides navigation to add movies, transfer movies, and view profits
- Handles real-time updates from server

**moviePanesController.java**
- Controls individual movie display panels within the main view
- Manages movie poster display and basic information
- Handles click events to show detailed movie information
- Provides access to movie transfer functionality

**showMovieController.java**
- Detailed movie information display controller
- Integrates WebView for trailer playback
- Shows comprehensive movie data including financial information
- Manages movie poster and backdrop image display

**AddMoviesController.java**
- New movie addition interface controller
- Validates movie data before submission
- Checks for duplicate movie titles
- Communicates with server to add movies to database

**TransferMovieController.java**
- Movie transfer between production companies interface
- Populates dropdown with available production companies
- Handles transfer confirmation and server communication
- Updates local data after successful transfer

**TotalProfitController.java**
- Displays total profit calculations for production companies
- Retrieves and formats financial data from server
- Shows company-specific profit information

#### Server-Side Classes

**server.java**
- Multi-threaded TCP server implementation
- Handles concurrent client connections
- Manages movie database operations
- Processes client requests: authentication, movie retrieval, additions, transfers
- Implements real-time notifications for movie transfers
- Provides graceful shutdown with data persistence

**movies.java**
- Core movie data model implementing Serializable
- Properties: title, year, genres, runtime, production company, budget, revenue, profit
- Supports creation from CSV data and individual parameters
- Automatically generates poster file paths and calculates profit
- Provides comprehensive getter methods and movie information display

**productions.java**
- Production company data model
- Stores company name and password information
- Implements Serializable for network communication

**movieDatabase.java**
- Database management class for movie collections
- Loads movie data from CSV files (movies.txt and movieResources.txt)
- Handles file I/O operations for data persistence
- Provides methods to add movies and write updated data back to files

**searchfile.java**
- Implements movie search functionality
- Supports searching by title, production company, genre, and year
- Returns filtered movie databases based on search criteria
- Provides sorting capabilities for search results

**SocketWrapper.java**
- Abstraction layer for socket communication
- Handles object serialization/deserialization
- Provides unified interface for client-server communication
- Manages connection lifecycle and error handling

**DataWrapper.java**
- Communication protocol wrapper class
- Encapsulates command and data for client-server messaging
- Supports various command types: authentication, movie operations, transfers
- Implements Serializable for network transmission

### Data Models

**Movie Data Structure**
```java
- Title: String
- Year of Release: int
- Genres: String (Genre1, Genre2, Genre3)
- Running Time: int (minutes)
- Production Company: String
- Budget: long (USD)
- Revenue: long (USD)
- Profit: long (calculated as Revenue - Budget)
- Poster: String (file path)
- Trailer: String (YouTube URL)
- Backdrop: String (file path)
```

**Production Company Structure**
```java
- Name: String
- Password: String
```

## Dependencies and Technologies

### Build System
- **Gradle 8.x**: Build automation and dependency management
- **Java 11**: Minimum required Java version
- **JavaFX 17.0.2**: UI framework for cross-platform desktop applications

### JavaFX Modules
- `javafx.controls`: UI controls and layouts
- `javafx.fxml`: FXML markup support for UI definition
- `javafx.web`: WebView component for trailer playback

### Third-Party Libraries
- **ControlsFX 11.1.1**: Extended JavaFX controls and utilities
- **FormsFX 11.5.0**: Form creation and validation framework
- **BootstrapFX 0.4.0**: Bootstrap-inspired styling for JavaFX

### Testing Framework
- **JUnit Jupiter 5.8.2**: Unit testing framework
- **JUnit Platform**: Test execution platform

## Installation and Setup

### Prerequisites

1. **Java Development Kit (JDK) 11 or higher**
   - Download from Oracle or OpenJDK
   - Ensure JAVA_HOME environment variable is set

2. **Gradle** (optional, wrapper included)
   - Gradle wrapper scripts are included in the project
   - No separate Gradle installation required

### Installation Steps

1. **Clone or Download the Repository**
   ```bash
   git clone <repository-url>
   cd Movie_Server_JavaFX_Project_1-2
   ```

2. **Verify Java Installation**
   ```bash
   java -version
   javac -version
   ```

3. **Build the Project**
   ```bash
   # On Windows
   gradlew.bat build
   
   # On Unix/Linux/macOS
   ./gradlew build
   ```

4. **Verify Build Success**
   - Check `build/libs/` directory for generated JAR file
   - Ensure no compilation errors in console output

## Usage Guidelines

### Starting the Server

1. **Navigate to Project Directory**
   ```bash
   cd Movie_Server_JavaFX_Project_1-2
   ```

2. **Run the Server Application**
   ```bash
   # On Windows
   gradlew.bat run --args="server"
   
   # Or compile and run directly
   java -cp build/classes/main com.example.javatermproject.serverpackage.server
   ```

3. **Server Initialization**
   - Server starts on port 3333
   - Loads movie database from `movies.txt` and `movieResources.txt`
   - Generates production company accounts with default password "123"
   - Ready to accept client connections

4. **Server Management**
   - Type "off" in server console to gracefully shutdown
   - Server automatically saves database changes before shutdown

### Running the Client Application

1. **Start the Client**
   ```bash
   # On Windows
   gradlew.bat run
   
   # On Unix/Linux/macOS
   ./gradlew run
   ```

2. **Application Workflow**
   - **Login**: Select production company from dropdown, enter password (default: "123")
   - **Dashboard**: View company's movie collection, search movies, access features
   - **Movie Details**: Click on movie to view detailed information and trailer
   - **Add Movies**: Use "Add Movie" button to add new movies to collection
   - **Transfer Movies**: Select movie and choose destination production company
   - **View Profits**: Access total profit calculations for the company

### Default Production Companies

The system automatically generates production companies based on movies in the database:
- Pixar Animation Studios
- TriStar Pictures
- Regency Enterprises
- New Line Cinema
- Blue Parrot Productions
- Miramax Films
- And many more...

All companies have default password: **123**

## Configuration Options

### Database Configuration

**movies.txt Format**
```csv
Title,Year,Genre1,Genre2,Genre3,Runtime,ProductionCompany,Budget,Revenue
```

**movieResources.txt Format**
```csv
Title,TrailerURL
```

### Server Configuration

**Port Configuration**
- Default server port: 3333
- Modify in `server.java` ServerSocket initialization

**Connection Settings**
- Client connection timeout: Not specified (uses Java defaults)
- Maximum concurrent clients: Limited by system resources

### Application Configuration

**Module Configuration (module-info.java)**
```java
module com.example.javatermproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    
    opens com.example.javatermproject to javafx.fxml;
    exports com.example.javatermproject;
}
```

## Development Guidelines

### Code Architecture Principles

1. **MVC Pattern**: Controllers handle UI logic, models manage data
2. **Separation of Concerns**: Client and server packages are clearly separated
3. **Object-Oriented Design**: Proper encapsulation and inheritance usage
4. **Network Communication**: Abstracted through wrapper classes
5. **Error Handling**: Try-catch blocks for I/O and network operations

### Adding New Features

1. **New UI Screens**: Create FXML file and corresponding controller class
2. **Server Commands**: Add new command types to DataWrapper protocol
3. **Database Operations**: Extend movieDatabase class with new functionality
4. **Search Features**: Enhance searchfile class with additional criteria

### Testing Considerations

- Unit tests should be added for core business logic
- Integration tests for client-server communication
- UI tests for JavaFX components
- Database tests for file I/O operations

## Troubleshooting

### Common Issues

**Connection Refused**
- Ensure server is running before starting clients
- Check firewall settings for port 3333
- Verify server IP address (currently hardcoded to 127.0.0.1)

**JavaFX Runtime Issues**
- Ensure JavaFX is properly installed and configured
- Check module path includes JavaFX modules
- Verify FXML files are in correct resource directories

**Database Loading Errors**
- Ensure `movies.txt` and `movieResources.txt` are in project root
- Check CSV format matches expected structure
- Verify file permissions for read/write access

**Movie Poster/Trailer Issues**
- Ensure poster images are in `src/main/resources/Assets/Thumbnails/`
- Check poster file naming convention: `MovieNameposter.jpg`
- Verify trailer URLs are accessible and properly formatted

### Debug Information

**Server Logging**
- Connection status messages
- Client authentication attempts
- Movie transfer notifications
- Database save operations

**Client Error Handling**
- Connection status monitoring
- Authentication failure messages
- Data validation errors
- UI navigation exceptions

## Performance Considerations

### Scalability

- **Concurrent Clients**: Server supports multiple simultaneous connections
- **Memory Usage**: Movie objects cached in memory for performance
- **Network Efficiency**: Object serialization minimizes bandwidth usage
- **Database Operations**: File-based storage suitable for moderate datasets

### Optimization Opportunities

1. **Database Migration**: Consider moving to SQL database for larger datasets
2. **Caching Strategy**: Implement intelligent movie poster caching
3. **Network Protocol**: Consider binary protocols for improved performance
4. **UI Responsiveness**: Implement background threading for server communication

## Security Considerations

### Current Security Model

- Basic password authentication for production companies
- Default passwords should be changed in production environment
- No encryption for network communication
- File-based storage without access controls

### Security Recommendations

1. **Encrypt Network Communication**: Implement SSL/TLS for client-server communication
2. **Strong Authentication**: Replace simple passwords with robust authentication system
3. **Data Encryption**: Encrypt sensitive data in storage files
4. **Access Control**: Implement role-based permissions for different operations
5. **Input Validation**: Enhance validation for all user inputs

## Future Enhancements

### Planned Features

1. **User Management**: Administrative interface for managing production companies
2. **Advanced Search**: Full-text search across all movie fields
3. **Reporting**: Comprehensive financial and operational reports
4. **Data Export**: Export movie data in various formats (JSON, XML, CSV)
5. **Backup System**: Automated database backup and restore functionality

### Technical Improvements

1. **Database Migration**: SQLite or PostgreSQL integration
2. **REST API**: HTTP-based API for web client development
3. **Mobile Support**: JavaFX mobile deployment options
4. **Cloud Integration**: Cloud storage and deployment capabilities
5. **Real-time Synchronization**: WebSocket-based real-time updates

## Contributing

### Development Environment Setup

1. **IDE Configuration**: IntelliJ IDEA or Eclipse with JavaFX support
2. **Code Style**: Follow Oracle Java coding conventions
3. **Version Control**: Git with feature branch workflow
4. **Testing**: Write tests for new functionality
5. **Documentation**: Update README for significant changes

### Code Review Process

1. **Feature Branches**: Create separate branches for new features
2. **Pull Requests**: Submit changes through pull request process
3. **Code Quality**: Ensure code follows project standards
4. **Testing**: All tests must pass before merging
5. **Documentation**: Update relevant documentation

## License

This project does not currently specify a license. Please consult with the project maintainers regarding licensing terms and usage restrictions.

---

**Note**: This documentation provides a comprehensive overview of the Movie Server JavaFX Project. For specific implementation details, refer to the source code and inline comments within individual classes.
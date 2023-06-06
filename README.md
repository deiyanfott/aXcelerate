# Getting Started

### Design Diagram
Please find the design diagram in src/main/resources/static

### Design Patterns
The following design patterns were used.
* Command - For turning on/off products and the undo function
* Builder - Constructor for the receiver class
* Singleton - Configuration for HomeHubBean

### Data Structures
* Hashmaps - Used to persist records like products and the state of the product, where they are on or off.
* Deque - I think this is the best data structure for the undo requirement as we are only adding or removing items.

### Product Mapping
* 1 - Garage Door
* 2 - Dishwasher
* 3 - Living Room Lights

### Decisions
The items below were not done as it might come to a point where the requirements are over-engineered.
* Exception Handlers - No exception handling was introduced along with API Errors and ResponseEntities. I based it on the premise that no UI and/or client is needed to the proof of concept.
* Decorator Pattern - Opted not to introduce decorators for the remote control and leaving such requirements for future enhancements, if any.
* Errors - Only one error is handled currently, which is if a product is not yet supported.
* Unchanging states and nothing to undo - I am only logging such events to the console.
* Logging - Logging only via console and no log file creation.
* Swagger - Tempted to implement but decided not to.
* Endpoints - Decided to create separate endpoints for turning on and off instead of one endpoint with an if else logic.
* Constants - Decided not create a constants class for now.
* Adding supported devices - Code change should only be in the constructor class of the HomeHubBean file. Need to update Product Mapping section in README.md or create a separate document as end user guide.
* Thread safety - Assumption is that this is a single user system, so it was not taken into consideration.

### Starting the application
This is a spring boot application with default port 8080.

### Endpoints
Change {productId} to the appropriate values. See product id mapping above.
* [Turning on product](http://localhost:8080/api/v1/remote-control/turn-on/{productId})
* [Turning off product](http://localhost:8080/api/v1/remote-control/turn-off/{productId})
* [Undo last action](http://localhost:8080/api/v1/remote-control/undo)

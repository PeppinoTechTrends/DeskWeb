# DeskWeb

DeskWeb is a dynamic project for streaming a computer's desktop to a web interface. 

## Key Features

- **Java Client**: Captures the desktop screen.
- **Python Webserver (Flask)**: Receives the screen data from the Java client.
- **Apache Kafka**: Facilitates communication between the client and server.

## How It Works

1. **Screen Capture**: The Java client captures the desktop screen.
2. **Data Transmission**: This data is sent to the Python webserver using Kafka.
3. **Web Streaming**: The webserver, built with Flask, streams this data onto a web interface.
# Installation Guide for DeskWeb

## Prerequisites

1. **Apache Kafka**: DeskWeb requires Kafka for messaging between the client and server. Follow the instructions at [Kafka Quickstart](https://kafka.apache.org/quickstart) to install and set it up.

## Cloning the Repository

```bash
git clone https://github.com/PeppinoTechTrends/DeskWeb.git
cd DeskWeb
```
Installing Dependencies
After cloning the repository, you need to install necessary Python packages:

```
pip install flask 
pip install confluent_kafka
```
This will install Flask for the webserver and confluent_kafka for Kafka integration.
## Running the Application
After created the topic in kafka with the name "streamingTopic"

### Start the Web Server
Launch the Flask web server:

```bash
flask run --app DeskWebFlask.py
```
### Start the Client
After the web server is running, you can start the client:

```bash
java -jar [path_to_jar]/DeskWebClient.jar
Make sure to replace [path_to_jar] with the actual path to the DeskWeb client JAR file.
```


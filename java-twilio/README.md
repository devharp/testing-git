# Twilio Messaging Application for OpticalArc

This application demonstrates sending a WhatsApp message using the Twilio API.

## Prerequisites

To run this application, you need to have the following:

- Java Development Kit (JDK) installed
- Apache Maven installed
- Twilio Account SID and Auth Token

## Setup

#### 1. Clone the repository:

   ```shell
   git clone https://github.com/devharp/testing-git.git
   ```
#### 2. Navigate to the project directory:

   ```shell
   cd testing-git\java-twilio
   ```

#### 3. Update AUTH_TOKEN
open the `App.java` file in the `src/main/java/com/mycompany/app` directory.

#### 4. Configure the Receiver's Phone Number:

In the same App.java file, replace the value of new com.twilio.type.PhoneNumber("whatsapp:+917020876827") with the phone number you want to receive the WhatsApp message.

Note: Make sure the phone number is configured in the Twilio sandbox for WhatsApp

## Usage

To run the application, follow these steps:

#### 1. Clean and build the project using Maven:
```shell
mvn clean install
```

#### 2. Execute the application:
```shell
mvn exec:java -Dexec.mainClass=com.mycompany.app.App
```

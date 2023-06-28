package com.mycompany.app;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;

 /**
  * The App class is the entry point for the Twilio messaging application.
  * It sends a WhatsApp message using the Twilio API.
  */
public class App {
    public static final String ACCOUNT_SID = "AC87d268fc8702274f083f6c42c58769ce";          // Twilio Account SID used for authentication
    public static final String AUTH_TOKEN = "8dbce5a2c5e236208265935f526e3d37";             // Twilio Auth Token used for authentication
    public static void main( String[] args ) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                new com.twilio.type.PhoneNumber("whatsapp:+917020876827"),                  // Receivers phone number
                new com.twilio.type.PhoneNumber("whatsapp:+14155238886"),                   // Senders phone number ie Twilio's number
                "Hello there harp!, msg from TWILIO")
            .create();

        System.out.println(message.getSid());
    }
}

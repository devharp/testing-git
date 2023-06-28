package com.mycompany.app;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;

/**
 * Hello world!
 *
 */
public class App {
    public static final String ACCOUNT_SID = "AC87d268fc8702274f083f6c42c58769ce";
    public static final String AUTH_TOKEN = "3850bb7071a3170de90465fceeb137e6";
    public static void main( String[] args ) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                new com.twilio.type.PhoneNumber("whatsapp:+917796591414"),
                new com.twilio.type.PhoneNumber("whatsapp:+14155238886"),
                "Hello there!, msg from TWILIO")
            .create();

        System.out.println(message.getSid());
    }
}

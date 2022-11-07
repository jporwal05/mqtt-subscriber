package com.example.mqttsubscriber.controller;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@Slf4j
public class MessageController {
    private static final String TOPIC = "/jp05/test";

    @Autowired
    private final IMqttClient mqttClient;

    public MessageController(IMqttClient mqttClient) {
        this.mqttClient = mqttClient;
    }

    @GetMapping
    public ResponseEntity<String> subscribe() throws MqttException {
        mqttClient.subscribe(TOPIC, (TOPIC, mqttMessage) -> {
            log.info(new String(mqttMessage.getPayload()));
        });
        return ResponseEntity.ok("Subscribed");
    }
}

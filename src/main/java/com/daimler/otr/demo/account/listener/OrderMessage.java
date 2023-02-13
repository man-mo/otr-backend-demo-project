package com.daimler.otr.demo.account.listener;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@ToString
@Getter
public class OrderMessage {
    private String messageId;

    private String messageContent;
}

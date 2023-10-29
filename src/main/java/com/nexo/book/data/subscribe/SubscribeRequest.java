package com.nexo.book.data.subscribe;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SubscribeRequest {
    private String event;
    private List<String> pair;
    private Subscription subscription;
}

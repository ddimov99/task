package com.nexo.book.service.ws.message;

import com.nexo.book.data.enums.MessageType;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ProcessMessageFactory {

    private final List<ProcessMessageService> processMessageServices;
    private final Map<MessageType, ProcessMessageService> manageCategoryServiceMap = new HashMap<>();

    public ProcessMessageFactory(List<ProcessMessageService> processMessageServices){
        this.processMessageServices = processMessageServices;
        initCategoryServices();
    }

    public void initCategoryServices() {
        processMessageServices.forEach(service -> manageCategoryServiceMap.put(service.getMessageType(), service));
    }

    public ProcessMessageService getService(MessageType messageType) {
        return manageCategoryServiceMap.get(messageType);
    }
}

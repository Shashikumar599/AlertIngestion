package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.concurrent.atomic.AtomicInteger;

@Data
@AllArgsConstructor
@Builder
public class Incidence {
//    {
//        "incidentId":     	"inc-1029",
//            "serviceId":      	"svc-payments",
//            "severity":      	"CRITICAL",
//            "status":         	"NOTIFIED", 	// TRIGGERED | NOTIFIED
//            "notificationStatus": "SENT"      	// SENT | FAILED | SKIPPED
//    }

    private final String id;
    private final Severity severity;
    private final ServiceCode serviceCode;
    private NotificationStatus notificationStatus;
    private int FailedCount;

}

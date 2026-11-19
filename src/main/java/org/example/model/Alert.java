package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Alert {
//    {
//        "serviceId":  "svc-payments",
//            "severity":   "CRITICAL",          	// INFO | WARNING | CRITICAL
//            "title":  	"Payment gateway timeout",
//            "dedupKey":   "payments-latency-001"
//    }

    String dedupKey;
    String title;
    ServiceCode serviceCode;
    Severity severity;

}

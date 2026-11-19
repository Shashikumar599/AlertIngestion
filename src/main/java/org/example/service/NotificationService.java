package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.model.Incidence;
import org.example.repository.IncidenceRepositary;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
@RequiredArgsConstructor
public class NotificationService {

    // Queue can be used here
    List<String> pendingNotification = new CopyOnWriteArrayList<>();
    List<String> failedNotification = new CopyOnWriteArrayList<>();

    private final IncidenceRepositary incidenceRepositary;

//    ExecutorService executorService = Executors.newFixedThreadPool(Integer.parseInt("${Threads.count}"));
   ExecutorService executorService = Executors.newFixedThreadPool(5);

    public void Process(String id){
        // save to Queue
        pendingNotification.add(id);
    }

    private void notif(){
        for(String id : pendingNotification){
            //Get Incidence
            Optional<Incidence> incidence = incidenceRepositary.findById(id);

            //Call External Client
            //success remove from queue
            // Failure add to failedNotification
        }
    }
    private void failedNotif(){
        for(String id : failedNotification){
            //Get Incidence
            Optional<Incidence> incidence = incidenceRepositary.findById(id);

            //Call External Client
            //success remove from queue
            // Failure add to failedNotification
        }
    }


}

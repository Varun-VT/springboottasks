package com.stackroute.controller;

import com.stackroute.domain.Track;
import com.stackroute.repository.TrackRepository;
import com.stackroute.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1")
public class TrackController {

//RestController is responsible for returning data by directly writing into http response as json

//At class level we provide RequestMapping to provide a std path "api/v1"
    TrackService trackService;

    @Autowired
    public TrackController(TrackService trackService) {
        this.trackService= trackService;
    }

    @PostMapping("/track")   /*user is resource name. This is feature of http REST level 1 where we
       perform operations like GET, POST, DELETE on resources. In our case the resource is a user*/

    public ResponseEntity<?> saveTrack(@RequestBody Track track){

//We've used RequestBody annotation for user parameter. Internally spring will use Jackson library,
// to map the json values of incoming request to a user domain entity.

        ResponseEntity responseEntity;
        try{
            trackService.saveTrack(track);
// Calling saveUser service method, passing user object we received from method.
            responseEntity= new ResponseEntity<String>("Successfully created", HttpStatus.CREATED);
// 1st para is string message to be sent back as response, 2nd para is http status code
        }
        catch (Exception e) {
//  If the saveUser fails, we create a responseEntity representing the exception message and a corresponding
//     status code.
            responseEntity= new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
        }
        return responseEntity;
// In spring REST, a responseEntity represents a complete http response including response body,
//  , status code, and headers.
    }

    @GetMapping("/track")
    public ResponseEntity<?> getAllTracks(){
//  This handler method is to retrieve all users.
    ResponseEntity responseEntity;
    try {
        responseEntity= new ResponseEntity<List<Track>>(trackService.getAllTracks(), HttpStatus.OK);
//   Here we've intensionally skipped exception handling part.
    }
    catch (Exception e){
        responseEntity= new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
    }
    return responseEntity;
    }

    @DeleteMapping("/track/{trackId}")
    public ResponseEntity<?> deleteTrack(@PathVariable("trackId") int trackId) {
        ResponseEntity responseEntity;
        try {
            trackService.deleteTrack(trackId);
            responseEntity= new ResponseEntity<List<Track>>(trackService.getAllTracks(), HttpStatus.OK);
//   Here we've intensionally skipped exception handling part.
        }
        catch (Exception e){
            responseEntity= new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
        }
        return responseEntity;
    }
    @DeleteMapping("/track/deletealltracks")
    public ResponseEntity<?> deleteAllTracks() {
        ResponseEntity responseEntity;
        try {
            trackService.deleteAllTrack();
            responseEntity= new ResponseEntity<List<Track>>(trackService.getAllTracks(), HttpStatus.OK);
//   Here we've intensionally skipped exception handling part.
        }
        catch (Exception e){
            responseEntity= new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

    @PutMapping("/track/{trackId}")
    public ResponseEntity<Object> updateTrack(@RequestBody Track track, @PathVariable int trackId) {
        ResponseEntity responseEntity;
        try {
        trackService.updateTrack(track, trackId);
        responseEntity= new ResponseEntity<List<Track>>(trackService.getAllTracks(), HttpStatus.OK);

        }
    catch (Exception e){
        responseEntity= new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);

    }
        return responseEntity;
    }

}

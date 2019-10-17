package com.stackroute.service;

import com.stackroute.domain.Track;
import com.stackroute.exceptions.TrackAlreadyExistsException;
import com.stackroute.exceptions.TrackNotFoundException;
import com.stackroute.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service("dummy")
@Profile("dev")
public class TrackDummyServiceImpl implements TrackService {

    @Autowired
    private Environment env;

    //    @Value("${trackRepository}")
    TrackRepository trackRepository;

    public void readProperty() {
        env.getProperty("trackRepository");
    }

    @PostConstruct
    private void init(){
        Track newtrack= new Track(1,"hello","Yppy");
        trackRepository.save(newtrack);
    }


    //    At runtime spring'll provide this service a UserRepository object via constructor dependency injection
    @Autowired
    public TrackDummyServiceImpl(TrackRepository trackRepository) {
        System.out.println("Inside Dummy");
        this.trackRepository = trackRepository;
    }

    @Override
    public Track saveTrack(Track track) throws TrackAlreadyExistsException {
// The save method comes from jpa repository inteface that our userRepository interface extends.
// Also, the actual implementation of save method'll be provided at runtime
        if (trackRepository.existsById(track.getTrackId())){
            throw new TrackAlreadyExistsException("Track Already exists");
        }
        Track savedTrack = trackRepository.save(track);
        if (savedTrack == null){
            throw new TrackAlreadyExistsException("Track Already exists");
        }
        return savedTrack;
    }

    @Override
    public List<Track> getAllTracks() {
        return trackRepository.findAll();
    }

    @Override
    public List<Track> getTrackByName(String trackName) throws TrackNotFoundException {
        if (trackRepository.gettrackByName(trackName) != null){
            return (List<Track>)trackRepository.gettrackByName(trackName);
        }
        else {
            throw new TrackNotFoundException("Track not found Exception");
        }
    }

    @Override
    public List<Track> deleteTrack(int trackId) throws TrackNotFoundException {
        if (trackRepository.existsById(trackId)) {
            trackRepository.deleteById(trackId);
            return trackRepository.findAll();
        }
        else {
            throw new TrackNotFoundException("Track not found Exception");
        }
    }

    @Override
    public List<Track> deleteAllTrack() {
        trackRepository.deleteAll();
        return trackRepository.findAll();
    }

    @Override
    public List<Track> updateTrack(Track track, int trackId) throws TrackNotFoundException {
        if (trackRepository.existsById(trackId)) {
            for (Track trck : trackRepository.findAll()) {
                if (trck.getTrackId() == trackId) {
                    trck.setComments(track.getComments());
                }
            }
        }
        else {
            throw new TrackNotFoundException("Track not found Exception");
        }
        return trackRepository.findAll();
    }

}

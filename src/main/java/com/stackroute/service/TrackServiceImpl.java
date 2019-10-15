package com.stackroute.service;

import com.stackroute.domain.Track;
import com.stackroute.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrackServiceImpl implements TrackService {

    TrackRepository trackRepository;

    //    At runtime spring'll provide this service a UserRepository object via constructor dependency injection
    @Autowired
    public TrackServiceImpl(TrackRepository trackRepository) {
        this.trackRepository = trackRepository;
    }

    @Override
    public Track saveTrack(Track track) {
// The save method comes from jpa repository inteface that our userRepository interface extends.
// Also, the actual implementation of save method'll be provided at runtime
        Track savedTrack = trackRepository.save(track);
        return savedTrack;
    }

    @Override
    public List<Track> getAllTracks() {
        return trackRepository.findAll();
    }

    @Override
    public List<Track> deleteTrack(int trackId) {
        trackRepository.deleteById(trackId);
        return trackRepository.findAll();
       /* for (Track track: trackRepository.findAll()) {
            if (track.getTrackId()== trackId){
                trackRepository.findAll().remove(track);
                System.out.println("You've deleted something");
            }*/

    }

    @Override
    public List<Track> deleteAllTrack() {
        trackRepository.deleteAll();
        return trackRepository.findAll();
    }

    @Override
    public List<Track> updateTrack(Track track, int trackId) {
        if (trackRepository.existsById(trackId)) {
            for (Track trck : trackRepository.findAll()) {
                if (trck.getTrackId() == trackId) {
                    trck.setComments(track.getComments());
                }
            }
        }
        return trackRepository.findAll();
    }
}

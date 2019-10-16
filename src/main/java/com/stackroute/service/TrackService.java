package com.stackroute.service;

import com.stackroute.domain.Track;
import com.stackroute.exceptions.TrackAlreadyExistsException;
import com.stackroute.exceptions.TrackNotFoundException;

import java.util.List;

public interface TrackService {

    public Track saveTrack(Track track) throws TrackAlreadyExistsException;

    public List<Track> getTrackByName(String trackName) throws TrackNotFoundException;

    public List<Track> getAllTracks() throws TrackNotFoundException;

    public List<Track> deleteTrack(int trackID) throws TrackNotFoundException;

    public List<Track> deleteAllTrack();

    public List<Track> updateTrack(Track track, int trackId) throws TrackNotFoundException;

}

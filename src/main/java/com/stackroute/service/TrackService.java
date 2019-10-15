package com.stackroute.service;

import com.stackroute.domain.Track;

import java.util.List;

public interface TrackService {

    public Track saveTrack(Track track);

    public List<Track> getAllTracks();

    public List<Track> deleteTrack(int trackID);

    public List<Track> deleteAllTrack();

    public List<Track> updateTrack(Track track, int trackId);

}

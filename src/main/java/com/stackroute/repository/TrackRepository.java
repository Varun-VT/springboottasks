package com.stackroute.repository;

import com.stackroute.domain.Track;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrackRepository extends JpaRepository<Track, Integer> {
// JpaRepository<User, Integer>- 1st para is domain entity, 2nd- identifier of domain entity i.e int id;
// We haven't written any implementation for repository as implementation will be provided to us at runtime

    // This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete
    @Query(value = "FROM Track WHERE TRACK_NAME= ?1")
    public List<Track> gettrackByName(String trackName);

}

package com.devmountain.locationserver.repositories.impl;

import com.devmountain.locationserver.dto.LocationDto;
import com.devmountain.locationserver.model.Location;
import com.devmountain.locationserver.repositories.LocationRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/*
METHOD IDEAS: Add new location, remove location?
 */

@SuppressWarnings("unchecked")
@Repository
public class LocationRepositoryImpl implements LocationRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public String addNewLocation(LocationDto locationDto) {
        if (findById(locationDto.getId()).isEmpty()) {
            Location location = new Location(locationDto);
            save(location);
        }
        return "Location Added Successfully!";
    }

    @Override
    public Optional<Location> findById(Long id) {
        if (id == null) {
            return Optional.empty();
        }
        return Optional.of(entityManager.find(Location.class, id));
    }

    @Override
    public String removeLocationByDeviceAfterDate(Long deviceId, LocalDateTime dateTime) {
        entityManager.createQuery(
                        "delete from Location l where l.device.id = :deviceId and l.dateTime > :date")
                .setParameter("deviceId", deviceId)
                .setParameter("date", dateTime);
        return "Dates removed Successfully";
    }

    @Override
    public List<Location> getLocationsByDevice(Long deviceId) {
        return entityManager.createQuery(
                        "select l.latitude, l.longitude, l.dateTime from Location l where l.device.id = :deviceId")
                .setParameter("deviceId", deviceId)
                .getResultList();
    }

    //private fields for class
    private void save(Location location) {
        entityManager.persist(location);
        entityManager.flush();
    }
}

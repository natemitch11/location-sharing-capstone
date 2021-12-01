package com.devmountain.locationserver.repositories.impl;


import com.devmountain.locationserver.dto.DeviceDto;
import com.devmountain.locationserver.model.Device;
import com.devmountain.locationserver.repositories.DeviceRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

/*
METHOD IDEAS:
 */

@Repository
public class DeviceRepositoryImpl implements DeviceRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<Device> findById(Long id) {
        if (id == null) {
            return Optional.empty();
        }
        return Optional.of(entityManager.find(Device.class, id));
    }

    @Override
    public DeviceDto addNewDevice(DeviceDto deviceDto) {
        if (findById(deviceDto.getId()).isEmpty()) {
            Device device = new Device(deviceDto);
            save(device);
            deviceDto.setId(device.getId());
        }
        return deviceDto;
    }

    @Override
    public void removeDevice(Long id) {
        Optional<Device> device = findById(id);
        device.ifPresent(this::delete);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Optional<List<Device>> getDeviceInfo(Long deviceId) {
        Optional<Device> device = findById(deviceId);
        if (device.isPresent()) {
            return Optional.of(entityManager.createQuery(
                            "select d.name, d.classification from Device d where d.id = :deviceId")
                    .setParameter("deviceId", deviceId)
                    .getResultList());
        }
        return Optional.empty();
    }

    //private methods
    private void save(Device device) {
        entityManager.persist(device);
        entityManager.flush();
    }

    private void delete(Device device) {
        entityManager.remove(device);
        entityManager.flush();
    }

}

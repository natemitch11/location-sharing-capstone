package com.devmountain.locationserver.repositories.impl;

import com.devmountain.locationserver.model.Device;
import com.devmountain.locationserver.model.User;
import com.devmountain.locationserver.repositories.UsersDevicesRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UsersDevicesRepositoryImpl implements UsersDevicesRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @SuppressWarnings(value = "unchecked")
    @Override
    public List<Device> getUserDevices(Long userId) {
        return entityManager.createNativeQuery(
                        "select d.name, d.classification, d.id, u.username from users u" +
                                " inner join users_devices ud on u.id = ud.user_id" +
                                " inner join devices d on ud.device_id = d.id" +
                                " where u.id = ?1")
                .setParameter(1, userId)
                .getResultList();
    }

    @Override
    public void addDeviceToUser(User user, Device device) {
        user.addDevice(device);
        save(user);
    }

    @Override
    public void removeDeviceFromUser(User user, Device device) {
        user.removeDevice(device);
        save(user);
    }

    private void save(User user) {
        entityManager.persist(user);
        entityManager.flush();
    }
}

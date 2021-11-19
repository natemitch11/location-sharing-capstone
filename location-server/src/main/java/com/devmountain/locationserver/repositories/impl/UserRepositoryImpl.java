package com.devmountain.locationserver.repositories.impl;

import com.devmountain.locationserver.dto.UserDto;
import com.devmountain.locationserver.model.User;
import com.devmountain.locationserver.repositories.UserRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;


/*
METHOD IDEAS FOR USERS:
 */

@Repository
public class UserRepositoryImpl implements UserRepository {
    @PersistenceContext
    private EntityManager entityManager;

    //FindBy methods
    @SuppressWarnings(value = "unchecked")
    @Override
    public Optional<User> findByUsername(String username) {
        List<Long> userId = entityManager.createQuery(
                        "Select u.id from User u where u.username = :username")
                .setParameter("username", username)
                .getResultList();
        if (userId.size() == 1) {
            return findById(userId.get(0));
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> findById(Long id) {
        if (id == null) {
            return Optional.empty();
        }
        return Optional.of(entityManager.find(User.class, id));
    }

    //New User Logic
    @Override
    public void saveNewUser(UserDto userDto) {
        if (findById(userDto.getId()).isEmpty()) {
            User user = new User(userDto);
            save(user);
        }
    }

    //Get User Info
    @SuppressWarnings(value = "unchecked")
    @Override
    public List<String> getUserInfo(String username) {
        return entityManager.createQuery(
                        "SELECT u.username, u.firstName, u.lastName, u.email from User u where u.username = :username")
                .setParameter("username", username)
                .getResultList();
    }

    //Delete User
    @Override
    public String deleteUser(String username) {
        Optional<User> user = findByUsername(username);
        if (user.isPresent()) {
            delete(user.get());
            return "User " + username + " successfully deleted.";
        }
        return "Unable to remove user, " + username + " not found.";
    }

    //private class fields
    private void save(User user) {
        entityManager.persist(user);
        entityManager.flush();
    }

    private void delete(User user) {
        entityManager.remove(user);
        entityManager.flush();
    }
}

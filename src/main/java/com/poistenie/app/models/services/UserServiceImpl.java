package com.poistenie.app.models.services;

import com.poistenie.app.data.entities.UserEntity;
import com.poistenie.app.data.repositories.UserRepository;
import com.poistenie.app.models.dto.UserDTO;
import com.poistenie.app.models.exceptions.DuplicateEmailException;
import com.poistenie.app.models.exceptions.PasswordsDoNotEqualException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Service implementation for managing users and authentication.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository usersRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Creates a new user with optional admin privileges.
     * @param user User data.
     * @param isAdmin Whether the new user should have admin role.
     */
    @Override
    public void create(UserDTO user, boolean isAdmin) {
        if (!user.getPassword().equals(user.getConfirmPassword()))
            throw new PasswordsDoNotEqualException();

        UserEntity userEntity = new UserEntity();

        userEntity.setEmail(user.getEmail());
        userEntity.setPassword(passwordEncoder.encode(user.getPassword()));
        userEntity.setAdmin(isAdmin);

        try {
            usersRepository.save(userEntity);
        } catch (DataIntegrityViolationException e) {
            throw new DuplicateEmailException();
        }
    }

    /**
     * Loads a user by their email address (username) for authentication.
     * @param username The email address used as username.
     * @return UserDetails of the authenticated user.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usersRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username, " + username + " not found"));
    }
}

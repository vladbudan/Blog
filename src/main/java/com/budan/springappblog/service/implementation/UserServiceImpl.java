package com.budan.springappblog.service.implementation;

import com.budan.springappblog.constants.Abbreviation;
import com.budan.springappblog.dto.status.DtoRequestStatus;
import com.budan.springappblog.dto.user.UserDto;
import com.budan.springappblog.model.ActivateCode;
import com.budan.springappblog.model.Role;
import com.budan.springappblog.model.User;
import com.budan.springappblog.model.UserStatus;
import com.budan.springappblog.repository.ActivateCodeRepository;
import com.budan.springappblog.repository.RoleRepository;
import com.budan.springappblog.repository.UserRepository;
import com.budan.springappblog.service.MailService;
import com.budan.springappblog.service.UserService;
import com.budan.springappblog.service.dtoConverters.DtoUserConverter;
import lombok.AllArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final DtoUserConverter dtoUserConverter;
    private final BCryptPasswordEncoder passwordEncoder;
    private final ActivateCodeRepository activateCodeRepository;
    private final MailService mailService;

    @Override
    public User findByFirstname(String firstName) {
        User user = userRepository.findByFirstname(firstName);
        return user;
    }

    @Override
    public DtoRequestStatus registerUser(UserDto userDto) {
        User user = dtoUserConverter.fromAddToModel(userDto);
        User existUser = userRepository.findByFirstname(user.getFirstName());
        if (existUser == user) {
            return new DtoRequestStatus(Abbreviation.ERROR, Abbreviation.ERROR_THAT_USER_ALREADY_EXIST);
        }
        setDefualtSettings(user);
        userRepository.save(user);
        ActivateCode activateCode = setActivateCode(user);
        sendMessageRegistration(user, activateCode.getActivateCode());

        return new DtoRequestStatus(Abbreviation.ERROR, Abbreviation.SUCCESS_REGISTRATION);
    }

    @Override
    public void activateUser(String code) {
        List<ActivateCode> activateCodes = (List<ActivateCode>) activateCodeRepository.findAll();
        ActivateCode activateCode = activateCodes.stream()
                .filter(currentCode -> code.equals(currentCode.getActivateCode()))
                .findAny()
                .orElseThrow(() -> new UsernameNotFoundException("Code not found"));
        try {
            if (activateCode == null) {
                throw new IllegalArgumentException("Invalid user activation code");
            }
            activateCode.setActivateCode(null);
            activateCode.setActivate(false);
            User user = userRepository.findByFirstname(activateCode.getUsername());
            user.setStatus(UserStatus.ACTIVE);
            activateCodeRepository.save(activateCode);
            userRepository.save(user);

        } catch (IllegalArgumentException ex) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, Abbreviation.ERROR_THAT_USER_EXIST_OR_REGISTERED, ex);
        }
    }

    @Override
    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDto forgotPassword(UserDto userDto) {
        User user = dtoUserConverter.fromAddToModel(userDto);
        User existUser = userRepository.findByEmail(user.getEmail());
        if (existUser == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Mail is not registered");
        }
        ActivateCode activateCode = setActivateCode(existUser);
        sendMessageNewPassword(existUser, activateCode.getActivateCode());

        return userDto;
    }

    @Override
    public UserDto newPassword(String code, UserDto userDto) {
        User user = dtoUserConverter.fromAddToModel(userDto);
        user = userRepository.findByFirstname(user.getFirstName());
        List<ActivateCode> activateCodes = (List<ActivateCode>) activateCodeRepository.findAll();
        ActivateCode activateCode = activateCodes.stream()
                .filter(currentCode -> code.equals(currentCode.getActivateCode()))
                .findAny()
                .orElseThrow(() -> new UsernameNotFoundException("Code not found"));
        try {
            if (!user.getFirstName().equals(activateCode.getUsername())) {
                throw new IllegalArgumentException("Invalid user activation code");
            }
            activateCode.setActivateCode(null);
            activateCode.setActivate(false);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setUpdated(new Date());

            activateCodeRepository.save(activateCode);

        } catch (IllegalArgumentException ex) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, Abbreviation.SUBJECT_REMEMBER_PASSWORD, ex);
        }
        return userDto;
    }

    private ActivateCode setActivateCode(User user) {
        if (StringUtils.isEmpty(user.getEmail())) {
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN, "Email is invalid");
        }
        ActivateCode activateCode = new ActivateCode();
        activateCode.setId(UUID.randomUUID());
        activateCode.setUsername(user.getFirstName());
        activateCode.setActivate(true);
        activateCode.setActivateCode(UUID.randomUUID().toString());
        return activateCodeRepository.save(activateCode);
    }

    private void sendMessageRegistration(User user, String code) {
        String message = String.format(
                "Hello, %s! \n" +
                        "Welcome to blog. Please, visit next link: http://localhost:8080/api/registration/activate/%s",
                user.getFirstName(),
                code);

        mailService.send(user.getEmail(), "Activation Code", message);
    }

    private void sendMessageNewPassword(User user, String activateCode) {
        String message = String.format(
                "Hello, %s! \n" +
                        "Welcome to blog. Please, visit next link: http://localhost:8080/api/auth/reset/%s",
                user.getFirstName(),
                activateCode);

        mailService.send(user.getEmail(), "Activation Code", message);
    }

    @Override
    public boolean userIsEnabled(String firstName) {
        return userRepository.findByFirstname(firstName).getIsEnabled();
    }

    private void setDefualtSettings(User user) {
        Role userRole = roleRepository.findByName(USER_ROLE);
        Set<Role> roleUser = new HashSet<>();
        roleUser.add(userRole);

        user.setRoles(userRole);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setStatus(UserStatus.ACTIVE);
        user.setCreated(new Date());
        user.setUpdated(new Date());
    }
}

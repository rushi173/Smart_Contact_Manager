package com.scm.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.scm.entities.User;
import com.scm.helpers.AppConstants;
import com.scm.helpers.Helper;
import com.scm.helpers.ResourceNotFoundException;
import com.scm.repsitories.UserRepo;
import com.scm.services.EmailService;
import com.scm.services.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EmailService emailService;
    
    private Logger logger = LoggerFactory.getLogger(this.getClass());

   

    @Override
    public User saveUser(User user) {
        String userId = UUID.randomUUID().toString();
        user.setuId(userId);
        user.setUpassword(passwordEncoder.encode(user.getUpassword()));


        user.setRoleList(List.of(AppConstants.ROLE_USER));

        logger.info(user.getProvider().toString());

       
        String emailToken=UUID.randomUUID().toString();
        user.setEmailToken(emailToken);
        User savedUser = userRepo.save(user);
        String emailLink = Helper.getLinkForEmailVerificatiton(emailToken);
        emailService.sendEmail(savedUser.getUemail(), "Verify Account : Smart  Contact Manager", emailLink);
        return savedUser;
    }

    @Override
    public Optional<User> getUserById(String id) {
        return userRepo.findById(id);
    }

    @Override
    public Optional<User> updateUser(User user) {
        User user2 = userRepo.findById(user.getuId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        user2.setUname(user.getUname());
        user2.setUemail(user.getUemail());
        user2.setUpassword(passwordEncoder.encode(user.getUpassword())); // Encode updated password
        user2.setUabout(user.getUabout());
        user2.setUponeNo(user.getUponeNo());
        user2.setUgender(user.getUgender());
        user2.setUprofilePic(user.getUprofilePic());
        user2.setEnabled(user.isEnabled());
        user2.setEmailVerified(user.isEmailVerified());
        user2.setPhoneVerified(user.isPhoneVerified());
        user2.setProvider(user.getProvider());
        user2.setProviderUserId(user.getProviderUserId());
        User save = userRepo.save(user2);
        return Optional.ofNullable(save);
    }

    @Override
    public void deleteUser(String id) {
        User user2 = userRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        userRepo.delete(user2);
    }

    @Override
    public boolean isUserExist(String userId) {
        return userRepo.findById(userId).isPresent();
    }

    @Override
    public boolean isUserExistByEmail(String email) {
        return userRepo.findByUemail(email).isPresent();
    }

    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepo.findByUemail(email).orElse(null);
    }
}

package com.agh.surveys.service.user;

import com.agh.surveys.exception.user.UserNotFoundException;
import com.agh.surveys.model.user.User;
import com.agh.surveys.model.user.dto.UserCreateDto;
import com.agh.surveys.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public String addUserFromDto(UserCreateDto userCreateDto) {
        User user = new User(userCreateDto);
        return userRepository.save(user).getUserNick();
    }

    @Override
    public void removeUser(User user) {
        userRepository.delete(user);
    }

    @Override
    public void removeUserByNick(String nick) {
        userRepository.removeByUserNick(nick);
    }

    @Override
    public User getUserByNick(String nick) {
        return userRepository.findByUserNick(nick)
                .orElseThrow(UserNotFoundException::new);
    }
}

package br.edu.fa7.cursojsf.service;

import br.edu.fa7.cursojsf.model.User;
import br.edu.fa7.cursojsf.repository.UserRepository;

import javax.inject.Inject;
import java.util.List;

public class UserService {

    @Inject
    private UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public void save(User user) {
        userRepository.save(user);
    }

    public void remove(Integer id) {
        userRepository.remove(id);
    }

    public User findById(Integer id) {
        return userRepository.findById(id);
    }
    
    public User findByUsernameAndPassword(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password);
    }
}

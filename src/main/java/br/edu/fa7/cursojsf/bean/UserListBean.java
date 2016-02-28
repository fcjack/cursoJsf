package br.edu.fa7.cursojsf.bean;

import br.edu.fa7.cursojsf.model.User;
import br.edu.fa7.cursojsf.repository.UserRepository;
import br.edu.fa7.cursojsf.service.UserService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@RequestScoped
public class UserListBean implements Serializable {

    @Inject
    private UserService userService;

    private List<User> userList;

    @PostConstruct
    public void init() {
        userList = userService.findAll();
    }

    public List<User> getUserList() {
        return userList;
    }
}

package main.main.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import main.main.entities.User;
import main.main.repositories.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    public List<User> findAll() {
        return repository.findAll();
    }

    public User findById(Integer id) {
        Optional<User> obj = repository.findById(id);
        return obj.get();
    }

    public User insert(User obj) {
        return repository.save(obj);
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    public User updateById(Integer id, User obj) {

        User entity = repository.getReferenceById(id); // PREPARAR O OBJETO MONITORADO
        updateData(obj, entity);
        return repository.save(entity);
    }

    private void updateData(User obj, User entity) {
        entity.setName(obj.getName());
        entity.setEmail(obj.getEmail());
        entity.setPhone(obj.getPhone());
    }
}

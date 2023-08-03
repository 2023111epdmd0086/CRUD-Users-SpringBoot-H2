package main.main.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import main.main.entities.User;
import main.main.services.UserService;

// CONFIGURO OS ENDPOINTS DO WEBSERVICE
@RestController
@RequestMapping(value = "/users")
public class UserResources {
    // Usa-se autowired para registrar injeção de dependencia
    @Autowired
    private UserService service;

    // Usa-se GetMapping para requisições GET.
    @GetMapping
    public ResponseEntity<List<User>> findAll() {
        List<User> lista = service.findAll(); // Criar lista de usuários que vao vir no service.
        return ResponseEntity.ok().body(lista);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<User> findById(@PathVariable Integer id) {
        User obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity<User> insert(@RequestBody User obj) {
        obj = service.insert(obj);
        // CRIAR URI PARA PASSAR NO 201 - CREATED
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{i}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(obj);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<User> update(@PathVariable Integer id, @RequestBody User obj) {
        obj = service.updateById(id, obj);
        return ResponseEntity.ok().body(obj);
    }
}

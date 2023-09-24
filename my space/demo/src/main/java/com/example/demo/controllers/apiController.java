package com.example.demo.controllers;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.apiNotFoundException;
import com.example.demo.model.api;
import com.example.demo.repo.apiRepo;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class apiController {
	@Autowired
	public apiRepo repo;

        @GetMapping
	    public ResponseEntity<?> getServers(@RequestParam(required = false) Long id) {
	        if (id != null) {
	            api server = repo.findById(id).orElse(null);
	            if (server != null) {
	                return ResponseEntity.ok(server);
	            } else {
	                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Server not found");
	            }
	        } else {
	            List<api> servers = repo.findAll();
	            return ResponseEntity.ok(servers);
	        }
	    }
	
        @GetMapping("/user/{id}")
        api getUserById(@PathVariable Long id) {
            return repo.findById(id)
                    .orElseThrow(() -> new apiNotFoundException(id));
        }
	
	@PostMapping(value = "/create")
	public String createServers(@RequestBody api a)
	{
		api insertedapi = repo.insert(a);
		return "server creatrd"+" "+insertedapi.getName();
	}
	
	
	
	  @DeleteMapping("/{id}")
	    public ResponseEntity<?> deleteServer(@PathVariable Long id) {
	        repo.deleteById(id);
	        return ResponseEntity.noContent().build();
	    }
	  
	    @PutMapping("/user/{id}")
	    api updateUser(@RequestBody api newUser, @PathVariable Long id) {
	        return repo.findById(id)
	                .map(api -> {
	                    api.setId(newUser.getId());
	                    api.setName(newUser.getName());
	                    api.setLanguage(newUser.getLanguage());
	                    api.setFramework(newUser.getFramework());
	                    return repo.save(api);
	                }).orElseThrow(() -> new apiNotFoundException(id));
	    }

	  
}

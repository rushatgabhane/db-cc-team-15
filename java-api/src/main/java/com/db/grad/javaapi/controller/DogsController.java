package com.db.grad.javaapi.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

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

import com.db.grad.javaapi.exception.ResourceNotFoundException;
import com.db.grad.javaapi.model.Dogs;
import com.db.grad.javaapi.repository.DogsRepository;

@RestController
@RequestMapping("/api/v1")
public class DogsController {
    @Autowired
    private DogsRepository dogsRepository;

    @GetMapping("/dogs")
    public List < Dogs > getAllDogs() {
        return dogsRepository.findAll();
    }

    @GetMapping("/dogs/{id}")
    public ResponseEntity < Dogs > getEmployeeById(@PathVariable(value = "id") Long id)
    throws ResourceNotFoundException {
        Dogs dogs = dogsRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Dog not found for this id :: " + id));
        return ResponseEntity.ok().body(dogs);
    }

    @PostMapping("/dogs")
    public Dogs createDog(@Valid @RequestBody Dogs dogs) {
        return dogsRepository.saveAndFlush(dogs);
    }

    @PutMapping("/dogs/{id}")
    public ResponseEntity < Dogs > updateDog(@PathVariable(value = "id") Long id,
        @Valid @RequestBody Dogs dogDetails) throws ResourceNotFoundException {
    	Dogs getDog = dogsRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Dog not found for this id :: " + id));

    	getDog.setName(dogDetails.getName());
    	getDog.setAge(dogDetails.getAge());
        final Dogs updatedDogs = dogsRepository.save(getDog);
        return ResponseEntity.ok(updatedDogs);
    }

    @DeleteMapping("/dogs/{id}")
    public Map < String, Boolean > deleteDog(@PathVariable(value = "id") Long id)
    throws Exception {
    	Dogs dogs = dogsRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Dog not found for this id :: " + id));

    	dogsRepository.delete(dogs);
        Map < String, Boolean > response = new HashMap <>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}

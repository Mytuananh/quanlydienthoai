package com.codegym.controller;

import com.codegym.model.Smartphone;
import com.codegym.service.ISmartphoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@RestController
@RequestMapping("/smartphones")
public class SmartphoneController {

    @Autowired
    ISmartphoneService smartphoneService;

    @GetMapping("/list")
    public ModelAndView getAllSmartPhone(){
        ModelAndView modelAndView=new ModelAndView("/smart/list");
        modelAndView.addObject("smartphones",smartphoneService.findAll());
        return modelAndView;
    }

    @GetMapping
    public ResponseEntity<Iterable<Smartphone>> show() {
        return new ResponseEntity<>(smartphoneService.findAll(),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Smartphone> create(@RequestBody Smartphone smartphone) {
        return new ResponseEntity<>(smartphoneService.save(smartphone), HttpStatus.CREATED);
    }

//    @GetMapping
//    public ResponseEntity<Iterable<Smartphone>> allPhones() {
//        return new ResponseEntity<>(smartphoneService.findAll(), HttpStatus.OK);
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Smartphone> deletePhone(@PathVariable Long id) {
        Optional<Smartphone> smartphoneOptional = smartphoneService.findById(id);
        if (!smartphoneOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        smartphoneService.remove(id);
        return new ResponseEntity<>(smartphoneOptional.get(),HttpStatus.NO_CONTENT);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Smartphone> findById(@PathVariable Long id) {
        Optional<Smartphone> smartphoneOptional = smartphoneService.findById(id);
        if (!smartphoneOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(smartphoneOptional.get(), HttpStatus.OK);
    }
    @PutMapping
    public ResponseEntity<Smartphone> update(@RequestBody Smartphone smartphone) {
        return new ResponseEntity<>(smartphoneService.save(smartphone), HttpStatus.OK);
    }
}

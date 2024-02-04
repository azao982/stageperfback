package com.example.demo.Controllers;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.Services.CoursService;
import com.example.demo.classes.Cours;
@RestController
@ResponseBody
@RequestMapping("/api/cours")
@CrossOrigin(origins = "*", maxAge = 3600)
public class CoursController {

 CoursService coursService;
    @PostMapping("/ajouterCours")
    // Ajoutez cette annotation
    public ResponseEntity<String> ajouterCours(@RequestBody Cours cours) {
        try {
            coursService.ajouterCours(cours);
            return ResponseEntity.ok("Cours ajouté avec succès");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de l'ajout du cours");
        }
    }
    @GetMapping("/ConsulterCours")
    public List<Cours> getCours() {
        return coursService.getAllCours();
    }
    @GetMapping("/SearchCours")
    public List<Cours> searchCours(@RequestParam String keyword) {
        return coursService.searchCours(keyword);
  }
    @DeleteMapping("/supprimerCours/{coursId}")
    public ResponseEntity<Map<String, String>> supprimerCours(@PathVariable Long coursId) {
        Map<String, String> response = new HashMap<>();
        try {
            coursService.supprimerCours(coursId);
            response.put("message", "Course deleted successfully");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("message", "Failed to delete course");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<Cours> getById(@PathVariable Long id) {

        Cours entity = coursService.getById(id);

        if (entity != null) {

            return new ResponseEntity<>(entity, HttpStatus.OK);
        } else {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/ModifierCours/{id}")
    public void updateCours (@RequestBody Cours cours, @PathVariable long id){
    	coursService.updateCours(cours,id);
    }
    public CoursController(CoursService coursService) {
        this.coursService = coursService;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file) {
        try {
            coursService.storeFile(file);
            return ResponseEntity.ok("{\"message\": \"Cours ajouté avec succès\"}");
        } catch (IOException e) {
            return ResponseEntity.status(500).body("{\"error\": \"Échec de l'ajout du cours\"}");
        }
    }
}
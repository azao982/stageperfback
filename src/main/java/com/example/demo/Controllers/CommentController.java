package com.example.demo.Controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.Services.ComService;
import com.example.demo.classes.Commentaire;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class CommentController {

    private final ComService comService;

    @Autowired
    public CommentController(ComService comService) {
        this.comService = comService;
    }

    @PostMapping("/ajouterCom")
    public void ajouterCom(@RequestBody Commentaire commentaire) {
        comService.ajouterCom(commentaire);
    }

    @GetMapping("/ConsulterCom")
    public List<Commentaire> getCom() {
        return comService.getAllCom();
    }
    @DeleteMapping("/supprimerCom/{id}")
    public ResponseEntity<Map<String, String>> supprimerCours(@PathVariable Long id) {
        Map<String, String> response = new HashMap<>();
        try {
            comService.supprimerCom(id);
            response.put("message", "Comment deleted successfully");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("message", "Failed to delete course");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/comment/{id}")
    public ResponseEntity<Commentaire>getById(@PathVariable Long id){
        Commentaire entity = comService.getById(id);
        if (entity != null) {
            return new ResponseEntity<>(entity, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/ModifierCom/{id}")
    public void updateCom (@RequestBody Commentaire commentaire,@PathVariable long id){
    	comService.updateCom(commentaire,id);
    }
}

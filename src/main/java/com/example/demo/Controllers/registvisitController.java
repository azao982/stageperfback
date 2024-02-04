package com.example.demo.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Services.VisitService;
import com.example.demo.classes.Visit;
@RestController
@ResponseBody
@RequestMapping("/api")
@CrossOrigin(origins = "*", maxAge = 3600)
public class registvisitController {
	 private final VisitService visitService;

	    @Autowired
	    public registvisitController(VisitService visitService) {
	        this.visitService = visitService;
	    }

	    @PostMapping("/regist")
	    public ResponseEntity<String> ajouterVisit(@RequestBody Visit visit) {
	        try {
	            visitService.ajouterVisit(visit);
	            return ResponseEntity.ok("visiteur inscrit avec succès");
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur");
	        }
	    }

	    @GetMapping("/ConsulterVisit")
	    public List<Visit> getAllVisits() {
	        return visitService.getAllVisits();
	    }
	    }

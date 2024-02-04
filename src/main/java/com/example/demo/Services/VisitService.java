package com.example.demo.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Repositories.VisitRepo;
import com.example.demo.classes.Visit;
@Service
public class VisitService {
	@Autowired

    private VisitRepo visitRepo;
	public Visit ajouterVisit(Visit visit) {
        return visitRepo.save(visit);
    }
	 public List<Visit> getAllVisits() {
	        return visitRepo.findAll();
	    }
}

package com.example.demo.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Repositories.ComRepository;
import com.example.demo.classes.Commentaire;
import com.example.demo.classes.Cours;

@Service
public class ComService {

    private final ComRepository comRepository;

    @Autowired
    public ComService(ComRepository comRepository) {
        this.comRepository = comRepository;
    }

    public Commentaire ajouterCom(Commentaire commentaire) {
        return comRepository.save(commentaire);
    }

    public List<Commentaire> getAllCom() {
        return comRepository.findAll();
    }
    public void supprimerCom(Long id) {
        comRepository.deleteById(id);
    }
    public boolean updateCom(Commentaire commentaire, long id) {
        Optional<Commentaire> coursOptional = comRepository.findById((long) id);
        
        if (coursOptional.isPresent()) {
            Commentaire comToUpdate = coursOptional.get();
            
            // Mettre à jour les champs du cours avec les nouvelles valeurs
            comToUpdate.setCommentaire(commentaire.getCommentaire());
            
            
            // Enregistrer les modifications dans la base de données
            comRepository.save(comToUpdate);
            return true;
        } else 
        	return false ;  
    }

    public Commentaire getById(Long id) {

        return comRepository.findById(id).orElse(null);

    }
}

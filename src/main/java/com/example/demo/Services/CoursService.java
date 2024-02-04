package com.example.demo.Services;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.Repositories.CoursRepository;
import com.example.demo.classes.Cours;

@Service
public class CoursService {
	@Autowired

     private  CoursRepository coursRepository;

    public CoursService(CoursRepository coursRepository) {
        this.coursRepository = coursRepository;
    }

    public Cours ajouterCours(Cours cours) {
        return coursRepository.save(cours);
    }
    
    public Cours getById(Long id) {

        return coursRepository.findById(id).orElse(null);

    }

    public List<Cours> getAllCours() {
        return coursRepository.findAll();
    }
    public List<Cours> searchCours(String keyword) {
        // Implement search logic here, for example, by filtering based on the keyword
        return coursRepository.findAll().stream()
                .filter(c -> containsKeyword(c, keyword))
                .collect(Collectors.toList());
    }

 // Helper method to check if any property of the course contains the keyword
    private boolean containsKeyword(Cours cours, String keyword) {
        return cours.getNomDuCours().contains(keyword) ||
                cours.getDescription().contains(keyword) ||
                (cours.getNomProfesseur() != null && cours.getNomProfesseur().contains(keyword));
        // Additional conditions if needed
    }
    public void supprimerCours(Long coursId) {
        coursRepository.deleteById(coursId);
    }

    public boolean updateCours(Cours cours, long id) {
        Optional<Cours> coursOptional = coursRepository.findById((long) id);
        
        if (coursOptional.isPresent()) {
            Cours coursToUpdate = coursOptional.get();
            
            // Mettre à jour les champs du cours avec les nouvelles valeurs
            coursToUpdate.setNomDuCours(cours.getNomDuCours());
            coursToUpdate.setDescription(cours.getDescription());
            coursToUpdate.setCoursFormatPdf(cours.getCoursFormatPdf());
            coursToUpdate.setCoursFormatVideo(cours.getCoursFormatVideo());
            coursToUpdate.setNomProfesseur(cours.getNomProfesseur());
            
            
            // Enregistrer les modifications dans la base de données
            coursRepository.save(coursToUpdate);
            return true;
        } else 
        	return false ;  
    }
    @Value("${file.upload-dir}") // Définissez cette propriété dans votre fichier application.properties ou application.yml
    private String uploadDir;
    public void storeFile(MultipartFile file) throws IOException {
        File directory = new File(uploadDir);
        if (!directory.exists()) {
            directory.mkdirs(); // Créez le répertoire s'il n'existe pas
        }

        String filePath = uploadDir + File.separator + file.getOriginalFilename();
        file.transferTo(new File(filePath));
    }

        // Add other properties as needed
    }
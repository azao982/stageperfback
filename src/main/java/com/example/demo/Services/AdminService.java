package com.example.demo.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Repositories.AdminRepository;
import com.example.demo.classes.Admin;

@Service
public class AdminService {
    private final AdminRepository adminRepository;

    @Autowired
    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public Admin register(Admin admin) {
        return adminRepository.save(admin);
    }

    public Admin fetchByEmail(String email) {
        return adminRepository.findByEmailId(email);
    }

    public Admin fetchByEmailAndPassword(String tempEmail, String tempPass) {
        // Vérifiez d'abord s'il s'agit de l'administrateur
        if ("admin@example.com".equals(tempEmail) && "adminPassword".equals(tempPass)) {
            Admin admin = new Admin();
            admin.setEmailId(tempEmail);
            admin.setPassword(tempPass);
            // Vous pouvez définir d'autres propriétés de l'administrateur si nécessaire
            return admin;
        }

        // Si ce n'est pas l'administrateur, recherchez dans la base de données
        return adminRepository.findByEmailIdAndPassword(tempEmail, tempPass);
    }
}

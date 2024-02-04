

package com.example.demo.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Services.AdminService;
import com.example.demo.classes.Admin;

@RestController
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping(path = "/register/admin")
    public Admin registerAdmin(@RequestBody Admin admin) throws Exception {
        // Supprimez la vérification d'existence pour permettre à l'admin de se connecter sans enregistrement
         String tempEmailId = admin.getEmailId();
         if (tempEmailId != null && !"".equals(tempEmailId)) {
           Admin adminObj = adminService.fetchByEmail(tempEmailId);
           if (adminObj != null) {
                throw new Exception("This admin with email " + tempEmailId + " already exists");
            }
         }

        // Vous pouvez définir un rôle spécifique pour l'administrateur, par exemple "admin"
        admin.setRole("admin");

        // Exécutez le service d'inscription avec l'admin mis à jour
        Admin adminObj = adminService.register(admin);

        // Retournez l'admin enregistré
        return adminObj;
    }
}

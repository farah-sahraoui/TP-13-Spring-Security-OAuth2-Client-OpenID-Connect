package ma.ens.security.web;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    /**
     * Gère l'accès à la page d'accueil.
     * Aucune authentification requise selon la config de sécurité.
     */
    @GetMapping(value = {"/", "/index"})
    public String indexPage() {

        return "index";
    }

    /**
     * Gère l'accès au profil utilisateur.
     * L'annotation @AuthenticationPrincipal injecte automatiquement l'utilisateur connecté.
     */
    @GetMapping("/profile")
    public String viewProfile(Model model, @AuthenticationPrincipal OAuth2User oauth2User) {

        String userName = oauth2User.getAttribute("name");
        String userEmail = oauth2User.getAttribute("email");
        String userPicture = oauth2User.getAttribute("picture");
        model.addAttribute("name", userName);
        model.addAttribute("email", userEmail);
        model.addAttribute("picture", userPicture);

        return "profile";
    }
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pens.ac.id.controller;

/**
 *
 * @author zapah
 */
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pens.ac.id.AppContainer;
import pens.ac.id.model.Peminjaman;
import pens.ac.id.model.User;

@Controller
public class ControllerUser {

    @RequestMapping(value = "/ceklogin", method = RequestMethod.POST)
    public String login(@ModelAttribute User user, HttpSession session) {
        User usr = AppContainer.getInstance().getServiceUser().login(user);
        if (usr != null) {
            session.setAttribute("userdata", usr);
            return "redirect:/home";
        }
        return "redirect:/login";
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String openMain(Model model, HttpSession session) {
        if (session.getAttribute("userdata") != null) {
            int s_pinjam = 0;
            for (Peminjaman p : AppContainer.getInstance().getServicePeminjaman().getAllPeminjaman()) {
                if (p.getStatus().equals("Belum dikembalikan")) {
                    s_pinjam++;
                }
            }
            model.addAttribute("mhs", AppContainer.getInstance().getServiceMahasiswa().getDbSize());
            model.addAttribute("buku", AppContainer.getInstance().getServiceBuku().getDbSize());
            model.addAttribute("pinjam", AppContainer.getInstance().getServicePeminjaman().getDbSize());
            model.addAttribute("s_pinjam", s_pinjam);
            return "/mainmenu";
        }
        else{
            return "redirect:/login";
        }
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String create(Model model) {
        model.addAttribute("user", new User());
        return "/login";
    }
    
     @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(Model model, HttpSession session) {
        session.removeAttribute("userdata");
        return "redirect:/login";
    }

}

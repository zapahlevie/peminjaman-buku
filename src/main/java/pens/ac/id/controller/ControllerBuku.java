package pens.ac.id.controller;

import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pens.ac.id.AppContainer;
import pens.ac.id.model.Buku;

@Controller
public class ControllerBuku {

    @RequestMapping(value = "/buku", method = RequestMethod.GET)
    public String getAll(ModelMap model, HttpSession session) {
        if (session.getAttribute("userdata") != null) {
            model.addAttribute("bukumodel", AppContainer.getInstance().getServiceBuku().getAllBuku());
            return "/buku/buku";
        } else {
            return "redirect:/login";
        }

    }

    @RequestMapping(value = "/buku_create", method = RequestMethod.GET)
    public String create(Model model, HttpSession session) {
        if (session.getAttribute("userdata") != null) {
            model.addAttribute("buku", new Buku());
            return "/buku/buku_create";
        } else {
            return "redirect:/login";
        }
    }

    @RequestMapping(value = "/buku_update", method = RequestMethod.GET)
    public String update(Model model, @RequestParam("id") Long id, HttpSession session) {
        if (session.getAttribute("userdata") != null) {
            model.addAttribute("buku", AppContainer.getInstance().getServiceBuku().getBukuById(id));
            return "/buku/buku_update";
        } else {
            return "redirect:/login";
        }
    }

    @RequestMapping(value = "/buku_delete", method = RequestMethod.GET)
    public String delete(@RequestParam("id") Long id, HttpSession session) {
        if (session.getAttribute("userdata") != null) {
            AppContainer.getInstance().getServiceBuku().deleteBuku(id);
            return "redirect:/buku";
        } else {
            return "redirect:/login";
        }
    }

    @RequestMapping(value = "/buku_submit", method = RequestMethod.POST)
    public String submit(@ModelAttribute Buku buku) {
        AppContainer.getInstance().getServiceBuku().saveBuku(buku);
        return "redirect:/buku";
    }

    @RequestMapping(value = "/buku_submit_update", method = RequestMethod.POST)
    public String submitUpdate(@ModelAttribute Buku buku) {
        AppContainer.getInstance().getServiceBuku().updateBuku(buku);
        return "redirect:/buku";
    }

}

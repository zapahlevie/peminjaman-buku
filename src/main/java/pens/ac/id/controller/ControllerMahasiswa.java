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
import pens.ac.id.model.Mahasiswa;

@Controller
public class ControllerMahasiswa {

    @RequestMapping(value = "/mahasiswa", method = RequestMethod.GET)
    public String getAll(ModelMap model, HttpSession session) {
        if (session.getAttribute("userdata") != null) {
            model.addAttribute("mahasiswamodel", AppContainer.getInstance().getServiceMahasiswa().getAllMahasiswa());
            return "/mhs/mahasiswa";
        } else {
            return "redirect:/login";
        }
    }

    @RequestMapping(value = "/mahasiswa_create", method = RequestMethod.GET)
    public String create(Model model, HttpSession session) {
        if (session.getAttribute("userdata") != null) {
            model.addAttribute("mahasiswa", new Mahasiswa());
            return "/mhs/mahasiswa_create";
        } else {
            return "redirect:/login";
        }
    }

    @RequestMapping(value = "/mahasiswa_update", method = RequestMethod.GET)
    public String update(Model model, @RequestParam("id") Long id, HttpSession session) {
        if (session.getAttribute("userdata") != null) {
            model.addAttribute("mahasiswa", AppContainer.getInstance().getServiceMahasiswa().getMahasiswaById(id));
            return "/mhs/mahasiswa_update";
        } else {
            return "redirect:/login";
        }
    }

    @RequestMapping(value = "/mahasiswa_delete", method = RequestMethod.GET)
    public String delete(@RequestParam("id") Long id, HttpSession session) {
        if (session.getAttribute("userdata") != null) {
            AppContainer.getInstance().getServiceMahasiswa().deleteMahasiswa(id);
            return "redirect:/mahasiswa";
        } else {
            return "redirect:/login";
        }
    }

    @RequestMapping(value = "/mahasiswa_submit", method = RequestMethod.POST)
    public String submit(@ModelAttribute Mahasiswa mahasiswa) {
        AppContainer.getInstance().getServiceMahasiswa().saveMahasiswa(mahasiswa);
        return "redirect:/mahasiswa";
    }

    @RequestMapping(value = "/mahasiswa_submit_update", method = RequestMethod.POST)
    public String submitUpdate(@ModelAttribute Mahasiswa mahasiswa) {
        AppContainer.getInstance().getServiceMahasiswa().updateMahasiswa(mahasiswa);
        return "redirect:/mahasiswa";
    }

}

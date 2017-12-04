package pens.ac.id.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pens.ac.id.AppContainer;
import pens.ac.id.model.CustomClass;
import pens.ac.id.model.Peminjaman;

@Controller
public class ControllerPeminjaman {

    private List<CustomClass> models;

    @RequestMapping(value = "/peminjaman", method = RequestMethod.GET)
    public String getAll(Model model, HttpSession session) {
        if (session.getAttribute("userdata") != null) {
            models = new ArrayList<CustomClass>();
            models = AppContainer.getInstance().getServicePeminjaman().getModel();
            model.addAttribute("models", models);
            return "/pinjam/peminjaman";
        } else {
            return "redirect:/login";
        }
    }

    @RequestMapping(value = "/peminjaman_create", method = RequestMethod.GET)
    public String create(Model model, HttpSession session) {
        if (session.getAttribute("userdata") != null) {
            model.addAttribute("mahasiswamodel", AppContainer.getInstance().getServiceMahasiswa().getAllMahasiswa());
            model.addAttribute("bukumodel", AppContainer.getInstance().getServiceBuku().getAllBuku());
            model.addAttribute("peminjaman", new Peminjaman("Belum dikembalikan"));
            return "/pinjam/peminjaman_create";
        } else {
            return "redirect:/login";
        }
    }

    @RequestMapping(value = "/peminjaman_update", method = RequestMethod.GET)
    public String update(Model model, @RequestParam("id") Long id, HttpSession session) {
        if (session.getAttribute("userdata") != null) {
            model.addAttribute("mahasiswamodel", AppContainer.getInstance().getServiceMahasiswa().getAllMahasiswa());
            model.addAttribute("bukumodel", AppContainer.getInstance().getServiceBuku().getAllBuku());
            model.addAttribute("peminjaman", AppContainer.getInstance().getServicePeminjaman().getPeminjamanById(id));
            return "/pinjam/peminjaman_update";
        } else {
            return "redirect:/login";
        }
    }

    @RequestMapping(value = "/peminjaman_delete", method = RequestMethod.GET)
    public String delete(@RequestParam("id") Long id, HttpSession session) {
        if (session.getAttribute("userdata") != null) {
            AppContainer.getInstance().getServicePeminjaman().deletePeminjaman(id);
            return "redirect:/peminjaman";
        } else {
            return "redirect:/login";
        }
    }

    @RequestMapping(value = "/peminjaman_submit", method = RequestMethod.POST)
    public String submit(@ModelAttribute Peminjaman peminjaman) {
        AppContainer.getInstance().getServicePeminjaman().savePeminjaman(peminjaman);
        return "redirect:/peminjaman";
    }

    @RequestMapping(value = "/peminjaman_submit_update", method = RequestMethod.POST)
    public String submitUpdate(@ModelAttribute Peminjaman peminjaman) {
        AppContainer.getInstance().getServicePeminjaman().updatePeminjaman(peminjaman);
        return "redirect:/peminjaman";
    }

}

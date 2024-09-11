package com.example.buah.controller;

import com.example.buah.entity.Buah;
import com.example.buah.service.BuahService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class BuahUIController {

    @Autowired
    private BuahService buahService;

    @GetMapping("/")
    public String viewHomePage(Model model) {
        model.addAttribute("buahList", buahService.getAllBuah());
        return "index";
    }

    @GetMapping("/create")
    public String showNewBuahForm(Model model) {
        Buah buah = new Buah();
        model.addAttribute("buah", buah);
        return "form";
    }

    @GetMapping("/edit/{id}")
    public String showEditBuahForm(@PathVariable(value = "id") Long id, Model model) {
        Buah buah = buahService.getBuahById(id).orElse(new Buah());
        model.addAttribute("buah", buah);
        return "form";
    }

    @PostMapping("/save")
    public String saveBuah(@ModelAttribute("buah") Buah buah) {
        buahService.saveBuah(buah);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteBuah(@PathVariable(value = "id") Long id) {
        buahService.deleteBuah(id);
        return "redirect:/";
    }
}


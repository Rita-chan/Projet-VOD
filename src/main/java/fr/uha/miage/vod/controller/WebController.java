package fr.uha.miage.vod.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import fr.uha.miage.vod.model.Pays;
import fr.uha.miage.vod.repository.PaysRepository;

@Controller
public class WebController {
	@GetMapping("/")
	public String home(){
		return "index";
	}
	

	@Autowired
	private PaysRepository paysRepository;
	
	@GetMapping("/payscreer")
	public String payscreerform(Model model){
		model.addAttribute("pays", new Pays());
		return "payscreer";
	}
	@PostMapping("/payscreer")
	public String payscreer(@Valid Pays pays, BindingResult bindingResult){
		
		if(bindingResult.hasErrors())
			return "payscreer";
		
		paysRepository.save(pays);
		
		return "redirect:/pays";
	}
	
	@GetMapping("/paysmodifier/{id}")
	public String paysmodifierform(@PathVariable("id") Long id, Model model){
		Pays pays = paysRepository.findOne(id);
		model.addAttribute("pays", pays);
		return "paysmodifier";
	}
	@PostMapping("/paysmodifier")
	public String paysmodifier(@Valid Pays pays){
		paysRepository.save(pays);
		return "redirect:/pays";
	}

	@GetMapping("/pays")
	public String pays(Model model){
		Iterable<Pays> liste = paysRepository.findAll();
		model.addAttribute("payss", liste);
		return "pays";
	}
	
	@GetMapping("/paysindex")
	public String paysindex(){
		return "paysindex";
	}
	
	@GetMapping("/payssupprimer/{id}")
	public String payssupprimer(@PathVariable("id") Long id){
		paysRepository.delete(id);
		return "redirect:/pays";
	}
}

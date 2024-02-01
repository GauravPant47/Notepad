package com.notepas.textEditor.controller;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.notepas.textEditor.model.NotepadFile;
import com.notepas.textEditor.service.NotepadService;

@Controller
@RequestMapping("/notepad")
public class NotepadController {

	@Autowired
	private NotepadService notepadService;

	@GetMapping("/")
	public String getAllFiles(Model model) {
		List<NotepadFile> files = notepadService.getAllFiles();
		model.addAttribute("files", files);
		return "index";
	}

	@GetMapping("/file/{fileName}")
	public String getFile(@PathVariable String fileName, Model model) {
		NotepadFile file = notepadService.getFile(fileName);
		List<NotepadFile> files = notepadService.getAllFiles();
		model.addAttribute("file", file);
		model.addAttribute("files", files);
		return "file";
	}

	@PostMapping("/file/{fileName}")
	public String updateFile(@ModelAttribute NotepadFile notepadFile) throws IOException {
		notepadService.updateFile(notepadFile);
		return "redirect:/notepad/";
	}

	@PostMapping("/create")
	public String createNewFile(@RequestParam String newFileName) throws IOException {
		NotepadFile newFile = new NotepadFile(newFileName, "");
		notepadService.updateFile(newFile);
		return "redirect:/notepad/";
	}

	@GetMapping("/delete/{fileName}")
	public String deleteFile(@PathVariable String fileName) {
		notepadService.deleteFile(fileName);
		return "redirect:/notepad/";
	}

	@PostMapping("/shutdown")
	public void shutdownServer() {
		// Implement server shutdown logic here
		System.exit(0);
	}
}

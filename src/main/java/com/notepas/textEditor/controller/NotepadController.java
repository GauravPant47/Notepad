package com.notepas.textEditor.controller;


import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
	
    @Value("${app.version}")
    private String appVersion;

	@GetMapping("/")
	public String getAllFiles(Model model) {
		List<NotepadFile> files = notepadService.getAllFiles();
		model.addAttribute("files", files);
		model.addAttribute("version", appVersion);
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
		return "redirect:/notepad/file/{fileName}";
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
	
	@PostMapping("/open-editor")
	public String openTextEditor(@RequestParam String fileName) {
	    try {
	        File fileToOpen = new File("files/" + fileName);
	        Desktop desktop = Desktop.getDesktop();	

	        if (desktop.isSupported(Desktop.Action.OPEN)) {
	            desktop.open(fileToOpen);
	        } else {
	            // Handle the case where opening with the default application is not supported
	            // You might want to provide a download link or an alternative way to view the file
	            System.out.println("Opening with the default application is not supported on this platform.");
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	        // Handle the exception as needed
	    }
	    return "redirect:/notepad/";
	}


	@PostMapping("/shutdown")
	public void shutdownServer() {
		// Implement server shutdown logic here
		System.exit(0);
	}
}

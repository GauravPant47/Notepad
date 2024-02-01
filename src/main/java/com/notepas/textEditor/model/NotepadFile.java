package com.notepas.textEditor.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class NotepadFile {
	private String fileName;
	private String content;

	public NotepadFile() {
		this.fileName = generateFileName();
	}

	// getters and setters

	private String generateFileName() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
		LocalDateTime now = LocalDateTime.now();
		return "file_" + now.format(formatter) + ".txt";
	}

	public NotepadFile(String fileName, String content) {
		this.fileName = fileName;
		this.content = content;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}

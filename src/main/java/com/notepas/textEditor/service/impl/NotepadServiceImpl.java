package com.notepas.textEditor.service.impl;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import org.springframework.stereotype.Service;

import com.notepas.textEditor.model.NotepadFile;
import com.notepas.textEditor.service.NotepadService;

@Service
public class NotepadServiceImpl implements NotepadService {

	private final String FILES_DIRECTORY = "files/";

	public NotepadServiceImpl() {
		initializeFilesDirectory();
	}

	private void initializeFilesDirectory() {
		try {
			Path directoryPath = Paths.get(FILES_DIRECTORY);
			Files.createDirectories(directoryPath);
		} catch (IOException e) {
			throw new RuntimeException("Error creating files directory", e);
		}
	}

	@Override
	public List<NotepadFile> getAllFiles() {
		try {
			return Files.list(Paths.get(FILES_DIRECTORY)).map(path -> {
				try {
					return new NotepadFile(path.getFileName().toString(), readFileContent(path));
				} catch (IOException e) {
					// Log the error, and return a placeholder or handle as needed
					// You might want to log the error using a logging framework
					e.printStackTrace(); // Temporary: Print the stack trace to console
					return new NotepadFile("Error reading file", "");
				}
			}).collect(Collectors.toList());
		} catch (IOException e) {
			// Log the error, and rethrow or handle as needed
			// You might want to log the error using a logging framework
			e.printStackTrace(); // Temporary: Print the stack trace to console
			throw new RuntimeException("Error reading files from directory");
		}
	}

	@Override
	public NotepadFile getFile(String fileName) {
		try {
			String filePath = FILES_DIRECTORY + fileName;
			return new NotepadFile(fileName, readFileContent(Paths.get(filePath)));
		} catch (IOException e) {
			throw new RuntimeException("Error reading the file", e);
		}
	}

	@Override
	public void updateFile(NotepadFile notepadFile) throws IOException {
	    String filePath = FILES_DIRECTORY + notepadFile.getFileName();
	    String processedContent = processHtmlContent(notepadFile.getContent());
	    Files.write(Paths.get(filePath), processedContent.getBytes());
	}

	private String processHtmlContent(String htmlContent) {
	    Document document = Jsoup.parse(htmlContent);
	    Elements tables = document.select("table");

	    for (Element table : tables) {
	        processTable(table);
	    }

	    return document.html();
	}

	private void processTable(Element table) {
	    Elements rows = table.select("tr");

	    for (Element row : rows) {
	        Elements cells = row.select("td");
	        row.empty();

	        for (Element cell : cells) {
	            row.appendElement("td").text(cell.text());
	        }
	    }
	}

	private String readFileContent(Path path) throws IOException {
		if (Files.exists(path)) {
			StringBuilder contentBuilder = new StringBuilder();
			try (BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
				char[] buffer = new char[8192]; // You can adjust the buffer size as needed
				int charsRead;
				while ((charsRead = reader.read(buffer)) != -1) {
					contentBuilder.append(buffer, 0, charsRead);
				}
			}
			return contentBuilder.toString();
		} else {
			throw new FileNotFoundException("File not found: " + path);
		}
	}

	@Override
	public void deleteFile(String fileName) {
		String filePath = FILES_DIRECTORY + fileName;
		try {
			Files.deleteIfExists(Paths.get(filePath));
		} catch (IOException e) {
			throw new RuntimeException("Error deleting the file", e);
		}
	}

}

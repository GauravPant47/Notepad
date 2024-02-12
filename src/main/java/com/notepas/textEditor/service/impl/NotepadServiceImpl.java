package com.notepas.textEditor.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

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

		// Check if the content is null
		String content = notepadFile.getContent();
		if (content == null) {
			content = ""; // Provide a default value or handle as needed
		}

		Files.write(Paths.get(filePath), content.getBytes());
	}

	private String readFileContent(Path path) throws IOException {
	    StringBuilder contentBuilder = new StringBuilder();
	    try (BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
	        char[] buffer = new char[8192]; // You can adjust the buffer size as needed
	        int charsRead;
	        while ((charsRead = reader.read(buffer)) != -1) {
	            contentBuilder.append(buffer, 0, charsRead);
	        }
	    }
	    return contentBuilder.toString();
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

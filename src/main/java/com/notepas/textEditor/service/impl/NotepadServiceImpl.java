package com.notepas.textEditor.service.impl;

import java.io.IOException;
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
			Files.createDirectories(Paths.get(FILES_DIRECTORY));
		} catch (IOException e) {
			throw new RuntimeException("Error creating files directory");
		}
	}

	@Override
	public List<NotepadFile> getAllFiles() {
		try {
			return Files.list(Paths.get(FILES_DIRECTORY))
					.map(path -> new NotepadFile(path.getFileName().toString(), readFileContent(path)))
					.collect(Collectors.toList());
		} catch (IOException e) {
			throw new RuntimeException("Error reading files from directory");
		}
	}

	@Override
	public NotepadFile getFile(String fileName) {
		try {
			String filePath = FILES_DIRECTORY + fileName;
			return new NotepadFile(fileName, readFileContent(Paths.get(filePath)));
		} catch (Exception e) {
			throw new RuntimeException("Error reading the file");
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

	private String readFileContent(Path path) {
		try {
			return Files.readString(path);
		} catch (IOException e) {
			throw new RuntimeException("Error reading file content", e);
		}
	}

	@Override
	public void deleteFile(String fileName) {
		String filePath = FILES_DIRECTORY + fileName;
		try {
			Files.deleteIfExists(Paths.get(filePath));
		} catch (IOException e) {
			throw new RuntimeException("Error deleting the file");
		}
	}
}

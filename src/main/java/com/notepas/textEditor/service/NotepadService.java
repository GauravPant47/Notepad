package com.notepas.textEditor.service;


import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.notepas.textEditor.model.NotepadFile;

public interface NotepadService {
    List<NotepadFile> getAllFiles();
    NotepadFile getFile(String fileName);
    void updateFile(NotepadFile notepadFile) throws IOException;
    void deleteFile(String fileName);
}
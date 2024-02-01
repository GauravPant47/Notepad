package com.notepas.textEditor;

import java.awt.*;
import javax.swing.*;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.web.WebView;

@SpringBootApplication
public class TextEditorApplication extends JFrame {
	private final JFXPanel jfxPanel = new JFXPanel();

	public static void main(String[] args) {
		SpringApplication.run(TextEditorApplication.class, args);

//		SwingUtilities.invokeLater(new Runnable() {
//			@Override
//			public void run() {
//				new TextEditorApplication().setVisible(true);
//			}
//		});
	}

//	public TextEditorApplication() {
//		super("Notepad Swing App");
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setSize(1920, 1080);
//		setLayout(new BorderLayout());
//
//		initJavaFX();
//
//		add(jfxPanel, BorderLayout.CENTER);
//	}

//	private void initJavaFX() {
//		Platform.runLater(() -> {
//			WebView webView = new WebView();
//			webView.getEngine().load("http://localhost:9091/notepad/");
//
//			jfxPanel.setScene(new Scene(webView));
//		});
//	}
}

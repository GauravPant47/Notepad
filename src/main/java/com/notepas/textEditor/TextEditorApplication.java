package com.notepas.textEditor;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.JFrame;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TextEditorApplication extends JFrame {
//	private final JFXPanel jfxPanel = new JFXPanel();

	public static void main(String[] args) {
		SpringApplication.run(TextEditorApplication.class, args);

//		SwingUtilities.invokeLater(new Runnable() {
//			@Override
//			public void run() {
//				new TextEditorApplication().setVisible(true);
//			}
//		});

		// Open default web browser
		openBrowser("http://localhost:8080/notepad/");
	}

//	public TextEditorApplication() {
//		super("Notepad Swing App");
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setSize(1920, 1080);
//		setLayout(new BorderLayout());
//
//		initJavaFX();
//		add(jfxPanel, BorderLayout.CENTER);
//	}
//
//	private void initJavaFX() {
//		Platform.runLater(() -> {
//			WebView webView = new WebView();
//			webView.getEngine().load("http://localhost:8080/notepad/");
//
//			jfxPanel.setScene(new Scene(webView));
//		});
//	}

	private static void openBrowser(String url) {
		try {
			if (Desktop.isDesktopSupported()) {
				Desktop desktop = Desktop.getDesktop();
				if (desktop.isSupported(Desktop.Action.BROWSE)) {
					desktop.browse(new URI(url));
				}
			} else {
				// For environments where Desktop is not supported
				String os = System.getProperty("os.name").toLowerCase();
				if (os.contains("win")) {
					Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + url);
				} else if (os.contains("nix") || os.contains("nux") || os.contains("mac")) {
					Runtime.getRuntime().exec("xdg-open " + url);
				}
			}
		} catch (IOException | URISyntaxException e) {
			e.printStackTrace();
		}
	}
}
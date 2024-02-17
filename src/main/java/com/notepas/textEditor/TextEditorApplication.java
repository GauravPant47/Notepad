package com.notepas.textEditor;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TextEditorApplication {
	public static void main(String[] args) {
		SpringApplication.run(TextEditorApplication.class, args);

		// Open default web browser
		openBrowser("http://localhost:9091/notepad/");
	}

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

package com.example.img_ocr;

import com.vaadin.Application;
import com.vaadin.ui.*;

/**
 * Main application class.
 */
public class Img_ocrApplication extends Application {

	@Override
	public void init() {
		Window mainWindow = new Window("Img_ocr Application");
		Label label = new Label("Hello Vaadin user");
		mainWindow.addComponent(label);
		setMainWindow(mainWindow);
	}

}


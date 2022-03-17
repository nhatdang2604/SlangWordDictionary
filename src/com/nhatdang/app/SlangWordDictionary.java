package com.nhatdang.app;

import com.nhatdang.view.IView;
import com.nhatdang.view.MenuView;

//The application
public class SlangWordDictionary {

	public SlangWordDictionary() {
		IView menu = MenuView.INSTANCE;
		menu.show();
	}
	
	//Run the application
	public void run() {
		//do nothing yet
	}
}

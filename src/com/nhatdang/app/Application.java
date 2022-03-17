package com.nhatdang.app;

import com.nhatdang.view.IView;
import com.nhatdang.view.MenuView;

//The application
public class Application{

	public Application() {
		IView menu = MenuView.INSTANCE;
		menu.show();
	}
	
	//Run the application
	//Return 0 when there are no error
	public int run() {
		//do nothing yet
		
		return 0;
	}
}

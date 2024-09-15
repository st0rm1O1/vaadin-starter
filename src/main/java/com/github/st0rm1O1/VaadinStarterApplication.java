package com.github.st0rm1O1;

import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.server.PWA;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@PWA(
		name = "My Progressive Web Application",
		shortName = "MyPWA",
		iconPath = "icons/icon.png",
		offlinePath = "offline.html",
		offlineResources = {"icons/icon.png", "offline.html"}
)
public class VaadinStarterApplication implements AppShellConfigurator {

	public static void main(String[] args) {
		SpringApplication.run(VaadinStarterApplication.class, args);
	}

}

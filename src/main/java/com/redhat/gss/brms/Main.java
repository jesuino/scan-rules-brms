package com.redhat.gss.brms;

import java.io.IOException;
import java.util.Date;
import java.util.Scanner;

import org.drools.compiler.kproject.ReleaseIdImpl;
import org.drools.core.impl.EnvironmentFactory;
import org.drools.core.io.impl.UrlResource;
import org.kie.api.KieServices;
import org.kie.api.builder.KieScanner;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

public class Main {

	private static final String URL = "http://bpms-wsiqueir.rhcloud.com/business-central/maven2";
	private static final String GROUP_ID = "com.redhat.gss.brms";
	private static final String ARTIFACT_ID = "my-rules-xpaas";
	private static final String VERSION = "LATEST";

	// AsnN_luuDW-E

	static private KieContainer kieContainer;

	public static void main(String[] args) throws IOException {
		System.setProperty("M2_HOME", "/opt/maven/apache-maven-3.1.1/");
		
		KieServices kieServices = KieServices.Factory.get();
	
		UrlResource urlResource = (UrlResource) kieServices.getResources()
				.newUrlResource(URL);
		urlResource.setUsername("bpm-admin");
		urlResource.setPassword("AsnN_luuDW-E");
		urlResource.setBasicAuthentication("enabled");

		
		ReleaseIdImpl releaseId = new ReleaseIdImpl(GROUP_ID, ARTIFACT_ID,
				VERSION);
		kieContainer = kieServices.newKieContainer(releaseId);
		KieScanner kScanner = kieServices.newKieScanner(kieContainer);
		kScanner.start(1000);
		kScanner.scanNow();
		System.out.println("== Executing scanned rules ==");
		Scanner sc = new Scanner(System.in);
		String in = "";
		for (;;) {
			if (in.length() > 0 && in.toLowerCase().charAt(0) == 'q') {
				break;
			} else {
				fireRules();
				System.out.println("Rules fired at: " + new Date());
				System.out
						.println("Press enter to continue or enter q to exit");
			}
			in = sc.nextLine();
		}
		System.out.println("Quiting...");
		sc.close();

	}

	public static void fireRules() {
		KieSession kieSession = kieContainer.newKieSession(EnvironmentFactory
				.newEnvironment());
		kieSession.fireAllRules();
		kieSession.destroy();
	}

}
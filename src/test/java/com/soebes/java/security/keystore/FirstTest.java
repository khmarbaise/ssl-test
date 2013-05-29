package com.soebes.java.security.keystore;

import static org.fest.assertions.Assertions.assertThat;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;

import org.testng.annotations.Test;

public class FirstTest extends TestBase {

	@Test
	public void firstTest() {
		assertThat(true).isTrue();
	}

	@Test
	public void firstSSLTest() throws KeyStoreException,
			NoSuchAlgorithmException, CertificateException, IOException {
		
		File keyStoreFile = new File(getTestResourcesDirectory(), "keystore.pkcs12");

		System.out.println("File:" + keyStoreFile);
//		char[] password = "password".toCharArray();
		String alias = "cn";

		FileInputStream keyStoreInputStream = new FileInputStream(keyStoreFile);
		KeyStore keystore = KeyStore.getInstance("pkcs12");
		
		keystore.load(keyStoreInputStream, null);

		System.out.println("Type:" + keystore.getType());
		Certificate cert = keystore.getCertificate(alias);
		
		System.out.println(cert);
	}
}

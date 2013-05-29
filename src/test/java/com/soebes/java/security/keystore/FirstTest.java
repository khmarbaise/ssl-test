package com.soebes.java.security.keystore;

import static org.fest.assertions.Assertions.assertThat;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.Key;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.util.Enumeration;

import org.testng.annotations.Test;

public class FirstTest extends TestBase {

	public static final char[] NO_PASSWORD = "".toCharArray();
	public static final String KEY_STORE_TYPE = "PKCS12";

	@Test
	public void firstTest() {
		assertThat(true).isTrue();
	}

	@Test
	public void firstSSLTest() throws KeyStoreException,
			NoSuchAlgorithmException, CertificateException, IOException, UnrecoverableKeyException {
		
		File keyStoreFile = new File(getTestResourcesDirectory(), "keystore.pkcs12");

		System.out.println("File:" + keyStoreFile);
//		char[] password = "password".toCharArray();

		FileInputStream keyStoreInputStream = new FileInputStream(keyStoreFile);
		KeyStore keystore = KeyStore.getInstance(KEY_STORE_TYPE);
		
		keystore.load(keyStoreInputStream, NO_PASSWORD);

		Enumeration<String> aliases = keystore.aliases();

		for ( ; aliases.hasMoreElements();) {
			String item = aliases.nextElement();
			System.out.println("Alias: '" + item + "'");
		}

		String certificateAlias = "cn";

		assertThat(keystore.containsAlias(certificateAlias)).isTrue();
		assertThat(keystore.isKeyEntry(certificateAlias)).isTrue();
//		assertThat(keystore.isCertificateEntry(certificateAlias)).isTrue();
		Certificate cert = keystore.getCertificate(certificateAlias);
		
		System.out.println("Certificate:");
		System.out.println("   Type: " + keystore.getType());
		System.out.println("   Cert: " + cert);
		
		Provider provider = keystore.getProvider();
		
		System.out.println("Provider:");
		System.out.println("     Name: " + provider.getName());
		System.out.println("     Info: " + provider.getInfo());
		System.out.println("  Version: " + provider.getVersion());

		Key key = keystore.getKey(certificateAlias, NO_PASSWORD);
		System.out.println("Key:");
		System.out.println("  " + key.getFormat());
		System.out.println("  " + key.getAlgorithm());
		
		
//		Certificate[] certificateChain = keystore.getCertificateChain(certificateAlias);
//		for (int i = 0; i < certificateChain.length; i++) {
//			System.out.println("C: " + i + " " + );
//		}
	}
}

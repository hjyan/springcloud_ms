package com.hongdian.sys.auth.test;

import java.io.File;
import java.io.FileOutputStream;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;

import org.junit.Test;

public class KeyGenerator {
	
	@Test
	public void generateKey() throws Exception {
		KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
		SecureRandom random = new SecureRandom("asdwfcvc".getBytes());
		generator.initialize(512, random);
		KeyPair keyPair = generator.generateKeyPair();
		String pubKeyFilePath = "pubKey.key";
		String priKeyFilePath = "priKey.key";
		PublicKey pub = keyPair.getPublic();
		PrivateKey pri = keyPair.getPrivate();
		FileOutputStream fos = new FileOutputStream(new File(pubKeyFilePath));
		fos.write(pub.getEncoded());
		fos.close();
		fos = new FileOutputStream(new File(priKeyFilePath));
		fos.write(pri.getEncoded());
		fos.close();
	}
}

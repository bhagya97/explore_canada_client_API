package com.example.ExploreCanada;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ExploreCanada1Application {

	public static void main(String[] args) {
		SpringApplication.run(ExploreCanada1Application.class, args);
		final String secretKey = "helloworldddd!!!!";
	     
	    String originalString = "ExploreCanada";
	    String encryptedString= EncryptDecrypt.encrypt(originalString, secretKey);
	    String decryptedString = EncryptDecrypt.decrypt(encryptedString, secretKey); 
	    System.out.println(originalString);
	    System.out.println(encryptedString);
	    System.out.println(decryptedString);
	}

}

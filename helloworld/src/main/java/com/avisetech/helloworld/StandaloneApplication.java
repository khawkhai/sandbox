package com.avisetech.helloworld;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.nio.ByteBuffer;
import java.util.UUID;

@SpringBootApplication
public class StandaloneApplication {

//	public static void main(String[] args) {
//		SpringApplication.run(HelloworldApplication.class, args);
//	}
	private static ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES * 2);

	public static void main(String[] args) {
//		final String uri = "http://:4000/v1/test/"+123;
//
//		Client client = Client.create();
//		WebResource webResource = client.resource(uri);
//
//		ClientResponse response = webResource.type("application/json")
//			.put(ClientResponse.class, "asd");

		long l1 = -2251799813706272L;
		long l2 = -4214599845706145L;
//		byte[] b1 = buffer.putLong(0, l1).array();
//		byte[] b2 = buffer.putLong(0, l2).array();

//		UUID u = UUID.nameUUIDFromBytes(buffer.putLong(l1).putLong(l2).array());
		UUID u = new UUID(l1,l2);
		System.out.println("UUID : " + u);


		System.out.println("L1 : " + u.getMostSignificantBits());
		System.out.println("L2 : " + u.getLeastSignificantBits());
	}
}

package com.benclaus.koperasi.utility;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Base64UrlEncoder {

	public static void main(String[] args) {
		String message = "This will be encoded using URL and Filename safe Base64 Alphabet!";
		System.out.println("Original: " + message);

		byte[] bytesToEncode = message.getBytes(StandardCharsets.UTF_8);

		byte[] encoded = encode(bytesToEncode);

		decode(encoded);
		
		String m2 = "7;701002;11/21/2016";
		bytesToEncode = m2.getBytes(StandardCharsets.UTF_8);
		
		encoded = encode(bytesToEncode);
		decode(encoded);
		decode("Nzs3MDEwMDI7MTEvMjMvMjAxNjtTVVJZQTtsYW1ib2suc2lhbnR1cmlAZ3VudW5nc2V3dS5jb20=".getBytes());
	}

	public static byte[] encode(byte[] bytesToEncode) {
		Base64.Encoder encoder = Base64.getUrlEncoder();
		byte[] encoded = encoder.encode(bytesToEncode);
		System.out.println("Encoded: " + new String(encoded));
		return encoded;
	}

	public static byte[] decode(byte[] encoded) {
		Base64.Decoder decoder = Base64.getUrlDecoder();
		byte[] decoded = decoder.decode(encoded);
		System.out.println("Decoded: " + new String(decoded));
		return decoded;
	}
}

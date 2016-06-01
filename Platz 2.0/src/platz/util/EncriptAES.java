package platz.util;

import javax.crypto.spec.SecretKeySpec;
import javax.crypto.spec.IvParameterSpec;

import javax.crypto.Cipher;

public class EncriptAES {

	// Vetor de inicialização
	static String IV = "1001HUAKKKRSRS9Z";

	static String textopuro = "senha";

	// Chave de encriptação
	private static String chaveEncriptacao = "Dnk4zr59FMA7na15";

	public byte[] encrypt(String textopuro, String chaveencriptacao) throws Exception {
		Cipher encripta = Cipher.getInstance("AES/CBC/PKCS5Padding", "SunJCE");
		SecretKeySpec key = new SecretKeySpec(chaveencriptacao.getBytes("UTF-8"), "AES");
		encripta.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(IV.getBytes("UTF-8")));
		return encripta.doFinal(textopuro.getBytes("UTF-8"));
	}

	public String decrypt(byte[] textoencriptado, String chaveencriptacao) throws Exception {
		Cipher decripta = Cipher.getInstance("AES/CBC/PKCS5Padding", "SunJCE");
		SecretKeySpec key = new SecretKeySpec(chaveencriptacao.getBytes("UTF-8"), "AES");
		decripta.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(IV.getBytes("UTF-8")));
		return new String(decripta.doFinal(textoencriptado), "UTF-8");
	}

	public String byteParaString(byte[] hex) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < hex.length; i++) {
			sb.append(Integer.toString((hex[i] & 0xff) + 0x100, 16).substring(1));
		}
		return sb.toString();
	}

	public byte[] stringParaByte(String s) {
		int len = s.length();
		byte[] data = new byte[len / 2];
		for (int i = 0; i < len; i += 2) {
			data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character.digit(s.charAt(i + 1), 16));
		}
		return data;
	}

	public static void main(String[] args) {

		try {

			EncriptAES aes = new EncriptAES();

			System.out.println("Texto Original: " + textopuro);

			byte[] textoEmBytesEncriptados = aes.encrypt(textopuro, getChaveEncriptacao());

			String textoEncriptado = aes.byteParaString(textoEmBytesEncriptados);

			System.out.println("Texto Encriptado: " + textoEncriptado + "Tamanho:" + textoEncriptado.length());

			String textoDescriptado = aes.decrypt(textoEmBytesEncriptados, getChaveEncriptacao());

			System.out.println("Texto Descriptado: " + textoDescriptado);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String getChaveEncriptacao() {
		return chaveEncriptacao;
	}

	public static void setChaveEncriptacao(String chaveEncriptacao) {
		EncriptAES.chaveEncriptacao = chaveEncriptacao;
	}

}

package presenter;

import test.Client;

import javax.net.ServerSocketFactory;
import javax.net.ssl.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ServerSocket;
import java.security.KeyStore;
import java.security.SecureRandom;

import static java.lang.System.exit;

/**
 * Factory class for loading keystore and making TLS ServerSocket
 */
public class TlsSocketFactory {
	private static TlsSocketFactory me = null;
	private SSLServerSocketFactory ssf;
	private SSLContext sslContext;

	private TlsSocketFactory() {
		try {
			FileInputStream fis = new FileInputStream(System.getProperty("user.home") + "/bstore.jks");
			char[] pass = "bridge".toCharArray();
			KeyStore keyStore = KeyStore.getInstance("JKS");
			keyStore.load(fis, pass);

			KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
			kmf.init(keyStore, pass);

			TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
			tmf.init(keyStore);

			sslContext = SSLContext.getInstance("TLSv1.2");
			sslContext.init(kmf.getKeyManagers(), tmf.getTrustManagers(), new SecureRandom());

			ssf = sslContext.getServerSocketFactory();
		} catch(Exception e) {
			e.printStackTrace();
			exit(1);
		}
	}

	public static TlsSocketFactory getInstance() {
		if(me == null) {
			me = new TlsSocketFactory();
		}
		return me;
	}

	public ServerSocket getServerSocket(int port) {
		try {
			return ssf.createServerSocket(port);
		} catch(IOException e) {
			e.printStackTrace();
			exit(1);
		}
		return null;
	}

	public test.Client getTestClient() {
		SSLSocketFactory factory = sslContext.getSocketFactory();
		try {
			return new Client(factory.createSocket("localhost", 3001));
		} catch(IOException e) {
			e.printStackTrace();
			exit(1);
		}
		return null;
	}
}

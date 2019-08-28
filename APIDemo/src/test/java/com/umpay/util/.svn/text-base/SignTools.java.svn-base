package com.umpay.util;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.spec.PKCS8EncodedKeySpec;


public class SignTools {
	public static byte[] getFileContent(String fileName) {
		byte data[] = (byte[]) null;
		FileInputStream fis = null;
		try {
			File f = new File(fileName);
			data = new byte[(int) f.length()];
			fis = new FileInputStream(f);
			fis.read(data);
			fis.close();
		} catch (IOException e) {
			return null;
		}
		return data;
	}

	/**
	 * 对报文签名进行验签
	 * 
	 * @param signStr
	 *            拼装后未加签名串
	 * @param signKey
	 *            通信传入签名
	 * @return boolean 验签结果
	 */
	public static boolean doCheckSign(String certFilePath, String certFileName, String signStr, String signKey) {
		boolean verify = true;
		try {
			// 从配置文件获取是否验签
			verify = checkSign(certFilePath, certFileName, signStr, signKey);
		} catch (Exception e) {
			verify = false;
			System.out.println("验签异常！");
		}
		return verify;
	}

	private static boolean checkSign(String certFilePath, String certFileName, String signStr, String sign)
			throws Exception {
		try {
			System.out.println("# 开始验证签名信息...");
			String keyFile = certFilePath + certFileName;
			System.out.println("平台公钥地址为： " + keyFile);
			if (StringUtil.isEmpty(certFilePath) || StringUtil.isEmpty(certFileName)) {
				throw new Exception("获取公钥地址失败!!!");
			}

			byte[] certData = getFileContent(keyFile);

			if (certData == null) {
				throw new Exception("获取证书失败!!!");
			}

			X509Certificate cert = genCertificate(certData);

			byte[] signData = Base64.decode(sign.getBytes());
			System.out.println("待签名字符串为： " + signStr);
			System.out.println("第三方平台签名值为： " + sign);
			return verify(cert, signStr.getBytes("UTF-8"), signData);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("验证签名信息异常");
		}
	}

	public static PrivateKey genPrivateKey(byte[] key) {
		// get the private key
		PrivateKey pk = null;
		try {
			PKCS8EncodedKeySpec peks = new PKCS8EncodedKeySpec(key);
			KeyFactory kf = KeyFactory.getInstance("RSA");
			pk = kf.generatePrivate(peks);
		} catch (Exception e) {
			return null;
		}
		return pk;
	}

	public static byte[] sign(PrivateKey pk, byte[] data) {
		// get the signature
		byte[] sb = null;
		try {
			Signature sig = Signature.getInstance("SHA1withRSA");
			sig.initSign(pk);
			sig.update(data);
			sb = sig.sign();
		} catch (Exception e) {
			return null;
		}
		return sb;
	}

	public static X509Certificate genCertificate(byte[] certData) {
		// load the cert
		ByteArrayInputStream bais = new ByteArrayInputStream(certData);
		X509Certificate cert = null;
		try {
			CertificateFactory cf = CertificateFactory.getInstance("X.509");
			cert = (X509Certificate) cf.generateCertificate(bais);
		} catch (Exception e) {
			return null;
		}
		return cert;
	}

	public static boolean verify(X509Certificate cert, byte[] plain, byte[] signData) {
		try {
			Signature sig = Signature.getInstance("SHA1withRSA");
			sig.initVerify(cert);
			sig.update(plain);
			return sig.verify(signData);
		} catch (Exception e) {
			return false;
		}
	}
}

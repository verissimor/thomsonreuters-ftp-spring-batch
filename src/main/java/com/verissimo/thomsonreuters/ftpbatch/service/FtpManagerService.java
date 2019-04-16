package com.verissimo.thomsonreuters.ftpbatch.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import org.apache.commons.net.ftp.FTPClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class FtpManagerService {

	private Logger LOG = LoggerFactory.getLogger(FtpManagerService.class);

	@Value("${config.ftp.host}")
	private String configFtpHost;

	@Value("${config.ftp.user}")
	private String configFtpUser;

	@Value("${config.ftp.pass}")
	private String configFtpPass;

	public Path download(final String filename) {
		Path local = getFileTempName(filename);

		try (OutputStream os = new FileOutputStream(local.toString())) {
			downloadFtpFile(filename, os);
			return local;
		} catch (IOException e) {
			LOG.error(e.getMessage());
			throw new IllegalArgumentException(e.getMessage());
		} 
	}

	public void delete(final String filename) {

		FTPClient client = new FTPClient();
		try {
			client.connect(configFtpHost);
			client.login(configFtpUser, configFtpPass);
			client.enterLocalPassiveMode();

			if (!client.deleteFile(filename)) {
				String msg = "Error delete the file by ftp. " + client.getReplyString();
				LOG.error(msg);
				throw new IllegalArgumentException(msg);
			}
		} catch (IOException e) {
			LOG.error(e.getMessage());
			throw new IllegalArgumentException(e.getMessage());
		} finally {
			try {
				client.disconnect();
			} catch (IOException e) {
				LOG.error(e.getMessage());
				throw new IllegalArgumentException(e.getMessage());
			}
		}
	}

	private void downloadFtpFile(final String filename, OutputStream os) {

		LOG.info("ftp conection: " + configFtpHost + " " + configFtpUser + " " + "configFtpPass");

		FTPClient client = new FTPClient();
		try {
			client.connect(configFtpHost);
			client.login(configFtpUser, configFtpPass);
			client.enterLocalPassiveMode();

			if (!client.retrieveFile(filename, os)) {
				String msg = "Error download the file by ftp. " + client.getReplyString();
				LOG.error(msg);
				throw new IllegalArgumentException(msg);
			}
		} catch (IOException e) {
			LOG.error(e.getMessage());
			throw new IllegalArgumentException(e.getMessage());
		} finally {
			try {
				client.disconnect();
			} catch (IOException e) {
				LOG.error(e.getMessage());
				throw new IllegalArgumentException(e.getMessage());
			}
		}
	}

	private Path getFileTempName(final String filename) {
		try {
			Path local = Files.createTempDirectory("ftp-temp-").resolve(filename);
			return local;
		} catch (IOException e) {
			LOG.error(e.getMessage());
			throw new IllegalArgumentException(e.getMessage());
		}

	}

}

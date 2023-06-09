package com.bolsadeideas.springboot.app.models.service;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.slf4j.Logger;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadFileServiceImpl implements IUploadFileService {

	private final Logger log = org.slf4j.LoggerFactory.getLogger(getClass());

	@Override
	public Resource load(String filename) throws MalformedURLException {

		Path pathFoto = getPath(filename);
		log.info("filename: ");
		log.info("pathFoto: " + pathFoto);

		Resource recurso = null;

		recurso = new UrlResource(pathFoto.toUri());
		if (!recurso.exists() || !recurso.isReadable()) {
			throw new RuntimeException("Error no se puede cargar la imagen: " + pathFoto.toString());
		}

		return recurso;

	}

	@Override
	public String copy(MultipartFile file) throws IOException {
		String uniqueFilename = UUID.randomUUID().toString() + " " + file.getOriginalFilename();

		Path rootPath = getPath(uniqueFilename);

		log.info("rootPath: " + rootPath);
		/*
		 * byte[] bytes = foto.getBytes(); Path rutaComleta = Paths.get(rootPath + "//"
		 * + foto.getOriginalFilename()); Files.write(rutaComleta, bytes);
		 */
		// Esta aqui el error
		Files.copy(file.getInputStream(), rootPath);

		return uniqueFilename;
	}

	@Override
	public boolean delete(String filename) {

		Path rootPath = getPath(filename);
		File archivo = rootPath.toFile();
		
		if(archivo.exists() && archivo.canRead())
		{
			if(archivo.delete())
			{
				return true;
			}
		}
		return false;
	}

	public Path getPath(String filename) {
		return Paths.get("uploads").resolve(filename).toAbsolutePath();
	}

	@Override
	public void deleteAll() {
		FileSystemUtils.deleteRecursively(Paths.get("uploads").toFile());
		
	}

	@Override
	public void init() throws IOException {
		// TODO Auto-generated method stub
		Files.createDirectory(Paths.get("uploads"));
	}

}

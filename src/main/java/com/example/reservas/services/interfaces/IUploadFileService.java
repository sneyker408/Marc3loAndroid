package com.example.reservas.services.interfaces;
//IMPORTACIONES
	import java.io.IOException;
	import java.nio.file.Path;
	import org.springframework.web.multipart.MultipartFile;
//CLASE 
	public interface IUploadFileService {
		
		String copyFile(MultipartFile file) throws IOException;
		
		boolean deleteFile(String fileName);
		  
		public Path getPath(String fileName);

	}


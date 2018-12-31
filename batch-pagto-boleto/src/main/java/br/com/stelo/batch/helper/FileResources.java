package br.com.stelo.batch.helper;

import java.io.File;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

public class FileResources {
	public static Resource[] inputResources(String input_location, String suffix) throws Exception {
		Resource resource = new FileSystemResource(input_location);
		File[] files = resource.getFile().listFiles();

		if (files == null || files.length == 0) {
			return null;
		}

		Resource[] resourceArray = new Resource[files.length];

		for (int i = 0; i < files.length; i++) {
			String name = files[i].getName().substring(files[i].getName().lastIndexOf(".") + 1);

			if (!suffix.equalsIgnoreCase(name)) {
				continue;
			}

			resourceArray[i] = new FileSystemResource(files[i].getPath());
		}
		return resourceArray;
	}
}

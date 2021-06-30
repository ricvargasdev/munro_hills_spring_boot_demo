package com.xdesign.munro.starter;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.xdesign.munro.model.Munro;
import com.xdesign.munro.repository.MunroRepository;

/**
 * 
 * @author Ricardo Vargas
 */
@Component
public class Bootstrap implements InitializingBean {
	
	@Autowired
	ResourceLoader resourceLoader;
	
	@Autowired
	MunroRepository repository;
	
	@Override
	public void afterPropertiesSet() throws Exception {
		String fileName = "classpath:csv/munrotab.csv";
		Reader reader;

		try {
			Resource resource = resourceLoader.getResource(fileName);
			reader = Files.newBufferedReader(Paths.get(resource.getURI()), Charset.forName("UTF-8"));
			// create csv bean reader
		    CsvToBean csvToBean = new CsvToBeanBuilder(reader)
		    		.withSkipLines(1)
		            .withType(Munro.class)
		            .build();

		    for (Munro munro : (Iterable<Munro>) csvToBean) {
		    	repository.save(munro);
		    }

//		    System.out.println("TOTAL: " + repository.count());
		    
		    // close the reader
		    reader.close();

		} catch (IOException e) {
			System.out.println("Error reading the input file: " + fileName);
			e.printStackTrace();
		}
		
	}

}

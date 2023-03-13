package com.admi.MiniPorject;

import com.admi.MiniPorject.models.MaterialOrder;
import com.admi.MiniPorject.models.Person;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.hibernate.tool.schema.internal.exec.ScriptTargetOutputToFile;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.Path;
import java.util.List;

@SpringBootApplication
public class MiniPorjectApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(MiniPorjectApplication.class, args);
//		String [] strs = {"reflower","flow","flight"};
//		System.out.println(longestCommonPrefix(strs));
	}

	public static String longestCommonPrefix(String[] strs) {

		String shortString = strs[0];
		int i=1;
		while (i< strs.length) {
			if(strs[i].length() < shortString.length())
				shortString = strs[i];
			i++;
		}
		StringBuilder builder = new StringBuilder(shortString);
		for(i=0;i< strs.length;i++) {
			for(int j=0;j< shortString.length();j++) {
				if(strs[i].charAt(j) != shortString.charAt(j)){
					builder = new StringBuilder(builder.substring(0,j));
					break;
				}
			}
			if(builder.length() == 0)
				break;


		}


		return builder.toString();
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
			return application.sources(MiniPorjectApplication.class);
	}
}

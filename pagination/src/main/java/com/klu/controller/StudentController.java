package com.klu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.klu.model.StudentManager;

@RestController
@RequestMapping("/student")
public class StudentController 
{
	@Autowired
	StudentManager M;
	
	@GetMapping("/data/{cpage}/{psize}")
	public String data(@PathVariable("cpage") int cpage,@PathVariable("psize") int psize)
	{
		return M.getData(cpage, psize).toString();
	}
	
	@GetMapping("/paging/{cpage}/{psize}")
	public String paging(@PathVariable("cpage") int cpage,@PathVariable("psize") int psize)
	{
		return M.getPaging(cpage, psize).toString();
	}
}

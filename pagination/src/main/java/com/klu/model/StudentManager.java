package com.klu.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.klu.entity.Student;
import com.klu.repository.StudentRepository;

@Service
public class StudentManager 
{
	@Autowired
	StudentRepository R;
	
	//Fetch Records
	public List<String> getData(int cpage, int psize)
	{
		List<String> list = new ArrayList<String>();
		for(Student S : R.findAll(PageRequest.of(cpage - 1, psize)))
		{
			list.add(toJSON(S));
		}
		return list;
	}
	
	//Paging
	public List<String> getPaging(int cpage, int psize)
	{
		int totRecords = R.totRecords();
		int pageCount = (totRecords / psize) + (totRecords % psize > 0 ? 1 : 0);
		int startPage = 1;
		int endPage = pageCount;
		
		List<String> list = new ArrayList<String>();
		for(int page=startPage; page<=endPage; page++)
		{
			PageData P = new PageData(page, page==cpage);
			list.add(toJSON(P));
		}
		return list;
	}
	
	//Convert JAVA object to JSON
	public String toJSON(Object obj)
	{
		GsonBuilder GB = new GsonBuilder();
		Gson G = GB.create();
		return G.toJson(obj);
	}
}

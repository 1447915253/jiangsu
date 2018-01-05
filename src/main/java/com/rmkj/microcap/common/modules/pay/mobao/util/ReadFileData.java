package com.rmkj.microcap.common.modules.pay.mobao.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadFileData {
	
	
	private  String  FilePath;
	private  String  FileReg;
	
	public ReadFileData(String filePath, String fileReg) {
		super();
		FilePath = filePath;
		FileReg = fileReg;
	}
	
	public ReadFileData(String filePath) {
		super();
		FilePath = filePath;
	}


	public String getFilePath() {
		return FilePath;
	}
	public void setFilePath(String filePath) {
		FilePath = filePath;
	}
	public String getFileReg() {
		return FileReg;
	}
	public void setFileReg(String fileReg) {
		FileReg = fileReg;
	}
	
	
	public  List<Amt>  readTxt() throws IOException{
		List<Amt>  amtlist= new ArrayList<Amt>();
		BufferedReader bf= new BufferedReader(new FileReader(this.FilePath));
		int  i=0;
		String  bff;
		
		while((bff=bf.readLine())!=null){
			Amt  at= new Amt();
			System.out.println(bff);
			String[] pans=bff.split(",");
			at.setPan(pans[0].trim());
			at.setAmt(pans[1].trim());
			at.setCerNo(pans[2].trim());
			at.setMb(pans[3].trim());
			at.setName(pans[4].trim());
			amtlist.add(at);
			
		}
		return  amtlist;
	}
}

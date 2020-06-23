package MyWebTester;

import java.io.*;
import java.util.Scanner;
import java.util.HashMap;

public class CodeFile {
	private HashMap <String,Integer> attrValue;    //储存结构，按照文档中的排序顺序 attribute value
	String filePath;
	public static void main(String avgs[]){

	}
	public CodeFile(String path)
	{
		filePath = path;
		attrValue =new HashMap<String,Integer>();
		try {
			Scanner  reader = new Scanner(new FileReader(filePath)); //按字符读取
			while(reader.hasNext()){
				String word = reader.next();
				word=word.replace("<"," <");
				word=word.replace(">","> ");
				String split[] =word.split(" ");
				for(int i=0;i<split.length;i++) {
					if (!attrValue.containsKey(split[i])) {
						attrValue.put(split[i], 1);
					} else {
						int count = attrValue.get(split[i]);
						++count;
						attrValue.replace(split[i], count);
					}
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		//System.out.println("Read:\n"+filePath);
	}

	public double compare(CodeFile anoFile) {
		double numerator =0, denominator = 0, anoDenominator=0;
		//分子ab
		for(String attr :attrValue.keySet()) {
			numerator += attrValue.get(attr) * anoFile.indexOf(attr);
		}
		//分母a
		for(int attr:attrValue.values()) {
			denominator += attr * attr;
		}
		//分母b
		for(int attr:anoFile.attrValue.values()) {
			anoDenominator += attr * attr;
		}
		denominator = Math.sqrt(denominator);
		anoDenominator = Math.sqrt(anoDenominator);
		double res = numerator/(denominator*anoDenominator);
		if(res>1){
			res=1.0;
		}
		return res;
	}

	public int indexOf(String key) {
		if (attrValue.containsKey(key)) {
			return attrValue.get(key);
		}
		else {
			return 0;
		}
	}
}

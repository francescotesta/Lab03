package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Dictionary {
	
	List<String> lista;
	public Dictionary() {
		lista = new ArrayList<>();
	}

	public boolean loadDictionary(String language) {
		
		try {
			FileReader fr = null;
			if(language.equals("Italian")) {
			    fr = new FileReader("src/main/resources/Italian.txt");
			} else if(language.equals("English")) {
				fr = new FileReader("src/main/resources/Italian.txt");
			}
			BufferedReader br = new BufferedReader(fr);
			String word;
			
			while((word = br.readLine()) != null) {
				lista.add(word.toLowerCase());
			}
			Collections.sort(lista);
			return true;
		
			
		}catch(IOException e) {
			return false;
		}
		
		
	}
	
	public List<RichWord> spellCheckText(List<String> inputTextList){
		List<RichWord> res = new ArrayList<>();
		
		for(String s:inputTextList) {
			RichWord r;
			if(lista.contains(s.toLowerCase())){
				r = new RichWord(s);
				r.setCorretto();
			}else
				r = new RichWord(s);
			res.add(r);
		}
		
		
		return res;
		
	}

	public List<RichWord> spellCheckTextLinear(List<String> input) {
		List<RichWord> res = new ArrayList<>();
		
		for(String s:input) {
			
			for(String d:lista) {
				if(s.equals(d)) {
					RichWord r = new RichWord(s);
					r.setCorretto();
					res.add(r);
				}
			}
		}
		
		return res;
	}
}

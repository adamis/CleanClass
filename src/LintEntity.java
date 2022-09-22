import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class LintEntity {
	
	
	public void run(File fileTemp) {
		try {			
			List<String> readTxtList = Utils.readTxtList(fileTemp.getAbsolutePath());
			List<String> tempFile = new ArrayList<>();
			List<String> newFile = new ArrayList<>();
			List<String> finalFile = new ArrayList<>();
			List<Integer> listRemover = new ArrayList<>();
			
			List<List<String>> llAnotation = new ArrayList<>();
			
			boolean cont = false;
			
			String nameFile = capt(fileTemp.getName()).replace(".java", "");
			System.err.println(nameFile);
			
			for (int i = 0; i < readTxtList.size(); i++) {
				
				//CONSTRUTOR
				if(readTxtList.get(i).contains("public "+nameFile)){
					cont = true;
				}
				
				if(cont) {
					
					listRemover.add(i);
					
					if(readTxtList.get(i).contains("}")) {
						cont = false;
					}
					
				}				
				
				//BUSCANDO AS ANOTAÇOES
				if((readTxtList.get(i).contains("public") && readTxtList.get(i).contains("get"))){
					int wCont = 1;
					
					List<String> lAnotation = new ArrayList<>();
			
					do {
						if(!readTxtList.get(i-wCont).trim().equals("")) {
							lAnotation.add(readTxtList.get(i-wCont));
						}
						listRemover.add(i-wCont);
						wCont++;
						
					}while (readTxtList.get(i-wCont).contains("@") || readTxtList.get(i-wCont).trim().equals(""));
					
					llAnotation.add(lAnotation);					
				}
				//GET
				if((readTxtList.get(i).contains("public") && readTxtList.get(i).contains("get"))){					
					listRemover.add(i);
					listRemover.add(i+1);
					listRemover.add(i+2);
					
				//SET	
				}else if((readTxtList.get(i).contains("public") && readTxtList.get(i).contains("set"))){
					listRemover.add(i);
					listRemover.add(i+1);
					listRemover.add(i+2);			
				}
				
			}
			
			//FILTRANDO O QUE VAI SER ESCRITO
			for (int i = 0; i < readTxtList.size(); i++) {
				boolean control = true;
				for (int j = 0; j < listRemover.size(); j++) {
					if(i == listRemover.get(j)) {
						control = false;
					}
				}
				
				if (control) {
					tempFile.add(readTxtList.get(i));	
				}			
				
			}
			
			
			
			//MONTANDO AS ANOTAÇOES
			int temp = 0;
			for (int i = 0; i < tempFile.size(); i++) {				
				
				
				
				if((readTxtList.get(i).contains("private") && !readTxtList.get(i).contains("static"))){
					
					List<String> list = llAnotation.get(temp);
					for (int j = list.size()-1; j >= 0 ; j--) {
						System.err.println("j: "+j);
						newFile.add(list.get(j));
					}
					
					temp++;
				}
				
				newFile.add(tempFile.get(i));
				
				if(readTxtList.get(i).contains("private")){
					newFile.add("");
				}
				
			}
		
			boolean lockImport = true;
			for (int i = 0; i < newFile.size(); i++) {		

				
				
				boolean control = true;
				
				if(newFile.get(i).trim().equals("") && newFile.get(i-1).trim().equals("")){
					control = false;
				}
				
				if(control) {
					finalFile.add(newFile.get(i));
				}
				
				//AJUSTANDO LOMBOK
				if(lockImport && newFile.get(i).trim().equals("") && newFile.get(i-1).contains("import")) {					
					finalFile.add("import lombok.Data;");
					finalFile.add("");
					lockImport = false;
				}
				
				if(newFile.get(i).contains("@Table")) {
					finalFile.add("@Data");
				}
			}
			
			
			Utils.writeTxtList(fileTemp.getAbsolutePath(), finalFile, false);
			
		} catch (Exception e) {			
			e.printStackTrace();
		}

	}
	
	public String capt(String text) {
		return (Character.toUpperCase(text.charAt(0)) + text.substring(1));
	}
	
}

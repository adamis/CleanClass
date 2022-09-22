import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Utils {

	public static String readTxt(String url) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(url));
		String text = "";
		while (br.ready()) {
			text += br.readLine();
		}
		br.close();
		return text;
	}

	public static List<String> readTxtList(String url) throws IOException {
		List<String> lista = new ArrayList<>();

		final BufferedReader br = new BufferedReader(new FileReader(url));

		while (br.ready()) {
			lista.add(br.readLine());
		}
		br.close();
		return lista;
	}

	public static void writeTxt(String file, String text, boolean replaceFile) throws Exception {

		file = file.trim();

//		System.err.println("File: " + file);
//		System.err.println("text: " + text);
//		System.err.println("Replace: " + replaceFile);

		new File(file).mkdirs();

		if (replaceFile && new File(file).exists()) {
			new File(file).delete();
		}

		// FileWriter arq = new FileWriter(file);
		PrintWriter gravarArq = new PrintWriter(new File(file), "UTF-8");

		// System.err.println(""+text);

		// gravarArq.printf(text);
		gravarArq.write(text);
		gravarArq.close();
	}

	public static void writeTxtList(String file, List<String> textList, boolean replaceFile) throws Exception {
		if (replaceFile && new File(file).exists()) {
			new File(file).delete();
		}
		FileWriter arq = new FileWriter(file);
		PrintWriter gravarArq = new PrintWriter(arq);

		for (int i = 0; i < textList.size(); i++) {
			System.err.println(""+textList.get(i));
			gravarArq.print(textList.get(i) + "\r\n");
		}
		arq.close();
	}

	
}

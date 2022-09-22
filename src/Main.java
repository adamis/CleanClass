import java.io.File;
import java.io.FilenameFilter;

public class Main {

	public static void main(String[] args) {
		
//		String path = "C:\\Users\\F5K5WQI\\Documents\\workspace-spring-tool-suite-4-4.10.0.RELEASE\\logistica-api\\src\\main\\java\\br\\com\\fiserv\\logistica\\entity\\";
		String path = "C:\\Users\\adamis.rocha\\Documents\\workspace-spring-tool-suite-4-4.10.0.RELEASE\\gerihotel\\src\\main\\java\\br\\com\\adamis\\gerihotel\\entity\\temp";
		File f = new File(path);

		FilenameFilter filter = new FilenameFilter() {
            @Override
            public boolean accept(File f, String name) {
                // We want to find only .c files
                return name.endsWith(".java");
            }
        };
        
        // Populates the array with names of files and directories
        File[] pathnames = f.listFiles(filter);

        // For each pathname in the pathnames array
        for (File pathname : pathnames) {
        	String name = pathname.getName();
        	
        	if(pathname.isFile()) {
        		System.out.println(path+name);
        		
        		LintEntity lintEntity = new LintEntity();
        		lintEntity.run(pathname);
//        		
//        		GenRepository process = new GenRepository();
//        		String packageRepository = "br.com.fiserv.logistica.repository";
//        		String packageEntity = "br.com.fiserv.logistica.entity";
//        		String src = "C:\\Users\\F5K5WQI\\Documents\\workspace-spring-tool-suite-4-4.10.0.RELEASE\\logistica-api\\src\\main\\java\\br\\com\\fiserv\\logistica\\repository\\";
//        		process.run(pathname,src,packageRepository,packageEntity);
        	}            
        }
		
		
//		Process process = new Process("c:/temp/Product.java");
//		process.run();
	}

}

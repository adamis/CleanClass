import java.io.File;
import java.util.List;
import java.util.Optional;

public class GenRepository {

	public enum KEYS  {		
		
		PACKAGE_REPOSITORY("{{packageRepository}}"),
		PACKAGE_ENTITY("{{packageEntity}}"), 
		ENTITY("{{entity}}");
		
		private String keysValue;

		KEYS(String value) {
			this.keysValue = value; 
		}
		
		public String getKeysValue() {
			return keysValue;
		}

		public void setKeysValue(String keysValue) {
			this.keysValue = keysValue;
		}
		
	}
	

	public void run(File pathEntity, String pathSrc, String packageRepository, String packageEntity) {
		
		try {

			String extension = "."+getExtensionByStringHandling(pathEntity.getAbsolutePath()).get();		
			String name = pathEntity.getName().replace(extension,"");
			String path = pathSrc+name+"Repository"+extension;		
			

			List<String> listFileRepo = Utils.readTxtList(new File("src/SampleRepository.txt").getAbsolutePath());
			
			for (int i = 0; i < listFileRepo.size(); i++) {				
				
				String newLine = listFileRepo.get(i)
				.replace(KEYS.PACKAGE_REPOSITORY.getKeysValue(), packageRepository)
				.replace(KEYS.ENTITY.getKeysValue(), name)
				.replace(KEYS.PACKAGE_ENTITY.getKeysValue(), packageEntity)				
				;				
				
				listFileRepo.set(i,newLine);
				
			}

			File fileRepository = new File(path);
			Utils.writeTxtList(fileRepository.getAbsolutePath(), listFileRepo, true);
		} catch (Exception e) {			
			e.printStackTrace();
		}
	}



	private Optional<String> getExtensionByStringHandling(String filename) {
		return Optional.ofNullable(filename)
				.filter(f -> f.contains("."))
				.map(f -> f.substring(filename.lastIndexOf(".") + 1));
	}
}

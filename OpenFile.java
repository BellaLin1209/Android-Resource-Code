import java.io.File;
import java.text.DecimalFormat;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;

public class OpenFile {

	static String ROOT = "C:\\Users\\bella_lin\\Desktop\\AutoParsingProject\\example";
	static Set<String> FolderSet = new LinkedHashSet<String>();

	
	private static void OpenFile(String ROOT) {
		// TODO Auto-generated method stub
		System.out.println("OpenFile enter ");
		ROOT = ROOT.replace("\\", "/").trim(); // 格式化路徑
		if (ROOT.lastIndexOf("/") != ROOT.length()) {
			ROOT = ROOT + "/";
		}
		File file = new File(ROOT);
		File[] files = file.listFiles();
		for (File r : files) {
			// 取得所有檔名
			if (r.getName().indexOf("-") != -1) {
				String ParentPath = r.getParent() + File.separator; // 根路徑
				String fileTag = r.getName().split("-")[0];// 日期+a 201812041155a
				String TagDate = fileTag.substring(0, r.getName().split("-")[0].length() - 1);// 只剩日期 201812041155
				createfolder(ParentPath + TagDate);
				remove(r.getPath(), ParentPath + TagDate + File.separator + r.getName());
				ROOT = ParentPath + TagDate + File.separator;
			} else {
				// 已有子路徑
				ROOT = r.getAbsolutePath() + File.separator;
			}
			FolderSet.add(ROOT);
		}

		file.delete();// 釋出記憶體
		files = null;
	}

	// 取得新路徑

	// 創建資料夾
	private static void createfolder(String newUrl) {
		File Folder = new File(newUrl);
		if (!Folder.exists()) {
			Folder.mkdirs();
		}
	}

	// 移動檔案
	private static void remove(String url, String newUrl) {

		File file = new File(url);
		file.renameTo(new java.io.File(newUrl));
	}

}

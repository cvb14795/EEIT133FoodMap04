package recipe.blob;

import com.azure.storage.file.share.*;

public class Test {
	// Define the connection-string.
	// Replace the values, including <>, with
	// the values from your storage account.
	public static final String connectStr = "DefaultEndpointsProtocol=https;" 
			+ "AccountName=storgefoodmap04;"
			+ "AccountKey=UIQW/1i5PSquSMx/m5kEthqK70gi1b7YYSqUadYq2A6fdUsWE8h1WQ/3JLoVHz7aLtpD3UmbfsaLqWu7TDZjBQ==;"
			+ "EndpointSuffix=core.windows.net";

	public static String shareName = "Team";

	// 建立檔案共用
	public static Boolean createFileShare(String connectStr, String shareName) {
		try {
			ShareClient shareClient = new ShareClientBuilder().connectionString(connectStr).shareName(shareName)
					.buildClient();

			shareClient.create();
			return true;
		} catch (Exception e) {
			System.out.println("createFileShare exception: " + e.getMessage());
			return false;
		}
	}

	// 刪除檔案共用
	public static Boolean deleteFileShare(String connectStr, String shareName) {
		try {
			ShareClient shareClient = new ShareClientBuilder().connectionString(connectStr).shareName(shareName)
					.buildClient();

			shareClient.delete();
			return true;
		} catch (Exception e) {
			System.out.println("deleteFileShare exception: " + e.getMessage());
			return false;
		}
	}

	// 上傳檔案
	public static Boolean uploadFile(String connectStr, String shareName, String dirName, String fileName) {
		try {
			ShareDirectoryClient dirClient = new ShareFileClientBuilder().connectionString(connectStr)
					.shareName(shareName).resourcePath(dirName).buildDirectoryClient();

			ShareFileClient fileClient = dirClient.getFileClient(fileName);
			fileClient.create(1024);
			fileClient.uploadFromFile(fileName);
			return true;
		} catch (Exception e) {
			System.out.println("uploadFile exception: " + e.getMessage());
			return false;
		}
	}

	// 下載檔案
	public static Boolean downloadFile(String connectStr, String shareName, String dirName, String destDir,
			String fileName) {
		try {
			ShareDirectoryClient dirClient = new ShareFileClientBuilder().connectionString(connectStr)
					.shareName(shareName).resourcePath(dirName).buildDirectoryClient();

			ShareFileClient fileClient = dirClient.getFileClient(fileName);

			// Create a unique file name
			String date = new java.text.SimpleDateFormat("yyyyMMdd-HHmmss").format(new java.util.Date());
			String destPath = destDir + "/" + date + "_" + fileName;

			fileClient.downloadToFile(destPath);
			return true;
		} catch (Exception e) {
			System.out.println("downloadFile exception: " + e.getMessage());
			return false;
		}
	}

	// 刪除檔案
	public static Boolean deleteFile(String connectStr, String shareName, String dirName, String fileName) {
		try {
			ShareDirectoryClient dirClient = new ShareFileClientBuilder().connectionString(connectStr)
					.shareName(shareName).resourcePath(dirName).buildDirectoryClient();

			ShareFileClient fileClient = dirClient.getFileClient(fileName);
			fileClient.delete();
			return true;
		} catch (Exception e) {
			System.out.println("deleteFile exception: " + e.getMessage());
			return false;
		}
	}

	// 建立目錄
	public static Boolean createDirectory(String connectStr, String shareName, String dirName) {
		try {
			ShareDirectoryClient dirClient = new ShareFileClientBuilder().connectionString(connectStr)
					.shareName(shareName).resourcePath(dirName).buildDirectoryClient();

			dirClient.create();
			return true;
		} catch (Exception e) {
			System.out.println("createDirectory exception: " + e.getMessage());
			return false;
		}
	}

	// 刪除目錄
	public static Boolean deleteDirectory(String connectStr, String shareName, String dirName) {
		try {
			ShareDirectoryClient dirClient = new ShareFileClientBuilder().connectionString(connectStr)
					.shareName(shareName).resourcePath(dirName).buildDirectoryClient();

			dirClient.delete();
			return true;
		} catch (Exception e) {
			System.out.println("deleteDirectory exception: " + e.getMessage());
			return false;
		}
	}

	// 列舉 Azure 檔案共用的檔案和目錄
	public static Boolean enumerateFilesAndDirs(String connectStr, String shareName, String dirName) {
		try {
			ShareDirectoryClient dirClient = new ShareFileClientBuilder().connectionString(connectStr)
					.shareName(shareName).resourcePath(dirName).buildDirectoryClient();

			dirClient.listFilesAndDirectories().forEach(fileRef -> System.out.printf("Resource: %s\t Directory? %b\n",
					fileRef.getName(), fileRef.isDirectory()));

			return true;
		} catch (Exception e) {
			System.out.println("enumerateFilesAndDirs exception: " + e.getMessage());
			return false;
		}
	}
		

}

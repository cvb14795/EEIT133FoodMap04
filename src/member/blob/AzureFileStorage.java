package member.blob;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.InvalidKeyException;
import java.text.MessageFormat;

import org.apache.commons.io.FilenameUtils;

import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.blob.CloudBlobClient;
import com.microsoft.azure.storage.blob.CloudBlobContainer;
import com.microsoft.azure.storage.blob.CloudBlockBlob;

public class AzureFileStorage {
	private static String ACCOUNT_NAME = "foodmap04";
	private static String ACCOUNT_KEY = "QxYA1vViyKKx3PQKBopyPfghnqCgVnSw7MULep4h8EkSBEHiPfnWGG0GqO6fwmoYjcYebo8ObGMZmjPVXXgQvg==";
	private static String END_POINT = "core.windows.net";
	private static String PROTOCOL = "https";
	private static String format = "DefaultEndpointsProtocol={0};AccountName={1};AccountKey={2};EndpointSuffix={3}";
	
	private static CloudStorageAccount storageAccount = null;
	private static CloudBlobClient blobClient = null;
	private static CloudBlobContainer container = null;
	
	public static void main(String[] args) {
		String containerName = "foodmap04";
		initAzure(containerName);
		
		Path filePath = Paths.get("C:\\Users\\user\\Desktop\\RecepiTable.txt");
		String fileName = FilenameUtils.getName(filePath.toString());
		
//		InputStream is = Files.newInputStream(filePath);				
//		InputStreamReader isr = new InputStreamReader(is);
		
		System.out.println("上傳檔案: "+fileName);
		System.out.println("檔案類型: "+ FilenameUtils.getExtension( filePath.toString()));
		
		uploadFileByPath(filePath.toFile(), fileName);
	}
	
	// 創建Container
	public static void initAzure(String containerName) {

		try {
			// 获得StorageAccount对象
			storageAccount = CloudStorageAccount
					.parse(MessageFormat.format(format, PROTOCOL, ACCOUNT_NAME, ACCOUNT_KEY, END_POINT));
			// 由StorageAccount对象创建BlobClient
			blobClient = storageAccount.createCloudBlobClient();
			// 根据传入的containerName, 获得container实例
			container = blobClient.getContainerReference(containerName);
		} catch (URISyntaxException | InvalidKeyException | StorageException e) {
			e.printStackTrace();
		}
	}

	// uploadFile 上傳檔案
	public static void uploadFileByPath(File file, String fileName) {
		try {
			// 构建目标BlockBlob对象
			CloudBlockBlob blob = container.getBlockBlobReference(fileName);
			// 将本地文件上传到Azure Container
			blob.uploadFromFile(file.getPath());
			// 获得获得属性
			blob.downloadAttributes();
			// 获得上传后的文件大小
			long blobSize = blob.getProperties().getLength();
			// 获得本地文件大小
			long localSize = file.length();
			// 校验
			if (blobSize != localSize) {
				System.out.println("校驗失败...上傳失敗");
				// 删除blob
				blob.deleteIfExists();
			} else {
				System.out.println("上傳成功");
			}
		} catch (URISyntaxException | StorageException | IOException e) {
			e.printStackTrace();
		}
	}

}

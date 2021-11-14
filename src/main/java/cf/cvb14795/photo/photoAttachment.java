package cf.cvb14795.photo;

import java.io.IOException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

public class photoAttachment {
	public static ResponseEntity<Resource> getPhoto(HttpServletResponse resp, byte[] photo, String fileName)  {
//        String base64String = Base64.getEncoder().encodeToString(photo);
        ByteArrayResource resource = new ByteArrayResource(photo);
//        String urlString="data:image/jpg;base64,"+base64String;
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentDispositionFormData("attachment", fileName);
        responseHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_OCTET_STREAM));
        return new ResponseEntity<Resource>(resource, responseHeaders, HttpStatus.OK);
	}
	
	public static void uploadToCloudinary(byte[] fileRef, String fileName) {
		System.out.println("uploadToCloudinary fileName: "+fileName);
		String fileType = "jpg";
		String fileBaseName = "";
		Map param = ObjectUtils.asMap(
			"use_filename", true,
			"resource_type", "auto"
		);
		String[] split = fileName.split(".");
		System.out.println(split);
		if (split.length > 0 && split[1] != "") {
			fileBaseName = split[0];
			fileType = split[1];
			System.out.println("fileBaseName:"+fileBaseName);
			System.out.println("fileType:"+fileType);
			param = ObjectUtils.asMap("public_id", fileBaseName);
		}
		
//		Cloudinary cloudinary = new Cloudinary("cloudinary://124178988976323:Q-4TFx-qRg3qnqARAkOS7_pWRXI@hcd6yhhui");
		Cloudinary cloudinary = new Cloudinary("cloudinary://145479634865899:svm1nEvuGpYRwZ96DcU0etMn-Hg@cvb14795");
		try {
			String base64Str = "data:image/"+fileType+";base64,"+Base64.getEncoder().encodeToString((byte[]) fileRef);
			
			cloudinary.uploader().upload(base64Str, param);
			System.out.println("上傳成功");
//			Map upload = cloudinary.uploader().upload(photoBytes, ObjectUtils.emptyMap());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.getMessage();
		}
	}
}

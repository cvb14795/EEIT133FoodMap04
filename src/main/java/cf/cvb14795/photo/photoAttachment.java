package cf.cvb14795.photo;

import java.util.Arrays;

import javax.servlet.http.HttpServletResponse;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

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
}

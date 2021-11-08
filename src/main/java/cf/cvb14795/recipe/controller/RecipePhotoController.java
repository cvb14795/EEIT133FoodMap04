package cf.cvb14795.recipe.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;

import cf.cvb14795.member.bean.Member;
import cf.cvb14795.recipe.model.AdminRecipeBean;
import cf.cvb14795.recipe.model.UserRecipeBean;
import cf.cvb14795.recipe.service.IAdminRecipeService;
import cf.cvb14795.recipe.service.IUserRecipeService;

@Controller
@RequestMapping("/Recipe")
public class RecipePhotoController {
	
	private final static String PREFIX = "Recipe/"; 
	IAdminRecipeService aRecipeService;
	IUserRecipeService uRecipeService;
	
	@Autowired
	public RecipePhotoController(IAdminRecipeService aRecipeService, IUserRecipeService uRecipeService) {
		this.aRecipeService = aRecipeService;
		this.uRecipeService = uRecipeService;
	}
	
	@GetMapping("/photo/{id}")
    @ResponseBody
    public ResponseEntity<?> getPicture(
    		HttpServletResponse resp,
    		@PathVariable String id) {
        int recipeId = Integer.valueOf(id);
		UserRecipeBean recipe = uRecipeService.getUpdateId(recipeId);
        if (recipe != null) {
	        byte[] photo = recipe.getPhoto();
//	        String base64String = Base64.getEncoder().encodeToString(photo);
	        ByteArrayResource resource = new ByteArrayResource(photo);
//	        String urlString="data:image/jpg;base64,"+base64String;
	        String fileName = "recipe_"+recipeId+".jpg";
	        HttpHeaders responseHeaders = new HttpHeaders();
	        responseHeaders.setContentDispositionFormData("attachment", fileName);
	        responseHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_OCTET_STREAM));
	        return new ResponseEntity<Resource>(resource, responseHeaders, HttpStatus.OK);
        } else {
        	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
	}
}

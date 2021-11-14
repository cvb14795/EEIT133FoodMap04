package cf.cvb14795.recipe.controller;

import java.awt.Image;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.swing.ImageIcon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cf.cvb14795.photo.photoAttachment;
import cf.cvb14795.recipe.model.UserRecipeBean;
import cf.cvb14795.recipe.service.IAdminRecipeService;
import cf.cvb14795.recipe.service.IUserRecipeService;

@Controller
@RequestMapping("/Recipe")
public class RecipePhotoController {
	
//	private final static String PREFIX = "Recipe/"; 
	IAdminRecipeService aRecipeService;
	IUserRecipeService uRecipeService;
	
	@Autowired
	public RecipePhotoController(IAdminRecipeService aRecipeService, IUserRecipeService uRecipeService) {
		this.aRecipeService = aRecipeService;
		this.uRecipeService = uRecipeService;
	}
	
	@GetMapping(path = "/user/photo/{id}", produces = MimeTypeUtils.IMAGE_JPEG_VALUE)
//    @ResponseBody
    public ResponseEntity<?> getPicture(
    		HttpServletResponse resp,
    		@PathVariable String id) {
        int recipeId = Integer.valueOf(id);
		Optional<UserRecipeBean> recipe = uRecipeService.getUpdateId(recipeId);
        if (recipe.isPresent()) {
	        byte[] photo = recipe.get().getPhoto();
//	        String fileName = "recipe_"+recipeId+".jpg";
//	        photoAttachment.uploadToCloudinary(photo, fileName);				
//	        return photoAttachment.getPhoto(resp, photo, fileName);
	        return new ResponseEntity<>(photo, HttpStatus.OK);
        } else {
        	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
	}
	
}

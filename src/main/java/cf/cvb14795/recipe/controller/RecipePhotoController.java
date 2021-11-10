package cf.cvb14795.recipe.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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
	        String fileName = "recipe_"+recipeId+".jpg";
	        return photoAttachment.getPhoto(resp, photo, fileName);
        } else {
        	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
	}
}

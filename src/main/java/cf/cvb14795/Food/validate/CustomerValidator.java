package cf.cvb14795.Food.validate;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import cf.cvb14795.Food.model.MapDataBean;

public class CustomerValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return MapDataBean.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "mapname", "mapDataBean.mapname.empty", "店家名稱欄位不正確");
		ValidationUtils.rejectIfEmpty(errors, "mapku", "mapDataBean.mapku.empty", "地址欄位不能空白");
		ValidationUtils.rejectIfEmpty(errors, "mapnb", "mapDataBean.mapnb.empty", "電話欄位不能空白");
		ValidationUtils.rejectIfEmpty(errors, "mapxy", "mapDataBean.mapxy.empty", "座標欄位不能空白");
		ValidationUtils.rejectIfEmpty(errors, "mapcheck", "mapDataBean.mapcheck.empty", "請選擇");

		MapDataBean mdb = (MapDataBean) target;

		if (mdb.getMapname() != null && mdb.getMapname().length() < 2 || mdb.getMapname().length() > 30) {
			if (errors.getFieldError("mapname") == null) {
				errors.rejectValue("mapname", "mapDataBean.mapname.size");
//				errors.rejectValue("mapname", "mapDataBean.mapname.size", "姓名欄至少要有兩個字元，最多不得超過30個字元-預設值");
			}	
		}

		if (mdb.getMapku() != null && mdb.getMapku().contains(" ")) {
			errors.rejectValue("mapku", "mapDataBean.mapku.space", "地址欄位不能空白");
		}
		
		if (mdb.getMapnb() != null && mdb.getMapnb().contains(" ")) {
			errors.rejectValue("mapnb", "mapDataBean.mapnb.space", "電話欄位不能空白");
		}
		
		if (mdb.getMapxy() != null && mdb.getMapxy().contains(" ")) {
			errors.rejectValue("mapxy", "mapDataBean.mapxy.space", "座標欄位不能空白");
		}		

		if (mdb.getMapcheck() != null && mdb.getMapcheck().contains(" ")) {
			errors.rejectValue("mapcheck", "mapDataBean.mapcheck.space", "請選擇");
		}	
	}

}

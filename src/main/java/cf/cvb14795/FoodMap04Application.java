package cf.cvb14795;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableJpaRepositories("UserRecipeRepository, AdminRecipeRepository")
//@ComponentScan("cf\\cvb14795\\recipe\\model\\UserRecipeBean")
public class FoodMap04Application {

	public static void main(String[] args) {
		SpringApplication.run(FoodMap04Application.class, args);
	}

}

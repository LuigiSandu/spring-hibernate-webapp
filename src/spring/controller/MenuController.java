package spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import spring.dao.ItemDAO;
import spring.entity.Account;
import spring.entity.CPU;
import spring.entity.Credential;
import spring.entity.GPU;
import spring.entity.HDD;
import spring.entity.PSU;
import spring.pojo.Validation;
import spring.service.AccountService;
import spring.util.ItemType;

@Controller
@RequestMapping("/menu")
@SessionAttributes({ "account", "credential" })
public class MenuController {

	Validation validation;
	@Autowired
	private ItemDAO itemDAO;
	@Autowired
	private AccountService accService;

	@RequestMapping("/home")
	public String menu(@ModelAttribute("account") Account acc, Model model) {
		model.addAttribute("account", acc);
		return "menu";
	}

	@RequestMapping("cpus")
	public String cpus(Model model) {
		//Get the cpus from the database and add it to the model.
		List<CPU> cpus = itemDAO.getCPUList();
		model.addAttribute("cpus", cpus);
		return "cpus";
	}

	@RequestMapping("gpus")
	public String gpus(Model model) {
		//Get the gpus from the database and add it to the model.
		List<GPU> gpus = itemDAO.getGPUList();
		model.addAttribute("gpus", gpus);
		return "gpus";
	}

	@RequestMapping("hdds")
	public String hdds(Model model) {
//		Get the hdds from the database and add it to the model.
		List<HDD> hdds = itemDAO.getHDDList();
		model.addAttribute("hdds", hdds);
		return "hdds";
	}

	@RequestMapping("psus")
	public String psus(Model model) {
		//Get the psus from the database and add it to the model.
		List<PSU> psus = itemDAO.getPSUList();
		model.addAttribute("psus", psus);
		return "psus";
	}

	@RequestMapping("addCPUtoCart")
	public String addCPUToCart(@ModelAttribute("account") Account acc, @RequestParam("cpuId") int itemId) {
		accService.addItemtoCart(ItemType.CPU, acc.getId(), itemId);
		return "redirect:cpus";
	}

	@RequestMapping("addGPUtoCart")
	public String addGPUToCart(@ModelAttribute("account") Account acc, @RequestParam("gpuId") int itemId) {
		accService.addItemtoCart(ItemType.GPU, acc.getId(), itemId);
		return "redirect:gpus";
	}

	@RequestMapping("addHDDtoCart")
	public String addHDDToCart(@ModelAttribute("account") Account acc, @RequestParam("hddId") int itemId) {
		accService.addItemtoCart(ItemType.HDD, acc.getId(), itemId);
		return "redirect:hdds";
	}

	@RequestMapping("addPSUtoCart")
	public String addPSUToCart(@ModelAttribute("account") Account acc, @RequestParam("psuId") int itemId) {
		accService.addItemtoCart(ItemType.PSU, acc.getId(), itemId);
		return "redirect:psus";
	}

	@RequestMapping("cart")
	public String cart(@ModelAttribute("account") Account acc, Model model) {
		//Get the list of items for the current account.
		List<String> items = accService.getItemsList(acc.getId());

		//Get the list of ids for the current account list.
		int[] ids = accService.getIdList(acc.getId());

		model.addAttribute("items", items);
		model.addAttribute("ids", ids);

		return "cart";
	}

	@RequestMapping("deleteItem")
	public String deleteItem(@ModelAttribute("account") Account acc, @RequestParam("itemId") int itemId , Model model) {

		//Get the updated list after deleting the item from the items list.
		List<String> items = accService.getUpdatedItemsList(acc.getId(), itemId);
		
		//Get the updated list after deleting id from the ids list.
		int[] ids = accService.getUpdatedIdList(acc.getId(), itemId);
		
		model.addAttribute("items", items);
		model.addAttribute("ids", ids);
		return "cart";
	}

	@RequestMapping("order")
	public String order(@ModelAttribute("account") Account acc, Model model) {
		Credential credential = new Credential();
		model.addAttribute("credential", credential);
		return "order";
	}

	@RequestMapping("saveOrder")
	public String saveOrder(@ModelAttribute("account") Account acc, @ModelAttribute("credential") Credential credential,
			Model model) {
		//1.Check if the cart is empty.
		if (accService.checkIfCartIsEmpty(acc.getId())) {
			model.addAttribute("emptyCartError", "The cart is empty.");
			return "order-error";
		}
		
		//2.Check if the cardNumber is valid.
		if (!(Validation.validateCardNumber(credential.getCardNumber()))) {
			model.addAttribute("credential", credential);
			model.addAttribute("cardNumberError", "Invalid Card Number Format.");
			return "order-error";
		}
		
		//3.Check if the cvc is valid.
		if (!(Validation.validateCVC(credential.getCVC()))) {
			model.addAttribute("credential", credential);
			model.addAttribute("cvcError", "Invalid CVC Format.");
			return "order-error";
		}
		
		accService.order(acc.getId(), credential);
		
		return "redirect:order";
	}

	@RequestMapping("details")
	public String details(@ModelAttribute("account") Account acc, Model model) {
		model.addAttribute(acc);
		return "details";
	}

	@RequestMapping("update")
	public String update(@ModelAttribute("account") Account acc, Model model) {
		return "update";
	}

	@PostMapping("updateUser")
	public String updateUser(@ModelAttribute("account") Account acc, @RequestParam("newUser") String user,Model model) {
		
		//1.Check if the field is empty. 
		if (user.isEmpty()) {
			model.addAttribute("userError", Validation.fieldEmptyError);
			model.addAttribute("account", acc);
			return "update-error";
		} 
		
		//2.Check if the new username is equal to old username.
		if (user.equals(acc.getUsername())) {
			model.addAttribute("userError", Validation.oldUserUnavailableError);
			model.addAttribute("account", acc);
			return "update-error";
		}
		
		//3.Check if the username already exists in database.
		if (accService.checkUsername(user)) {
			model.addAttribute("userError", Validation.userUnavailableError);
			model.addAttribute("account", acc);
			return "update-error";
		}
		
		//update username in view
		acc.setUsername(user);
		//update username in database
		accService.updateUsername(acc.getId(), user);
		return "update";
	}

	@PostMapping("updatePass")
	public String updatePass(@ModelAttribute("account") Account acc, @RequestParam("newPass") String pass,Model model) {
		
		//1.Check if the field is empty. 
		if (pass.isEmpty()) {
			model.addAttribute("passError", Validation.fieldEmptyError);
			model.addAttribute("account", acc);
			return "update-error";
		} 
		
		//2.Check if the new password is equal to old password.
		if (pass.equals(acc.getPassword())) {
			model.addAttribute("passError", Validation.oldPassUnavailableError);
			model.addAttribute("account", acc);
			return "update-error";
		} 
		
		//3.Check if the password length is less than 8.
		if (pass.length() < 8) {
			model.addAttribute("passError", Validation.passLengthError);
			model.addAttribute("account", acc);
			return "update-error";
		}
		
		//update password in view
		acc.setPassword(pass);
		//update password in database
		accService.updatePassword(acc.getId(), pass);
		return "update";
	}

	@PostMapping("updateEmail")
	public String updateEmail(@ModelAttribute("account") Account acc, @RequestParam("newEmail") String email,	Model model) {
		
		//1.Check if the field is empty.
		if (email.isEmpty()) {
			model.addAttribute("emailError", Validation.fieldEmptyError);
			model.addAttribute("account", acc);
			return "update-error";
		}
		
		//2.Check if the new email is equal to old email.
		if (email.equals(acc.getEmail())) {
			model.addAttribute("emailError", Validation.oldEmailUnavailableError);
			model.addAttribute("account", acc);
			return "update-error";
		} 
		
		//3.Check if the email already exists in the database.
		if (accService.checkEmail(email)) {
			model.addAttribute("emailError", Validation.emailUnavailableError);
			model.addAttribute("account", acc);
			return "update-error";
		}
		
		//update email in view
		acc.setEmail(email);
		//update email in databse
		accService.updateEmail(acc.getId(), email);
		return "update";
	}

	

	
}

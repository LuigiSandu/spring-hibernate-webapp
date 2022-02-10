package spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import spring.entity.Account;
import spring.service.AccountService;

@Controller
@RequestMapping("/account")
public class AccountController {
	
	@Autowired
	private AccountService accountService;

	@RequestMapping("/signIn")
	public String signInPage(Model model) {

		Account acc = new Account();
		model.addAttribute("account", acc);

		return "sign-in";
	}

	@PostMapping("/saveAccount")
	public String saveNewAccount(@ModelAttribute("account") Account acc, RedirectAttributes redirectAttributes,
			Model model) {

		// 1.Checking if any fields are empty.
		if (acc.getUsername() == null) {
			model.addAttribute("emptyError1", "Field cannot be empty.");
		}
		if (acc.getPassword() == null) {
			model.addAttribute("emptyError2", "Field cannot be empty.");
		}
		if (acc.getEmail() == null) {
			model.addAttribute("emptyError3", "Field cannot be empty.");
		}
		if (acc.getUsername() == null || acc.getPassword() == null || acc.getEmail() == null)
			return "sign-in-error";

		// 2.Check if username exists in database.
		if (accountService.checkUsername(acc.getUsername())) {
			model.addAttribute("usernameError", "Username unavailable.");
			return "sign-in-error";
		}

		// 3.Check if email exists in database.
		if (accountService.checkEmail(acc.getEmail())) {
			model.addAttribute("emailError", "Email unavailable.");
			return "sign-in-error";
		}

		// 4.Check if password length is less than 8.
		if (acc.getPassword().length() < 8) {
			model.addAttribute("passwordError", "Password must be at least 8 characters long.");
			return "sign-in-error";
		}

		// 5.Add the new account to the database and redirect model to the menu controller
		accountService.addNewAccount(acc);
		redirectAttributes.addFlashAttribute("account", acc);

		return "redirect:/menu/home";
	}

	@RequestMapping("logInPage")
	public String logInPage(Model model) {

		Account account = new Account();
		model.addAttribute("account", account);

		return "log-in";
	}

	@PostMapping("logIn")
	public String logIn(@ModelAttribute Account acc, RedirectAttributes redirectAttributes) {

		// Check if username exists in database. If it does, check the corresponding password.
		if (accountService.checkUserAndPass(acc.getUsername(), acc.getPassword())) {

			// Get the account for the username and redirect model to menu controller.
			Account account = accountService.getAccountForUser(acc.getUsername());
			redirectAttributes.addFlashAttribute("account", account);

			return "redirect:/menu/home";
		}
		return "log-in-error";
	}

//trimming whitespace
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}
}

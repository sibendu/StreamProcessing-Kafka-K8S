package net.codejava;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AppController {
	
	@Autowired
	private SalesDAO dao;
	
	@RequestMapping("/")
	public String viewTxnPage(Model model) {
		List<AccountTxn> listAccountTxn = dao.listAccountTxn();
		model.addAttribute("listAccountTxn", listAccountTxn);
		
		List<AccountLogin> listAccountLogin = dao.listAccountLogin();
		model.addAttribute("listAccountLogin", listAccountLogin);
		
		
		model.addAttribute("accountTxn", new AccountTxn());
		model.addAttribute("accountLogin", new AccountLogin());
	    return "index";
	}
	
	@RequestMapping(value = "/saveTxn", method = RequestMethod.POST)
	public String saveAccountTxn(@ModelAttribute("accountTxn") AccountTxn accountTxn) {
	    dao.saveAccountTxn(accountTxn);
		
	    return "redirect:/";
	}
	
	@RequestMapping(value = "/saveLogin", method = RequestMethod.POST)
	public String saveAccountLogin(@ModelAttribute("accountLogin") AccountLogin accountLogin) {
	    dao.saveAccountLogin(accountLogin);
		
	    return "redirect:/";
	}
	
	@RequestMapping("/sale")
	public String viewHomePage(Model model) {
		List<Sale> listSale = dao.list();
		model.addAttribute("listSale", listSale);
	    return "sale";
	}
	
	@RequestMapping("/new")
	public String showNewForm(Model model) {
	    Sale sale = new Sale();
	    model.addAttribute("sale", sale);
	     
	    return "new_form";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@ModelAttribute("sale") Sale sale) {
	    dao.save(sale);
	     
	    return "redirect:/";
	}
	
	@RequestMapping("/edit/{id}")
	public ModelAndView showEditForm(@PathVariable(name = "id") int id) {
	    ModelAndView mav = new ModelAndView("edit_form");
	    Sale sale = dao.get(id);
	    mav.addObject("sale", sale);
	     
	    return mav;
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(@ModelAttribute("sale") Sale sale) {
	    dao.update(sale);
	     
	    return "redirect:/";
	}
	
	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable(name = "id") int id) {
	    dao.delete(id);
	    return "redirect:/";       
	}	
}

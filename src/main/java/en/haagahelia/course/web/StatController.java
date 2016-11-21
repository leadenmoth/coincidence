package en.haagahelia.course.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import en.haagahelia.course.domain.Stat;
import en.haagahelia.course.domain.StatRepository;
import en.haagahelia.course.domain.PollRepository;

@Controller
public class StatController {
	@Autowired
	private StatRepository repository;
	@Autowired
	private PollRepository crepository;
	
	@RequestMapping(value="/index", method=RequestMethod.GET)
	public String bookIndex(Model model) {
		return "index";
	}
	
	@RequestMapping(value="/booklist", method=RequestMethod.GET)
	public String bookList(Model model) {
		model.addAttribute("booklist", repository.findAll());
		return "booklist";
	}
	
	//So that logging out and in again would open booklist. There are probably better ways of doing this, but...
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String redirect() {
		return "redirect:/booklist";
	}
	
    @RequestMapping(value="/books", method = RequestMethod.GET)
    public @ResponseBody List<Stat> bookListRest() {	
        return (List<Stat>) repository.findAll();
    } 
	
    @RequestMapping(value="/book/{id}", method = RequestMethod.GET)
    public @ResponseBody Stat findBookRest(@PathVariable("id") Long bookId) {	
    	return repository.findOne(bookId);
    }  
    
    @RequestMapping(value = "/add")
    public String addBook(Model model){
    	model.addAttribute("book", new Stat());
    	model.addAttribute("categories", crepository.findAll());
        return "addbook";
    }     
    
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveBook(Stat stat){
        repository.save(stat);
        return "redirect:booklist";
    }
    @RequestMapping(value="/delete/{id}", method = RequestMethod.GET)
    public String deleteBook(@PathVariable("id") Long bookId, Model model) {
    	repository.delete(bookId);
    	return "redirect:/booklist";
    }
    
    @RequestMapping(value="/login")
	public String login() {
		return "login";
	} 

}

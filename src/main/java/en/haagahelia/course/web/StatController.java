package en.haagahelia.course.web;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import en.haagahelia.course.domain.Stat;
import en.haagahelia.course.domain.StatRepository;
import en.haagahelia.course.domain.Message;
import en.haagahelia.course.domain.PollRepository;

@Controller
public class StatController {
	@Autowired
	private StatRepository repository;
	@Autowired
	private PollRepository prepository;
	
	@RequestMapping(value="/index", method=RequestMethod.GET)
	public String statIndex(Model model) {
		model.addAttribute("message", new Message());
		return "index";
	}
	@RequestMapping(value="/index", method=RequestMethod.POST)
	public String statSubmit(@ModelAttribute Message msg, Model model) {
		int value = msg.getValue();
		Random random = new Random();
		while (repository.findByPercent(value).size() < 2)
		{
			value = random.nextInt(101);
		}
		
		List<Stat> fittingStats = repository.findByPercent(value);
		msg.setValue(value);
		int chosenStatIndex = random.nextInt(fittingStats.size());
		msg.setMsg1(fittingStats.get(chosenStatIndex).getPoll().getSubject() + " " + fittingStats.get(chosenStatIndex).getAnswer());
		fittingStats.remove(chosenStatIndex);
		chosenStatIndex = random.nextInt(fittingStats.size());
		msg.setMsg2(fittingStats.get(chosenStatIndex).getPoll().getSubject() + " " + fittingStats.get(chosenStatIndex).getAnswer());
		model.addAttribute("message", msg);
		return "result";
	}
	
	@RequestMapping(value="/statlist", method=RequestMethod.GET)
	public String statList(Model model) {
		model.addAttribute("statlist", repository.findAll());
		return "statlist";
	}
	
	//So that logging out and in again would open statlist. There are probably better ways of doing this, but...
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String redirect() {
		return "redirect:/index";
	}
	
    @RequestMapping(value="/stats", method = RequestMethod.GET)
    public @ResponseBody List<Stat> statListRest() {	
        return (List<Stat>) repository.findAll();
    } 
	
    @RequestMapping(value="/stat/{id}", method = RequestMethod.GET)
    public @ResponseBody Stat findStatRest(@PathVariable("id") Long statId) {	
    	return repository.findOne(statId);
    }  
    
    @RequestMapping(value = "/add")
    public String addStat(Model model){
    	model.addAttribute("stat", new Stat());
    	model.addAttribute("polls", prepository.findAll());
        return "addstat";
    }     
    
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveStat(Stat stat){
        repository.save(stat);
        return "redirect:statlist";
    }
    @RequestMapping(value="/delete/{id}", method = RequestMethod.GET)
    public String deleteStat(@PathVariable("id") Long statId, Model model) {
    	repository.delete(statId);
    	return "redirect:/statlist";
    }
    
    @RequestMapping(value="/login")
	public String login() {
		return "login";
	} 

}

package en.haagahelia.course.web;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import en.haagahelia.course.domain.Message;
import en.haagahelia.course.domain.Polls;
import en.haagahelia.course.domain.PollsRepository;

@Controller
public class PollsController {
	//Connect Polls table
	@Autowired
	private PollsRepository repository;
	
	//Get request to index creates a new Message object and preps it for input of the form data
	@RequestMapping(value="/index", method=RequestMethod.GET)
	public String statIndex(Model model) {
		model.addAttribute("message", new Message());
		return "index";
	}
	
	//Process form input from index. Find a pair of answers by user's or random value
	@RequestMapping(value="/index", method=RequestMethod.POST)
	public String statSubmit(@ModelAttribute Message msg, Model model) {
		//Put user value into a temporary variable
		int value = msg.getValue();
		Random random = new Random();
		//If we can't find a pair of answers with that value, we randomize the value until we can
		while (repository.findByPercentage(value).size() < 2)
		{
			value = random.nextInt(101);
		}
		//Check if we had to use a random value so we can notify the user
		if (msg.getValue() == value) {
			msg.setMatch(true);
		} else {
			msg.setMatch(false);
		}
		//Collect all fitting answers into a list
		List<Polls> fittingPolls = repository.findByPercentage(value);
		//Save new value, whatever it is, back to Message object
		msg.setValue(value);
		//Pick one random answer and save it into Message object
		int chosenPollsIndex = random.nextInt(fittingPolls.size());
		msg.setMsg1(fittingPolls.get(chosenPollsIndex).getAnswer());
		//Remove the first answer from the next draw and pick a second random answer
		fittingPolls.remove(chosenPollsIndex);
		chosenPollsIndex = random.nextInt(fittingPolls.size());
		msg.setMsg2(fittingPolls.get(chosenPollsIndex).getAnswer());
		//Save Message object to model for result page to call
		model.addAttribute("message", msg);
		return "result";
	}
	
	//This *should* redirect root requests to index
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String redirect() {
		return "redirect:/index";
	}
	
	//RESTful service to list all Polls
    @RequestMapping(value="/polls", method = RequestMethod.GET)
    public @ResponseBody List<Polls> pollsListRest() {	
        return (List<Polls>) repository.findAll();
    } 
	
    //RESTful service to find a Polls entry by id
    @RequestMapping(value="/polls/{id}", method = RequestMethod.GET)
    public @ResponseBody Polls findPollsRest(@PathVariable("id") Long pollsId) {	
    	return repository.findOne(pollsId);
    } 
    /*
	//Get all values from the Polls repository to show on statlist page and also delete at will
	@RequestMapping(value="/statlist", method=RequestMethod.GET)
	public String statList(Model model) {
		model.addAttribute("statlist", repository.findAll());
		return "statlist";
	}
    
    //Create new Stat object and load Poll repository for addstat.html form to enter a new stat
    @RequestMapping(value = "/add")
    public String addStat(Model model){
    	model.addAttribute("stat", new Stat());
    	model.addAttribute("polls", prepository.findAll());
        return "addstat";
    }     
    
    //Save new Stat from addstat.html form to stat repository
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveStat(Stat stat){
        repository.save(stat);
        return "redirect:statlist";
    }
    
    //Delete stat entry by id
    //TODO method level security @Preauthorize - so only admins can delete
    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value="/delete/{id}", method = RequestMethod.GET)
    public String deleteStat(@PathVariable("id") Long statId, Model model) {
    	repository.delete(statId);
    	return "redirect:/statlist";
    }*/
    
    //Handle calls to login page
    @RequestMapping(value="/login")
	public String login() {
		return "login";
	} 

}

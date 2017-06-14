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
import en.haagahelia.course.domain.Poll;
import en.haagahelia.course.domain.PollRepository;

@Controller
public class PollController {
	//Connect Poll table
	@Autowired
	private PollRepository repository;
	
	//Get request to index creates a new Message object and preps it for input of the form data
	@RequestMapping(value="/index", method=RequestMethod.GET)
	public String pollIndex() {
		return "index";
	}
	
	//Get random answers from the database
	@RequestMapping(value="/message/{pc}", method=RequestMethod.GET)
	public @ResponseBody Message getMessage(@PathVariable("pc") int percent) {
		//Put user value into a temporary variable
		Message msg = new Message();
		int value = percent;
		Random random = new Random();
		//If we can't find a pair of answers with that value, we randomize the value until we can
		while (repository.findByPercentage(value).size() < 2)
		{
			value = random.nextInt(101);
		}
		//Check if we had to use a random value so we can notify the user
		if (percent == value) {
			msg.setMatch(true);
		} else {
			msg.setMatch(false);
		}
		//Collect all fitting answers into a list
		List<Poll> fittingPolls = repository.findByPercentage(value);
		//Save new value, whatever it is, to Message object
		msg.setValue(value);
		//Pick one random answer and save it into Message object
		int chosenPollsIndex = random.nextInt(fittingPolls.size());
		msg.setMsg1(fittingPolls.get(chosenPollsIndex).getAnswer());
		//Remove the first answer from the next draw and pick a second random answer
		fittingPolls.remove(chosenPollsIndex);
		chosenPollsIndex = random.nextInt(fittingPolls.size());
		msg.setMsg2(fittingPolls.get(chosenPollsIndex).getAnswer());
		return msg;
	}

	
	//This *should* redirect root requests to index
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String redirect() {
		return "redirect:/index";
	}
	
	//RESTful service to list all Polls
    @RequestMapping(value="/polls", method = RequestMethod.GET)
    public @ResponseBody List<Poll> pollsListRest() {	
        return (List<Poll>) repository.findAll();
    } 
	
    //RESTful service to find a Poll entry by id
    @RequestMapping(value="/polls/{id}", method = RequestMethod.GET)
    public @ResponseBody Poll findPollsRest(@PathVariable("id") Long pollsId) {	
    	return repository.findOne(pollsId);
    } 
    
	//Get all values from the Poll repository to show on polllist page and also delete at will
	@RequestMapping(value="/polllist", method=RequestMethod.GET)
	public String pollList(Model model) {
		model.addAttribute("polllist", repository.findAll());
		return "polllist";
	}
    
    //Create new Poll object and load Poll repository for addpoll.html form to enter a new poll
    @RequestMapping(value = "/add")
    public String addPoll(Model model){
    	model.addAttribute("poll", new Poll());
        return "addpoll";
    }     
    
    //Save new Poll from addpoll.html form to poll repository
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String savePoll(Poll poll){
        repository.save(poll);
        return "redirect:polllist";
    }
    
    //Delete poll entry by id
    //TODO method level security @Preauthorize - so only admins can delete
    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value="/delete/{id}", method = RequestMethod.GET)
    public String deletePoll(@PathVariable("id") Long pollId, Model model) {
    	repository.delete(pollId);
    	return "redirect:/polllist";
    }
    
    //Handle calls to login page
    @RequestMapping(value="/login")
	public String login() {
		return "login";
	} 

}

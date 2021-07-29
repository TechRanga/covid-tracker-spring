package techranga.covidtracker.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import techranga.covidtracker.CovidTrackerDataService;
import techranga.covidtracker.models.LocationStats;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    CovidTrackerDataService dataService;

    @GetMapping("/")
    public String Home(Model model){
        List<LocationStats> stats = dataService.getStats();
        int totalCases = stats.stream().mapToInt(stat -> stat.getLatestCaseCount()).sum();
        int newCases = stats.stream().mapToInt(stat -> stat.getDelta()).sum();
        model.addAttribute("stats", stats);
        model.addAttribute("totalCases",totalCases );
        model.addAttribute("newCases",newCases);
        return "Home";
    }
}



package org.launchcode.techjobsmvc.controllers;

import org.launchcode.techjobsmvc.models.Job;
import org.launchcode.techjobsmvc.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

import static org.launchcode.techjobsmvc.controllers.ListController.columnChoices;


/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @GetMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", columnChoices);
        return "search";
    }

    // TODO #3 - Create a handler to process a search request and render the updated search view.
    @PostMapping(value="results")
    public String displaySearchResults(Model model, @RequestParam("searchType") String column, @RequestParam(required = false) String searchTerm)
    {
        ArrayList<Job> jobs;
        if (column.equals("all")){
            jobs = JobData.findAll();
            System.out.println("searching all" + jobs);


        } else {
            jobs = JobData.findByColumnAndValue(column, searchTerm);

        }
        model.addAttribute("columns", columnChoices);
        model.addAttribute("jobs", jobs);

        return "search";

    }
}


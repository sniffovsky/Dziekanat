package pl.edu.agh.ki.mwo.SchoolWebApp.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import pl.edu.agh.ki.mwo.SchoolWebApp.entity.School;
import pl.edu.agh.ki.mwo.SchoolWebApp.repository.SchoolRepository;

@Controller
public class SchoolsController {

    @Autowired
    private SchoolRepository schoolRepository;

    @RequestMapping(value = "/Schools")
    public String listSchools(Model model, HttpSession session) {

        model.addAttribute("schools", schoolRepository.findAll());

        return "schoolsList";
    }

    @RequestMapping(value = "/AddSchool")
    public String displayAddSchoolForm(Model model, HttpSession session) {

        return "schoolForm";
    }

    @RequestMapping(value = "/CreateSchool", method = RequestMethod.POST)
    public String createSchool(@RequestParam(value = "schoolName", required = false) String name,
            @RequestParam(value = "schoolAddress", required = false) String address, Model model, HttpSession session) {

        School school = new School();
        school.setName(name);
        school.setAddress(address);

        // DatabaseConnector.getInstance().addSchool(school);
        schoolRepository.save(school);
        // model.addAttribute("schools", DatabaseConnector.getInstance().getSchools());
        model.addAttribute("schools", schoolRepository.findAll());
        model.addAttribute("message", "Nowa szkoła została dodana");

        return "schoolsList";
    }

    @RequestMapping(value = "/DeleteSchool", method = RequestMethod.POST)
    public String deleteSchool(@RequestParam(value = "schoolId", required = false) String schoolId, Model model, HttpSession session) {

        schoolRepository.deleteById(Long.valueOf(schoolId));

        model.addAttribute("schools", schoolRepository.findAll());
        model.addAttribute("message", "Szkoła została usunięta");

        return "schoolsList";
    }

    @RequestMapping(value = "/ShowUpdateSchoolForm")
    public String showUpdateSchoolForm(@RequestParam(value = "schoolId") String schoolId, Model model, HttpSession session) {

        School school = schoolRepository.findById(Long.valueOf(schoolId)).get();
        model.addAttribute("school", school);

        return "schoolUpdateForm";
    }

    @RequestMapping(value = "/UpdateSchool", method = RequestMethod.POST)
    public String updateSchool(@RequestParam(value = "schoolName", required = false) String name,
            @RequestParam(value = "schoolAddress", required = false) String address, @RequestParam(value = "schoolId") String schoolId, Model model,
            HttpSession session) {

        School school = schoolRepository.findById(Long.valueOf(schoolId)).get();
        school.setName(name);
        school.setAddress(address);
        // update
        schoolRepository.save(school);
        model.addAttribute("schools", schoolRepository.findAll());
        model.addAttribute("message", "Szkola zaktualizowana");

        return "schoolsList";
    }

}
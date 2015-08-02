package backend.web;

import backend.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by simon on 8/2/15.
 */

@Controller
public class IndexController {

    @Autowired
    private TaskService taskService;

    @RequestMapping("/")
    String index(Model model) {
        model.addAttribute("tasks", taskService.findAll());
        return "index";
    }

}

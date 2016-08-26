package lexis.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import lexis.models.File;
import lexis.models.Folder;
import lexis.models.User;

@RestController
@RequestMapping("/editor")
public class EditorController {

	@RequestMapping
	public ModelAndView editor() {
		ModelAndView editor = new ModelAndView("/editor");
		return editor;
	}
	

	@RequestMapping("fileEditor")
	public static File fileEditor(Folder currentFolder,File file) {
		ModelAndView editor = new ModelAndView("/editor");
		return file;
	}
}

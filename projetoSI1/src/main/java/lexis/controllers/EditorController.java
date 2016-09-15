package lexis.controllers;
 
import java.time.LocalDateTime;
 
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
 
import lexis.models.DataBase;
import lexis.models.Explorer;
import lexis.models.File;
import lexis.models.SharedFile;
import lexis.models.Type;
import lexis.models.User;
import lexis.util.JsonUtil;
 
@RestController
@RequestMapping("/editor")
public class EditorController {
 
    private Explorer explorer;
    private File file;
    private SharedFile sharedFile;
   
    /**
     * metodo responsavel por mapear a pagina correta
     * se nao houver aquivo, mapeia para o home.html
     * se houver mapeia para editor.html
     * @return
     */
    @RequestMapping
    public ModelAndView editor() {
        ModelAndView editor;
        if((file == null)&(sharedFile == null)){
            editor = new ModelAndView("home");
        }else{
            editor = new ModelAndView("editor");
        }
        return editor;
    }
   
    /**
     * @param json -Object Json com os dados do arquivo a ser buscado
     */
    @RequestMapping(value = "viewFile", method = RequestMethod.POST)
    public void viewFile(@RequestBody Object json) {
        JsonUtil.json(json);   
        setExplorer();
 
        String fileName = JsonUtil.getName();
        Type type = JsonUtil.getType();
        this.file = explorer.getFile(fileName,type);
        this.sharedFile = null;
    }
   
    /**
     * @param json -Object Json com os dados do arquivo a ser buscado
     */
    @RequestMapping(value = "viewSharedFile", method = RequestMethod.POST)
    public void viewSharedFile(@RequestBody Object json) {
        JsonUtil.json(json);   
        setExplorer();
       
        String fileOwner = JsonUtil.getOwner();
        int index = JsonUtil.getIndex();
        this.sharedFile = explorer.getSharedFile(fileOwner,index);
        this.file = null;
    }
   
    /**
     * metodo reponsavel pelo retorno do arquivo
     * @return o arquivo requisitado
     */
    @RequestMapping(value = "viewFile", method = RequestMethod.GET)
    public File viewFile() {
        return this.file;
    }
   
    @RequestMapping(value = "viewSharedFile", method = RequestMethod.GET)
    public SharedFile viewSharedFile() {
        return this.sharedFile;
    }
 
    /**
     * metodo resposavel por salvar o codigo fonte do arquivo
     * @param json -Object Json com o dados a sem modificados
     */
    @RequestMapping(value = "saveData", method = RequestMethod.POST)
    public void saveData(@RequestBody Object json){
        JsonUtil.json(json);
       
        String data = JsonUtil.getData();
        LocalDateTime dateEdition = LocalDateTime.now();
       
        if(file == null){
            sharedFile.setData(data, dateEdition);
        }else{
            file.setData(data, dateEdition);
        }
       
    }
   
    /**
     * retorna o usuario logafo
     * @return
     */
    private User userLogged(){
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
   
    /**
     * modifica ou altera o explorer atual
     */
    public void setExplorer(){
        this.explorer = DataBase.getInstance().getUser(userLogged().getUsername());
    }
}
import java.io.FileNotFoundException;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class TestNashorn {

    public static void main(String[] args) throws FileNotFoundException, ScriptException {

        ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
        Object obj=engine.eval("function xyz(){return 'Hello World!';}xyz();");
        System.out.println(obj);
    }
}

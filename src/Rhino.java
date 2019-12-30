import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.ContextFactory;
import org.mozilla.javascript.EcmaError;
import org.mozilla.javascript.JavaScriptException;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;

public class Rhino {

    public static void main(String[] args) {
    	//CompilerEnvirons compilerEnv = new CompilerEnvirons();

    	if (args.length <= 0){
    		System.out.println("使用方法");
    		System.out.println("Greeting1 jsfile");
    		return;
    	}

        String path = args[0];

        //Context cx = Context.enter();
        ContextFactory factory = new ContextFactory();
//        Main debugger = new org.mozilla.javascript.tools.debugger.Main("debug window");
//        debugger.attachTo(factory);
//        debugger.pack();
//        debugger.setSize(600, 460);
//        debugger.setVisible(true);

        Context cx = factory.enterContext();
        cx.setLanguageVersion(Context.VERSION_1_7);
        try{
	        // グローバルスコープオブジェクトを取得する
	        Scriptable globalScope = cx.initStandardObjects();
	        ScriptableObject.putProperty(globalScope, "out", Context.javaToJS(System.out, globalScope));
	        ScriptableObject.putProperty(globalScope, "console", new ConsoleObject());
            // ホストオブジェクトを登録する
			ScriptableObject.defineClass(globalScope, CustomHostObject.class);
	        // 文字列をevalする(引数：評価を行うスコープ、評価するJavaScriptのソースコードの文字列、ソースの名前、開始行番号、セキュリティドメイン)
	        Object result = cx.evaluateReader(globalScope, new FileReader(new File(path)), path, 1, null);
	        if (!result.equals(Context.getUndefinedValue()) && result != null){
	        	System.out.println(Context.toString(result));
	        }
        }catch(JavaScriptException je) {
			je.printStackTrace();
        } catch(IOException | IllegalAccessException | InstantiationException | InvocationTargetException | EcmaError e){
			e.printStackTrace();
        } finally {
		    Context.exit();
        }
    }
}

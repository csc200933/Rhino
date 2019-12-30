import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.NativeArray;
import org.mozilla.javascript.NativeObject;
import org.mozilla.javascript.ScriptableObject;

public class ConsoleObject {

	public ConsoleObject(){}

//    public void log(ScriptableObject obj) {
//    	System.out.println(obj.getClass().getName());
//        System.out.println(Context.jsToJava(obj, String.class));
//    }

    public void log(Object obj) {
    	if (obj instanceof NativeObject){
    		// TODO more smartly
    		System.out.println(toString((NativeObject)obj));
    	} else if (obj instanceof NativeArray){
            System.out.println(Arrays.toString(((NativeArray)obj).toArray()));
    	} else if (obj instanceof ScriptableObject){
    		System.out.println(Context.jsToJava(obj, String.class));
    	} else {
        	System.out.println(obj.getClass().getName());
            System.out.println(obj.toString());
    	}
    }

    public void log(String message) {
        System.out.println(message);
    }

    public void error(Object obj){
    	System.err.println(obj);
    }


	private static String toString(NativeObject val) {

		StringBuffer buffer = new StringBuffer();
		for (Iterator i = val.entrySet().iterator(); i.hasNext();) {
			Map.Entry entry = (Map.Entry)i.next();
			if (buffer.length() == 0) {
				buffer.append("{");
			} else {
				buffer.append(", ");
			}
			buffer.append(entry.getKey());
			buffer.append("=");
			buffer.append(entry.getValue());
		}
		buffer.append("}");
		return buffer.toString();
	}
}

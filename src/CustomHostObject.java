import org.mozilla.javascript.ScriptableObject;

public class CustomHostObject extends ScriptableObject {
    private int count;

    // 引数のないコンストラクタは: Rhinoランタイムによって、インスタンスの作成に使用される。
    public CustomHostObject() {}

    // @JSConstructor
    // JavaScriptコンストラクタ: jsConstructorメソッドによって定義。
    public void jsConstructor(int a) { count = a; }

    // クラス名: getClassNameメソッドによって定義。コンストラクター名を決定するのに使用。
    @Override
    public String getClassName() { return "Custom"; }

    // 動的プロパティ: 「jsGet_ / jsSet_ プロパティ」ではじまるメソッド定義。
    public int jsGet_count() { return count++; }

    // @JSFunction
    // JavaScriptメソッド: 「jsFunction_メソッド名」ではじまるメソッド定義。
    public void jsFunction_resetCount() { count = 0; }

    @Override
    public String toString()
    {
    	return Integer.toString(this.count);
    }
}
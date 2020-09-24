package LearningWithGoogleCom;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;

public class LinkedBinding {
    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new TextEditorModule());
        TextEditor editor = injector.getInstance(TextEditor.class);
        editor.makeSpellCheck();
    }
}

class TextEditor{
    private final SpellCheckerImpl spellChecker;
    @Inject
    public TextEditor(SpellCheckerImpl spellChecker){
        this.spellChecker = spellChecker;
    }
    void makeSpellCheck(){
        spellChecker.makeSpellCheck();
    }
}

class TextEditorModule extends AbstractModule{
    @Override
    protected void configure() {
        bind(SpellChecker.class).to(SpellCheckerImpl.class);
    }
}
class SpellCheckerImpl implements SpellChecker{

    @Override
    public void makeSpellCheck() {
        System.out.println("Binding the SpellChecker");
    }
}
interface SpellChecker{
    void makeSpellCheck();
}
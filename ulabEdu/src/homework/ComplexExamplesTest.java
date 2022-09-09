package homework;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ComplexExamplesTest {
    @Test
    void fuzzySearchWorksProperly (){
        /*
                    fuzzySearch("car", "ca6$$#_rtwheel"); // true
                    fuzzySearch("cwhl", "cartwheel"); // true
                    fuzzySearch("cwhee", "cartwheel"); // true
                    fuzzySearch("cartwheel", "cartwheel"); // true
                    fuzzySearch("cwheeel", "cartwheel"); // false
                    fuzzySearch("lw", "cartwheel"); // false
         */
        var examples=new ComplexExamples();
        assertEquals(true,examples.fuzzySearch("car","ca6$$#_rtwheel"));
        assertEquals(true,examples.fuzzySearch("cwhl", "cartwheel"));
        assertEquals(true,examples.fuzzySearch("cwhee", "cartwheel"));
        assertEquals(true,examples.fuzzySearch("cartwheel", "cartwheel"));
        assertEquals(false,examples.fuzzySearch("cwheeel", "cartwheel"));
        assertEquals(false,examples.fuzzySearch("lw", "cartwheel"));
    }

}
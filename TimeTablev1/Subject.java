import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

public class Subject{
    String title;
    static final int
            LECTURE = 0,
            TUTORIAL = 1,
            PRACTICAL = 2;

    ArrayList<SubjectComponent> sub_comp = new ArrayList<SubjectComponent>();   // All sub-components of the course (L, P, T).


    Subject (String subject, int Lecture, int Practical, int Tutorial){         // Creates subcomponent data from hour spread.
        this.title = subject;
        sub_comp.add(new SubjectComponent(subject, Lecture, LECTURE));
        sub_comp.add(new SubjectComponent(subject, Tutorial, TUTORIAL));
        sub_comp.add(new SubjectComponent(subject, Practical, PRACTICAL));
    }
    public String toString(){
        return ("Components of " + title.toUpperCase() + ":\n" + sub_comp.get(0) + "\n" + sub_comp.get(1) + "\n" + sub_comp.get(2));
    }
}
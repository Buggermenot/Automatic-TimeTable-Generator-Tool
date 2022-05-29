import java.util.Collections;

public class SubjectComponent implements Comparable<SubjectComponent> {
    int hours_per_week;

    static int
            LECTURE = 0,
            TUTORIAL = 1,
            PRACTICAL_CSE = 2,
            PRACTICAL_PHY = 3;

    int day_counter = 0;                            // stores previous day
    int time_counter = 0;
    int block_counter = 0;
    int type;
    final char[] types = {'L', 'T', 'P', 'Q'};                                    // L -> Lecture, P -> Practical, T -> Tutorial.

    int[] allowed_hours;                                                    // Allowed time slots per subject component. 2, 1, or 0.
    String subject;                                                         // Subject taught
    int SubjectID;
    static int PHY = 0,
        MATH = 1,
        DMS = 2,
        DD = 3,
        JAVA = 4,
        ECE = 5,
        INN = 6;

    public SubjectComponent(String subject, int hours, int type) {
        this.subject = subject;
        if (subject.equalsIgnoreCase("PHY")){this.SubjectID = PHY;}
        else if(subject.equalsIgnoreCase("Math")){this.SubjectID = MATH;}
        else if(subject.equalsIgnoreCase("DMS")){this.SubjectID = DMS;}
        else if(subject.equalsIgnoreCase("DD")){this.SubjectID = DD;}
        else if(subject.equalsIgnoreCase("JAVA")){this.SubjectID = JAVA;}
        else if(subject.equalsIgnoreCase("EECE")){this.SubjectID = ECE;}
        else if(subject.equalsIgnoreCase("Entrepreneurship")){this.SubjectID = INN;}

        hours_per_week = hours;
        if (subject.equalsIgnoreCase("PHY") && type == PRACTICAL_CSE){
            this.type = PRACTICAL_PHY;
        }
        else{this.type = type;}
    }

    public int compareTo(SubjectComponent subjectComponent){
        int x = this.hours_per_week * (this.type + 1);
        int y = subjectComponent.hours_per_week * (subjectComponent.type + 1);

        if (x > y){return -1;}
        else if (x < y) {return 1;}
        else {return 0;}
    }

    public String toString(){
        return (subject + "-" + types[type] + "|" + hours_per_week);
    }
}

import java.util.ArrayList;
import java.util.Arrays;

public class Batch implements Comparable<Batch>{
    int Batch_no;

    ArrayList<SubjectComponent> subjectComponents = new ArrayList<>();
    ArrayList<SubjectComponent> subjectLectures = new ArrayList<SubjectComponent>();
    static Subject[] subjects;

    String[] TimeTable = new String[40];
    String[][] TimeTable2 = new String[5][8];
    int block_counter = 0;                            // main (counter / 8) = day;
    int groupNo;

    void run(){
        Subject Phy = new Subject("Phy", 2, 2, 0);
        Subject Math = new Subject("Math", 3, 0, 1);
        Subject DMS = new Subject("DMS", 3, 0, 1);
        Subject DD = new Subject("DD", 3, 2, 0);
        Subject Java = new Subject("Java", 3, 4, 0);
        Subject ECE = new Subject("EECE", 3, 2, 1);
        Subject Inn = new Subject("Entrepreneurship", 2, 0, 0);

        subjects = new Subject[]{Phy, Math, DMS, DD, Java, ECE, Inn};
    }

    Batch(int BatchNo){
        run();
        this.Batch_no = BatchNo;

        for(Subject subject : subjects){
            for (SubjectComponent sub_comp : subject.sub_comp){
                if (sub_comp.type == SubjectComponent.LECTURE){
                    subjectLectures.add(sub_comp);
                }
                else if (sub_comp.hours_per_week > 0){
                    subjectComponents.add(sub_comp);
                }
            }
        }
    }

    public int compareTo(Batch other_batch){
        int sum_this = 0, sum_other = 0;
        for (SubjectComponent ss : this.subjectComponents){
            sum_this += ss.hours_per_week * (ss.type + 1);
        }
        for (SubjectComponent ss : other_batch.subjectComponents){
            sum_other += ss.hours_per_week * (ss.type + 1);
        }

        if (sum_this < sum_other) {return -1;}
        else if (sum_this > sum_other) {return 1;}
        else {return 0;}
    }

    public String toString(){
        return("EB" + this.Batch_no);
    }
}

import java.util.ArrayList;

public class Group {

    int Group_no;

    ArrayList<Batch> batchGroup = new ArrayList<>();
    ArrayList<SubjectComponent> subjectLectures = new ArrayList<>();

    Group(ArrayList<Batch> batches){
        this.Group_no = (batches.get(0).Batch_no/6) + 1;
        this.batchGroup = batches;
        this.subjectLectures = batches.get(0).subjectLectures;
    }

    void groupLecture(int subject, int index){
        Batch baseBatch = this.batchGroup.get(0);
        SubjectComponent temp = baseBatch.subjectLectures.get(subject);
        this.subjectLectures = baseBatch.subjectLectures;

        baseBatch.block_counter = Main.block;
        this.subjectLectures.get(subject).day_counter = Main.day;
        this.subjectLectures.get(subject).hours_per_week -= 1;

        for(Batch batch : this.batchGroup){
            batch.block_counter = Main.block;
            batch.subjectLectures = this.subjectLectures;
            batch.TimeTable[MainTest.block] = "G" + (Group_no) + "-" + temp.subject + "-" + temp.types[temp.type] + "-" + Rooms.rooms[temp.type][index];
        }
    }

}
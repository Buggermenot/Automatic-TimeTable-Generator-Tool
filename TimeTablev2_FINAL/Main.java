import javax.lang.model.type.NullType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Main {

    static int no_of_batches = 30;

    static int
            LECTURE = 0,
            TUTORIAL = 1,
            PRACTICAL_CSE = 2,
            PRACTICAL_PHY = 3;

    public static void main(String[] args) {
        ArrayList<Batch> batches = new ArrayList<>();

        for(int batch_no = 1; batch_no <= no_of_batches; batch_no++){
            batches.add(new Batch(batch_no));
        }

        for(int block = 0; block < 40; block++) {

            Rooms R = new Rooms();

            int day = block / 8;

//            System.out.println("Block No. = " + (block + 1));

            Collections.sort(batches);
//            System.out.println(batches);
            // Allotments
            String[][] allotments = new String[4][];
            allotments[LECTURE] = new String[4];
            allotments[TUTORIAL] = new String[10];
            allotments[PRACTICAL_CSE] = new String[6];
            allotments[PRACTICAL_PHY] = new String[2];


            // All batches


            l:
            for (Batch batch : batches) {
                int counter = 0;
                Collections.sort(batch.subjectComponents);
                while (true) {

                    SubjectComponent temp = batch.subjectComponents.get(counter);

                    if (temp.hours_per_week == 0) {
                        continue l;
                    }

                    for (int index = 0; index < allotments[temp.type].length; index++) {

                        if ((allotments[temp.type][index] == null) && ((temp.day_counter / 8 != day) || (temp.day_counter == day - 1) || (temp.day_counter == 0))){
                            System.out.println(temp.day_counter + " " + block);
                            if (temp.type == LECTURE){
                                int range = batch.Batch_no / 6;
                                allotments[temp.type][index] = "Group-" + (range+1) + "-" + temp.subject + "-" + temp.types[temp.type];
                                for (int i = range*6; i < (range + 1)*6; i++){
                                    if (i == no_of_batches){break;}
                                    batches.get(i).subjectLectures.get(temp.SubjectID).hours_per_week -= 1;
                                    batches.get(i).subjectComponents.get(counter).day_counter = block + 1;
                                    System.out.println(batches.get(i) + ":" + batches.get(i).subjectComponents.get(counter).day_counter);
                                }
                            }
                            else {
                                allotments[temp.type][index] = batch + "-" + temp.subject + "-" + temp.types[temp.type];
                                batch.subjectComponents.get(counter).hours_per_week -= 1;
                                batch.subjectComponents.get(counter).day_counter = block + 1;
                            }

                            batch.TimeTable[block] = temp.subject + "-" + temp.types[temp.type] + "-" + Rooms.rooms[temp.type][index];

                            counter += 1;

                            if (counter >= 14) {
                                break l;
                            }
                            continue l;
                        }
                    }

                    counter += 1;
                    if (counter >= 14) {
                        break l;
                    }
                }
            }

            System.out.println("Lecture Rooms");
            System.out.println(Arrays.toString(allotments[LECTURE]));
            System.out.println("Tutorial Rooms");
            System.out.println(Arrays.toString(allotments[TUTORIAL]));
            System.out.println("Practical CSE Rooms");
            System.out.println(Arrays.toString(allotments[PRACTICAL_CSE]));
            System.out.println("Practical PHY Rooms");
            System.out.println(Arrays.toString(allotments[PRACTICAL_PHY]));
            System.out.println('\n');




        }
        for(Batch batch : batches){
            System.out.println(batch + ":" + batch.subjectComponents);
        }

        for(Batch batch : batches){
            for (int i = 0; i < 5; i++) {
                System.arraycopy(batch.TimeTable, i*8, batch.TimeTable2[i], 0, 8);
            }
            System.out.println("Timetable for " + batch + " :\n");
            for (String[] s : batch.TimeTable2){
                System.out.println(Arrays.toString(s));
            }
            System.out.println('\n');
        }
    }
}

//        b2.subjectComponents.get(2).hours_per_week -= 1;
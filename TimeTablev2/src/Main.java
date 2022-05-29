import javax.lang.model.type.NullType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Main {

    static int no_of_batches = 30;

    static int week_hours = 8,
            working_days = 5;

    static int
            LECTURE = 0,
            TUTORIAL = 1,
            PRACTICAL_CSE = 2,
            PRACTICAL_PHY = 3;

    static int block = 1,
            day = 0,
            MAX_BLOCK = week_hours * working_days;

    public static void main(String[] args) {
        ArrayList<Batch> batches = new ArrayList<>();
        Group[] groups = new Group[no_of_batches / 6];

        for (int batch_no = 1; batch_no <= no_of_batches; batch_no++) {
            batches.add(new Batch(batch_no));
        }

        for (int g_no = 0; g_no < no_of_batches / 6; g_no++) {

            ArrayList<Batch> groupBatches = new ArrayList<>();
            try {
                for (int i = g_no * 6; i < (g_no + 1) * 6; i++) {
                    batches.get(i).groupNo = i / 6;
                    groupBatches.add(batches.get(i));
                }
            } catch (Exception e) {
                break;
            } finally {
                groups[g_no] = new Group(groupBatches);
            }
        }

        // Week begins
        while (block <= MAX_BLOCK) {
            day = (block / 6) + 1;

            Rooms R = new Rooms();

            Collections.sort(batches, Collections.reverseOrder());

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
                batch.subjectLectures.sort(Collections.reverseOrder());


                m:
                while (true) {                                                                                       // Can be replaced with a for loop with counter.

                    SubjectComponent tempS = batch.subjectComponents.get(counter);
                    SubjectComponent tempL = batch.subjectLectures.get(counter);

                    if (tempS.hours_per_week <= 0 || tempL.hours_per_week <= 0) {
                        counter++;
                        if (counter >= 7) {
                            continue l;
                        }
                        continue m;
                    }

                    boolean allocateFlag = false;
                    // LECTURES
                    for (int index = 0; index < allotments[LECTURE].length; index++) {
                        if (allotments[LECTURE][index] == null &&                                                       // Checks if room is empty
                                (batch.block_counter != block || batch.block_counter == 0)) {
                            if (tempL.day_counter != day ||                                                             // Checks if the component was taught previously on the same day
                                    tempL.time_counter == block - 1 ||
                                    tempL.day_counter == 0) {

                                if (tempL.time_counter != block - 1 || tempL.day_counter != day) {                      // If starting for the first time, initialises count to 1;
                                    batch.subjectLectures.get(counter).block_counter = 1;
                                } else if (tempL.time_counter == block - 1 && tempL.block_counter == 2) {               // Breaks Repetition more than twice.
                                    break;
                                } else {                                                                                // Adds repetition.
                                    batch.subjectLectures.get(counter).block_counter++;
                                }

//                                System.out.println(day + ", " + block);
//                                System.out.println(batch);
//                                System.out.println(batch.subjectLectures.get(counter).subject);
//                                System.out.println(batch.subjectLectures);
//                                System.out.println("Dayb: " + batch.subjectLectures.get(counter).day_counter);
                                groups[batch.groupNo].subjectLectures = batch.subjectLectures;
                                groups[batch.groupNo].groupLecture(counter, index);
//                                System.out.println("Dayc: " + batch.subjectLectures.get(counter).day_counter);
//                                System.out.println(counter);
//                                System.out.println(batch.subjectLectures);
//                                System.out.println("----------");
                                allotments[LECTURE][index] = "G" + (batch.groupNo + 1) + "-" + batch.subjectLectures.get(counter).subject + tempL.types[LECTURE];
                                continue l;
                            }
                        }
                    }

                    if (!allocateFlag) {
                        for (int index = 0; index < allotments[tempS.type].length; index++) {
                            if (allotments[tempS.type][index] == null &&                                                // Checks if room is empty
                                    (batch.block_counter != block || batch.block_counter == 0)) {                       // Checks if the batch has another class elsewhere in the same block.


                                // TUTORIALS
                                if (tempS.type == TUTORIAL && (tempS.day_counter != day || tempS.day_counter == 0)) {

                                    allotments[TUTORIAL][index] = batch + "-" + tempS.subject + "-" + tempS.types[tempS.type];
                                    batch.subjectComponents.get(counter).hours_per_week -= 1;
                                    batch.subjectComponents.get(counter).day_counter = Main.day;
                                    batch.TimeTable[block] = tempS.subject + "-" + tempS.types[tempS.type] + "-" + Rooms.rooms[tempS.type][index];
                                    continue l;

                                }
                                // OTHERS
                                else if (tempS.day_counter != day || tempS.day_counter == 0 || tempS.block_counter == block - 1) {
                                    allotments[tempS.type][index] = batch + "-" + tempS.subject + "-" + tempS.types[tempS.type];
                                    batch.subjectComponents.get(counter).hours_per_week -= 1;
                                    batch.subjectComponents.get(counter).day_counter = Main.day;
                                    batch.TimeTable[block] = tempS.subject + "-" + tempS.types[tempS.type] + "-" + Rooms.rooms[tempS.type][index];
                                    continue l;

                                } else {
                                    counter++;
                                    if (counter >= 14) {
                                        continue l;
                                    }
                                    continue m;
                                }
                            }
                        }
                    }
                    counter++;
                    if (counter >= 7) {
                        continue l;
                    }
                }
            }
            // All Room Allotments for the given block

            System.out.println("Day: " + day + " Block: " + block);
            System.out.println("Lecture Rooms");
            System.out.println(Arrays.toString(allotments[LECTURE]));
            System.out.println("Tutorial Rooms");
            System.out.println(Arrays.toString(allotments[TUTORIAL]));
            System.out.println("Practical CSE Rooms");
            System.out.println(Arrays.toString(allotments[PRACTICAL_CSE]));
            System.out.println("Practical PHY Rooms");
            System.out.println(Arrays.toString(allotments[PRACTICAL_PHY]));
            System.out.println('\n');

            block++;
        }
//        for (Batch batch : batches) {
//            System.out.println(batch + ":" + batch.subjectComponents);
//            System.out.println(batch + ":" + batch.subjectLectures);
//        }
//
//        for (Batch batch : batches) {
//            for (int i = 0; i < 5; i++) {
//                System.arraycopy(batch.TimeTable, i * 8, batch.TimeTable2[i], 0, 8);
//            }
//            System.out.println("Timetable for " + batch + " :\n");
//            for (String[] s : batch.TimeTable2) {
//                System.out.println(Arrays.toString(s));
//            }
//            System.out.println('\n');
//        }
    }
}
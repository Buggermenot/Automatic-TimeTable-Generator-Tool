import javax.lang.model.type.NullType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class MainTest {

    static int no_of_batches = 30;

    static int week_hours = 8,
            working_days = 5;

    static int
            LECTURE = 0,
            TUTORIAL = 1,
            PRACTICAL_CSE = 2,
            PRACTICAL_PHY = 3;

    static int block = 0,
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
                    batches.get(i).groupNo = i/6;
                    groupBatches.add(batches.get(i));
                }
            } catch (Exception e) {
                break;
            } finally {
                groups[g_no] = new Group(groupBatches);
            }
        }

        Rooms r = new Rooms();

        System.out.println(groups[0].subjectLectures);
        System.out.println(groups[0].batchGroup);

        groups[0].groupLecture(0, 0);

        System.out.println(groups[0].subjectLectures);
        System.out.println(groups[0].batchGroup.get(1).subjectLectures);
        System.out.println(batches.get(1).subjectLectures);
        System.out.println(Arrays.deepToString(batches.get(1).TimeTable));

    }
}
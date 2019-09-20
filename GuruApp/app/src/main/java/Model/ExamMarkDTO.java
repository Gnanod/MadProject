package Model;

public class ExamMarkDTO {


    private int markId;
   private String exam_ID;
   private String student_Id;
   private double student_Marks;
   private String student_Center;

    public int getMarkId() {
        return markId;
    }

    public void setMarkId(int markId) {
        this.markId = markId;
    }

    public String getExam_ID() {
        return exam_ID;
    }

    public void setExam_ID(String exam_ID) {
        this.exam_ID = exam_ID;
    }

    public String getStudent_Id() {
        return student_Id;
    }

    public void setStudent_Id(String student_Id) {
        this.student_Id = student_Id;
    }

    public double getStudent_Marks() {
        return student_Marks;
    }

    public void setStudent_Marks(double student_Marks) {
        this.student_Marks = student_Marks;
    }

    public String getStudent_Center() {
        return student_Center;
    }

    public void setStudent_Center(String student_Center) {
        this.student_Center = student_Center;
    }
}

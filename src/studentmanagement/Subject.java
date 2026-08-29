package studentmanagement;

public class Subject {

    private int subjectId;
    private String subjectName;
    private int courseId;

    // Default constructor
    public Subject() {
    }

    // Constructor
    public Subject(
            int subjectId,
            String subjectName,
            int courseId
    ) {
        this.subjectId = subjectId;
        this.subjectName = subjectName;
        this.courseId = courseId;
    }

    // Get Subject ID
    public int getSubjectId() {
        return subjectId;
    }

    // Set Subject ID
    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    // Get Subject Name
    public String getSubjectName() {
        return subjectName;
    }

    // Set Subject Name
    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    // Get Course ID
    public int getCourseId() {
        return courseId;
    }

    // Set Course ID
    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    @Override
    public String toString() {
        return subjectName;
    }
}
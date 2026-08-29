package studentmanagement;

public class Student {

    // =========================================
    // STUDENT INFORMATION
    // =========================================

    private String name;
    private int age;
    private String gender;
    private String address;
    private String phone;
    private String email;
    private int studentId;
    private String course;
    private int semester;

    // =========================================
    // MARKS
    // =========================================

    private double organization;
    private double operatingSystem;
    private double oop;
    private double networking;
    private double ethics;

    private boolean marksAdded;

    // =========================================
    // CONSTRUCTOR
    // =========================================

    public Student(
            String name,
            int age,
            String gender,
            String address,
            String phone,
            String email,
            int studentId,
            String course,
            int semester) {

        this.name = name;
        this.age = age;
        this.gender = gender;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.studentId = studentId;
        this.course = course;
        this.semester = semester;

        this.marksAdded = false;
    }

    // =========================================
    // GETTERS
    // =========================================

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public int getStudentId() {
        return studentId;
    }

    public String getCourse() {
        return course;
    }

    public int getSemester() {
        return semester;
    }

    public double getOrganization() {
        return organization;
    }

    public double getOperatingSystem() {
        return operatingSystem;
    }

    public double getOop() {
        return oop;
    }

    public double getNetworking() {
        return networking;
    }

    public double getEthics() {
        return ethics;
    }

    public boolean isMarksAdded() {
        return marksAdded;
    }

    // =========================================
    // SETTERS
    // =========================================

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    // =========================================
    // SET / UPDATE MARKS
    // =========================================

    public void setMarks(
            double organization,
            double operatingSystem,
            double oop,
            double networking,
            double ethics) {

        this.organization = organization;
        this.operatingSystem = operatingSystem;
        this.oop = oop;
        this.networking = networking;
        this.ethics = ethics;

        this.marksAdded = true;
    }

    // =========================================
    // TOTAL MARKS
    // =========================================

    public double getTotalMarks() {

        return organization
                + operatingSystem
                + oop
                + networking
                + ethics;
    }

    // =========================================
    // AVERAGE MARKS
    // =========================================

    public double getAverageMarks() {

        return getTotalMarks() / 5;
    }

    // =========================================
    // DISPLAY STUDENT
    // =========================================

    public void displayStudent() {

        System.out.println("\n------------------------------");

        System.out.println("Student ID : " + studentId);
        System.out.println("Name       : " + name);
        System.out.println("Age        : " + age);
        System.out.println("Gender     : " + gender);
        System.out.println("Address    : " + address);
        System.out.println("Phone      : " + phone);
        System.out.println("Email      : " + email);
        System.out.println("Course     : " + course);
        System.out.println("Semester   : " + semester);

        if (marksAdded) {

            System.out.println("\n---------- MARKS ------------");

            System.out.println(
                    "Organization     : " + organization
            );

            System.out.println(
                    "Operating System : " + operatingSystem
            );

            System.out.println(
                    "OOP              : " + oop
            );

            System.out.println(
                    "Networking       : " + networking
            );

            System.out.println(
                    "Ethics           : " + ethics
            );

            System.out.println(
                    "Total Marks      : " + getTotalMarks()
            );

            System.out.println(
                    "Average Marks    : " + getAverageMarks()
            );

        } else {

            System.out.println("\nMarks            : Not Added");
        }

        System.out.println("------------------------------");
    }
}
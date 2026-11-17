package b9_1;

import java.util.ArrayList;
import java.util.Scanner;

public class StudentManager {
    static Scanner scanner = new Scanner(System.in);

        // Danh sách sinh viên
        static ArrayList<Student> studentList = new ArrayList<>();

        public static void main(String[] args) {

            int choice;

            do {
                System.out.println("******** QUẢN LÝ SINH VIÊN ********");
                System.out.println("1. Hiển thị danh sách sinh viên");
                System.out.println("2. Thêm sinh viên");
                System.out.println("3. Cập nhật thông tin sinh viên theo mã");
                System.out.println("4. Xóa sinh viên theo mã");
                System.out.println("5. Tìm sinh viên theo tên");
                System.out.println("6. Thoát");
                System.out.print("Lựa chọn của bạn: ");

                choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {

                    case 1:
                        displayStudents();
                        break;

                    case 2:
                        addStudents();
                        break;

                    case 3:
                        updateStudent();
                        break;

                    case 4:
                        deleteStudent();
                        break;

                    case 5:
                        searchStudent();
                        break;

                    case 6:
                        System.out.println("Thoát chương trình!");
                        break;

                    default:
                        System.out.println("Lựa chọn không hợp lệ!");
                }

            } while (choice != 6);
        }

        // 1. Hiển thị danh sách sinh viên
        public static void displayStudents() {

            if (studentList.isEmpty()) {
                System.out.println("Danh sách sinh viên trống!");
                return;
            }

            System.out.println("===== DANH SÁCH SINH VIÊN =====");

            for (Student student : studentList) {
                student.displayData();
            }
        }

        // 2. Thêm nhiều sinh viên
        public static void addStudents() {

            System.out.print("Nhập số lượng sinh viên muốn thêm: ");
            int n = Integer.parseInt(scanner.nextLine());

            for (int i = 0; i < n; i++) {

                System.out.println("Nhập thông tin sinh viên thứ " + (i + 1));

                Student student = new Student();

                student.inputData(scanner);

                studentList.add(student);

                System.out.println("Thêm sinh viên thành công!");
            }
        }

        // 3. Cập nhật thông tin sinh viên theo mã
        public static void updateStudent() {

            System.out.print("Nhập mã sinh viên cần cập nhật: ");
            String id = scanner.nextLine();

            for (Student student : studentList) {

                if (student.getStudentId().equalsIgnoreCase(id)) {

                    System.out.println("Nhập thông tin mới:");

                    System.out.print("Nhập tên sinh viên: ");
                    student.setStudentName(scanner.nextLine());

                    System.out.print("Nhập tuổi: ");
                    student.setAge(Integer.parseInt(scanner.nextLine()));

                    System.out.print("Nhập chuyên ngành: ");
                    student.setMajor(scanner.nextLine());

                    System.out.println("Cập nhật thành công!");

                    return;
                }
            }

            System.out.println("Mã sinh viên không tồn tại");
        }

        // 4. Xóa sinh viên theo mã
        public static void deleteStudent() {

            System.out.print("Nhập mã sinh viên cần xóa: ");
            String id = scanner.nextLine();

            for (int i = 0; i < studentList.size(); i++) {

                if (studentList.get(i).getStudentId().equalsIgnoreCase(id)) {

                    studentList.remove(i);

                    System.out.println("Xóa sinh viên thành công!");

                    return;
                }
            }

            System.out.println("Mã sinh viên không tồn tại");
        }

        // 5. Tìm sinh viên theo tên
        public static void searchStudent() {

            System.out.print("Nhập tên sinh viên cần tìm: ");
            String name = scanner.nextLine();

            int count = 0;

            for (Student student : studentList) {

                if (student.getStudentName()
                        .toLowerCase()
                        .contains(name.toLowerCase())) {

                    student.displayData();

                    count++;
                }
            }

            System.out.println("Tổng số lượng sinh viên tìm thấy: " + count);
        }
    }


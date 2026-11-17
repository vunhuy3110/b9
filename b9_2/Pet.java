package b9_2;

import java.util.Scanner;

public abstract class Pet {
        private String petId;
        private String petName;
        private int age;

        public Pet() {
        }

        public Pet(String petId, String petName, int age) {
            this.petId = petId;
            this.petName = petName;
            this.age = age;
        }

        public String getPetId() {
            return petId;
        }

        public void setPetId(String petId) {
            this.petId = petId;
        }

        public String getPetName() {
            return petName;
        }

        public void setPetName(String petName) {
            this.petName = petName;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public void inputData(Scanner scanner) {

            do {
                System.out.print("Nhập mã thú cưng (C/D + 3 số): ");
                petId = scanner.nextLine();

                if (!petId.matches("^[CD][0-9]{3}$")) {
                    System.out.println("Mã không hợp lệ! Ví dụ: C001 hoặc D001");
                }

            } while (!petId.matches("^[CD][0-9]{3}$"));


            do {
                System.out.print("Nhập tên thú cưng: ");
                petName = scanner.nextLine();

                if (petName.length() < 20 || petName.length() > 50) {
                    System.out.println("Tên phải có từ 20 đến 50 ký tự!");
                }

            } while (petName.length() < 20 || petName.length() > 50);


            do {
                System.out.print("Nhập tuổi: ");
                age = Integer.parseInt(scanner.nextLine());

                if (age <= 0) {
                    System.out.println("Tuổi phải lớn hơn 0!");
                }

            } while (age <= 0);
        }


        public void displayData() {

            System.out.println("Mã thú cưng: " + petId);
            System.out.println("Tên thú cưng: " + petName);
            System.out.println("Tuổi: " + age);
            System.out.println("----------------------------");
        }


        public abstract void speak();
    }


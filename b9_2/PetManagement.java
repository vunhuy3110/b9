package b9_2;

import java.util.ArrayList;
import java.util.Scanner;

public class PetManagement {
    static Scanner scanner = new Scanner(System.in);

        static ArrayList<Pet> petList = new ArrayList<>();

        public static void main(String[] args) {

            int choice;

            do {
                System.out.println("******** QUẢN LÝ THÚ CƯNG ********");
                System.out.println("1. Hiển thị danh sách thú cưng");
                System.out.println("2. Thêm thú cưng");
                System.out.println("3. Gọi tiếng kêu");
                System.out.println("4. Xóa thú cưng");
                System.out.println("5. Tìm thú cưng theo tên");
                System.out.println("6. Thoát");
                System.out.print("Lựa chọn của bạn: ");

                choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {

                    case 1:
                        displayPets();
                        break;

                    case 2:
                        addPet();
                        break;

                    case 3:
                        callPet();
                        break;

                    case 4:
                        deletePet();
                        break;

                    case 5:
                        searchPet();
                        break;

                    case 6:
                        System.out.println("Thoát chương trình!");
                        break;

                    default:
                        System.out.println("Lựa chọn không hợp lệ!");
                }

            } while (choice != 6);
        }

        public static void displayPets() {

            if (petList.isEmpty()) {
                System.out.println("Danh sách thú cưng trống!");
                return;
            }

            System.out.println("===== DANH SÁCH THÚ CƯNG =====");

            for (Pet pet : petList) {
                pet.displayData();
            }
        }


        public static void addPet() {

            System.out.println("1. Thêm chó");
            System.out.println("2. Thêm mèo");
            System.out.print("Lựa chọn của bạn: ");

            int type = Integer.parseInt(scanner.nextLine());

            Pet pet;

            if (type == 1) {
                pet = new Dog();
            } else if (type == 2) {
                pet = new Cat();
            } else {
                System.out.println("Lựa chọn không hợp lệ!");
                return;
            }

            pet.inputData(scanner);

              while (true) {

                boolean exists = false;

                for (Pet p : petList) {
                    if (p.getPetId().equalsIgnoreCase(pet.getPetId())) {
                        exists = true;
                        break;
                    }
                }

                if (!exists) {
                    break;
                }

                System.out.println("Mã thú cưng đã tồn tại!");

                String newId;

                do {
                    System.out.print("Nhập lại mã thú cưng (C/D + 3 số): ");
                    newId = scanner.nextLine();

                    if (!newId.matches("^[CD][0-9]{3}$")) {
                        System.out.println("Mã không hợp lệ!");
                    }

                } while (!newId.matches("^[CD][0-9]{3}$"));

                pet.setPetId(newId);
            }

            petList.add(pet);

            System.out.println("Thêm thú cưng thành công!");
        }


        public static void callPet() {

            if (petList.isEmpty()) {
                System.out.println("Danh sách thú cưng trống!");
                return;
            }

            System.out.println("===== TIẾNG KÊU THÚ CƯNG =====");

            for (Pet pet : petList) {

                System.out.print(pet.getPetName() + ": ");

                pet.speak();
            }
        }


        public static void deletePet() {

            System.out.print("Nhập mã thú cưng cần xóa: ");
            String id = scanner.nextLine();

            for (int i = 0; i < petList.size(); i++) {

                if (petList.get(i).getPetId().equalsIgnoreCase(id)) {

                    petList.remove(i);

                    System.out.println("Xóa thú cưng thành công!");
                    return;
                }
            }

            System.out.println("Mã thú cưng không tồn tại!");
        }


        public static void searchPet() {

            System.out.print("Nhập tên thú cưng cần tìm: ");
            String name = scanner.nextLine();

            int count = 0;

            for (Pet pet : petList) {

                if (pet.getPetName()
                        .toLowerCase()
                        .contains(name.toLowerCase())) {

                    pet.displayData();

                    count++;
                }
            }

            System.out.println("Tổng số thú cưng tìm thấy: " + count);
        }
    }


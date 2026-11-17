package b9_3;

import java.util.Scanner;

public class Categories implements IShop {
        private int catalogId;
        private String catalogName;
        private String descriptions;
        private boolean catalogStatus;

        public Categories() {
        }

        public Categories(int catalogId, String catalogName,
                          String descriptions, boolean catalogStatus) {
            this.catalogId = catalogId;
            this.catalogName = catalogName;
            this.descriptions = descriptions;
            this.catalogStatus = catalogStatus;
        }

        public int getCatalogId() {
            return catalogId;
        }

        public void setCatalogId(int catalogId) {
            this.catalogId = catalogId;
        }

        public String getCatalogName() {
            return catalogName;
        }

        public void setCatalogName(String catalogName) {
            this.catalogName = catalogName;
        }

        public String getDescriptions() {
            return descriptions;
        }

        public void setDescriptions(String descriptions) {
            this.descriptions = descriptions;
        }

        public boolean isCatalogStatus() {
            return catalogStatus;
        }

        public void setCatalogStatus(boolean catalogStatus) {
            this.catalogStatus = catalogStatus;
        }

        public void inputData(Scanner scanner,
                              Categories[] arrCategories, int index) {

            int maxId = 0;

            for (int i = 0; i < index; i++) {
                if (arrCategories[i] != null
                        && arrCategories[i].getCatalogId() > maxId) {
                    maxId = arrCategories[i].getCatalogId();
                }
            }

            catalogId = maxId + 1;

            System.out.println("Mã danh mục: " + catalogId);

            do {
                System.out.print("Nhập tên danh mục: ");
                catalogName = scanner.nextLine().trim();

                if (catalogName.isEmpty() || catalogName.length() > 50) {
                    System.out.println("Tên không được để trống và tối đa 50 ký tự!");
                    continue;
                }

                boolean exists = false;

                for (int i = 0; i < index; i++) {
                    if (arrCategories[i] != null
                            && arrCategories[i].getCatalogName()
                            .equalsIgnoreCase(catalogName)) {
                        exists = true;
                        break;
                    }
                }

                if (exists) {
                    System.out.println("Tên danh mục đã tồn tại!");
                } else {
                    break;
                }

            } while (true);

            System.out.print("Nhập mô tả danh mục: ");
            descriptions = scanner.nextLine();

            String status;

            do {
                System.out.print("Nhập trạng thái (true/false): ");
                status = scanner.nextLine();

                if (!status.equalsIgnoreCase("true")
                        && !status.equalsIgnoreCase("false")) {
                    System.out.println("Chỉ được nhập true hoặc false!");
                }

            } while (!status.equalsIgnoreCase("true")
                    && !status.equalsIgnoreCase("false"));

            catalogStatus = Boolean.parseBoolean(status);
        }

        @Override
        public void displayData() {

            System.out.println("Mã danh mục: " + catalogId);
            System.out.println("Tên danh mục: " + catalogName);
            System.out.println("Mô tả: " + descriptions);
            System.out.println("Trạng thái: "
                    + (catalogStatus ? "Hoạt động" : "Không hoạt động"));
            System.out.println("--------------------------------");
        }
    }


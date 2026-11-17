package b9_3;

import java.util.Scanner;

public class ShopManagement {
    static Scanner scanner = new Scanner(System.in);
    static Categories[] arrCategories = new Categories[100];
    static Product[] arrProduct = new Product[100];

    static int indexCatalog = 0;
    static int indexProduct = 0;

    public static void main(String[] args) {

        int choice;

        do {
            System.out.println("************ SHOP MENU ************");
            System.out.println("1. Quản lý danh mục sản phẩm");
            System.out.println("2. Quản lý sản phẩm");
            System.out.println("3. Thoát");
            System.out.print("Lựa chọn của bạn: ");

            choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {

                case 1:
                    catalogMenu();
                    break;

                case 2:
                    productMenu();
                    break;

                case 3:
                    System.out.println("Thoát chương trình!");
                    break;

                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }

        } while (choice != 3);
    }

    public static void catalogMenu() {

        int choice;

        do {
            System.out.println("******** CATEGORIES MENU ********");
            System.out.println("1. Nhập thông tin các danh mục");
            System.out.println("2. Hiển thị thông tin các danh mục");
            System.out.println("3. Cập nhật thông tin danh mục");
            System.out.println("4. Xóa danh mục");
            System.out.println("5. Cập nhật trạng thái danh mục");
            System.out.println("6. Thoát");
            System.out.print("Lựa chọn của bạn: ");

            choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {

                case 1:
                    addCategories();
                    break;

                case 2:
                    displayCategories();
                    break;

                case 3:
                    updateCategory();
                    break;

                case 4:
                    deleteCategory();
                    break;

                case 5:
                    changeCategoryStatus();
                    break;

                case 6:
                    System.out.println("Quay lại SHOP MENU!");
                    break;

                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }

        } while (choice != 6);
    }

    public static void addCategories() {

        if (indexCatalog >= arrCategories.length) {
            System.out.println("Danh sách danh mục đã đầy!");
            return;
        }

        System.out.print("Nhập số lượng danh mục muốn thêm: ");

        int n = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < n; i++) {

            if (indexCatalog >= arrCategories.length) {
                System.out.println("Danh sách danh mục đã đầy!");
                break;
            }

            System.out.println("Nhập danh mục thứ " + (i + 1));

            Categories category = new Categories();

            category.inputData(scanner, arrCategories, indexCatalog);

            arrCategories[indexCatalog] = category;

            indexCatalog++;

            System.out.println("Thêm danh mục thành công!");
        }
    }

    public static void displayCategories() {

        if (indexCatalog == 0) {
            System.out.println("Danh sách danh mục trống!");
            return;
        }

        for (int i = 0; i < indexCatalog; i++) {

            if (arrCategories[i] != null) {
                arrCategories[i].displayData();
            }
        }
    }

    public static void updateCategory() {

        System.out.print("Nhập mã danh mục cần cập nhật: ");
        int id = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < indexCatalog; i++) {

            if (arrCategories[i].getCatalogId() == id) {

                System.out.print("Nhập tên danh mục mới: ");
                String name = scanner.nextLine().trim();

                boolean exists;

                do {
                    exists = false;

                    if (name.isEmpty() || name.length() > 50) {
                        System.out.println("Tên không hợp lệ!");
                        System.out.print("Nhập lại tên danh mục: ");
                        name = scanner.nextLine().trim();
                        exists = true;
                        continue;
                    }

                    for (int j = 0; j < indexCatalog; j++) {
                        if (j != i
                                && arrCategories[j].getCatalogName()
                                .equalsIgnoreCase(name)) {
                            exists = true;
                            System.out.println("Tên danh mục đã tồn tại!");
                            System.out.print("Nhập lại tên danh mục: ");
                            name = scanner.nextLine().trim();
                            break;
                        }
                    }

                } while (exists);

                arrCategories[i].setCatalogName(name);

                System.out.print("Nhập mô tả mới: ");
                arrCategories[i].setDescriptions(scanner.nextLine());

                System.out.println("Cập nhật danh mục thành công!");
                return;
            }
        }

        System.out.println("Mã danh mục không tồn tại!");
    }


    public static void deleteCategory() {

        System.out.print("Nhập mã danh mục cần xóa: ");
        int id = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < indexCatalog; i++) {

            if (arrCategories[i].getCatalogId() == id) {


                boolean hasProduct = false;

                for (int j = 0; j < indexProduct; j++) {

                    if (arrProduct[j] != null
                            && arrProduct[j].getCatalogId() == id) {
                        hasProduct = true;
                        break;
                    }
                }

                if (hasProduct) {
                    System.out.println("Không thể xóa danh mục vì đang có sản phẩm!");
                    return;
                }

                // Xóa và dồn mảng
                for (int j = i; j < indexCatalog - 1; j++) {
                    arrCategories[j] = arrCategories[j + 1];
                }

                arrCategories[indexCatalog - 1] = null;
                indexCatalog--;

                System.out.println("Xóa danh mục thành công!");
                return;
            }
        }

        System.out.println("Mã danh mục không tồn tại!");
    }


    public static void changeCategoryStatus() {

        System.out.print("Nhập mã danh mục cần đổi trạng thái: ");
        int id = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < indexCatalog; i++) {

            if (arrCategories[i].getCatalogId() == id) {

                boolean status = arrCategories[i].isCatalogStatus();

                arrCategories[i].setCatalogStatus(!status);

                System.out.println("Cập nhật trạng thái thành công!");
                System.out.println("Trạng thái mới: "
                        + (arrCategories[i].isCatalogStatus()
                        ? "Hoạt động" : "Không hoạt động"));

                return;
            }
        }

        System.out.println("Mã danh mục không tồn tại!");
    }


    public static void productMenu() {

        int choice;

        do {
            System.out.println("******** PRODUCT MANAGEMENT ********");
            System.out.println("1. Nhập thông tin các sản phẩm");
            System.out.println("2. Hiển thị thông tin các sản phẩm");
            System.out.println("3. Sắp xếp sản phẩm theo giá");
            System.out.println("4. Cập nhật thông tin sản phẩm");
            System.out.println("5. Xóa sản phẩm");
            System.out.println("6. Tìm kiếm sản phẩm theo tên");
            System.out.println("7. Tìm sản phẩm theo khoảng giá");
            System.out.println("8. Thoát");
            System.out.print("Lựa chọn của bạn: ");

            choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {

                case 1:
                    addProducts();
                    break;

                case 2:
                    displayProducts();
                    break;

                case 3:
                    sortProductsByPrice();
                    break;

                case 4:
                    updateProduct();
                    break;

                case 5:
                    deleteProduct();
                    break;

                case 6:
                    searchProductByName();
                    break;

                case 7:
                    searchProductByPrice();
                    break;

                case 8:
                    System.out.println("Quay lại SHOP MENU!");
                    break;

                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }

        } while (choice != 8);
    }

    public static void addProducts() {

        if (indexCatalog == 0) {
            System.out.println("Phải tạo danh mục trước khi thêm sản phẩm!");
            return;
        }

        if (indexProduct >= arrProduct.length) {
            System.out.println("Danh sách sản phẩm đã đầy!");
            return;
        }

        System.out.print("Nhập số lượng sản phẩm muốn thêm: ");
        int n = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < n; i++) {

            if (indexProduct >= arrProduct.length) {
                System.out.println("Danh sách sản phẩm đã đầy!");
                break;
            }

            System.out.println("Nhập sản phẩm thứ " + (i + 1));

            Product product = new Product();

            product.inputData(scanner, arrProduct, indexProduct,
                    arrCategories, indexCatalog);

            arrProduct[indexProduct] = product;

            indexProduct++;

            System.out.println("Thêm sản phẩm thành công!");
        }
    }

    public static void displayProducts() {

        if (indexProduct == 0) {
            System.out.println("Danh sách sản phẩm trống!");
            return;
        }

        for (int i = 0; i < indexProduct; i++) {

            if (arrProduct[i] != null) {
                arrProduct[i].displayData();
            }
        }
    }


    public static void sortProductsByPrice() {

        for (int i = 0; i < indexProduct - 1; i++) {

            for (int j = i + 1; j < indexProduct; j++) {

                if (arrProduct[i].getPrice()
                        > arrProduct[j].getPrice()) {

                    Product temp = arrProduct[i];

                    arrProduct[i] = arrProduct[j];

                    arrProduct[j] = temp;
                }
            }
        }

        System.out.println("Đã sắp xếp sản phẩm theo giá tăng dần!");

        displayProducts();
    }

    public static void updateProduct() {

        System.out.print("Nhập mã sản phẩm cần cập nhật: ");
        String id = scanner.nextLine();

        for (int i = 0; i < indexProduct; i++) {

            if (arrProduct[i].getProductId()
                    .equalsIgnoreCase(id)) {

                Product product = arrProduct[i];

                // Cập nhật tên
                String name;

                do {
                    System.out.print("Nhập tên sản phẩm mới: ");
                    name = scanner.nextLine().trim();

                    if (name.length() < 10 || name.length() > 50) {
                        System.out.println("Tên phải từ 10 đến 50 ký tự!");
                        continue;
                    }

                    boolean exists = false;

                    for (int j = 0; j < indexProduct; j++) {
                        if (j != i && arrProduct[j].getProductName()
                                .equalsIgnoreCase(name)) {
                            exists = true;
                            break;
                        }
                    }

                    if (exists) {
                        System.out.println("Tên sản phẩm đã tồn tại!");
                    } else {
                        break;
                    }

                } while (true);

                product.setProductName(name);

                // Cập nhật giá
                float price;

                do {
                    System.out.print("Nhập giá mới: ");

                    try {
                        price = Float.parseFloat(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        price = 0;
                    }

                    if (price <= 0) {
                        System.out.println("Giá phải lớn hơn 0!");
                    }

                } while (price <= 0);

                product.setPrice(price);


                System.out.print("Nhập mô tả mới: ");
                product.setDescription(scanner.nextLine());


                System.out.println("===== DANH SÁCH DANH MỤC =====");

                for (int j = 0; j < indexCatalog; j++) {
                    if (arrCategories[j].isCatalogStatus()) {
                        System.out.println(
                                arrCategories[j].getCatalogId() + ". "
                                        + arrCategories[j].getCatalogName());
                    }
                }

                boolean valid;

                do {
                    System.out.print("Nhập mã danh mục mới: ");

                    int catalogId;

                    try {
                        catalogId = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        catalogId = -1;
                    }

                    valid = false;

                    for (int j = 0; j < indexCatalog; j++) {
                        if (arrCategories[j].getCatalogId() == catalogId
                                && arrCategories[j].isCatalogStatus()) {
                            product.setCatalogId(catalogId);
                            valid = true;
                            break;
                        }
                    }

                    if (!valid) {
                        System.out.println("Danh mục không hợp lệ!");
                    }

                } while (!valid);


                int status;

                do {
                    System.out.println("0. Đang bán");
                    System.out.println("1. Hết hàng");
                    System.out.println("2. Không bán");
                    System.out.print("Nhập trạng thái mới: ");

                    try {
                        status = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        status = -1;
                    }

                    if (status < 0 || status > 2) {
                        System.out.println("Trạng thái không hợp lệ!");
                    }

                } while (status < 0 || status > 2);

                product.setProductStatus(status);

                System.out.println("Cập nhật sản phẩm thành công!");
                return;
            }
        }

        System.out.println("Mã sản phẩm không tồn tại!");
    }


    public static void deleteProduct() {

        System.out.print("Nhập mã sản phẩm cần xóa: ");
        String id = scanner.nextLine();

        for (int i = 0; i < indexProduct; i++) {

            if (arrProduct[i].getProductId()
                    .equalsIgnoreCase(id)) {

                for (int j = i; j < indexProduct - 1; j++) {
                    arrProduct[j] = arrProduct[j + 1];
                }

                arrProduct[indexProduct - 1] = null;

                indexProduct--;

                System.out.println("Xóa sản phẩm thành công!");
                return;
            }
        }

        System.out.println("Mã sản phẩm không tồn tại!");
    }


    public static void searchProductByName() {

        System.out.print("Nhập tên sản phẩm cần tìm: ");
        String name = scanner.nextLine().toLowerCase();

        int count = 0;

        for (int i = 0; i < indexProduct; i++) {

            if (arrProduct[i].getProductName()
                    .toLowerCase().contains(name)) {

                arrProduct[i].displayData();

                count++;
            }
        }

        System.out.println("Số sản phẩm tìm thấy: " + count);
    }


    public static void searchProductByPrice() {

        System.out.print("Nhập giá thấp nhất (a): ");
        float a = Float.parseFloat(scanner.nextLine());

        System.out.print("Nhập giá cao nhất (b): ");
        float b = Float.parseFloat(scanner.nextLine());

        if (a > b) {
            System.out.println("Khoảng giá không hợp lệ!");
            return;
        }

        int count = 0;

        for (int i = 0; i < indexProduct; i++) {

            if (arrProduct[i].getPrice() >= a
                    && arrProduct[i].getPrice() <= b) {

                arrProduct[i].displayData();

                count++;
            }
        }

        System.out.println("Số sản phẩm tìm thấy: " + count);
    }
}


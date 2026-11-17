package b9_3;

import java.util.Scanner;
public class Product implements IShop {
    private String productId;
    private String productName;
    private float price;
    private String description;
    private int catalogId;
    private int productStatus;

    public Product() {
    }

    public Product(String productId, String productName,
                   float price, String description,
                   int catalogId, int productStatus) {

        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.description = description;
        this.catalogId = catalogId;
        this.productStatus = productStatus;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(int catalogId) {
        this.catalogId = catalogId;
    }

    public int getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(int productStatus) {
        this.productStatus = productStatus;
    }

    public void inputData(Scanner scanner,
                          Product[] arrProduct,
                          int indexProduct,
                          Categories[] arrCategories,
                          int indexCatalog) {


        do {
            System.out.print("Nhập mã sản phẩm (C/S/A + 3 số): ");
            productId = scanner.nextLine().trim().toUpperCase();

            if (!productId.matches("^[CSA][0-9]{3}$")) {
                System.out.println("Mã sản phẩm không hợp lệ!");
                continue;
            }

            boolean exists = false;

            for (int i = 0; i < indexProduct; i++) {
                if (arrProduct[i] != null
                        && arrProduct[i].getProductId()
                        .equalsIgnoreCase(productId)) {
                    exists = true;
                    break;
                }
            }

            if (exists) {
                System.out.println("Mã sản phẩm đã tồn tại!");
            } else {
                break;
            }

        } while (true);


        do {
            System.out.print("Nhập tên sản phẩm: ");
            productName = scanner.nextLine().trim();

            if (productName.length() < 10 || productName.length() > 50) {
                System.out.println("Tên sản phẩm phải từ 10 đến 50 ký tự!");
                continue;
            }

            boolean exists = false;

            for (int i = 0; i < indexProduct; i++) {
                if (arrProduct[i] != null
                        && arrProduct[i].getProductName()
                        .equalsIgnoreCase(productName)) {
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

        do {
            System.out.print("Nhập giá sản phẩm: ");

            try {
                price = Float.parseFloat(scanner.nextLine());

                if (price <= 0) {
                    System.out.println("Giá phải lớn hơn 0!");
                }

            } catch (NumberFormatException e) {
                price = 0;
                System.out.println("Giá phải là số!");
            }

        } while (price <= 0);

        System.out.print("Nhập mô tả sản phẩm: ");
        description = scanner.nextLine();

        if (indexCatalog == 0) {
            System.out.println("Chưa có danh mục! Không thể thêm sản phẩm.");
            return;
        }

        System.out.println("===== DANH SÁCH DANH MỤC =====");

        for (int i = 0; i < indexCatalog; i++) {
            if (arrCategories[i] != null) {
                System.out.println(
                        arrCategories[i].getCatalogId() + ". "
                                + arrCategories[i].getCatalogName()
                                + (arrCategories[i].isCatalogStatus()
                                ? " (Hoạt động)" : " (Không hoạt động)")
                );
            }
        }

        boolean validCatalog;

        do {
            System.out.print("Nhập mã danh mục: ");

            try {
                catalogId = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                catalogId = -1;
            }

            validCatalog = false;

            for (int i = 0; i < indexCatalog; i++) {
                if (arrCategories[i] != null
                        && arrCategories[i].getCatalogId() == catalogId
                        && arrCategories[i].isCatalogStatus()) {
                    validCatalog = true;
                    break;
                }
            }

            if (!validCatalog) {
                System.out.println("Mã danh mục không tồn tại hoặc không hoạt động!");
            }

        } while (!validCatalog);

        do {
            System.out.println("0. Đang bán");
            System.out.println("1. Hết hàng");
            System.out.println("2. Không bán");
            System.out.print("Nhập trạng thái sản phẩm: ");

            try {
                productStatus = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                productStatus = -1;
            }

            if (productStatus < 0 || productStatus > 2) {
                System.out.println("Trạng thái chỉ nhận 0, 1 hoặc 2!");
            }

        } while (productStatus < 0 || productStatus > 2);
    }

    @Override
    public void displayData() {

        System.out.println("Mã sản phẩm: " + productId);
        System.out.println("Tên sản phẩm: " + productName);
        System.out.println("Giá: " + price);
        System.out.println("Mô tả: " + description);
        System.out.println("Mã danh mục: " + catalogId);

        String status;

        switch (productStatus) {
            case 0:
                status = "Đang bán";
                break;
            case 1:
                status = "Hết hàng";
                break;
            default:
                status = "Không bán";
        }

        System.out.println("Trạng thái: " + status);
        System.out.println("--------------------------------");
    }
}


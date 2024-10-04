import java.io.File;
import java.security.MessageDigest;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class InteractiveMode {

    private final Scanner scanner = new Scanner(System.in);

    public void start() {
        try {
            System.out.println("Добро пожаловать в интерактивный режим. Введите 'exit' для выхода.");

            while (true) {
                System.out.print("Введите путь к файлу (или 'exit' для выхода): ");
                String filePath = scanner.nextLine();

                if (filePath.equalsIgnoreCase("exit")) {
                    break;
                }

                File file = new File(filePath);
                if (!file.exists()) {
                    System.out.println("Файл не найден. Попробуйте снова.");
                    continue;
                }

                System.out.print("Выберите алгоритм (md5/sha256): ");
                String algorithm = scanner.nextLine();

                try {
                    final MessageDigest digest;
                    if (algorithm.equalsIgnoreCase("md5")) {
                        digest = MessageDigest.getInstance("MD5");
                    } else if (algorithm.equalsIgnoreCase("sha256")) {
                        digest = MessageDigest.getInstance("SHA-256");
                    } else {
                        System.out.println("Неверный алгоритм. Попробуйте снова.");
                        continue;
                    }

                    String hash = Main.getFileChecksum(digest, file);
                    System.out.println("Хеш файла: " + hash);
                } catch (Exception e) {
                    System.out.println("Ошибка: " + e.getMessage());
                }
            }

        } catch (NoSuchElementException e) {
            System.out.println("\nВвод завершён с помощью Ctrl+D. Программа завершает работу.");
        } finally {
            scanner.close();
        }
    }
}
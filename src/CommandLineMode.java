import java.io.File;
import java.security.MessageDigest;

public class CommandLineMode {
    public void start(String[] args) {
        try {
            String algorithm = null;
            File[] files = null;

            for (int i = 0; i < args.length; i++) {
                switch (args[i]) {
                    case "-md5":
                        algorithm = "MD5";
                        break;
                    case "-sha256":
                        algorithm = "SHA-256";
                        break;
                    case "-f":
                        int fileCount = args.length - i - 1;
                        files = new File[fileCount];
                        for (int j = 0; j < fileCount; j++) {
                            files[j] = new File(args[i + 1 + j]);
                        }
                        i += fileCount;
                        break;
                    default:
                        System.out.println("Неверный флаг");
                }

            }

            if (algorithm == null || files == null) {
                System.out.println("Неверные аргументы. Пример: java -jar myHashFunc.jar -md5 -f file1 file2");
                return;
            }

            MessageDigest digest = MessageDigest.getInstance(algorithm);
            for (File file : files) {
                if (!file.exists()) {
                    System.out.println("Файл не найден: " + file.getAbsolutePath());
                    continue;
                }

                String hash = Main.getFileChecksum(digest, file);
                System.out.println("Хеш для файла " + file.getAbsolutePath() + ": " + hash);
            }
        } catch (Exception e) {
            System.out.println("Ошибка " + e.getMessage());
        }
    }
}

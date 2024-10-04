import java.io.File;
import java.io.FileInputStream;
import java.security.MessageDigest;

public class Main {
    public static void main(String[] args) {
        if (args.length > 0 && args[0].equals("-i")) {
            new InteractiveMode().start();
        } else {
            new CommandLineMode().start(args);
        }
    }

    public static String getFileChecksum(MessageDigest digest, File file) throws Exception {
        FileInputStream fis = new FileInputStream(file);
        byte[] byteArray = new byte[1024];
        int bytesCount;

        while ((bytesCount = fis.read(byteArray)) != -1) {
            digest.update(byteArray, 0, bytesCount);
        }

        fis.close();

        byte[] bytes = digest.digest();

        StringBuilder sb = new StringBuilder();
        for (byte aByte : bytes) {
            sb.append(String.format("%02x", aByte));
        }

        return sb.toString();
    }

}



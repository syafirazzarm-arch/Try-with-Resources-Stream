
package handson8;

import java.io.BufferedWriter;
import java.io.IOException;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        // Menentukan lokasi file
        Path path = Paths.get("handson8", "user_input.txt");

        // Format timestamp
        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        System.out.println("=== WRITE USER INPUT ===");
        System.out.println("Ketik teks yang ingin disimpan.");
        System.out.println("Ketik 'exit' untuk mengakhiri program.");
        System.out.println();

        // Try-with-resources
        try (
            Scanner sc = new Scanner(System.in);

            BufferedWriter bw = Files.newBufferedWriter(
                    path,
                    StandardCharsets.UTF_8,
                    StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND
            )
        ) {

            while (true) {

                System.out.print("Enter text: ");

                String text = sc.nextLine();

                // Menghentikan input jika pengguna mengetik exit
                if (text.equalsIgnoreCase("exit")) {
                    break;
                }

                // Membuat timestamp
                String timestamp =
                        LocalDateTime.now().format(formatter);

                // Menulis timestamp dan teks ke file
                bw.write("[" + timestamp + "] " + text);
                bw.newLine();

                // Memastikan data langsung ditulis ke file
                bw.flush();

                System.out.println("Data saved!");
            }

            System.out.println("\nProgram selesai.");
            System.out.println("Data berhasil disimpan ke: " + path);

        } catch (IOException e) {

            System.out.println("Terjadi kesalahan: " + e.getMessage());
        }
    }
}

package var2_302_2;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class main {
    public static String path = "C:\\Users\\HP\\Desktop\\Exam_Inf\\src\\var2_302_2\\test";

    public static void main(String[] args) {
        try {
            // Читаем исходный файл и разбиваем на числа
            List<Integer> numbers = readNumbersFromFile(path);

            // Создаем файлы для хранения чисел по условию
            for (int k = 1; k <= 9; k++) {
                String fileName = "numbers_" + k + ".bin";
                writeNumbersToFile(numbers, fileName, k);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<Integer> readNumbersFromFile(String filename) throws IOException {
        List<Integer> numbers = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            int c;
            StringBuilder sb = new StringBuilder();
            while ((c = br.read()) != -1) {
                if (Character.isDigit(c)) {
                    sb.append((char) c);
                } else if (sb.length() > 0) {
                    numbers.add(Integer.parseInt(sb.toString()));
                    sb.setLength(0);
                }
            }
            if (sb.length() > 0) {
                numbers.add(Integer.parseInt(sb.toString()));
            }
        }
        return numbers;
    }

    private static void writeNumbersToFile(List<Integer> numbers, String filename, int k) throws IOException {
        try {
            DataOutputStream fos = new DataOutputStream(new FileOutputStream(filename));
            for (int number : numbers) {
                if (startsAndEndsWith(number, k)) {
                    fos.writeInt(number);
                }
            }
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean startsAndEndsWith(int number, int k) {
        int firstDigit = getFirstDigit(number);
        int lastDigit = getLastDigit(number);
        return firstDigit == k && lastDigit == k;
    }

    private static int getFirstDigit(int number) {
        while (number >= 10) {
            number /= 10;
        }
        return number;
    }

    private static int getLastDigit(int number) {
        return number % 10;
    }
}
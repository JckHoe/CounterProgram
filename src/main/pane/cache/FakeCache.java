package main.pane.cache;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;
import java.util.Scanner;

public class FakeCache extends BaseCache {
    private String filePath = "src/main/cache/.data.txt";

    public FakeCache() {
    }

    public Optional<Integer> getCount() {
        Scanner input = null;
        try {
            input = new Scanner(readFakeCache().get());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int amount = -1;
        while (input.hasNext()) {
            amount = input.nextInt();
        }
        input.close();

        if (amount != -1) {
            return Optional.of(amount);
        } else {
            return null;
        }
    }

    public Optional<Integer> addCount(int amount) {
        amount = amount + 1;
        try (
                PrintWriter output = new PrintWriter(readFakeCache().get());
        ) {
            output.println(amount);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return Optional.of(amount);
    }

    public Optional<Integer> minusCount(int amount) {
        amount = amount - 1;
        try (
                PrintWriter output = new PrintWriter(readFakeCache().get());
        ) {
            if (amount < 0) {
                output.println(0);
                amount = 0;
            } else {
                output.println(amount);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return Optional.of(amount);
    }

    public Integer resetCount() {
        try (
                PrintWriter output = new PrintWriter(readFakeCache().get());
        ) {
            output.println("0");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public Optional<File> readFakeCache() {
        File fakeCache = new File(filePath);
        try {
            if (fakeCache.createNewFile()) {
                //Write 0
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return Optional.of(fakeCache);
    }

    public String getFilePath() {
        return filePath;
    }
}

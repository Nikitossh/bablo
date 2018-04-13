package forThinkingInJava.Chapter9;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.nio.CharBuffer;
import java.util.Random;
import java.util.Scanner;

public class RandomDoubles {
    private static Random random = new Random();

    public char next() {
        return (char) random.nextInt();
    }
}


class AdaptedRandomDoubles extends RandomDoubles implements Readable{
    private int counter;

    public AdaptedRandomDoubles(int counter) {
        this.counter = counter;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(new AdaptedRandomDoubles(10));
        while (scanner.hasNext()) {
            System.out.println(scanner.next() + " ");
        }
    }

    @Override
    public int read(@NotNull CharBuffer charBuffer) throws IOException {
        if (counter-- == 0) {
            return -1;
        }
        //String result = Character.toString(next()) + " ";
        charBuffer.append(next());
        return 10;
    }
}
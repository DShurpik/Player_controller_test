package helpers;

import java.util.Random;

public class IntRandom {
    public int generateRandomNumber() {
        Random random = new Random();
        return random.nextInt(Integer.MAX_VALUE);
    }
}

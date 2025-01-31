package alpine.cryxie;

import java.io.IOException;

import alpine.cryxie.utils.DateFormatter;

public class Main{
    public static void main(String[] args) throws IOException, InterruptedException {
        var parsedBr = DateFormatter.parseFromBrazilian("28/01/2025 15:30:45");
        System.out.println("Data Convertida do BR: " + parsedBr);
    }
}
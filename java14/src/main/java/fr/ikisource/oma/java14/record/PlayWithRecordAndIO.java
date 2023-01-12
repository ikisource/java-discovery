package fr.ikisource.oma.java14.record;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;

/*
⚠️ La sérialisation d'une classe n'utilise pas le constructeur de la classe et donc n'applique pas la règle de validation
👌 La sérialisation d'un record utilise systématiquement le constructeur canonique ce qui permet d'appliquer la règle de validation
 */
public class PlayWithRecordAndIO {

    static record RangeRecord(int begin, int end) implements Serializable {

        RangeRecord {
            if (end < begin) {
                throw new IllegalStateException("end must be greater than begin");
            }
        }
    }

    static class RangeClass implements Serializable {

        private final int begin;
        private final int end;

        public RangeClass(int begin, int end) {
            if (end < begin) {
                throw new IllegalStateException("begin must be smaller than end");
            }
            this.begin = begin;
            this.end = end;
            System.out.println("Building Range");
        }

        @Override
        public String toString() {
            return "RangeClass{" +
                    "begin=" + begin +
                    ", end=" + end +
                    '}';
        }
    }
    public static void main(String[] args) throws IOException, ClassNotFoundException {

        var range = new RangeRecord(10, 0);
        System.out.println("range = " + range);

        var fos = Files.newOutputStream(Path.of("files/range-record-10-0.dat"));
        var oos = new ObjectOutputStream(fos);
        oos.writeObject(range);

        var is = Files.newInputStream(Path.of("files/range-record-10-0.dat"));
        var ois = new ObjectInputStream(is);
        System.out.println("Reading range");
        var readRange = ois.readObject();
        System.out.println("readRange = " + readRange);
    }
}

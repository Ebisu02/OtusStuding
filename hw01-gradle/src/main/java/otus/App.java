package otus;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {
        List<Integer> example = new ArrayList<>();
        int min = 0, max = 100;
        for (int i = 0; i < max; ++i) {
            example.add(i);
        }
        System.out.println(Lists.reverse(example));
    }
}
